package com.examropali

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.examropali.data.User
import com.examropali.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val firstName = binding.eTxtFirstName.text.toString()
            val lastName = binding.eTxtLastName.text.toString()
            val birthday = binding.editTextDate.text.toString()
            val userName = binding.editTextUser.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(firstName,lastName,birthday,userName)
            database.child(userName).setValue(User).addOnSuccessListener {

                binding.eTxtFirstName.text.clear()
                binding.eTxtLastName.text.clear()
                binding.editTextDate.text.clear()
                binding.editTextUser.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


            }


        }

    }
}
