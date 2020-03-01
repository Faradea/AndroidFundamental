package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_task.view.*

class TasksRecyclerViewAdapter : RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder>() {

    var tasks: List<Task> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((task: Task) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view =  LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_task, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.count()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private lateinit var item: Task

        init {
            containerView.taskCl.setOnClickListener { onItemClickListener?.invoke(item) }
        }

        fun bind(task: Task) {
            this.item = task
            containerView.taskTv.text = item.text
            containerView.taskChb.isChecked = item.isDone
        }


    }

}