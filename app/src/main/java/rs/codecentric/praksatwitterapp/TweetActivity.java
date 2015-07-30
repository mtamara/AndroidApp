package rs.codecentric.praksatwitterapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Twit;
import model.User;
import rest.ApiUtil;
import rest.CommentService;
import rest.TwitService;
import rest.UserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TweetActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ApiUtil api = new ApiUtil();
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(api.getAPI_URL()).build();
    String userId;
    String tweetId;
    User sessionUser = new User();

    ListView lview;
    CommentCustomAdapter adapter;
    private ArrayList<Object> commentList;
    private CommentBean bean;
    Twit twit = new Twit();
    private List<Comment> comments = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        userId = getIntent().getStringExtra("userId");
        Log.d("userId", "ID " + userId);
        tweetId = getIntent().getStringExtra("tweetId");

        final TextView txtTweetOwner = (TextView) findViewById(R.id.tweet_owner);
        final TextView txtTweetContent = (TextView) findViewById(R.id.tweet_content);
        final TextView txtTweetDate = (TextView) findViewById(R.id.tweet_date);

        //REST
        TwitService twitService = restAdapter.create(TwitService.class);
        twitService.getTweetById(tweetId, new Callback<Twit>() {
            @Override
            public void success(Twit twit, Response response) {
                txtTweetOwner.setText(twit.getOwnerName());
                txtTweetContent.setText(twit.getContent());
                txtTweetDate.setText("25.07.2015");
                //twit = twit;
                comments = twit.getComments();
                if (!comments.isEmpty()) {
                    prepareCommentsList();
                    lview = (ListView) findViewById(R.id.comments_list);
                    adapter = new CommentCustomAdapter(TweetActivity.this, commentList);
                    lview.setAdapter(adapter);
                    lview.setOnItemClickListener(TweetActivity.this);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
            }
        });

        //REST added
        UserService userService = restAdapter.create(UserService.class);
        if (userId == null) {
            userId = "55aea961f4aa1cb3ec9ed3d4";
        }
        userService.getUser(userId, new Callback<User>() {

            @Override
            public void success(User user, Response response) {
                sessionUser = user;
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
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    public void favorite(View view) {
        ImageButton btnFav = (ImageButton)view.findViewById(R.id.img_favorite);
        if (btnFav.getContentDescription().equals("unclicked")) {
            btnFav.setImageResource(R.drawable.ic_clicked_fav);
            btnFav.setContentDescription("clicked");
        }
        else {
            btnFav.setImageResource(R.drawable.ic_favorite);
            btnFav.setContentDescription("unclicked");
        }
    }

    public void showComments(View view) {
        ImageButton btnCom = (ImageButton)view.findViewById(R.id.img_comment_tweet);
        if (!comments.isEmpty()) {
            if (btnCom.getContentDescription().equals("unclicked")) {
                lview.setVisibility(View.INVISIBLE);
                btnCom.setContentDescription("clicked");
            } else {
                lview.setVisibility(View.VISIBLE);
                btnCom.setContentDescription("unclicked");
            }
        }
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

    public void viewFollowing() {
        Intent intent = new Intent(this, FollowingActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void homepage() {
        Intent intent = new Intent(this, HomepageActivity.class);
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
        if (id == R.id.action_settings) {
            settings();
        }
        else if (id == R.id.action_profile) {
            userProfile();
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

    public void addComment(View view) {

        EditText comment = (EditText) findViewById(R.id.txt_comment_content);
        //REST added
        CommentService commentService = restAdapter.create(CommentService.class);
        Comment com = new Comment();
        com.setContent(comment.getText().toString());
        commentService.addComment(userId, tweetId, com, new Callback<Twit>() {

            @Override
            public void success(Twit twit, Response response) {
                Intent intent = new Intent(TweetActivity.this, TweetActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("tweetId", twit.getId());
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
                Log.d("Comment", "Greska " + error.getMessage());
            }
        });
        //end of rest
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        CommentBean bean = (CommentBean) adapter.getItem(position);
    }

    /* Method used to prepare the ArrayList,
     * Same way, you can also do looping and adding object into the ArrayList.
     */
    public void prepareCommentsList()
    {
        commentList = new ArrayList<Object>();
        Log.d("Twit", "twit " + twit.getContent());
        Log.d("Comments", "size " + comments.size());
        Log.d("Tweet ID", "je " + tweetId);
        for (Comment comment : comments) {
            AddObjectToList(R.drawable.ic_default_user, comment.getContent(), comment.getOwnerName());
        }
       // adapter.notifyDataSetChanged();

    }

    // Add one item into the Array List
    public void AddObjectToList(int ownerImage, String content, String ownerName)
    {
        bean = new CommentBean();
        bean.setOwnerImage(ownerImage);
        bean.setCommentContent(content);
        bean.setOwnerName(ownerName);
        commentList.add(bean);
    }
}
