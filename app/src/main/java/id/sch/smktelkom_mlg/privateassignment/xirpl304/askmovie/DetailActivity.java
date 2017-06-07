package id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.model.Fav;

public class DetailActivity extends AppCompatActivity {
    public String url = "https://image.tmdb.org/t/p/w500";
    public byte[] gambarsave = new byte[2048];
    Fav fav;
    String Id, img, des, gambar, popu, rate, release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button bfav = (Button) findViewById(R.id.buttonfav);

        Intent intent = getIntent();
        Id = intent.getStringExtra("movie_title");
        img = intent.getStringExtra("poster_path");
        des = intent.getStringExtra("description");
        popu = intent.getStringExtra("popularity");
        rate = intent.getStringExtra("vote_average");
        release = intent.getStringExtra("release_date");
        setTitle(Id);
        gambar = url + img;
        ImageView detail = (ImageView) findViewById(R.id.imageViewDetail);
        TextView descipsi = (TextView) findViewById(R.id.DetailDescription);
        TextView popula = (TextView) findViewById(R.id.Popularity);
        TextView rating = (TextView) findViewById(R.id.Rating);
        TextView release_date = (TextView) findViewById(R.id.ReleaseDate);
        Glide.with(this).load(gambar)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(detail);
        descipsi.setText(des);
        popula.setText(popu);
        rating.setText(rate);
        release_date.setText(release);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosimpan();
            }
        });
    }

    private void dosimpan() {
        String overview = getIntent().getStringExtra(Main2Activity.RESULTOVERVIEW);
        String title = getIntent().getStringExtra(Main2Activity.RESULTTITLE);
        byte[] backdrop_path = gambarsave;

        fav = new Fav(overview, title, backdrop_path);
        fav.save();
    }

}
