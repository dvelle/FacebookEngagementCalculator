package com.mapplinks.facebookengagementcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Double likes, comments, shares, postReach, pageLikes, result;
    String stLikes, stComments, stShare, stPostReach, stPageLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText postLikesNumber = (EditText) findViewById(R.id.likes);
        final EditText postCommentsNumber = (EditText) findViewById(R.id.comments);
        final EditText postSharesNumber = (EditText) findViewById(R.id.shares);
        final TextView postResult = (TextView) findViewById(R.id.result);
        final EditText postReachNumber = (EditText) findViewById(R.id.reach);
        final EditText pageLikeNumber = (EditText) findViewById(R.id.page_likes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLikesNumber.setText("");
                postCommentsNumber.setText("");
                postSharesNumber.setText("");
                postResult.setText("");
                postReachNumber.setText("");
                pageLikeNumber.setText("");

                postReachNumber.setEnabled(false);
                pageLikeNumber.setEnabled(false);

                postLikesNumber.requestFocus();
            }
        });


        Button byReach = (Button) findViewById(R.id.byReach);                       //Engagement by Reach
        byReach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stLikes = postLikesNumber.getText().toString();
                stComments = postCommentsNumber.getText().toString();
                stShare = postSharesNumber.getText().toString();

                if (stLikes.isEmpty() || stComments.isEmpty() || stComments.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all the three values", Toast.LENGTH_SHORT).show();
                } else {
                    postReachNumber.setEnabled(true);
                    postReachNumber.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            stPostReach = postReachNumber.getText().toString();
                            if(stPostReach.isEmpty()){
                                Toast.makeText(MainActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                            }else{
                                postReach = Double.parseDouble(stPostReach);
                                shares = Double.parseDouble(stShare);
                                comments = Double.parseDouble(stComments);
                                likes = Double.parseDouble(stLikes);
                                result = reachEngagement();
                                postResult.setText("Reach by Engagement: " + result);

                            }
                        }
                    });
                }
            }

        });

//----------------------------------------------------------------------------------------------------------------------------//
        Button byFans = (Button) findViewById(R.id.byFans);
        byFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stLikes = postLikesNumber.getText().toString();
                stComments = postCommentsNumber.getText().toString();
                stShare = postSharesNumber.getText().toString();

                if (stLikes.isEmpty() || stComments.isEmpty() || stComments.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all the three values", Toast.LENGTH_SHORT).show();
                } else {
                    pageLikeNumber.setEnabled(true);
                    pageLikeNumber.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            stPageLikes = pageLikeNumber.getText().toString();
                            if(stPageLikes.isEmpty()){
                                Toast.makeText(MainActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                            }else{
                                pageLikes = Double.parseDouble(stPageLikes);
                                shares = Double.parseDouble(stShare);
                                comments = Double.parseDouble(stComments);
                                likes = Double.parseDouble(stLikes);
                                result = reachFans();
                                postResult.setText("Reach by Fans: " + result);

                            }
                        }
                    });
                }
            }

        });
    }

    double reachEngagement() {
        return (((likes + comments + shares) / postReach) * 100);

    }

    double reachFans() {
        return (((likes + comments + shares) / pageLikes) * 100);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
