package ludwig.thales.appsenac

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.Realm

class ProjectAdapter(
    private val projects: ArrayList<Project>,
    private val context: Context,
    private val realm: Realm) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>()
{

    private fun removeProject(position: Int, id: Int){
        projects.removeAt(position)
        realm.beginTransaction()
        val result = realm.where(Project::class.java)
                .equalTo("id", id)
                .findAll()
        result.deleteAllFromRealm()
        realm.commitTransaction()
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_project, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val comic = projects[position]
        holder.bindView(comic)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Project) {
            val nameTextView = itemView.findViewById<TextView>(R.id.cardviewProjectName)
            nameTextView.text = item.name

            val descTextView = itemView.findViewById<TextView>(R.id.cardviewProjectDesc)
            descTextView.text = item.desc
        }
    }

}