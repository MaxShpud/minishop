package com.example.minishop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.minishop.adapter.ProductAdapter
import com.example.minishop.interfaces.OnCheckedChangeListener
import com.example.minishop.model.Product

class MainActivity : AppCompatActivity() {
    // создание переменных с отложенной инициализацией
    private lateinit var productListView: ListView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var footerView: View
    private lateinit var checkedItemCountTextView: TextView
    private lateinit var showCheckedItemsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)

        // Инициализация views
        productListView = findViewById(R.id.product_list_view)
        footerView = layoutInflater.inflate(R.layout.footer_product_list, productListView, false)
        checkedItemCountTextView = footerView.findViewById(R.id.selected_items_text_view)
        showCheckedItemsButton = footerView.findViewById(R.id.show_cart_button)

        //заполнение массива элементами
        val products = ArrayList<Product>()
        products.add(Product(1, "Car", 10.0))
        products.add(Product(2, "Baby", 20.0))
        products.add(Product(3, "Cat", 30.0))
        products.add(Product(4, "Dog", 40.0))
        products.add(Product(5, "Black men", 50.0))
        products.add(Product(6, "Plane", 60.0))
        products.add(Product(7, "Pokemon", 70.0))
        products.add(Product(8, "Ben ten", 80.0))
        products.add(Product(9, "Bear", 90.0))
        products.add(Product(10, "Princess", 100.0))

        // Установка адаптера и добавление footer-а
        productAdapter = ProductAdapter(this, products)
        productListView.adapter = productAdapter

        productListView.addFooterView(footerView)

        // Установка листенера на кнопку для перехода в CartActivity
        showCheckedItemsButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", productAdapter.getCheckedItems())
            Log.d("HHH MainActivity", productAdapter.getCheckedItems().toString())
            startActivity(intent)
        }
        // изменение checkbox-ов
        productAdapter.setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChanged() {
                checkedItemCountTextView.text = productAdapter.getCheckedItemCount().toString()

            }
        })
    }
}