package gark.splendo.cardlist;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.model.Card;
import gark.splendo.model.mapper.CardsMapper;
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
    private final ExecutorService mExecutorService;
    private final CardsMapper mDeathRattleMapper;

    public CardListPresenterImpl(final CardRepository cardRepository, final NetworkManager networkManager
            , final ExecutorService executorService, CardsMapper deathrattleMapper) {
        mCardRepository = cardRepository;
        mCardRepository.setCallback(this);
        mExecutorService = executorService;
        mDeathRattleMapper = deathrattleMapper;
        mNetworkManager = networkManager;
    }

    @Override
    public void requestCards() {
        mExecutorService.execute(new CardRequestRunnable());
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

    private class CardRequestRunnable implements Runnable {

        @Override
        public void run() {
            try {
                final List<Card> cards = mNetworkManager.requestCards();
                final List<Card> filteredCards = mDeathRattleMapper.mapDeathRattleCards(cards);
                mCardRepository.saveCards(filteredCards);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
