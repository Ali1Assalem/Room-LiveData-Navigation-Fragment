package com.example.mvvm_livedata_room_navigation_components_note_app.ui.Fragment

import android.os.Binder
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvm_livedata_room_navigation_components_note_app.Model.Notes
import com.example.mvvm_livedata_room_navigation_components_note_app.R
import com.example.mvvm_livedata_room_navigation_components_note_app.ViewModel.NotesViewModel
import com.example.mvvm_livedata_room_navigation_components_note_app.databinding.FragmentHomeBinding
import com.example.mvvm_livedata_room_navigation_components_note_app.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var oldNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        val staggeredGridLayoutManager =StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recAllNotes.layoutManager = staggeredGridLayoutManager

        //get All notes
        viewModel.getNotes().observe(viewLifecycleOwner,{ noteList ->
            oldNotes = noteList as ArrayList<Notes>
            adapter= NotesAdapter(requireContext(),noteList)
            binding.recAllNotes.adapter=adapter
        })


        binding.filterTitleHigh.setOnClickListener{
            viewModel.getHighNotes().observe(viewLifecycleOwner,{ noteList ->
                oldNotes = noteList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(),noteList)
                binding.recAllNotes.adapter=adapter
            })

        }

        binding.filterTitleLow.setOnClickListener{
            viewModel.getLowNote().observe(viewLifecycleOwner,{ noteList ->
                oldNotes = noteList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(),noteList)
                binding.recAllNotes.adapter=adapter
            })

        }
        binding.filterTitleMedium .setOnClickListener{
            viewModel.getMediumNote().observe(viewLifecycleOwner,{ noteList ->
                oldNotes = noteList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(),noteList)
                binding.recAllNotes.adapter=adapter
            })

        }

        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        binding.allNotes.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner,{ noteList ->
                oldNotes = noteList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(),noteList)
                binding.recAllNotes.adapter=adapter
            })
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)

        val searchView = item.actionView as SearchView

        searchView.queryHint = "search note .."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                 return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                noteFiltering(p0)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun noteFiltering(p0 : String?){
        val newFilteredList = arrayListOf<Notes>()

        for (i in oldNotes) {
            if (i.title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}