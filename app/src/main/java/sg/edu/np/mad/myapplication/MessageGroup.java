package sg.edu.np.mad.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {
    private String TAG = "Message Group Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button group1Button = findViewById(R.id.Group1Button);
        Button group2Button = findViewById(R.id.Group2Button);

        group1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Group 1 Button Pressed");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.Fragment, new Group1Fragment());
                ft.commit();
            }
        });

        group2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Group 2 Button Pressed");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.Fragment, new Group2Fragment());
                ft.commit();
            }
        });
    }
}