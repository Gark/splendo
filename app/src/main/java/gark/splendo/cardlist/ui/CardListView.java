package gark.splendo.cardlist.ui;

import java.util.List;

import gark.splendo.mvp.PresenterView;
import gark.splendo.model.Card;

public interface CardListView extends PresenterView {

    void onCardsLoaded(final List<Card> cards);

    void onCardsLoadingError();

}
