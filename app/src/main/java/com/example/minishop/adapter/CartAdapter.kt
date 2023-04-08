package com.example.minishop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.minishop.R
import com.example.minishop.model.Product

class CartAdapter(private val context: Context, private val cartItems: ArrayList<Product>) : BaseAdapter() {

    override fun getCount(): Int {
        return cartItems.size
    }

    override fun getItem(position: Int): Any {
        return cartItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false)
            holder = ViewHolder()
            holder.productNameTextView = view.findViewById(R.id.product_name)
            holder.productIdTextView = view.findViewById(R.id.product_id)
            holder.productPriceTextView = view.findViewById(R.id.product_price)

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val item = getItem(position) as Product
        holder.productNameTextView.text = item.name
        val productCheckBox = view?.findViewById<CheckBox>(R.id.product_checkbox)
        if (productCheckBox != null) {
            productCheckBox.visibility = View.GONE
        }
        holder.productPriceTextView.text = "$${item.price}"
        holder.productIdTextView.text = item.id.toString()

        return view
    }

    private class ViewHolder {
        lateinit var productNameTextView: TextView
        lateinit var productPriceTextView: TextView
        lateinit var productIdTextView: TextView
    }
}
