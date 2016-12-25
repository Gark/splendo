package gark.splendo.network.retrofit;

import java.util.List;

import gark.splendo.model.Card;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Retrofit interface for HearthstoneApi
 * https://omgvamp-hearthstone-v1.p.mashape.com/cards/qualities/{quality}
 */
public interface HearthstoneApi {

    @GET("/cards/qualities/Legendary")
    Call<List<Card>> cards(@Header("X-Mashape-Key") String token);
}
