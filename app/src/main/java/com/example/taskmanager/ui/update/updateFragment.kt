package com.example.taskmanager.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.database.TaskEntry
import com.example.taskmanager.databinding.FragmentUpdateBinding
import com.example.taskmanager.viewmodel.TaskViewModel


class updateFragment : Fragment() {
    private val viewModel:TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentUpdateBinding.inflate(inflater)

        val args=updateFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            updateEditTask.setText(args.taskEntry.title)
            updateSpinner.setSelection(args.taskEntry.priority)

            btnUpdate.setOnClickListener{
                if(TextUtils.isEmpty(updateEditTask.text)){
                    Toast.makeText(requireContext(), "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val task_str=updateEditTask.text
                val priority=updateSpinner.selectedItemPosition

                val taskEntry=TaskEntry(
                    args.taskEntry.id,
                    task_str.toString(),
                    priority,
                    args.taskEntry.timestamp
                )

                viewModel.update(taskEntry)
                Toast.makeText(requireContext(), "Updated!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_taskFragment3)
            }
        }
        return binding.root
    }


}