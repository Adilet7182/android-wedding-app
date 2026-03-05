package com.example.weddingguesttracker.ui.addguest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.weddingguesttracker.R
import com.example.weddingguesttracker.data.models.Gender
import com.example.weddingguesttracker.data.models.Guest
import com.example.weddingguesttracker.data.models.Side

interface OnGuestSavedListener{
    fun onGuestSaved(guest: Guest)
}

class AddGuestFragment : Fragment(){

    private var guestSavedListener: OnGuestSavedListener? = null

    fun setOnGuestSavedListener(listener: OnGuestSavedListener){
        guestSavedListener = listener
    }

    companion object{
        private const val ARG_TABLE_ID = "table_id"

        fun newInstance(tableId: Int): AddGuestFragment{
            val fragment = AddGuestFragment()
            val args = Bundle()
            args.putInt(ARG_TABLE_ID, tableId)
            fragment.arguments = args
            return fragment
        }
    }

    private var tableId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tableId = arguments?.getInt(ARG_TABLE_ID) ?: -1
        Log.d("AddGuestFragment", "Table ID: $tableId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_guest, container, false)

        val nameText: EditText = view.findViewById(R.id.editTextName)
        val ageText: EditText = view.findViewById(R.id.editTextAge)
        val buttonSave: Button = view.findViewById(R.id.buttonSave)
        val buttonCancel: Button = view.findViewById(R.id.buttonCancel)
        val radioGroupGender: RadioGroup = view.findViewById(R.id.radioGroupGender)
        val radioGroupSide: RadioGroup = view.findViewById(R.id.radioGroupSide)


        buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        buttonSave.setOnClickListener {
            val name = nameText.text.toString()
            val age = ageText.text.toString().toIntOrNull() ?: 0

            if (name.isEmpty()){
                nameText.error = "Введите имя"
                return@setOnClickListener
            }
            if (age <= 0){
                ageText.error = "Введите корректный возраст"
                return@setOnClickListener
            }

            val gender = if (radioGroupGender.checkedRadioButtonId == R.id.radioMale){
                Gender.MALE
            } else{
                Gender.FEMALE
            }

            val side = if (radioGroupSide.checkedRadioButtonId == R.id.radioGroom){
                Side.GROOM
            } else{
                Side.BRIDE
            }


            val guest = Guest(
                name = name,
                age = age,
                gender = gender,
                side = side,
                tableId = tableId
            )

            guestSavedListener?.onGuestSaved(guest)
            Log.d("AddGuestFragment", "Guest created: $guest")
            parentFragmentManager.popBackStack()
        }

        return view
    }
}