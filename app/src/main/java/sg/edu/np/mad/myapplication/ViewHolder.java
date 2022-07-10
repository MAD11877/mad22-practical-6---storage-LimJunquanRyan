package sg.edu.np.mad.myapplication;

import static sg.edu.np.mad.myapplication.random.getRandom;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder {
    private String TAG = "List Activity";
    CardView bigImageCardView;
    CardView imageCardView;
    TextView name;
    TextView description;

    public ViewHolder(View itemView) {
        super(itemView);
        bigImageCardView = itemView.findViewById(R.id.bigImageCardView);
        imageCardView = itemView.findViewById(R.id.imageCardView);
        name = itemView.findViewById(R.id.nameTextView);
        description = itemView.findViewById(R.id.descriptionTextView);

        imageCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Press");
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle("Profile");
                builder.setMessage(name.getText());
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mainActivityIntent = new Intent(itemView.getContext(), MainActivity.class);
                        mainActivityIntent.putExtra("Name", name.getText());
                        itemView.getContext().startActivity(mainActivityIntent);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
