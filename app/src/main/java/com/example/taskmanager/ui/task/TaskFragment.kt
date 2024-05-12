package com.example.taskmanager.ui.task
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar

class TaskFragment : Fragment() {
    private val viewModel:TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentTaskBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel


        adapter= TaskAdapter(TaskClickListener { taskEntry ->
            findNavController().navigate(TaskFragmentDirections.actionTaskFragmentToUpdateFragment(taskEntry))
        })
        viewModel.getAllTasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.apply {
            binding.recyclerView.adapter=adapter
            floatingActionButton2.setOnClickListener{
                findNavController().navigate(R.id.action_taskFragment_to_addFragment)
            }
        }

        ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position=viewHolder.absoluteAdapterPosition
                val taskEntry=adapter.currentList[position]
                viewModel.delete(taskEntry)

                Snackbar.make(binding.root,"Deleted",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.insert(taskEntry)
                    }
                    show()
                }
            }

        }).attachToRecyclerView(binding.recyclerView)

        return binding.root
    }
}
