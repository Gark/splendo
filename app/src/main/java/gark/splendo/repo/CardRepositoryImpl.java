package gark.splendo.repo;

import java.util.ArrayList;
import java.util.List;

import gark.splendo.model.Card;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

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

    @Override
    public void saveCards(final List<Card> cards) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Card item : cards) {
                    final Card card = realm.where(Card.class).equalTo("mCardId", item.mCardId).findFirst();
                    if (card == null) {
                        realm.insert(item);
                    }
                }
            }
        });
    }

    @Override
    public void toggleFavouriteState(final String cardId) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Card card = realm.where(Card.class).equalTo("mCardId", cardId).findFirst();
                card.mFavorite = !card.mFavorite;
            }
        });
    }
}
