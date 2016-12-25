package gark.splendo.cardlist.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import gark.splendo.R;
import gark.splendo.cardlist.CardListPresenter;
import gark.splendo.cardlist.CardListPresenterImpl;
import gark.splendo.mvp.PresenterActivity;
import gark.splendo.model.Card;

public class MainActivity extends PresenterActivity<CardListPresenter> implements CardListView {

    private CardsAdapter mAdapter;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progress);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cards_list);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CardsAdapter(this, mPresenter);
        recyclerView.setAdapter(mAdapter);

        mPresenter.requestCards();
    }

    @Override
    protected CardListPresenter onCreatePresenter() {
        return new CardListPresenterImpl();
    }

    @Override
    public void onCardsLoaded(final List<Card> cards) {
        mAdapter.updateCardsList(cards);
        mProgress.setVisibility(mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onCardsLoadingError() {
        Toast.makeText(this, R.string.network_operation_error, Toast.LENGTH_SHORT).show();
    }
}
