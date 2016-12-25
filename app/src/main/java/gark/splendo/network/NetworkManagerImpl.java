package gark.splendo.network;


import java.util.List;

import gark.splendo.model.Card;
import gark.splendo.network.retrofit.HearthstoneApi;
import gark.splendo.network.retrofit.RetrofitService;
import gark.splendo.repo.CardRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManagerImpl implements NetworkManager {

    public interface NetworkCallback {
        void onNetworkError();
    }

    private static final String MASHAPE_KEY = "tt9izL4ylUmshTdwUb2vKZoxhC5Kp1e7XH2jsn9JE96tX3TjUC";

    private final Call<List<Card>> mCall;
    private final CardRepository mCardRepository;
    private final NetworkCallback mCallback;

    public NetworkManagerImpl(final CardRepository cardRepository, final NetworkCallback callback) {
        mCardRepository = cardRepository;
        mCallback = callback;

        final HearthstoneApi api = new RetrofitService().createApi();
        mCall = api.cards(MASHAPE_KEY);
    }

    @Override
    public void requestCards() {
        // TODO switch to synch excecution.
        mCall.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response != null && response.isSuccessful()) {
                    mCardRepository.saveCards(response.body());
                } else {
                    mCallback.onNetworkError();
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                mCallback.onNetworkError();
            }
        });
    }
}
