package gark.splendo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import gark.splendo.cardlist.CardListPresenter;
import gark.splendo.cardlist.CardListPresenterImpl;
import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.model.Card;
import gark.splendo.model.mapper.CardsMapper;
import gark.splendo.network.NetworkManager;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.RepositoryCallback;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Covers the test scenarios for class {@link gark.splendo.cardlist.CardListPresenter}
 */

@RunWith(MockitoJUnitRunner.class)
public class CardListPresenterTest {

    @Mock
    private CardRepository mCardRepository;
    @Mock
    private NetworkManager mNetworkManager;
    @Mock
    private CardListView mView;
    @Mock
    private CardsMapper mMapper;

    private CardListPresenter mPresenter;

    private ArrayList<Card> mCardList;

    @Before
    public void setUp() {
        mCardList = new ArrayList<>();
        mCardList.add(new Card());

        ExecutorServiceSameThread sameThreadExecutor = new ExecutorServiceSameThread();
        mPresenter = new CardListPresenterImpl(mCardRepository, mNetworkManager, sameThreadExecutor, mMapper);
        mPresenter.onAttach(mView);
    }

    /**
     * action: {@code CardDetailPresenter} init
     * assert: Callback is set.
     * assert: request card list from local storage executed.
     */
    @Test
    public void testOnCardListPresenterInitialization() {
        verify(mCardRepository).setCallback((RepositoryCallback) mPresenter);
        verify(mCardRepository).requestCards();
    }

    /**
     * action: request card.
     * assert: {@link NetworkManager} "requestCards" is been executed.
     */
    @Test
    public void testRequestCardFromNetwork() throws IOException {
        when(mNetworkManager.requestCards()).thenReturn(mCardList);
        when(mMapper.mapDeathRattleCards(mCardList)).thenReturn(mCardList);

        mPresenter.requestCards();
        verify(mNetworkManager).requestCards();
        verify(mMapper).mapDeathRattleCards(mCardList);
        verify(mCardRepository).saveCards(mCardList);
    }

    /**
     * action: network request fails with exception
     *
     * @throws IOException exception.
     */
    @Test
    public void testRequestCardsNetworkException() throws IOException {
        when(mNetworkManager.requestCards()).thenThrow(new IOException());
        mPresenter.requestCards();

        verify(mMapper, never()).mapDeathRattleCards(mCardList);
        verify(mCardRepository, never()).saveCards(mCardList);
        verify(mView).notifyCardRequestError();
    }

    /**
     * action: onCardListChanged callback triggered
     * assert: onCardsLoaded method from view called.
     */
    @Test
    public void testOnDataFromLocalStorageCome() {
        RepositoryCallback callback = ((RepositoryCallback) mPresenter);

        callback.onCardListChanged(mCardList);

        verify(mView).onCardsLoaded(mCardList);
    }

    /**
     * action: Card item selected.
     * assert: view navigates to detail screen.
     */
    @Test
    public void testOnCardItemPressed() {
        mPresenter.openDetailScreen(27);
        verify(mView).navigateToDetailScreen(27);
    }
}