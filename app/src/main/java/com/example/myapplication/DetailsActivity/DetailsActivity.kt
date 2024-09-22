package com.example.myapplication.DetailsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.Model.CryptoModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailsBinding
import java.text.DecimalFormat

class DetailsActivity : AppCompatActivity() {
    // binding
    lateinit var binding:ActivityDetailsBinding
    private lateinit var item:CryptoModel
    var formatter:DecimalFormat = DecimalFormat("###,###,###.##")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //.....................................................//
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
        //.....................................................//


        getBundle()
        orderType()
        setVariable()

    }

    private fun setVariable() {
        binding.buyPositionButton.setOnClickListener{
            binding.buyPositionButton.setBackgroundResource(R.drawable.green_bg)
            binding.sellPositionButton.setBackgroundResource(R.drawable.semi_white_bg)
            binding.sendOrderButton.setBackgroundResource(R.drawable.green_bg)
            binding.sendOrderButton.setText("Buy" + item.ShortSymbol.toString().replace("/USDT",""))
        }
        binding.sellPositionButton.setOnClickListener{
            binding.buyPositionButton.setBackgroundResource(R.drawable.semi_white_bg)
            binding.sellPositionButton.setBackgroundResource(R.drawable.red_bg)
            binding.sendOrderButton.setBackgroundResource(R.drawable.red_bg)
            binding.sendOrderButton.setText("Sell" + item.ShortSymbol.replace("/USDT",""))
        }

        // + button
        binding.plusAmount.setOnClickListener{
            if(binding.amountEditText.text.isEmpty()){
                binding.amountEditText.setText("0")
            }

            var n:Double = binding.amountEditText.text.toString().toDouble()
            n=n+1
            binding.amountEditText.setText(n.toString())
        }
        //..........................................................//

        // - button
        binding.minusAmount.setOnClickListener{
            if(binding.amountEditText.text.isEmpty()){
                binding.amountEditText.setText("0")
            }

            var n:Double = binding.amountEditText.text.toString().toDouble()
            if(n>0){
                n=n-1
                binding.amountEditText.setText(n.toString())

            }
        }
        //..........................................................//

    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.symbolNameText.text = item.ShortSymbol
        binding.priceText.text = item.Price.toString()
        binding.symbolNameText.text = item.ShortSymbol
        binding.changePercentDetails.text = item.ChangePercent.toString()+"$"
        //.........................................................................//
        binding.pSellText1.text = formatter?.format(item.SellPrice1)?: "0"
        binding.pSellText2.text = formatter?.format(item.SellPrice2)?: "0"
        binding.pSellText3.text = formatter?.format(item.SellPrice3)?: "0"
        binding.pSellText4.text = formatter?.format(item.SellPrice4)?: "0"
        binding.pSellText5.text = formatter?.format(item.SellPrice5)?: "0"
        //.........................................................................//
        binding.aSellText1.text = item.SellAmount1.toString()
        binding.aSellText2.text = item.SellAmount2.toString()
        binding.aSellText3.text = item.SellAmount3.toString()
        binding.aSellText4.text = item.SellAmount4.toString()
        binding.aSellText5.text = item.SellAmount5.toString()
        //.........................................................................//
        binding.pBuyText1.text= formatter?.format(item.BuyPrice1)?: "0"
        binding.pBuyText2.text= formatter?.format(item.BuyPrice2)?: "0"
        binding.pBuyText3.text= formatter?.format(item.BuyPrice3)?: "0"
        binding.pBuyText4.text= formatter?.format(item.BuyPrice4)?: "0"
        binding.pBuyText5.text= formatter?.format(item.BuyPrice5)?: "0"
        //.........................................................................//
        binding.aBuyText1.text = item.BuyAmount1.toString()
        binding.aBuyText2.text = item.BuyAmount2.toString()
        binding.aBuyText3.text = item.BuyAmount3.toString()
        binding.aBuyText4.text = item.BuyAmount4.toString()
        binding.aBuyText5.text = item.BuyAmount5.toString()
        //.........................................................................//
        binding.openText.text = formatter?.format(item.Open)?:"0"
        binding.closeText.text = formatter?.format(item.Close)?:"0"
        binding.highText.text = formatter?.format(item.High)?:"0"
        binding.lowText.text = formatter?.format(item.Low)?:"0"
        binding.dailyChangeText.text = item.DailyChange.toString()
        binding.dailyVolumeText.text = "$"+item.DailyVol.toString()+"T"
        //.........................................................................//


        if(item.ChangePercent>0){
            binding.priceText.setTextColor(resources.getColor(R.color.green))
            binding.changePercentDetails.setTextColor(resources.getColor(R.color.green))
        }else{
            binding.priceText.setTextColor(resources.getColor(R.color.red))
            binding.changePercentDetails.setTextColor(resources.getColor(R.color.red))
        }

        val drawable = resources.getIdentifier(item.SymbolLogo,"drawable",packageName)

        Glide
            .with(this)
            .load(drawable)
            .into(binding.logoImage)


        binding.backButtonDetails.setOnClickListener{
            finish()
        }

    }
    // This function for Spinner
    private fun orderType(){
        val adapter = ArrayAdapter(this,R.layout.spinner_item,listOf("Limit Order","Market Order","Stop Order"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.orderTypeSpin.adapter =  adapter

    }
}