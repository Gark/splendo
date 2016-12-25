package gark.splendo.detail.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import gark.splendo.R;
import gark.splendo.detail.CardDetailPresenterImpl;
import gark.splendo.mvp.Presenter;
import gark.splendo.mvp.PresenterActivity;


public class CardDetailActivity extends PresenterActivity implements CardDetailView {

    private static final String POSITION_KEY = "POSITION_KEY";

    private ViewPager mViewPager;

    public static void start(final Activity activity, final int position) {
        final Intent intent = new Intent(activity, CardDetailActivity.class);
        intent.putExtra(POSITION_KEY, position);
        activity.startActivity(intent);
    }

    @Override
    protected Presenter onCreatePresenter() {
        return new CardDetailPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        final int position = getIntent().getIntExtra(POSITION_KEY, 0);
    }
}
