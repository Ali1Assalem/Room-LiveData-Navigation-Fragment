package com.example.mvvm_livedata_room_navigation_components_note_app.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_livedata_room_navigation_components_note_app.R
import com.example.mvvm_livedata_room_navigation_components_note_app.databinding.ItemNoteBinding
import com.example.mvvm_livedata_room_navigation_components_note_app.Model.Notes
import com.example.mvvm_livedata_room_navigation_components_note_app.ui.Fragment.HomeFragmentDirections

class NotesAdapter (val requiContext: Context ,var notesList: List<Notes>): RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){


    fun filtering(newFilteredList:ArrayList<Notes>){
        notesList=newFilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text=data.title
        holder.binding.notesSubtitle.text=data.subTitle
        holder.binding.notesDate.text=data.date

        when (data.priority){
            "1" ->
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            "2" ->
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            "3" ->
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
        }

        holder.binding.root.setOnClickListener{
            val action= HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount()=notesList.size


}