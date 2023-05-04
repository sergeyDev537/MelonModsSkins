package com.playground.modmelonskins.firebase

import android.graphics.BitmapFactory
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FirebaseManager {

    private val database = Firebase.database

    suspend fun getJson(typeMods: String): String {
        val myRef = database.getReference(typeMods)
        var str = myRef.get().await()
        return str.toString()
//        myRef.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                //val value = snapshot.getValue<String>()
//                Log.d("TAGING", "Value is: " + snapshot.value)
//                str = snapshot.value.toString()
//                callback(str)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("TAGING", "Failed to read value.", error.toException())
//                callback(EMPTY_STRING)
//            }
//        })
    }

    suspend fun downloadFile(pathFile: String){
        // Create a storage reference from our app
        //var storageRef = FirebaseStorage.getInstance().reference

    }

    companion object{

        const val FILE_MODS_JSON = "mods"
        const val FILE_SKINS_JSON = "skins"
        const val EMPTY_STRING = ""

    }

}