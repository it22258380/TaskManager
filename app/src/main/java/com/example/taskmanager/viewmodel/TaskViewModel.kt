package com.example.taskmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.database.TaskDatabase
import com.example.taskmanager.database.TaskEntry
import com.example.taskmanager.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {

    private val taskDAO=TaskDatabase.getDatabase(application).taskDao()
    private val repository:TaskRepository

       val getAllTasks:LiveData<List<TaskEntry>>
           init{
               repository= TaskRepository(taskDAO)
               getAllTasks=repository.getAllTasks()
           }
        fun insert(taskEntry: TaskEntry){
            viewModelScope.launch(Dispatchers.IO){
                repository.insert(taskEntry)
            }
        }

        fun delete(taskEntry: TaskEntry){
            viewModelScope.launch(Dispatchers.IO){
                repository.deleteItem(taskEntry)
            }
        }

        fun update(taskEntry: TaskEntry){
            viewModelScope.launch(Dispatchers.IO){
                repository.updateData(taskEntry)
            }
        }

        fun deleteAll(){
            viewModelScope.launch(Dispatchers.IO){
                repository.deleteAll()
            }
        }



}