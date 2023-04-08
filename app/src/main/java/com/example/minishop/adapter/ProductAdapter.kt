package com.example.minishop.adapter

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.minishop.R
import com.example.minishop.interfaces.OnCheckedChangeListener
import com.example.minishop.model.Product

class ProductAdapter(private val context: Context, private var productList: List<Product>) : BaseAdapter() {

    private var checkedItemCount = 0
    private var checkedItems: SparseBooleanArray = SparseBooleanArray()
    private var checkedChangeListener: OnCheckedChangeListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_product, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val product = productList[position]
        holder.productNameTextView.text = product.name
        holder.productIdTextView.text = product.id.toString()
        holder.productPriceTextView.text = "$"+product.price.toString()

        holder.checkBox.isChecked = checkedItems.get(position, false)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            setChecked(position, isChecked)
            checkedChangeListener?.onCheckedChanged()
        }

        return view!!
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return productList.size
    }

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        checkedChangeListener = listener
    }

    fun getCheckedItemCount(): Int {
        return checkedItemCount
    }

    fun getCheckedItems(): ArrayList<Product> {
        val checkedProducts = ArrayList<Product>()
        for (i in 0 until checkedItems.size()) {
            if (checkedItems.valueAt(i)) {
                checkedProducts.add(productList[checkedItems.keyAt(i)])
            }
        }
        return checkedProducts
    }

    private fun setChecked(position: Int, isChecked: Boolean) {
        checkedItems.put(position, isChecked)
        if (isChecked) {
            checkedItemCount++
        } else {
            checkedItemCount--
        }
    }



    inner class ViewHolder(itemView: View?) {
        val productNameTextView: TextView = itemView?.findViewById(R.id.product_name) as TextView
        val productIdTextView: TextView = itemView?.findViewById(R.id.product_id) as TextView
        val productPriceTextView: TextView = itemView?.findViewById(R.id.product_price) as TextView
        val checkBox: CheckBox = itemView?.findViewById(R.id.product_checkbox) as CheckBox

        init {
            itemView?.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
        }
    }
}
