package com.example.mvvm_livedata_room_navigation_components_note_app.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.mvvm_livedata_room_navigation_components_note_app.R
import com.example.mvvm_livedata_room_navigation_components_note_app.ViewModel.NotesViewModel
import com.example.mvvm_livedata_room_navigation_components_note_app.databinding.FragmentCreateNotesBinding
import com.example.mvvm_livedata_room_navigation_components_note_app.Model.Notes
import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priorety:String ="1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_white)

        binding.pGreen.setOnClickListener{
            priorety="1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_white)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener{
            priorety="2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_white)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.pRed.setOnClickListener{
            priorety="3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_white)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }


        binding.btnSaveNote.setOnClickListener{
            createNotes(it)
        }

        return binding.root
    }
    private fun createNotes(it : View?){
        val title= binding.edtTitle.text.toString()
        val subTitle=binding.edtSubtitle.text.toString()
        val notes=binding.edtNotes.text.toString()

        val d = Date()
        val notesDate:CharSequence=DateFormat.format("MMMM d, yyyy  ",d.time)

        val data=Notes(null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priorety
            )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes Added Successfully..",Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}