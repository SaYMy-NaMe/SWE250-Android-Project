package com.hafiz.reach_main_10.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hafiz.reach_main_10.JobDetailActivity;
import com.hafiz.reach_main_10.R;
import com.hafiz.reach_main_10.model.GigModel;
import com.hafiz.reach_main_10.model.PostModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GigAdapter extends RecyclerView.Adapter<GigAdapter.viewholder> {

    ArrayList<PostModel> list;
    Context context;

    public GigAdapter(ArrayList<PostModel> list, Context context) {
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
        PostModel gigModel = list.get(position);

        Picasso.get().load(gigModel.getProductImage()).placeholder(R.drawable.car_repairing).into(holder.imageView);

        holder.postTitle.setText(gigModel.getTitle());
        holder.fair.setText(gigModel.getFair() + "Tk ");
//        holder.area.setText("Interested Area:"+gigModel.getArea()  );
//        holder.NID.setText("NID"+gigModel.getNID());
//        holder.PrmAdress.setText("Address: "+gigModel.getPrmAdress());
//        holder.CntNo.setText("Contact :"+gigModel.getCntNo());
        //holder.imageView.setImageResource(gigModel.setProductImage();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobDetailActivity.class);
                intent.putExtra("JobDetImage", gigModel.getProductImage());
                intent.putExtra("JobDetTitle", gigModel.getTitle());
                intent.putExtra("JobDetFair", gigModel.getFair());
                intent.putExtra("JobDetInArea", gigModel.getArea());
                intent.putExtra("JobDetNid", gigModel.getNID());

                intent.putExtra("JobDetAddr", gigModel.getPrmAdress());
                intent.putExtra("JobDetCnt", gigModel.getCntNo());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);



            }
        });




    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  viewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView postTitle,fair,area,NID , PrmAdress,CntNo;;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.Sample_image);
            postTitle = itemView.findViewById(R.id.Sample_title);
            fair = itemView.findViewById(R.id.Sample_fair);
//            area = itemView.findViewById(R.id.Sample_inArea);
//            NID= itemView.findViewById(R.id.Sample_nid);
//            PrmAdress = itemView.findViewById(R.id.Sample_adrr);
//            CntNo = itemView.findViewById(R.id.Sample_cont);

        }
    }
}
