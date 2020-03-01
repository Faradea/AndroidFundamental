package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tasksRecyclerViewAdapter: TasksRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tasksRecyclerViewAdapter = TasksRecyclerViewAdapter()
        tasksRecyclerViewAdapter.onItemClickListener = {
            Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
        }
        tasksRv.adapter = tasksRecyclerViewAdapter
        tasksRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        tasksRecyclerViewAdapter.tasks = getTasks()
    }

    private fun getTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val task1 = Task("Task1", false)
        tasks.add(task1)
        tasks.add(Task("Task2", true))
        tasks.add(Task("Task3", false))
        return tasks
    }
}

data class Task(
    val text: String,
    val isDone: Boolean
)