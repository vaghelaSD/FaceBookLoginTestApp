package com.example.rahul.facebooktestlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    LoginButton fbButton;
    private CallbackManager manager;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbButton = (LoginButton) findViewById(R.id.login_button);
        setupLoginButton();
    }

    private void setupLoginButton() {
        manager = CallbackManager.Factory.create();
        fbButton.setReadPermissions(Arrays.asList(EMAIL));
        fbButton.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "onSuccess FB_Token: " + loginResult.getAccessToken());
                getUserFacebookData();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("TAG", "onSuccess FB_Token: " + error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        manager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

// super.onActivityResult(requestCode, resultCode, data);
    }


    private void getUserFacebookData() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Profile profile = Profile.getCurrentProfile();
        profile.getFirstName();

//AccessToken accessToken = AccessToken.getCurrentAccessToken();
  //      Profile profile = Profile.getCurrentProfile();
    //    profile.getFirstName();

        Log.d("TAG", "Profile: " + profile.getId());
        Log.d("TAG", "Profile: " + accessToken.getToken());
        Log.d("TAG", "Profile: " + profile.getFirstName());
        Log.d("TAG", "Profile: " + profile.getMiddleName());
        Log.d("TAG", "Profile: " + profile.getLastName());
        Log.d("TAG", "Profile: " + profile.getProfilePictureUri(800, 800));

        if (accessToken != null && !accessToken.isExpired()) {
            Log.d("TAG", "Logged In already");
        }
    }
}
