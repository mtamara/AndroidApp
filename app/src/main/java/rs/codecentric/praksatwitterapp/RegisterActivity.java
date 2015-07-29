package rs.codecentric.praksatwitterapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import model.User;
import rest.ApiUtil;
import rest.UserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RegisterActivity extends ActionBarActivity {

    //String API = "http://10.10.10.134:8083/praksaREST";
    ApiUtil api = new ApiUtil();
    Button btnRegister;
    EditText firstName, lastName, username, password, email;
    public static String message;
    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(api.getAPI_URL()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.register);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
    }

    public void register(View view) {
        if (!username.getText().toString().equals("") && !password.getText().toString().equals("")
                && !firstName.getText().toString().equals("") && !lastName.getText().toString().equals("")) {
            //REST added
            User newUser = new User();
            newUser.setFirstName(firstName.getText().toString());
            newUser.setLastName(lastName.getText().toString());
            newUser.setUsername(username.getText().toString());
            newUser.setPassword(password.getText().toString());
            newUser.setEmail(email.getText().toString());
            UserService userService = restAdapter.create(UserService.class);
            userService.registerUser(newUser, new Callback<User>() {

                @Override
                public void success(User registeredUser, Response response) {
                    Toast.makeText(getApplicationContext(), "Registering...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, HomepageActivity.class);
                    message = username.getText().toString();
                    intent.putExtra("userId", registeredUser.getId());
                    startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    //TO-DO go to error page
                }
            });
            //end of rest

        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
