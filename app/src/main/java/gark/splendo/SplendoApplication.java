package gark.splendo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class SplendoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());

        // For migration.
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config);
        realm.close();

    }
}
