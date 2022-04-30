package com.example.mvvm_livedata_room_navigation_components_note_app.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm_livedata_room_navigation_components_note_app.Database.NotesDatabase
import com.example.mvvm_livedata_room_navigation_components_note_app.Repository.NotesRepository
import com.example.mvvm_livedata_room_navigation_components_note_app.Model.Notes

class NotesViewModel(application: Application): AndroidViewModel (application) {

    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes:Notes)
    {
        repository.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun getHighNotes() : LiveData<List<Notes>>{
        return repository.getHighNote()
    }

    fun getMediumNote() : LiveData<List<Notes>>{
        return repository.getMediumNote()
    }

    fun getLowNote() : LiveData<List<Notes>>{
        return repository.getLowNote()
    }

    fun deleteNotes(id:Int)
    {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes)
    {
        repository.updateNotes(notes)
    }

}



