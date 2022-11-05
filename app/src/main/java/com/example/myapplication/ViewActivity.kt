package com.example.myapplication
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class ViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar2)
        var arrayListProducto = loadDatainGridView()
        //val gridView = findViewById<GridView>(R.id.gridView) as GridView

        val buttonClick = findViewById<Button>(R.id.button4)
        buttonClick.setOnClickListener {
            try {
                val adapter = CustomListAdapter(this, arrayListProducto)
                // get the ListView and attach the adapter
                // get the ListView and attach the adapter
                val itemsListView = findViewById<GridView>(R.id.list_view_items)
                //val itemsListView: ListView = findViewById<View>(R.id.) as ListView
                itemsListView.adapter = adapter
            }catch (e: Exception){
                Log.d(TAG, "<= ********************************************* =>")
            }
        }



    }


    private fun loadDatainGridView() :ArrayList<Producto> {
        val db = Firebase.firestore
        val arrayListProducto = ArrayList<Producto>()
        val docRef = db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d(TAG, "${document.id} => ${document.data}")
                    val miProducto = document.toObject<Producto>()

                    arrayListProducto.add(miProducto)
                    //Log.d(TAG, "${miProducto.toString()} =>")
                }


            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        return arrayListProducto
    }
    private fun imprimir(lista:ArrayList<Producto>){
        for (product in lista){
            Log.w(ContentValues.TAG, "Los elementos de este array son ${product.nombre_Producto}.")
        }

    }


}
/*
class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val gridView = findViewById<GridView>(R.id.gridView) as GridView



        val db = Firebase.firestore

        val arrayListImage = ArrayList<Int>()
        val letters : MutableList<String> = mutableListOf<String>()
        var i =  0
        val arrayList = ArrayList<String>()
        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.w(ContentValues.TAG, "Los elementos de este array son ${element}.")
                    for (obj in document.data.values){
                        val element =obj.toString()

                        if(element.length > 4) {
                            letters.add(element)
                            arrayList.add(element)
                        }



                        //Log.w(ContentValues.TAG, "Los elementos de este array son ${element}.")


                    }

                    Log.d(ContentValues.TAG, "Documentos son ${document.data.values} => ${document.data.entries} =>> ${result.javaClass.name}  =>> ${document.javaClass.name}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        Log.w(ContentValues.TAG, "Los documentos son ${arrayList.toString()}.")


        val name = arrayOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread","Cupcake1", "Donut3",
            "Eclai5r", "Fr6oyo", "Gingerbre7ad","Cupc8ake", "Don6ut", "Ecla3ir", "Fro2syo", "Gingerxcvbread")


        for (na in arrayList){
            arrayListImage.add(R.drawable.ic_baseline_fastfood_24)
            //Log.w(ContentValues.TAG, "Los elementos de este array son ${na}.")
        }
        val arrayNombres: Array<String> = arrayList.toTypedArray()
        Log.w(ContentValues.TAG, "Los elementos de este array son ${arrayNombres::class}.")
        Log.w(ContentValues.TAG, "el tamaño es ${arrayListImage.size.toString()} y eñ tamaño es ${arrayList.size.toString()}.")
        val customAdapter = CustomAdapter(this@ViewActivity, arrayListImage,
            arrayNombres
        )


        gridView.adapter = customAdapter

        gridView.setOnItemClickListener { adapterView, parent, position, l ->
            Toast.makeText(this@ViewActivity, "Click on : " + name[position], Toast.LENGTH_LONG).show()
            Log.w(ContentValues.TAG, "El tamaño del documentos es ${arrayList.size.toString()}.")
            for(letter in arrayList){
                Log.w(ContentValues.TAG, "Los documentos son ${letter}.")
                Log.w(ContentValues.TAG, "el tamaño es ${arrayListImage.size.toString()} y eñ tamaño es ${arrayList.size.toString()}.")
            }
        }
    }


}*/
