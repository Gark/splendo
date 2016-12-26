package gark.splendo.cardlist;

import java.util.List;

import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.model.Card;
import gark.splendo.mvp.BasePresenter;
import gark.splendo.network.NetworkManager;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.RepositoryCallback;

/**
 * Card list Presenter Implementation.
 */
public class CardListPresenterImpl extends BasePresenter<CardListView>
        implements CardListPresenter, RepositoryCallback {

    private final NetworkManager mNetworkManager;
    private final CardRepository mCardRepository;

    public CardListPresenterImpl(final CardRepository cardRepository, final NetworkManager networkManager) {
        mCardRepository = cardRepository;
        mCardRepository.setCallback(this);

        mNetworkManager = networkManager;
    }

    @Override
    public void requestCards() {
        mNetworkManager.requestCards();
    }

    @Override
    public void onAttach(CardListView view) {
        super.onAttach(view);
        mCardRepository.requestCards();
    }

    @Override
    public void onCardListChanged(List<Card> list) {
        notifyViewWithResult(list);
    }

    private void notifyViewWithResult(final List<Card> cards) {
        if (mView != null) {
            mView.onCardsLoaded(cards);
        }
    }

    @Override
    public void openDetailScreen(int position) {
        if (mView != null) {
            mView.navigateToDetailScreen(position);
        }
    }
}
