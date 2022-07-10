package sg.edu.np.mad.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<User> data;
    public adapter (ArrayList<User> input) {
        data = input;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new ViewHolder(item);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        User u = data.get(position);
        holder.name.setText(u.name);
        holder.description.setText(u.description);
        if (Integer.parseInt(u.name.substring(u.name.length() - 1)) != 7) {
            holder.bigImageCardView.setVisibility(View.GONE);
            holder.bigImageCardView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        }
    }

    public int getItemCount() {
        return data.size();
    }
}
