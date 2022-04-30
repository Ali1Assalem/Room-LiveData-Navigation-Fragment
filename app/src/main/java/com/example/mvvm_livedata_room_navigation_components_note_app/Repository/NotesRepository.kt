package com.example.mvvm_livedata_room_navigation_components_note_app.Repository

import androidx.lifecycle.LiveData
import com.example.mvvm_livedata_room_navigation_components_note_app.Dao.NotesDao
import com.example.mvvm_livedata_room_navigation_components_note_app.Model.Notes

class NotesRepository (val dao:NotesDao)  {

    fun getAllNotes() : LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getHighNote() : LiveData<List<Notes>>{
        return dao.getHighNotes()
    }

    fun getMediumNote() : LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }

    fun getLowNote() : LiveData<List<Notes>>{
        return dao.getLowNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

}