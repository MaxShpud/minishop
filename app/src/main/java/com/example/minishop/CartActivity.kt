package com.example.minishop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.minishop.adapter.CartAdapter
import com.example.minishop.adapter.ProductAdapter
import com.example.minishop.model.Product

class CartActivity : AppCompatActivity() {

    // создание переменных с отложенной инициализацией
    private lateinit var cartListView: ListView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_cart)

        // Инициализация views
        cartListView = findViewById(R.id.cart_list_view)
        totalPriceTextView = findViewById(R.id.total_price_text_view)
        checkoutButton = findViewById(R.id.checkout_button)

        // Получение элементов из MainActivity
        val cartItems: ArrayList<Product> = intent.getParcelableArrayListExtra("cartItems")!!

        // Установка адаптера
        cartAdapter = CartAdapter(this, cartItems)
        cartListView.adapter = cartAdapter

        Log.d("HHH CartActivity", cartItems.toString())

        // Вычисление и вывод тотал прайса
        val totalPrice = cartItems?.sumByDouble { it.price }
        totalPriceTextView.text = "Total price: $$totalPrice"

        // Установка слушателя на кнопку и завершение действия активити
        checkoutButton.setOnClickListener {
            Toast.makeText(this, "Thank you for your purchase!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}