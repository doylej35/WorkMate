package com.example.workmate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginFragment<web_client_id> extends Fragment {
    EditText mEmail, mPassword;
    View view;
    FirebaseAuth fAuth;
    Button login, logout;
    TextView passRest;
    private final static int RC_SIGN_IN=1;

    private GoogleSignInClient mGoogleSignInClient;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_login,  container, false);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();

        mEmail = view.findViewById(R.id.loginUsername);                         //entered email
        mPassword = view.findViewById(R.id.loginPassword);

        login = (Button) view.findViewById(R.id.loginLogin);
        passRest = view.findViewById(R.id.tvForgotPass);

        SignInButton googleSignInButton = view.findViewById(R.id.btnGoogle);                   //this is the google button
        googleSignInButton.setSize(SignInButton.SIZE_STANDARD);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());


        Intent intent = new Intent( getActivity(), OKHttpGET.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();          //email text box from login_activity.xml
                String password = mPassword.getText().toString().trim();    //password text box from login_activity.xml

                if(currentUser!=null) {
                    Toast.makeText(getActivity(), "Already Logged in", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(TextUtils.isEmpty(email)){               //is email empty?
                        mEmail.setError("Email is required.");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){            //is password empty?
                        mEmail.setError("Password is required.");
                    }
                    //run the firebase login functions
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            //login was successful, links to main activity page via openact() function
                            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = fAuth.getCurrentUser();  //creates a warning but is necessary so that
                            //the user stays logged in

                            //sends them to okhttpget to update their local database
                            startActivity(intent);
                        }else {
                            //login not successful, prints reason
                            //example: login credentials are not registered
                            Toast.makeText(getActivity(), "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        //this button is to login with google
        View.OnClickListener handleGoogleLogin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signIn();
            }
        };

        googleSignInButton.setOnClickListener(handleGoogleLogin);

        passRest.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();      //email entered on login screen text box
            if(email.equals("")){       //better than using '=='
                Toast.makeText(getActivity(), "Please Enter Email Above to Reset Password", Toast.LENGTH_SHORT).show();
            }
            else {
                fAuth.sendPasswordResetEmail(email);    //sends recovery email
                Toast.makeText(getActivity(), "Recovery Email Sent", Toast.LENGTH_SHORT).show();
                startActivity(intent);  //returns to main screen
            }
        });

        return view;
    }

    public void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //result returned from launching the intent from googlesigninclient.getsigninintent
        if(requestCode == RC_SIGN_IN) {
            Log.d("ON_ACTIVITY_RESULT", "entered");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGNIN", "signInWithCredential:success");
                            FirebaseUser user = fAuth.getCurrentUser();
                            Intent intent = new Intent( getActivity(), OKHttpGET.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("f", "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            //uncomment this when it is registering properly again
            if((databaseHelper.searchSupplier(account.getEmail())==null)){
                if(databaseHelper.searchClient(account.getEmail())==null) {
                    Toast.makeText(getActivity(), "Please register first", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                }
                else {
                    Log.d("LOGIN", "google authentication for client" + databaseHelper.searchClient(account.getEmail()).toString());
                    firebaseAuthWithGoogle(account);
                }
            }
            else {
                Log.d("LOGIN", "google authentication for supplier" + databaseHelper.searchSupplier(account.getEmail()).toString());
                firebaseAuthWithGoogle(account);
            }
        } catch (ApiException e) {
            Log.w("ERROR","signInResult:failed code=" + e.getStatusCode() + " ");
            e.printStackTrace();
        }
    }
}
