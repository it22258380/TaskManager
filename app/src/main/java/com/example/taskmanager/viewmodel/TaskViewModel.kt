package com.example.taskmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.taskmanager.database.TaskDatabase

class TaskViewModel(application: Application):AndroidViewModel(application) {

    private val taskDAO=TaskDatabase.getDatabase(application).taskDao()
    private val repository
}