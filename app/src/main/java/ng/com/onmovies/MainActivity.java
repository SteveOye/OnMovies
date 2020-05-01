package ng.com.onmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        getList();
    }

    public void getList() {
        Call<ArrayList<Movie>> call = RetrofitClient.getInstance().
                getMovieApi()
                .getMovies();

        call.enqueue(new Callback<ArrayList<Movie>>() {

            @Override
            public void onResponse(@NonNull Call<ArrayList<Movie>> call, @NonNull Response<ArrayList<Movie>> response) {
                ArrayList<Movie> movies = response.body();


                assert movies != null;
                for (Movie movie : movies) {
                    Movie mv = new Movie();
                    String title = movie.getTitle();
                    String plot = movie.getPlot();
                    String poster = movie.getImages().get(0);
                    String releaseDate = movie.getReleased();
                    String duration = movie.getRuntime();
                    String rate = movie.getImdbRating();
                    String imdbVotes = movie.getImdbVotes();
                    String director = movie.getDirector();
                    String writer = movie.getWriter();
                    String actors = movie.getActors();

                    mv.setTitle(title);
                    mv.setPlot(plot);
                    mv.setPoster(poster);
                    mv.setReleased(releaseDate);
                    mv.setRuntime(duration);
                    mv.setImdbRating(rate);
                    mv.setImdbVotes(imdbVotes);
                    mv.setDirector(director);
                    mv.setWriter(writer);
                    mv.setActors(actors);
                    movieArrayList.add(mv);
                }

                progressBar.setVisibility(View.GONE);
                recyclerView = findViewById(R.id.movie_recycler);
                // use a linear layout manager
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                movieAdapter = new MovieAdapter(MainActivity.this, movieArrayList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(movieAdapter);
                movieAdapter.setOnItemClickListener(position -> {

                    Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
                    intent.putExtra("image", movieArrayList.get(position).getPoster());
                    intent.putExtra("title", movieArrayList.get(position).getTitle());
                    intent.putExtra("plot", movieArrayList.get(position).getPlot());
                    intent.putExtra("rate", movieArrayList.get(position).getImdbRating());
                    intent.putExtra("votes", movieArrayList.get(position).getImdbVotes());
                    intent.putExtra("time", movieArrayList.get(position).getRuntime());
                    intent.putExtra("director", movieArrayList.get(position).getDirector());
                    intent.putExtra("writer", movieArrayList.get(position).getWriter());
                    intent.putExtra("actors", movieArrayList.get(position).getActors());
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Movie>> call, @NonNull Throwable t) {

            }
        });
    }
}
