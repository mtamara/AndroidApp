package rs.codecentric.praksatwitterapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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


public class UserProfileActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

   // String API = "http://10.10.10.134:8083/praksaREST";
    ApiUtil api = new ApiUtil();
    ListView lview;
    TweetCustomAdapter adapter;
    private ArrayList<Object> tweetList;
    private TweetBean bean;
    private User sessionUser;
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(api.getAPI_URL()).build();
    String userId;
    int tweetsNo = 0;
    TextView txtTweetsNo;
    private String tweetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

       // lview.setOnItemClickListener(this);
        txtTweetsNo = (TextView) findViewById(R.id.tweets_no);
        userId = getIntent().getStringExtra("userId");
        //REST added
        final TextView txtUser = (TextView) findViewById(R.id.user);
        UserService git = restAdapter.create(UserService.class);
        if (userId == null) {
            userId = "55aea961f4aa1cb3ec9ed3d4";
        }
        git.getUser(userId, new Callback<User>() {

            @Override
            public void success(User user, Response response) {
                txtUser.setText(user.getFirstName() + " " + user.getLastName());
                txtTweetsNo.setText(tweetsNo + " Tweets");
                sessionUser = user;
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
            }
        });
        //end of rest

        prepareTweetsList(sessionUser);
        lview = (ListView) findViewById(R.id.tweets_list);
        adapter = new TweetCustomAdapter(this, tweetList);
        lview.setAdapter(adapter);
    }

    public void postTweet(View view) {
        //REST added
        TwitService twitService = restAdapter.create(TwitService.class);
        final EditText tweetContent = (EditText) findViewById(R.id.txt_tweet_content);
        Twit twit = new Twit();
        twit.setContent(tweetContent.getText().toString());
        Log.d("Tweet", "Content: " + tweetContent.getText().toString());
        if (userId == null) {
            userId = "55aea961f4aa1cb3ec9ed3d4";
        }
        twitService.postTweet(userId, twit, new Callback<Twit>() {

            @Override
            public void success(Twit tweet, Response response) {
                TweetBean newTweet = new TweetBean();
                newTweet.setTweetContent(tweet.getContent());
                newTweet.setUserData(tweet.getOwnerName());
                newTweet.setTweetDate("20.06.2015");

                tweetList.add(0, newTweet);
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(UserProfileActivity.this, UserProfileActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
            }
        });
        //end of rest
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

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void viewSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
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
        if (id == R.id.action_profile) {
            return true;
        }
        else if (id == R.id.action_settings) {
            settings();
        }
        else if (id == R.id.home) {
            homepage();
        }
        else if (id == R.id.action_following) {
            viewFollowing();
        }
        else if (id == R.id.action_follow) {
            viewSearch();
        }
        else if (id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        TweetBean bean = (TweetBean) adapter.getItem(position);
        tweetId = bean.getTweetId();
        Log.d("Tweet ID", "ID " + bean.getTweetId());
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareTweetsList(User user)
    {
        tweetList = new ArrayList<Object>();

        //REST added
        TwitService twitService = restAdapter.create(TwitService.class);
        if (userId == null) {
            userId = "55aea961f4aa1cb3ec9ed3d4";
        }
        twitService.getUserTweets(userId, "profile", new Callback<List<Twit>>() {

            @Override
            public void success(List<Twit> twits, Response response) {
                Log.d("velicina liste", "broj:" + tweetList.size());
                tweetsNo = twits.size();
                txtTweetsNo.setText(tweetsNo + " Tweets");
                for (Twit twit : twits) {
                    AddObjectToList(R.drawable.ic_bird, twit.getOwnerName(), twit.getContent(), "11.11.2015", twit.getId());
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
    public void AddObjectToList(int image, String userData, String tweetContent, String tweetDate, String tweetId)
    {
        bean = new TweetBean();
        bean.setUserData(userData);
        bean.setUserImage(image);
        bean.setTweetContent(tweetContent);
        bean.setTweetDate(tweetDate);
        bean.setTweetId(tweetId);
        tweetList.add(bean);
    }

    public void favorite(View view) {
        ImageButton imgFav = (ImageButton) view.findViewById(R.id.img_favorite);
        Log.d("Img button", "usao u favorite");
        tweetId = imgFav.getContentDescription().toString();
        Log.d("Img button", "usao u favorite" + tweetId);
        Log.d("Img button", "tag" + imgFav.getTag());
        if (imgFav.getTag() != 1) {
            imgFav.setTag(1);
            imgFav.setImageResource(R.drawable.ic_clicked_fav);
        }
        else {
            imgFav.setTag(0);
            imgFav.setImageResource(R.drawable.ic_favorite);
        }

    }

}
