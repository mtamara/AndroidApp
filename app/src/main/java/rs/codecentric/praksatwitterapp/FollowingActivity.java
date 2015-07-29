package rs.codecentric.praksatwitterapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Twit;
import model.User;
import rest.ApiUtil;
import rest.TwitService;
import rest.UserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FollowingActivity extends ActionBarActivity implements OnItemClickListener {

        ApiUtil api = new ApiUtil();
        ListView lview;
        FollowingCustomAdapter adapter;
        private ArrayList<Object> followingList;
        private FollowingBean bean;
        RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(api.getAPI_URL()).build();

        private String followerId;
        String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        userId = getIntent().getStringExtra("userId");

        prepareFollowingList();
        lview = (ListView) findViewById(R.id.following_list);
        adapter = new FollowingCustomAdapter(this, followingList);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_following) {
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        FollowingBean bean = (FollowingBean) adapter.getItem(position);
        followerId = bean.getFollowingId() + bean.getFollowingFirstName();
        Log.d("following name", "name" + bean.getFollowingFirstName());
        Toast.makeText(this, "Title => "+bean.getFollowingFirstName()+" n Description => "+bean.getFollowingLastName(), Toast.LENGTH_SHORT).show();
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareFollowingList()
    {
        followingList = new ArrayList<Object>();
        //REST added
        UserService userService = restAdapter.create(UserService.class);
        if (userId == null) {
            userId = "55aea961f4aa1cb3ec9ed3d4";
        }
        userService.getFollowing(userId, new Callback<List<User>>() {

            @Override
            public void success(List<User> following, Response response) {
                Log.d("Following", "Following: " + following.size());
                for (User user : following) {
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

    public void removeUser(View view){

        followingList = new ArrayList<Object>();
        //REST added
        Log.d("usaoUremove", "usao");
        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.remove_follower);
        followerId = imgbtn.getContentDescription().toString();

        UserService userService = restAdapter.create(UserService.class);
        userService.addRemoveFollowingUser("55aeb60633bb921850b8fd55", "removeFollower", followerId, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Log.d("size", "size" + users.size());
                Log.d("Followerid", "id" + followerId);
                for (User user : users) {
                    AddObjectToList(R.drawable.ic_bird, user.getFirstName(), user.getLastName(), user.getId());

                    Intent intent = new Intent(FollowingActivity.this,
                            FollowingActivity.class);

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
