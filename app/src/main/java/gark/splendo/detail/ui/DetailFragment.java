package gark.splendo.detail.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gark.splendo.R;
import gark.splendo.model.Card;

/**
 * Fragment display detail card data.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    /**
     * Register a callback to be invoked when this card is marked like favourite.
     */
    public interface Callback {
        void onFavouriteClicked(final String cardId);
    }

    public static DetailFragment newInstance(final Card card, final int position) {
        final DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(CARD_KEY, card);
        args.putInt(POSITION_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    private static final String CARD_KEY = "CARD_KEY";
    private static final String POSITION_KEY = "POSITION_KEY";

    private ImageView mImage;
    private TextView mPosition;
    private TextView mName;
    private TextView mDescription;
    private ImageView mFavourite;
    private Callback mCallback;
    private Card mCard;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new IllegalStateException("must implements Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.card_detail_layout, container, false);
        mImage = (ImageView) view.findViewById(R.id.detail_icon);
        mPosition = (TextView) view.findViewById(R.id.detail_position);
        mName = (TextView) view.findViewById(R.id.detail_name);
        mDescription = (TextView) view.findViewById(R.id.detail_description);
        mFavourite = (ImageView) view.findViewById(R.id.detail_favourite);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCard = getArguments().getParcelable(CARD_KEY);
        final int position = getArguments().getInt(POSITION_KEY);
        if (mCard == null) return;

        setFavouriteIcon(mCard.mFavorite);

        mFavourite.setOnClickListener(this);

        mPosition.setText(String.valueOf(position));
        mName.setText(mCard.mName);
        final String text = mCard.mText == null ? "" : mCard.mText;
        mDescription.setText(Html.fromHtml(text));

        Picasso.with(getActivity())
                .load(mCard.mImage)
                .error(R.drawable.ic_photo_black_24dp)
                .placeholder(R.drawable.ic_photo_black_24dp)
                .into(mImage);
    }

    private void setFavouriteIcon(final boolean isFavourite) {
        mFavourite.setImageResource(isFavourite ? R.drawable.ic_star_yellow_24dp : R.drawable.ic_star_border_black_24dp);
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            setFavouriteIcon(!mCard.mFavorite);
            mCallback.onFavouriteClicked(mCard.mCardId);
        }
    }
}
