package com.example.rama.smarthealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    //private LinearLayout Prof_Section;
    private Button SignOut;
    private SignInButton SignIn;
    private TextView Name,Email;
    private ImageView Prof_Pic;
    private GoogleApiClient googleApiClient;
    private static  final int REQ_CODE = 9001;
   // private Button bt;
    private Button qw;
    private Button sa;
    private Button up;
    private Button da;
    private Button gr;
    private Button dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Prof_Section = (LinearLayout)findViewById(R.id.prof_section);
        SignOut = (Button)findViewById(R.id.bn_logout);
        SignIn = (SignInButton)findViewById(R.id.bn_login);
        Name = (TextView)findViewById(R.id.name);
        Email = (TextView)findViewById(R.id.email);
        Prof_Pic = (ImageView)findViewById(R.id.prof_pic);
        //bt = (Button)findViewById(R.id.button);
        qw = (Button)findViewById(R.id.button2);
        sa = (Button)findViewById(R.id.button3);
        up = (Button)findViewById(R.id.button4);
        da = (Button)findViewById(R.id.button5);
        gr = (Button)findViewById(R.id.button6);
        dp = (Button) findViewById(R.id.button7);

        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
     //   bt.setOnClickListener(this);
        qw.setOnClickListener(this);
        sa.setOnClickListener(this);
        up.setOnClickListener(this);
        da.setOnClickListener(this);
        gr.setOnClickListener(this);
        dp.setOnClickListener(this);

        Prof_Pic.setVisibility(View.GONE);
        Name.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        SignOut.setVisibility(View.GONE);
       // bt.setVisibility(View.GONE);
        qw.setVisibility(View.GONE);
        sa.setVisibility(View.GONE);
        up.setVisibility(View.GONE);
        da.setVisibility(View.GONE);
        gr.setVisibility(View.GONE);
        dp.setVisibility(View.GONE);


        //Prof_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();




    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bn_login:
                signIn();
                break;
            case R.id.bn_logout:
                signOut();
                break;
            /*case R.id.button:
                shareIt();
                break;*/
            case R.id.button2:
                ana();
                break;
            case R.id.button3:
                salr();
                break;
            case R.id.button4:
                uploadPhoto();
                break;
            case R.id.button5:
                data();
                break;
            case R.id.button6:
                gra();
                break;
            case R.id.button7:
                dietplan();
                break;
        }
    }

    private void dietplan() {
        Intent diet = new Intent(MainActivity.this, DietPlan.class);
        startActivity(diet);
    }

    private void gra() {
        Intent graph = new Intent(MainActivity.this, GraphActivity.class);
        startActivity(graph);
    }

    private void data() {
        Intent dada = new Intent(MainActivity.this, DbDetails.class);
        startActivity(dada);
    }

    private void uploadPhoto() {
        Intent qwe= new Intent(MainActivity.this, photoUploader.class);
        startActivity(qwe);
    }

    private void salr() {
        Intent aa = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(aa);
    }

    private void ana() {
        Intent month = new Intent(MainActivity.this, MonthlyDisease.class);
        startActivity(month);
    }

    /*private void shareIt() {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = "Respected Doctor, PFA shared file";
        String shareSub = "Sharing my data with my doctor";
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(myIntent, "Share Using"));
    }*/

    private void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void signIn() {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }
    private void handleResult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String img_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
            Glide.with(this).load(img_url).into(Prof_Pic);
            updateUI(true);

        }
        else {
            updateUI(false);
        }
    }
    private void updateUI(boolean isLogin)
    {
        if(isLogin)
        {
            //Prof_Section.setVisibility(View.VISIBLE);
            Prof_Pic.setVisibility(View.VISIBLE);
            Name.setVisibility(View.VISIBLE);
            Email.setVisibility(View.VISIBLE);
            SignOut.setVisibility(View.VISIBLE);
           // bt.setVisibility(View.VISIBLE);
            qw.setVisibility(View.VISIBLE);
            sa.setVisibility(View.VISIBLE);
            up.setVisibility(View.VISIBLE);
            da.setVisibility(View.VISIBLE);
            gr.setVisibility(View.VISIBLE);
            dp.setVisibility(View.VISIBLE);

            SignIn.setVisibility(View.GONE);

        }
        else
        {
            //Prof_Section.setVisibility(View.GONE);
            Prof_Pic.setVisibility(View.GONE);
            Name.setVisibility(View.GONE);
            Email.setVisibility(View.GONE);
            SignOut.setVisibility(View.GONE);
            //bt.setVisibility(View.GONE);
            qw.setVisibility(View.GONE);
            sa.setVisibility(View.GONE);
            up.setVisibility(View.GONE);
            da.setVisibility(View.GONE);
            gr.setVisibility(View.GONE);
            dp.setVisibility(View.GONE);

            SignIn.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
