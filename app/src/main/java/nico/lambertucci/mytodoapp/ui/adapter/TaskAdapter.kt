package nico.lambertucci.mytodoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.domain.database.Task

class TaskAdapter(
    private val taskList: List<Task>
): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false))
    }

    override fun getItemCount(): Int {
       return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = taskList[position]
        holder.taskName.text = item.taskName
        holder.taskStatus.text = "Estado: ${item.taskStatus}"
        holder.taskSize.text = "Puntaje: ${item.taskSize}"
        if(item.isFavorite){
            holder.isTaskFav.setImageResource(R.drawable.filled_star)
        }else{
            holder.isTaskFav.setImageResource(R.drawable.empty_star)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.taskTitle)
        val taskStatus: TextView = itemView.findViewById(R.id.taskStatus)
        val taskSize: TextView = itemView.findViewById(R.id.taskSize)
        val isTaskFav: ImageView = itemView.findViewById(R.id.taskFavorite)
    }
}