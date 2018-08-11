package ludwig.thales.appsenac

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Project(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var desc: String = ""
): RealmObject()