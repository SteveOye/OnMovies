package ng.com.onmovies;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://gist.githubusercontent.com/saniyusuf/";
    private static RetrofitClient mInstance = new RetrofitClient();
    private Retrofit retrofit;

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    private RetrofitClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build());
        retrofit = builder.build();
    }

    public MovieApi getMovieApi() {
        return retrofit.create(MovieApi.class);
    }
}

