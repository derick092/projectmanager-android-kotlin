package ludwig.thales.appsenac

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        realm = Realm.getDefaultInstance()
    }

    fun createProject(view: View) {
        val realmId = realm.where(Project::class.java).max("id")
        val nextId: Int

        nextId = if(realmId==null){
            0
        } else {
            realmId.toInt() + 1
        }

        var name: String = editTextName.text.toString()
        var desc: String = editTextDesc.text.toString()

        realm.beginTransaction()
        val project: Project = realm.createObject(Project::class.java, nextId)
        project.name = name
        project.desc = desc
        realm.commitTransaction()

        finish()
    }
}
