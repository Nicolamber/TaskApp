package nico.lambertucci.mytodoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.utils.FavItemListener

class FavoritesAdapter(
    private val favList: List<Task>,
    private val listener: FavItemListener
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.task_item, parent, false),
            listener
        )
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favList[position]
        holder.taskName.text = item.taskName
        holder.isTaskFav.setImageResource(R.drawable.filled_star)

    }

    class ViewHolder(itemView: View, private var listener: FavItemListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val taskName: TextView = itemView.findViewById(R.id.taskTitle)
        val isTaskFav: ImageView = itemView.findViewById(R.id.taskFavorite)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            this.listener.onClick(adapterPosition)
        }
    }
}