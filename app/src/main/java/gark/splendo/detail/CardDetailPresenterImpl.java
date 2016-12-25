package gark.splendo.detail;

import java.util.List;

import gark.splendo.detail.ui.CardDetailView;
import gark.splendo.model.Card;
import gark.splendo.mvp.BasePresenter;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.CardRepositoryImpl;

public class CardDetailPresenterImpl extends BasePresenter<CardDetailView>
        implements CardDetailPresenter, CardRepositoryImpl.Callback {

    private final CardRepository mCardRepository;

    public CardDetailPresenterImpl() {
        mCardRepository = new CardRepositoryImpl(this);
        mCardRepository.requestCards();
    }

    @Override
    public void onCardListChanged(List<Card> list) {
        if (mView != null) {
            mView.onCardsLoaded(list);
        }
    }

    @Override
    public void toggleFavouriteCardState(final String cardId) {
        mCardRepository.toggleFavouriteState(cardId);
    }
}
