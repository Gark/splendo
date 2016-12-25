package gark.splendo.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Card plain object.
 * converted from retrofit response to class.
 */

public class Card extends RealmObject implements Parcelable {

    @PrimaryKey
    @SerializedName("cardId")
    String mCardId;

    @SerializedName("name")
    public String mName;

    @SerializedName("text")
    public String mText;

    @SerializedName("img")
    public String mImage;

    @SerializedName("mechanics")
    RealmList<Mechanics> mMechanics;


    public Card() {

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