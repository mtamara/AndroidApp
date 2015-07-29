package rs.codecentric.praksatwitterapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class TweetActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        getIntent().getStringExtra("id");
        TextView text = (TextView)findViewById(R.id.tweetBody);
        text.setText("to je id tweeta");
        text.append(getIntent().getStringExtra("id"));

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

    public void comment(View view) {
        CommentFragment comFrag = new CommentFragment();
        ImageButton btnCom = (ImageButton) view.findViewById(R.id.img_comment);
        Fragment com_frag = getFragmentManager().findFragmentById(R.id.comment_fragment);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

        if (comFrag == null) {
            ft.add(R.id.comment_fragment, comFrag, "tagComment");
            ft.commit();
        }
        else {
            ft.hide(com_frag.getFragmentManager().getFragment(null, "tagComment"));
        }

        /*
        if (com_frag.isHidden()) {
            ft.show(com_frag);
        }
        */
        if (btnCom.getContentDescription().equals("unclicked")) {
            //ft.hide(com_frag);
            ft.detach(com_frag);
        }
    }

    public void settings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void userProfile() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void viewFollowing() {
        Intent intent = new Intent(this, FollowingActivity.class);
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

        else if (id == R.id.action_following) {
            viewFollowing();
        }

        return super.onOptionsItemSelected(item);
    }
}
