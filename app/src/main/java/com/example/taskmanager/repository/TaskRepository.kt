package com.example.taskmanager.repository

import androidx.lifecycle.LiveData
import com.example.taskmanager.database.TaskDAO
import com.example.taskmanager.database.TaskEntry

class TaskRepository(val taskDAO: TaskDAO) {
    suspend fun insert(taskEntry: TaskEntry)=taskDAO.insert(taskEntry)

    suspend fun updateData(taskEntry: TaskEntry)=taskDAO.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry)=taskDAO.delete(taskEntry)



    fun getAllTasks() : LiveData<List<TaskEntry>> = taskDAO.getAllTasks()



}