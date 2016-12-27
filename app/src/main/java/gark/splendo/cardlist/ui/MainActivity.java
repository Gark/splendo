package gark.splendo.cardlist.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gark.splendo.R;
import gark.splendo.cardlist.CardListPresenter;
import gark.splendo.cardlist.CardListPresenterImpl;
import gark.splendo.detail.ui.CardDetailActivity;
import gark.splendo.model.mapper.CardsMapper;
import gark.splendo.model.mapper.CardsMapperImpl;
import gark.splendo.mvp.PresenterActivity;
import gark.splendo.model.Card;
import gark.splendo.network.NetworkManager;
import gark.splendo.network.NetworkManagerImpl;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.CardRepositoryImpl;

/**
 * Activity displays legendary card list.
 */
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
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final CardRepository cardRepository = new CardRepositoryImpl();
        final NetworkManager networkManager = new NetworkManagerImpl();
        final CardsMapper cardsMapper = new CardsMapperImpl();
        return new CardListPresenterImpl(cardRepository, networkManager, executorService, cardsMapper);
    }

    @Override
    public void onCardsLoaded(final List<Card> cards) {
        mAdapter.updateCardsList(cards);
        mProgress.setVisibility(mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void navigateToDetailScreen(int position) {
        CardDetailActivity.start(this, position);
    }
}
