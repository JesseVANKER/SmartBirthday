package com.example.firstproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firstproject.R
import com.example.firstproject.data.PersonneViewModel
import com.example.firstproject.model.Personne
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var mUserViewModel: PersonneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(PersonneViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prenom = view.findViewById<TextView>(R.id.editTextTextPersonPrenom)
        val nom = view.findViewById<TextView>(R.id.editTextTextPersonName)
        val anniversaire = view.findViewById<TextView>(R.id.textViewShowDate)

        prenom.text = args.currentPersonne.prenom
        nom.text = args.currentPersonne.nom
        anniversaire.text = args.currentPersonne.anniversaire

        val updateButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddPersonne)
        updateButton.setOnClickListener {
            updateItem(prenom.text.toString(), nom.text.toString(), anniversaire.text.toString())
        }
    }

    private fun updateItem(prenom : String, nom : String, anniversaire: String){
        if(inputCheck(prenom, nom, anniversaire)){
            val updatePersonne = Personne(args.currentPersonne.id, prenom,nom, anniversaire)
            mUserViewModel.updatePersonne(updatePersonne)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(), "Enter all fields correctly", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(nom: String, prenom: String, anniversaire: String): Boolean{
        return !(TextUtils.isEmpty(nom) || TextUtils.isEmpty(anniversaire) || TextUtils.isEmpty(prenom))
    }

}