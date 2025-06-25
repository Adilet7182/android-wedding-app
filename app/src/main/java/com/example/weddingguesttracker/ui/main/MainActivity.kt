package com.example.weddingguesttracker.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingguesttracker.R
import com.example.weddingguesttracker.data.models.Guest
import com.example.weddingguesttracker.data.models.Table
import com.example.weddingguesttracker.ui.addguest.AddGuestFragment
import com.example.weddingguesttracker.ui.addguest.OnGuestSavedListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate started")
        super.onCreate(savedInstanceState)

        try {
            Log.d("MainActivity", "Setting content view")
            setContentView(R.layout.activity_main)

            Log.d("MainActivity", "Finding views")
            val myButton: Button = findViewById(R.id.buttonAddTable)
            val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTables)

            Log.d("MainActivity", "Setting layout manager")
            recyclerView.layoutManager = LinearLayoutManager(this)

            Log.d("MainActivity", "Creating ViewModel")
            val viewModel: MainViewModel by viewModels()

            Log.d("MainActivity", "Creating adapter")
            val adapter = TableAdapter()
            recyclerView.adapter = adapter

            adapter.setOnTableClickListener(object : TableAdapter.OnTableClickListener{
                override fun onTableClick(table: Table) {
                    Log.d("MainActivity", "Opening AddGuestFragment for table: ${table.name}")

                    //передаю id стола
                    val fragment = AddGuestFragment.newInstance(table.id)

                    fragment.setOnGuestSavedListener(object : OnGuestSavedListener{
                        override fun onGuestSaved(guest: Guest){
                            Log.d("MainActivity", "Saving guest: ${guest.name} to table ${guest.tableId}")
                            viewModel.addGuestToTable(guest.tableId -1, guest)
                        }
                    })

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            })

            Log.d("MainActivity", "Setting up observer")
            viewModel.tablesList.observe(this) { tables ->
                Log.d("MainActivity", "Tables updated: ${tables.size}")
                adapter.updateTables(tables)
            }

            Log.d("MainActivity", "Setting up button click listener")
            myButton.setOnClickListener {
                Log.d("MainActivity", "Button clicked")
                viewModel.addTable()
            }

            Log.d("MainActivity", "onCreate completed successfully")
        } catch (e: Exception) {
            Log.e("MainActivity", "Error in onCreate", e)
            throw e
        }
    }
}




























