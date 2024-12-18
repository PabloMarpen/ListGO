package com.example.listgo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MostrarProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_productos)

        val textViewProductos = findViewById<TextView>(R.id.listViewProductos)
        val ButtonVolver = findViewById<Button>(R.id.buttonImprimir)
        // Obtener el texto de los productos del Intent
        val itemText = intent.getStringExtra("item_text")

        if (itemText != null) {
            // Mostrar el texto en el TextView
            textViewProductos.text = itemText
        } else {
            // Si no se recibe ningún texto, mostrar un mensaje predeterminado
            textViewProductos.text = "No hay productos para mostrar."
        }

        ButtonVolver.setOnClickListener {
            finish()
        }
    }
}

