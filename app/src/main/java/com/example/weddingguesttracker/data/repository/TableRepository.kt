package com.example.weddingguesttracker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weddingguesttracker.data.models.Guest
import com.example.weddingguesttracker.data.models.Table

class TableRepository {
    private val _tables = MutableLiveData<MutableList<Table>>(mutableListOf())
    val tables: LiveData<List<Table>> get() = _tables as LiveData<List<Table>>


    fun addTable(){
        val currentTables = _tables.value ?: mutableListOf()
        val tableNumber = currentTables.size + 1
        val newTable = Table(
            id = tableNumber,
            name = "Стол №$tableNumber"
        )
        currentTables.add(newTable)
        _tables.value = currentTables

    }

    fun addGuestToTable(tableIndex: Int, guest: Guest){
        val currentTables = _tables.value ?: return
        if (tableIndex < currentTables.size) {
            val currentTable = currentTables[tableIndex]
            val updatedGuests = currentTable.guests + guest // Используйте + вместо toMutableList()
            currentTables[tableIndex] = currentTable.copy(guests = updatedGuests)
            _tables.value = currentTables
        }
    }
}