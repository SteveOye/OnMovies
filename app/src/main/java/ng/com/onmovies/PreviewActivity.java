package ng.com.onmovies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {

    ImageView poster;
    TextView txtTitle, txtTime, txtRate, txtVotes, txtPlot, txtDir, txtWriter, txtActors;
    String image, title, rate, time, vote, plot, director, writer, actors;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        initView();
        getIntentMethod();

        Picasso.get()
                .load(image)
                .into(poster);
        txtTitle.setText(title);
        txtActors.setText(actors);
        txtTime.setText(time);
        txtVotes.setText(vote);
        txtRate.setText(rate);
        txtPlot.setText(plot);
        txtWriter.setText(writer);
        txtDir.setText(director);
    }

    public void initView(){
        txtActors = findViewById(R.id.actors);
        txtDir = findViewById(R.id.director_name);
        txtWriter = findViewById(R.id.writer_name);
        txtPlot = findViewById(R.id.plot);
        txtRate = findViewById(R.id.rate);
        txtVotes = findViewById(R.id.votes);
        txtTime = findViewById(R.id.time);
        txtTitle = findViewById(R.id.title);
        poster = findViewById(R.id.poster);
    }

    public void getIntentMethod(){
        image = getIntent().getStringExtra("image");
        title = getIntent().getStringExtra("title");
        plot = getIntent().getStringExtra("plot");
        rate = getIntent().getStringExtra("rate");
        vote = getIntent().getStringExtra("votes");
        director = getIntent().getStringExtra("director");
        writer = getIntent().getStringExtra("writer");
        actors = getIntent().getStringExtra("actors");
        time = getIntent().getStringExtra("time");
    }
}
