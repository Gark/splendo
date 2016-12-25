package gark.splendo.cardlist;

import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.mvp.Presenter;

/**
 *
 */
public interface CardListPresenter extends Presenter<CardListView> {

    /**
     *
     */
    void requestCards();

    /**
     * @param mPosition
     */
    void openDetailScreen(int mPosition);
}
