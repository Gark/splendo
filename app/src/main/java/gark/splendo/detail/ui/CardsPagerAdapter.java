package gark.splendo.detail.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import gark.splendo.model.Card;

class CardsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Card> mCards;

    CardsPagerAdapter(final FragmentManager fm, final List<Card> cards) {
        super(fm);
        mCards = cards;
    }

    @Override
    public Fragment getItem(int position) {
        final Card card = mCards.get(position);
        return DetailFragment.newInstance(card, position);
    }

    @Override
    public int getCount() {
        return mCards.size();
    }
}
