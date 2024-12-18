package com.example.listgo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(private val context: Context, private val items: ArrayList<Item>) : BaseAdapter() {

    // Lista de items seleccionados
    private val selectedItems = mutableSetOf<Item>()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_view, parent, false)

        val item = items[position]

        val nameTextView = view.findViewById<TextView>(R.id.item_name)
        val numberTextView = view.findViewById<TextView>(R.id.item_number)
        val checkBox = view.findViewById<CheckBox>(R.id.item_checkbox)
        val importantImageView = view.findViewById<ImageView>(R.id.item_important)

        // Actualizar los datos del item
        nameTextView.text = item.name
        numberTextView.text = item.number.toString()

        // Mostrar u ocultar el ImageView basado en el estado de 'isImportant'
        if (item.isImportant) {
            importantImageView.setImageResource(R.drawable.important) // Imagen cuando es importante
        } else {
            importantImageView.setImageResource(0) // No mostrar imagen o poner una imagen predeterminada
        }

        // Marcar o desmarcar el CheckBox según si el item está seleccionado
        checkBox.isChecked = selectedItems.contains(item)

        // Cambiar el estado de selección cuando se marca/desmarca el CheckBox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedItems.add(item) // Agregar el item a los seleccionados
            } else {
                selectedItems.remove(item) // Eliminar el item de los seleccionados
            }
        }

        return view
    }

    // Método para obtener los ítems seleccionados
    fun getSelectedItems(): List<Item> {
        return selectedItems.toList() // Retorna los elementos seleccionados
    }

    // Método para eliminar los ítems seleccionados
    fun removeItems(itemsToRemove: List<Item>) {
        items.removeAll(itemsToRemove) // Elimina los ítems seleccionados
        selectedItems.clear() // Limpiar la lista de selección
        notifyDataSetChanged() // Notificar al adaptador para que actualice la vista
    }
}




