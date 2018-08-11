package ludwig.thales.appsenac

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

@Suppress("unused")
class AppsenacApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val newConfiguration: RealmConfiguration = RealmConfiguration.Builder().name("appsenac.realm").build()
        Realm.setDefaultConfiguration(newConfiguration)
    }

}