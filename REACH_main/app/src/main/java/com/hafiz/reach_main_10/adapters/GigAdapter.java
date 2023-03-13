package com.hafiz.reach_main_10.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz.reach_main_10.R;
import com.hafiz.reach_main_10.model.GigModel;

import java.util.ArrayList;

public class GigAdapter extends RecyclerView.Adapter<GigAdapter.viewholder> {

    ArrayList<GigModel> list;
    Context context;

    public GigAdapter(ArrayList<GigModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_view,parent,false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        GigModel gigModel = list.get(position);
        holder.imageView.setImageResource(gigModel.getPic());
        holder.textView.setText(gigModel.getText());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"item is clicked", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  viewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public viewholder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
