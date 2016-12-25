package gark.splendo.detail.ui;

import java.util.List;

import gark.splendo.model.Card;
import gark.splendo.mvp.PresenterView;


public interface CardDetailView extends PresenterView {

    void onCardsLoaded(final List<Card> cards);
}
