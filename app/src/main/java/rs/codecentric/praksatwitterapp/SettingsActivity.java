package rs.codecentric.praksatwitterapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import model.User;
import rest.ApiUtil;
import rest.UserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SettingsActivity extends ActionBarActivity {

    ApiUtil api = new ApiUtil();
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(api.getAPI_URL()).build();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userId = getIntent().getStringExtra("userId");
        //REST added
        final EditText firstName = (EditText) findViewById(R.id.first_name);
        final EditText lastName = (EditText) findViewById(R.id.last_name);
        UserService git = restAdapter.create(UserService.class);
        if (userId == null) {
            userId = "55ae13cc4c32e338b01d2c8c";
        }
        git.getUser(userId, new Callback<User>() {

            @Override
            public void success(User user, Response response) {
                firstName.setText(user.getFirstName());
                lastName.setText(user.getLastName());
            }

            @Override
            public void failure(RetrofitError error) {
                //TO-DO go to error page
            }
        });
        //end of rest
    }

    public void showTab(View view) {
        EditText password = (EditText) findViewById(R.id.password);
        EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        if (password.getVisibility() != View.VISIBLE) {
            password.setVisibility(View.VISIBLE);
            confirmPassword.setVisibility(View.VISIBLE);
        }
        else {
            password.setVisibility(View.INVISIBLE);
            confirmPassword.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    public void homepage() {
        Intent intent = new Intent(this, HomepageActivity.class);
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

    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
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
            return true;
        }
        else if (id == R.id.home) {
            homepage();
        }
        else if (id == R.id.action_profile) {
            userProfile();
        }
        else if (id == R.id.action_following) {
            viewFollowing();
        }
        else if (id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

}
