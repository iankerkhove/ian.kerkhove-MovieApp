package com.example.ian.werkstuk;

import android.arch.persistence.room.Database;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ian.werkstuk.dao.DB;
import com.example.ian.werkstuk.model.movie;

public class DetailLocalActivity extends AppCompatActivity {
    DB database = null;
    int id;
    TextView titel;
    TextView tagline;
    TextView release;
    TextView orilanguage;
    TextView spolanguage;
    TextView spolanguageLabel;
    TextView genre;
    TextView statu;
    TextView productionCountryLabel;
    TextView productionCountry;
    TextView votesAverage;
    TextView overview;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_local);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        database = DB.getDb(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie m = database.MovieDAO().findById(id);
                database.MovieDAO().delete(m);

                Snackbar.make(view, "Movie succesvol deleted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent i = getIntent();
        id = i.getIntExtra("movieId",0);

        tagline = (TextView) findViewById(R.id.tagline);
        release = (TextView) findViewById(R.id.releaseDate);
        orilanguage = (TextView) findViewById(R.id.originalLanguage);
        spolanguageLabel = (TextView) findViewById(R.id.spokenLanguageLabel);
        spolanguage = (TextView) findViewById(R.id.spokenLanguage);
        genre = (TextView) findViewById(R.id.genres);
        statu = (TextView) findViewById(R.id.status);
        productionCountryLabel = (TextView) findViewById(R.id.prodCountryLabel);
        productionCountry = (TextView) findViewById(R.id.prodCountry);
        votesAverage = (TextView) findViewById(R.id.voteAverage);
        overview = (TextView) findViewById(R.id.overview);
        image = (ImageView) findViewById(R.id.headImage);
        titel = (TextView) findViewById(R.id.title);

        fillView();
    }

    public void fillView(){
        movie m = database.MovieDAO().findById(id);
        titel.setText(m.getTitle());
        tagline.setText(m.getTagline());
        release.setText(m.getReleasedate());
        orilanguage.setText(m.getOriLanguage());
        spolanguage.setText(m.getSpoLanguage());
        genre.setText(m.getGenre());
        statu.setText(m.getStatus());
        productionCountry.setText(m.getProdCountry());
        votesAverage.setText(m.getVoteAverage());
        overview.setText(m.getOverview());
    }
}
