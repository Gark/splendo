package gark.splendo.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Card plain object.
 * converted from retrofit response to class.
 */

public class Card extends RealmObject implements Parcelable {

    @PrimaryKey
    @SerializedName("cardId")
    private String mCardId;

    @SerializedName("name")
    private String mName;

    @SerializedName("text")
    private String mText;

    @SerializedName("img")
    private String mImage;

    private boolean mFavorite;

    @Ignore
    @SerializedName("mechanics")
    private List<Mechanics> mMechanics;

    public Card() {

    }

    public String getCardId() {
        return mCardId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getText() {
        return mText;
    }

    public String getImage() {
        return mImage;
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.mFavorite = favorite;
    }

    public List<Mechanics> getMechanics() {
        return mMechanics;
    }

    public void setMechanics(final List<Mechanics> mMechanics) {
        this.mMechanics = mMechanics;
    }

    protected Card(Parcel in) {
        mCardId = in.readString();
        mName = in.readString();
        mText = in.readString();
        mImage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCardId);
        dest.writeString(mName);
        dest.writeString(mText);
        dest.writeString(mImage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}