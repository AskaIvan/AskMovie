package id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.DetailActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.HomeFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.model.Results;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> mlist;
    HomeFragment homefragment;
    Context context;
    private int lastposition = -1;

    public MyAdapter(HomeFragment homefragment, ArrayList<Results> mlist, Context context) {
        this.mlist = mlist;
        this.homefragment = homefragment;
        this.context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        final Results results = mlist.get(position);
        holder.tvName.setText(results.title);
        holder.tvRel.setText(results.release_date);
        image = url + results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = results.id;
                Intent intent = new Intent(context, DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title", results.title);
                intent.putExtra("poster_path", results.backdrop_path);
                intent.putExtra("description", results.overview);
                intent.putExtra("release_date", results.release_date);
                intent.putExtra("vote_average", results.vote_average);
                intent.putExtra("popularity", results.popularity);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvRel;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View v) {
            super(v);

            tvName = (TextView) v.findViewById(R.id.tv_text);
            tvRel = (TextView) v.findViewById(R.id.tv_release);
            imageView = (ImageView) v.findViewById(R.id.iv_image);
            cardView = (CardView) itemView.findViewById(R.id.CardView);
        }
    }
}