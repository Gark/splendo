package gark.splendo.network;


import android.support.annotation.WorkerThread;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import gark.splendo.model.Card;
import gark.splendo.network.retrofit.HearthstoneApi;
import gark.splendo.network.retrofit.RetrofitService;
import gark.splendo.repo.CardRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation {@link NetworkManager} interface.
 * Based on Retrofit network engine.
 */
public class NetworkManagerImpl implements NetworkManager {

    private static final String MASHAPE_KEY = "tt9izL4ylUmshTdwUb2vKZoxhC5Kp1e7XH2jsn9JE96tX3TjUC";

    private final Call<List<Card>> mCall;

    public NetworkManagerImpl() {
        final HearthstoneApi api = new RetrofitService().createApi();
        mCall = api.cards(MASHAPE_KEY);
    }

    @WorkerThread
    @Override
    public List<Card> requestCards() throws IOException {
        final Response<List<Card>> response = mCall.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Network operation error");
        }
    }
}
