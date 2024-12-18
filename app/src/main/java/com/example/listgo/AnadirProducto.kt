package com.example.listgo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class AnadirProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anadir_producto)

        val ButtonAnadir = findViewById<Button>(R.id.ButtonAnadir)
        val ButtonVolver = findViewById<Button>(R.id.buttonImprimir)
        val NombreProducto = findViewById<EditText>(R.id.etNombre)
        val CantidadProducto = findViewById<EditText>(R.id.etCantidad)
        val Seccion = findViewById<Spinner>(R.id.spinnerSeccion)
        val SwitchUrgente = findViewById<SwitchCompat>(R.id.switchUrgente)


        val secciones = arrayOf("","Panadería", "Pescadería", "Frutería", "Carnicería", "Charcutería", "Conservas", "Perfumería", "General")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, secciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Seccion.adapter = adapter

        ButtonAnadir.setOnClickListener {
            val nombre = NombreProducto.text.toString()
            val cantidadText = CantidadProducto.text.toString()
            val esUrgente = SwitchUrgente.isChecked // Se usa el estado del SwitchCompat

            if (nombre.isBlank() || cantidadText.isBlank()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                val cantidad = cantidadText.toIntOrNull()
                if (cantidad != null) {
                    val nuevoItem = Item(
                        name = nombre,
                        number = cantidad,
                        isImportant = esUrgente // Aquí se asigna el valor de urgencia al nuevo ítem
                    )

                    val resultIntent = Intent()
                    resultIntent.putExtra("nuevo_item", nuevoItem)  // Enviamos el producto a la MainActivity
                    setResult(RESULT_OK, resultIntent)
                    finish()

                    NombreProducto.text.clear()
                    CantidadProducto.text.clear()
                    SwitchUrgente.isChecked = false
                    Toast.makeText(this, "Producto añadido correctamente.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Por favor, ingrese una cantidad válida.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ButtonVolver.setOnClickListener {
            finish()
        }
    }
}

