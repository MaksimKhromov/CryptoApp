package com.example.cryptoapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter: RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    private lateinit var binding: ItemCoinInfoBinding
    var coinInfoList : List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCoinInfoBinding.inflate(layoutInflater)
        return CoinInfoViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        binding.tvSymbols.text = "${coin.fromsymbol} / ${coin.tosymbol}"
        binding.tvPrice.text = coin.price.toString()
        binding.tvLastUpdate.text = "Время последнего обновления: ${coin.getFormattedTime()}"
        Picasso.get().load(coin.getFullImageUrl()).into(binding.ivLogoCoin)
        binding.itemView.setOnClickListener(){
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivLogoCoin = binding.ivLogoCoin
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvLastUpdate = binding.tvLastUpdate
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}