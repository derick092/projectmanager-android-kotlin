package ludwig.thales.appsenac

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val projects: ArrayList<Project> = arrayListOf()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProjectAdapter
    private var layoutManager = GridLayoutManager(this@MainActivity, StaggeredGridLayoutManager.VERTICAL)
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        projects.clear()
        realm = Realm.getDefaultInstance()
        recyclerViewInitializer()
        loadProjectsFromRealm()
    }

    private fun loadProjectsFromRealm(){
        val allProjects = realm.where(Project::class.java).findAll()
        allProjects.forEach {
            projects.add(it)
        }
    }

    private fun recyclerViewInitializer(){
        recyclerView = findViewById(R.id.projectRecyclerView)
        adapter = ProjectAdapter(projects, this@MainActivity, realm)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    fun goToNewProject(view: View) {
        startActivity(Intent(this, ProjectActivity::class.java))
    }

}