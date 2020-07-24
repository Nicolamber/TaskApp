package nico.lambertucci.mytodoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.utils.FavItemListener

class TaskAdapter(
    private val taskList: List<Task>,
    private val listener: FavItemListener
): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.task_item,parent,false), listener)
    }

    override fun getItemCount(): Int {
       return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = taskList[position]
        holder.bindUI(item)
    }

    class ViewHolder(itemView: View, private var listener: FavItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val taskName: TextView = itemView.findViewById(R.id.taskTitle)
        private val isTaskFav: ImageView = itemView.findViewById(R.id.taskFavorite)
        private var taskId: Int = 0
        init {
            itemView.setOnClickListener(this)
        }
        fun bindUI(item: Task){
            taskName.text = item.taskName
            if(item.isFavorite){
                isTaskFav.setImageResource(R.drawable.filled_star)
            }else{
                isTaskFav.setImageResource(R.drawable.empty_star)
            }
            taskId = item.taskId!!
        }
        override fun onClick(view: View?) {
            this.listener.onClick(adapterPosition,taskId)
        }
    }
}