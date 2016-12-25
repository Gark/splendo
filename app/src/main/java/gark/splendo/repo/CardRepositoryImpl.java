package gark.splendo.repo;

import java.util.ArrayList;
import java.util.List;

import gark.splendo.model.Card;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Java Doc here
 */
public class CardRepositoryImpl implements CardRepository, RealmChangeListener<RealmResults<Card>> {

    /**
     * Java Doc here
     */
    public interface Callback {
        void onCardSetChanged(final List<Card> list);
    }

    private final Realm mRealm;
    private final RealmResults<Card> mCardRealmList;
    private final Callback mCallback;

    public CardRepositoryImpl(final Callback callback) {
        mCallback = callback;
        mRealm = Realm.getDefaultInstance();
        mCardRealmList = mRealm.where(Card.class).findAllAsync();

    }

    @Override
    public void requestCards() {
        mCardRealmList.addChangeListener(this);
        onChange(mCardRealmList);
    }

    @Override
    public void onChange(RealmResults<Card> cards) {
        mCallback.onCardSetChanged(new ArrayList<>(mCardRealmList));
    }

    @Override
    public void saveCards(final List<Card> cards) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(cards);
            }
        });
    }
}
