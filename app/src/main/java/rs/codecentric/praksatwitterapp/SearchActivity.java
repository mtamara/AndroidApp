package rs.codecentric.praksatwitterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.User;
import rest.ApiUtil;
import rest.UserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 29.7.2015.
 */
public class SearchActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ApiUtil api = new ApiUtil();
    ListView lview;
    SearchCustomAdapter adapter;
    private ArrayList<Object> followingList;
    private FollowingBean bean;
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(api.getAPI_URL()).build();

    private String followerId;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        userId = getIntent().getStringExtra("userId");

        prepareSearchList();
        lview = (ListView) findViewById(R.id.following_list);
        adapter = new SearchCustomAdapter(this, followingList);
        lview.setAdapter(adapter);
        lview.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_homepage, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void settings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void userProfile() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void homepage() {
        Intent intent = new Intent(this, HomepageActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void viewFollowing() {
        Intent intent = new Intent(this, FollowingActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_follow) {
            return true;
        }
        else if (id == R.id.action_settings) {
            settings();
        }
        else if (id == R.id.action_profile) {
            userProfile();
        }
        else if (id == R.id.action_logout) {
            logout();
        }
        else if (id == R.id.home) {
            homepage();
        }
        else if (id == R.id.action_following) {
            viewFollowing();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        FollowingBean bean = (FollowingBean) adapter.getItem(position);
        followerId = bean.getFollowingId() + bean.getFollowingFirstName();
        Log.d("following name", "name" + bean.getFollowingFirstName());
        Toast.makeText(this, "Title => " + bean.getFollowingFirstName() + " n Description => " + bean.getFollowingLastName(), Toast.LENGTH_SHORT).show();
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareSearchList()
    {
        followingList = new ArrayList<Object>();
        //REST added
        UserService userService = restAdapter.create(UserService.class);
        if (userId == null) {
            userId = "55aea961f4aa1cb3ec9ed3d4";
        }

        userService.getUsers(userId, new Callback<List<User>>() {

            @Override
            public void success(List<User> searchList, Response response) {
                Log.d("Searched", "Searched list: " + searchList.size());
                for (User user : searchList) {
                    AddObjectToList(R.drawable.ic_bird, user.getFirstName(), user.getLastName(), user.getId());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
            }
        });
        //end of rest
    }

    // Add one item into the Array List
    public void AddObjectToList(int image, String firstName, String lastName, String id)
    {
        bean = new FollowingBean();
        bean.setFollowingFirstName(firstName);
        bean.setFollowingLastName(lastName);
        bean.setFollowingImage(image);
        bean.setFollowingId(id);
        followingList.add(bean);
    }

    public void addUser(View view){

        followingList = new ArrayList<Object>();
        //REST added
        Log.d("usaoUremove", "usao");
        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.remove_follower);
        followerId = imgbtn.getContentDescription().toString();

        UserService userService = restAdapter.create(UserService.class);
        userService.addRemoveFollowingUser(userId, "addFollower", followerId, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Log.d("size", "size" + users.size());
                Log.d("Followerid", "id" + followerId);
                for (User user : users) {
                    AddObjectToList(R.drawable.ic_bird, user.getFirstName(), user.getLastName(), user.getId());

                    Intent intent = new Intent(SearchActivity.this,
                            SearchActivity.class);

                    startActivity(intent);

                    //lview.invalidate();
                    //lview.refreshDrawableState();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Poruka greske: ", "greska" + error.getMessage());
            }
        });

    }
}
