package com.example.washmart

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class adminpage : AppCompatActivity() {

    private lateinit var dbHelper: Dbhelper
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var users: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adminpage)

        dbHelper = Dbhelper(this)
        val lv:ListView=findViewById(R.id.lv)

        val users = dbHelper.getAllUsers()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        lv.adapter = adapter

        lv.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = users[position]
            showDeleteConfirmationDialog(selectedUser, position)
            true
            val intent = Intent(this,userdesign::class.java)
            intent.putExtra("username",selectedUser)
            startActivity(intent)
        }
    }



    private fun showDeleteConfirmationDialog(username: String, position: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Do you want to delete the user $username?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                deleteUser(username, position)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Delete User")
        alert.show()
    }




    private fun deleteUser(username: String, position: Int) {
        if (dbHelper.deleteUser(username)) {
            users.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "User $username deleted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show()
        }
    }
}