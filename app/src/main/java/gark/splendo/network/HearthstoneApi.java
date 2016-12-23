package gark.splendo.network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit interface for HearthstoneApi
 */
interface HearthstoneApi {

//    https://omgvamp-hearthstone-v1.p.mashape.com/cards/qualities/{quality}

    @GET("/cards/qualities/Legendary")
    Call<Object> cards();
}
