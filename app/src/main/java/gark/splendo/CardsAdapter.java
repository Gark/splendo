package gark.splendo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<String> mCards = new ArrayList<>();

    CardsAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void updateCardsList(final List<String> cards) {
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
        holder.displayData(mCards.get(position));
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        CardViewHolder(View itemView) {
            super(itemView);
        }

        void displayData(String s) {

        }
    }
}
