package gark.splendo.model;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Card plain object.
 * converted from retrofit response to class.
 */

public class Card extends RealmObject {

    @PrimaryKey
    @SerializedName("cardId")
    String mCardId;

    @SerializedName("name")
    public String mName;

    @SerializedName("type")
    String mType;

    @SerializedName("rarity")
    String mRarity;

    @SerializedName("text")
    String mText;

    @SerializedName("img")
    public String mImage;

    @SerializedName("imgGold")
    public String mGif;

    @SerializedName("mechanics")
    RealmList<Mechanics> mMechanics;
}

//public class Mechanics extends RealmObject {
//    @SerializedName("name")
//    String mNames;
//}

//"cardId": "BRMA14_3",
//        "name": "Arcanotron",
//        "cardSet": "Blackrock Mountain",
//        "type": "Minion",
//        "rarity": "Legendary",
//        "cost": 0,
//        "attack": 2,
//        "health": 2,
//        "text": "Both players have <b>Spell Damage +2</b>.",
//        "elite": true,
//        "race": "Mech",
//        "playerClass": "Neutral",
//        "img": "http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRMA14_3.png",
//        "imgGold": "http://wow.zamimg.com/images/hearthstone/cards/enus/animated/BRMA14_3_premium.gif",
//        "locale": "enUS",
//        "mechanics": [
//        {
//        "name": "Spell Damage"
//        }
//        ]