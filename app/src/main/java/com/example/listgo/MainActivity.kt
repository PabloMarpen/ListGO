package com.example.listgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemList: ArrayList<Item>
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar lista y adaptador
        itemList = ArrayList()
        customAdapter = CustomAdapter(this, itemList)
        val lista = findViewById<ListView>(R.id.listView)
        lista.adapter = customAdapter

        val ButtonAnadir = findViewById<Button>(R.id.ButtonAnadir)
        val ButtonSalir = findViewById<Button>(R.id.buttonSalir)
        val ButtonEliminar = findViewById<Button>(R.id.buttonBorrarSel)
        val ButtonEliminarTodo = findViewById<Button>(R.id.buttonBorrarTodo)
        val ButtonImprimir = findViewById<Button>(R.id.buttonImprimir)

        ButtonAnadir.setOnClickListener {
            val intent = Intent(this, AnadirProducto::class.java)
            startActivityForResult(intent, 1) // Esperamos el resultado de AnadirProducto
        }

        ButtonSalir.setOnClickListener {
            finish()
        }

        ButtonEliminarTodo.setOnClickListener {
            itemList.clear() // Elimina todos los items
            customAdapter.notifyDataSetChanged() // Notificar al adaptador para que actualice la vista
        }

        // Eliminar los productos seleccionados
        ButtonEliminar.setOnClickListener {
            val selectedItems = customAdapter.getSelectedItems()
            if (selectedItems.isNotEmpty()) {
                customAdapter.removeItems(selectedItems) // Eliminar solo los productos seleccionados
                Toast.makeText(this, "Productos eliminados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, selecciona productos para eliminar", Toast.LENGTH_SHORT).show()
            }
        }


        ButtonImprimir.setOnClickListener {
            val itemText = itemList.joinToString("\n") {
                "${it.name} - ${it.number} unidades - Urgente: ${if (it.isImportant) "Sí" else "No"}"
            }

            val intent = Intent(this, MostrarProductosActivity::class.java)
            intent.putExtra("item_text", itemText) // Pasar el texto de los productos
            startActivity(intent) // Iniciar la actividad de los productos
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val nuevoItem = data?.getParcelableExtra<Item>("nuevo_item")
            nuevoItem?.let {
                itemList.add(it) // Añadir el nuevo producto a la lista
                customAdapter.notifyDataSetChanged() // Notificar al adaptador para que actualice la vista
            }
        }
    }
}



