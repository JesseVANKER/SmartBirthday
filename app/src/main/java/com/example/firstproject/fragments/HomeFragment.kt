package com.example.firstproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstproject.ListAdapter
import com.example.firstproject.data.PersonneViewModel
import com.example.firstproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var mPersonneViewModel: PersonneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        mPersonneViewModel = ViewModelProvider(this).get(PersonneViewModel::class.java)
        mPersonneViewModel.readAllData.observe(viewLifecycleOwner, Observer { personne ->
            adapter.setData(personne)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButtonAddBirthday.setOnClickListener{
            val addBirthdayDestination = HomeFragmentDirections.actionHomeFragmentToAddbirthdayFragment()

            it.findNavController().navigate(addBirthdayDestination)
        }
    }

}