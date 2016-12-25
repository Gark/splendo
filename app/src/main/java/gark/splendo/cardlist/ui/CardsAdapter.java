package gark.splendo.cardlist.ui;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import gark.splendo.R;
import gark.splendo.cardlist.CardListPresenter;
import gark.splendo.model.Card;

class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<Card> mCards = new ArrayList<>();
    private final Picasso mPicasso;
    private final CardListPresenter mPresenter;

    CardsAdapter(final Context context, final CardListPresenter presenter) {
        mPicasso = Picasso.with(context);
        mPresenter = presenter;
        mLayoutInflater = LayoutInflater.from(context);
    }

    void updateCardsList(final List<Card> cards) {
        mCards.clear();
        mCards.addAll(cards);
        notifyDataSetChanged();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mLayoutInflater.inflate(R.layout.card_iem, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.displayData(mCards.get(position), position);
    }

    @Override
    public void onViewRecycled(CardViewHolder holder) {
        super.onViewRecycled(holder);
        mPicasso.cancelRequest(holder.mCardImage);
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView mCardImage;
        private final TextView mCardText;
        private int mPosition;

        CardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mCardImage = (ImageView) itemView.findViewById(R.id.card_image);
            mCardText = (TextView) itemView.findViewById(R.id.card_name);
        }

        void displayData(final Card card, final int position) {
            mCardText.setText(card.mName);
            mPosition = position;

            mPicasso.load(card.mImage)
                    .error(R.drawable.ic_photo_black_24dp)
                    .placeholder(R.drawable.ic_photo_black_24dp)
                    .into(mCardImage);
        }

        @Override
        public void onClick(View v) {
            mPresenter.openDetailScreen(mPosition);
        }
    }
}
