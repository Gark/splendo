package gark.splendo.detail.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.List;

import gark.splendo.R;
import gark.splendo.detail.CardDetailPresenter;
import gark.splendo.detail.CardDetailPresenterImpl;
import gark.splendo.model.Card;
import gark.splendo.mvp.PresenterActivity;


public class CardDetailActivity extends PresenterActivity<CardDetailPresenter> implements CardDetailView, DetailFragment.Callback {

    private static final String POSITION_KEY = "POSITION_KEY";

    private CardsPagerAdapter mAdapter;
    private ViewPager mViewPager;

    public static void start(final Activity activity, final int position) {
        final Intent intent = new Intent(activity, CardDetailActivity.class);
        intent.putExtra(POSITION_KEY, position);
        activity.startActivity(intent);
    }

    @Override
    protected CardDetailPresenter onCreatePresenter() {
        return new CardDetailPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void onCardsLoaded(List<Card> cards) {
        if (mAdapter == null) {
            mAdapter = new CardsPagerAdapter(getSupportFragmentManager(), cards);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setCurrentItem(getIntent().getIntExtra(POSITION_KEY, 0));
        }
    }

    @Override
    public void onFavouritePressed(String cardId) {
        mPresenter.toggleFavouriteCardState(cardId);
    }
}
