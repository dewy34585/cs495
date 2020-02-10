package com.example.questv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    /*You can also get the user's email address with getEmail, 
    the user's Google ID (for client-side use) with getId, 
    and an ID token for the user with getIdToken. 
    If you need to pass the currently signed-in user to a backend server, 
    send the ID token to your backend server and validate the token on the server.*/

    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    //Button signOutButton; //FIXME type

    private static final String TAG = SignIn.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        }); //FIXME: this?


        /*signOutButton = findViewById(R.id.sign_out_button);
        signOutButton.setSize(signOutButton.SIZE_STANDARD);
        signOutButton.setOnClickListener(this); //FIXME: this?*/
    }

    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        /*Note: If you need to detect changes to a user's auth state that happen outside your app,
        such as access token or ID token revocation, or to perform cross-device sign-in,
        you might also call GoogleSignInClient.silentSignIn when your app starts.*/
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account == null) {
            signInButton.setVisibility(View.VISIBLE);
            //signOutButton.setVisibility(View.GONE);
        }
        else {
            signInButton.setVisibility(View.GONE);
            //signOutButton.setVisibility(View.VISIBLE);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        updateUI(null);
                    }
                });
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference("userLists");
            
            //next 5 lines could have conditional where we check to see if the ID is already exists
            User newUser = new User();
            newUser.setName(account.getDisplayName());
            newUser.setMail(account.getEmail());
            newUser.setUserID(databaseUsers.push().getKey());
            databaseUsers.child(newUser.toString());

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

}
