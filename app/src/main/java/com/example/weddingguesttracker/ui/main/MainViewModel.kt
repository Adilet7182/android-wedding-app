package com.example.weddingguesttracker.ui.main

import androidx.lifecycle.ViewModel
import com.example.weddingguesttracker.data.models.Guest
import com.example.weddingguesttracker.data.repository.TableRepository

class MainViewModel : ViewModel() {
    val tableRepository = TableRepository()

    fun addTable(){
        tableRepository.addTable()
    }

    fun addGuestToTable(tableIndex: Int, guest: Guest){
        tableRepository.addGuestToTable(tableIndex, guest)
    }

    val tablesList = tableRepository.tables



}

























