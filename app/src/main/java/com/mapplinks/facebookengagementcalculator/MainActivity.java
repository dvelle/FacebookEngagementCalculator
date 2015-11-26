package com.mapplinks.facebookengagementcalculator;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;

public class MainActivity extends AppCompatActivity {

    Double likes, comments, shares, postReach, pageLikes;
    String stLikes, stComments, stShare, stPostReach, stPageLikes;
    EditText postLikesNumber, postCommentsNumber, postSharesNumber, choiceValue;
    TextView resultCaption, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MaterialFavoriteButton toolbarReset = new MaterialFavoriteButton.Builder(this)
                .favorite(false)
                .animateUnfavorite(true)
                .animateFavorite(true)
                .favoriteResource(R.drawable.ic_reset)
                .notFavoriteResource(R.drawable.ic_reset)
                .rotationDuration(400)
                .rotationAngle(360)
                .create();

        toolbar.addView(toolbarReset);

        toolbarReset.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                reset();
                Toast.makeText(MainActivity.this, "Cleared!", Toast.LENGTH_SHORT).show();
                toolbarReset.setFavorite(false, false);
            }
        });


        postLikesNumber = (EditText) findViewById(R.id.likes);
        postCommentsNumber = (EditText) findViewById(R.id.comments);
        postSharesNumber = (EditText) findViewById(R.id.shares);

        final Switch choice = (Switch) findViewById(R.id.choice);

        choiceValue = (EditText) findViewById(R.id.second_value);
        resultCaption = (TextView) findViewById(R.id.result_caption);

        result = (TextView) findViewById(R.id.result);


        choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    choiceValue.setHint("Enter Post Reach");
                    if (Build.VERSION.SDK_INT > 22)
                        choice.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.on_color)));
                    choiceValue.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            stLikes = postLikesNumber.getText().toString();
                            stComments = postCommentsNumber.getText().toString();
                            stShare = postSharesNumber.getText().toString();

                            if (stLikes.isEmpty() || stComments.isEmpty() || stShare.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Enter all the three values", Toast.LENGTH_SHORT).show();
                            } else {
                                shares = Double.parseDouble(stShare);
                                comments = Double.parseDouble(stComments);
                                likes = Double.parseDouble(stLikes);
                            }
                            stPostReach = s.toString();
                            if (!stPostReach.isEmpty()) {
                                resultCaption.setText("Engagement by Post:");
                                postReach = Double.parseDouble(stPostReach);
                                result.setText("" + reachEngagement() + " %");
                            }
                        }
                    });
                } else {

                    if (Build.VERSION.SDK_INT > 22)
                        choice.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.off_color)));
                    choiceValue.setHint("Enter Page Likes");
                    choiceValue.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            stLikes = postLikesNumber.getText().toString();
                            stComments = postCommentsNumber.getText().toString();
                            stShare = postSharesNumber.getText().toString();

                            if (stLikes.isEmpty() || stComments.isEmpty() || stShare.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Enter all the three values", Toast.LENGTH_SHORT).show();
                            } else {
                                shares = Double.parseDouble(stShare);
                                comments = Double.parseDouble(stComments);
                                likes = Double.parseDouble(stLikes);
                            }
                            stPageLikes = s.toString();
                            if (!stPageLikes.isEmpty()) {
                                resultCaption.setText("Engagement by Fans:");
                                pageLikes = Double.parseDouble(stPageLikes);
                                result.setText("" + reachFans() + " %");
                            } else {
                                try {

                                } catch (Exception e) {

                                }
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

    void reset() {
        postCommentsNumber.setText("");
        postLikesNumber.setText("");
        postSharesNumber.setText("");
        choiceValue.setText("");
        resultCaption.setText("");
        result.setText("");
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
//        if (id == R.id.reset) {
//            reset();
//        }
        return super.onOptionsItemSelected(item);
    }
}

