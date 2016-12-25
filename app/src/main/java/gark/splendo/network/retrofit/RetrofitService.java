package gark.splendo.network.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit network service.
 */

public class RetrofitService {

    private final String API_URL = "https://omgvamp-hearthstone-v1.p.mashape.com";
    private final Retrofit mRetrofit;

    public RetrofitService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public HearthstoneApi createApi() {
        return mRetrofit.create(HearthstoneApi.class);
    }

}
