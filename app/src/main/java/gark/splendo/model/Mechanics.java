package gark.splendo.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Mechanics extends RealmObject {
    @SerializedName("name")
    String mNames;
}
