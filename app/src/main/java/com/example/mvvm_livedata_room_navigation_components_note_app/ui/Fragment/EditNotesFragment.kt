package com.example.mvvm_livedata_room_navigation_components_note_app.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mvvm_livedata_room_navigation_components_note_app.Model.Notes
import com.example.mvvm_livedata_room_navigation_components_note_app.R
import com.example.mvvm_livedata_room_navigation_components_note_app.ViewModel.NotesViewModel
import com.example.mvvm_livedata_room_navigation_components_note_app.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding :FragmentEditNotesBinding
    var priorety:String ="1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubtitle.setText(oldNotes.data.subTitle)
        binding.edtNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority){
            "1" ->{
                priorety="1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_white)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priorety="2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_white)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                priorety="3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_white)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }


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






        binding.btnEditSaveNote.setOnClickListener{
            updateNotes(it)
        }

        return binding.root
    }


    private fun updateNotes(it:View?){
        val title= binding.edtTitle.text.toString()
        val subTitle=binding.edtSubtitle.text.toString()
        val notes=binding.edtNotes.text.toString()

        val d = Date()
        val notesDate:CharSequence= DateFormat.format("MMMM d, yyyy  ",d.time)

        val data= Notes(oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString (),
            priorety
        )
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Notes Updated Successfully..", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete)
        {
             val bottomSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val txtYes=bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val txtNo=bottomSheet.findViewById<TextView>(R.id.dialog_no)

            txtYes?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                getActivity()?.onBackPressed();
            }
            txtNo?.setOnClickListener{
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}