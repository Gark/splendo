package gark.splendo.detail.ui;

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


public class DetailFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.card_detail_layout, container, false);
        mImage = (ImageView) view.findViewById(R.id.detail_icon);
        mPosition = (TextView) view.findViewById(R.id.detail_position);
        mName = (TextView) view.findViewById(R.id.detail_name);
        mDescription = (TextView) view.findViewById(R.id.detail_description);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Card card = getArguments().getParcelable(CARD_KEY);
        final int position = getArguments().getInt(POSITION_KEY);
        if (card == null) return;

        mPosition.setText(String.valueOf(position));
        mName.setText(card.mName);
        final String text = card.mText == null ? "" : card.mText;
        mDescription.setText(Html.fromHtml(text));

        Picasso.with(getActivity())
                .load(card.mImage)
                .error(R.drawable.ic_photo_black_24dp)
                .placeholder(R.drawable.ic_photo_black_24dp)
                .into(mImage);

    }
}
