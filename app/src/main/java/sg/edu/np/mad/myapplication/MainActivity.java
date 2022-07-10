package sg.edu.np.mad.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Scanner;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "On Create!");
        Button followButton = findViewById(R.id.followButton);
        Button messageButton = findViewById(R.id.messageButton);
        TextView nameText = findViewById(R.id.nameText);
        TextView descriptionText = findViewById(R.id.descriptionText);
        ImageView imageView = findViewById(R.id.imageView);
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        String name = getIntent().getStringExtra("Name");
        User userObj = dbHandler.findUser(name);
        nameText.setText(userObj.name);
        descriptionText.setText(userObj.description);
        messageButton.setText("Message");
        if (userObj.followed) {
            followButton.setText("Unfollow");
        } else {
            followButton.setText("Follow");
        }

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Follow Button Press");
                userObj.followed = !(userObj.followed);
                if (userObj.followed) {
                    followButton.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                } else {
                    followButton.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                dbHandler.updateFollowed(userObj);
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Message Button Press");
                Intent myIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(myIntent);
            }
        });
    }
}