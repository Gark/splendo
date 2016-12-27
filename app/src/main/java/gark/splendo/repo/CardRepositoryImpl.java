package gark.splendo.repo;

import android.support.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.List;

import gark.splendo.model.Card;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Implementation of {@link CardRepository} interface.
 * Based on Realm database.
 */
public class CardRepositoryImpl implements CardRepository, RealmChangeListener<RealmResults<Card>> {

    private final Realm mRealm;
    private final RealmResults<Card> mCardRealmList;
    private RepositoryCallback mCallback;

    public CardRepositoryImpl() {
        mRealm = Realm.getDefaultInstance();
        mCardRealmList = mRealm.where(Card.class).findAllAsync();
    }

    @Override
    public void setCallback(final RepositoryCallback callback) {
        mCallback = callback;
    }

    @Override
    public void requestCards() {
        mCardRealmList.addChangeListener(this);
        onChange(mCardRealmList);
    }

    @Override
    public void onChange(RealmResults<Card> cards) {
        mCallback.onCardListChanged(new ArrayList<>(mCardRealmList));
    }

    @WorkerThread
    @Override
    public void saveCards(final List<Card> cards) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (Card item : cards) {
            final Card card = realm.where(Card.class).equalTo("mCardId", item.getCardId()).findFirst();
            if (card == null) {
                realm.insert(item);
            }
        }
        realm.commitTransaction();
    }

    @Override
    public void toggleFavouriteState(final String cardId) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Card card = realm.where(Card.class).equalTo("mCardId", cardId).findFirst();
                card.setFavorite(!card.isFavorite());
            }
        });
    }
}
