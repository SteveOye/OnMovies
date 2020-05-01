package ng.com.onmovies;

import android.content.Context;
import android.content.pm.ModuleInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context context;
    private ArrayList<Movie> moviesList;
    private OnItemClickListener mListener;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieTitle, moviePlot, releaseDate, duration, rate;
        CardView movieItem;

        MyViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            movieImage = view.findViewById(R.id.poster);
            movieTitle = view.findViewById(R.id.title);
            moviePlot = view.findViewById(R.id.plot);
            duration = view.findViewById(R.id.duration);
            releaseDate = view.findViewById(R.id.release_date);
            rate = view.findViewById(R.id.rating);
            movieItem = view.findViewById(R.id.container);

            movieItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.movieItem(position);
                        }
                    }
                }
            });
        }
    }

    public MovieAdapter(Context context, ArrayList<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items, parent, false);

        return new MyViewHolder(itemView, mListener);
    }

    public void setOnItemClickListener(MovieAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void movieItem(int position);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
//
//        if (movie.getImages().get(0) != null)
        Picasso.get()
                .load(movie.getPoster())
                .into(holder.movieImage);
        holder.movieTitle.setText(movie.getTitle());
        holder.moviePlot.setText(movie.getPlot());
        holder.releaseDate.setText(movie.getReleased());
        holder.duration.setText(movie.getRuntime());
        holder.rate.setText(movie.getImdbRating());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
