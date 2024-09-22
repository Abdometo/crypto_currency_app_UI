package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.DetailsActivity.DetailsActivity
import com.example.myapplication.Model.CryptoModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderwalletBinding
import java.text.DecimalFormat

class CryptoListAdapter(private val items:MutableList<CryptoModel>): RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    // declare Variables
    private lateinit var context: Context
    var formatter:DecimalFormat?=null




    // View Holder Class
    class ViewHolder(val binding:ViewholderwalletBinding):RecyclerView.ViewHolder(binding.root){}

    // Implement Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        formatter = DecimalFormat("###,###,###,###")
        val binding = ViewholderwalletBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get item position
        val item = items[position]
        holder.binding.cryptoName.text = item.Symbol
        holder.binding.cryptoPriceText.text = "$"+formatter?.format(item.Price)
        holder.binding.changePercent.text = item.ChangePercent.toString()+"%"
        holder.binding.propertySizeText.text = item.AmountNumber.toString()+item.ShortSymbol.replace("/USDT","")
        holder.binding.propertyAmountText.text = "$"+formatter?.format(item.AmountDollar)
        if(item.ChangePercent<0) holder.binding.changePercent.setText(context.resources.getColor(R.color.red))

        val drawableResourceId = holder.itemView.resources.getIdentifier(item.SymbolLogo,"drawable",holder.itemView.context.packageName)


        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.binding.logoImage)

        holder.itemView.setOnClickListener{
            val intent =  Intent(context,DetailsActivity::class.java)
            intent.putExtra("object",item)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}