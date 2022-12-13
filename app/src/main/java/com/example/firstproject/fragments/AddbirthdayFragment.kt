package com.example.firstproject.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firstproject.R
import com.example.firstproject.model.Personne
import com.example.firstproject.data.PersonneViewModel
import com.example.firstproject.databinding.FragmentAddbirthdayBinding
import java.text.SimpleDateFormat
import java.util.*

class AddbirthdayFragment : Fragment() {

    lateinit var binding: FragmentAddbirthdayBinding
    lateinit var mPersonneViewModel: PersonneViewModel

    var formatDate = SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddbirthdayBinding.inflate(inflater, container, false)

        mPersonneViewModel = ViewModelProvider(this).get(PersonneViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSelectDate.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(view.context, android.R.style.Theme_Holo_InputMethod,
                { datePicker, year, month, dayOfMonth ->

                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, month)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = formatDate.format(selectDate.time)
                    Toast.makeText(view.context, "Date : "+date, Toast.LENGTH_LONG).show()
                    binding.textViewShowDate.text=date

                },getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        binding.floatingActionButtonAddPersonne.setOnClickListener {
            val prenom = binding.editTextTextPersonPrenom.text.toString()
            val nom = binding.editTextTextPersonName.text.toString()
            val anniversaire = binding.textViewShowDate.text.toString()

            if(inputCheck(prenom, nom, anniversaire)){
                val personne = Personne(0,prenom, nom, anniversaire)
                mPersonneViewModel.addPersonne(personne)
                Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.action_addbirthdayFragment_to_homeFragment2)
            }else{
                Toast.makeText(requireContext(), "Enter all fields correctly", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun inputCheck(nom: String, prenom: String, anniversaire: String): Boolean{
        return !(TextUtils.isEmpty(nom) || TextUtils.isEmpty(anniversaire) || TextUtils.isEmpty(prenom))
    }
}