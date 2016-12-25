package gark.splendo.cardlist;

import java.util.List;

import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.detail.ui.CardDetailActivity;
import gark.splendo.model.Card;
import gark.splendo.mvp.BasePresenter;
import gark.splendo.network.NetworkManager;
import gark.splendo.network.NetworkManagerImpl;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.CardRepositoryImpl;

public class CardListPresenterImpl extends BasePresenter<CardListView>
        implements CardListPresenter, CardRepositoryImpl.Callback, NetworkManagerImpl.NetworkCallback {

    private final NetworkManager mNetworkManager;
    private final CardRepository mCardRepository;

    public CardListPresenterImpl() {
        mCardRepository = new CardRepositoryImpl(this);
        mNetworkManager = new NetworkManagerImpl(mCardRepository, this);
    }

    @Override
    public void requestCards() {
        mCardRepository.requestCards();
        mNetworkManager.requestCards();
    }

    @Override
    public void onCardSetChanged(List<Card> list) {
        notifyViewWithResult(list);
    }

    private void notifyViewWithResult(final List<Card> cards) {
        if (mView != null) {
            mView.onCardsLoaded(cards);
        }
    }

    @Override
    public void onNetworkError() {
        if (mView != null) {
            mView.onCardsLoadingError();
        }
    }

    @Override
    public void openDetailScreen(int position) {
        if (mView != null) {
            CardDetailActivity.start(mView.getActivity(), position);
        }
    }
}
