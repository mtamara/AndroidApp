package rs.codecentric.praksatwitterapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
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


public class HomepageActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ApiUtil api = new ApiUtil();
    ListView lview;
    TweetCustomAdapter adapter;
    private ArrayList<Object> tweetList;
    private TweetBean bean;
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(api.getAPI_URL()).build();
    String registeredUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        registeredUserId = getIntent().getStringExtra("userId");
        Log.d("UserId: ", "registered: " + registeredUserId);

        prepareTweetsList();
        lview = (ListView) findViewById(R.id.tweets_list_homepage);
        adapter = new TweetCustomAdapter(this, tweetList);
        lview.setAdapter(adapter);
    }

    public void postTweet(View view) {
        //REST added
        TwitService twitService = restAdapter.create(TwitService.class);
        final EditText tweetContent = (EditText) findViewById(R.id.txt_tweet_content_home);
        Twit twit = new Twit();
        twit.setContent(tweetContent.getText().toString());
        Log.d("Tweet", "Content: " + tweetContent.getText().toString());
        if (registeredUserId == null) {
            registeredUserId = "55aea961f4aa1cb3ec9ed3d4";
        }
        twitService.postTweet(registeredUserId, twit, new Callback<Twit>() {

            @Override
            public void success(Twit tweet, Response response) {
                TweetBean newTweet = new TweetBean();
                newTweet.setTweetContent(tweet.getContent());
                newTweet.setUserData(tweet.getOwnerName());
                newTweet.setTweetDate("20.06.2015");

                tweetList.add(0, newTweet);
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(HomepageActivity.this, HomepageActivity.class);
                intent.putExtra("userId", registeredUserId);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
            }
        });
        //end of rest
    }


    /*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //TextView t = (TextView) v.findViewById(R.id.tweetTitle);
        //t.setText("Tweet Clicked");
        Intent intent = new Intent(this, TweetActivity.class);
        intent.putExtra("id", String.valueOf(id));
        startActivity(intent);
    }
    */

    public void settings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("userId", registeredUserId);
        startActivity(intent);
    }

    public void userProfile() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("userId", registeredUserId);
        startActivity(intent);
    }

    public void viewFollowing() {
        Intent intent = new Intent(this, FollowingActivity.class);
        intent.putExtra("userId", registeredUserId);
        startActivity(intent);
    }

    public void viewSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("userId", registeredUserId);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_homepage, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            settings();
        }
        else if (id == R.id.action_profile) {
            userProfile();
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
        // Toast.makeText(this, "Title => " + bean.getTitle() + " n Description => " + bean.getDescription(), Toast.LENGTH_SHORT).show();
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareTweetsList()
    {
        tweetList = new ArrayList<Object>();
        final TextView txtNoTweets = (TextView) findViewById(R.id.no_tweets);
        //REST added
        TwitService twitService = restAdapter.create(TwitService.class);
        if (registeredUserId == null) {
            registeredUserId = "55aea961f4aa1cb3ec9ed3d4";
        }
        twitService.getUserTweets(registeredUserId, "home", new Callback<List<Twit>>() {

            @Override
            public void success(List<Twit> twits, Response response) {
                Log.d("velicina liste", "broj:" + tweetList.size());
                for (Twit twit : twits) {
                    AddObjectToList(R.drawable.ic_bird, twit.getOwnerName(), twit.getContent(), "11.11.2015");
                }
                if (twits.isEmpty()) {
                    txtNoTweets.setVisibility(View.VISIBLE);
                    lview.setVisibility(View.INVISIBLE);
                }
                else {
                    txtNoTweets.setVisibility(View.INVISIBLE);
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
    public void AddObjectToList(int image, String userData, String tweetContent, String tweetDate)
    {
        bean = new TweetBean();
        bean.setUserData(userData);
        bean.setUserImage(image);
        bean.setTweetContent(tweetContent);
        bean.setTweetDate(tweetDate);
        tweetList.add(bean);
    }
}
