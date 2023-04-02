package com.example.hien_android_final

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hien_android_final.api.ApiInterface
import com.example.hien_android_final.api.CoinPriceItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LosersFragment : Fragment() {

    private var nameList = mutableListOf<String>()
    private var idList = mutableListOf<String>()
    private var priceList = mutableListOf<String>()
    private var priceLast24hList = mutableListOf<String>()
    private var imageURLList = mutableListOf<String>()
    private lateinit var adapter: RecycleAdapter
    private lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_losers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APIURL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<CoinPriceItem>?> {
            override fun onResponse(
                call: Call<List<CoinPriceItem>?>?,
                response: Response<List<CoinPriceItem>?>?
            ) {
                val responseBody = response?.body()?.sortedBy { coinPriceItem -> coinPriceItem.price_change_percentage_24h }!!
                Log.v("tin", responseBody.toString())

                for (myData in responseBody) {
                    addToList(myData.name, myData.id, myData.current_price.toString()
                        , myData.price_change_percentage_24h.toString(), myData.image)
                }
                val layoutManager = LinearLayoutManager(context)
                recycleView = view.findViewById(R.id.rv_recyclerView_loser)
                recycleView.layoutManager = layoutManager
                recycleView.setHasFixedSize(true)
                adapter = RecycleAdapter(nameList, idList, priceList, priceLast24hList, imageURLList)
                recycleView.adapter = adapter
            }

            override fun onFailure(call: Call<List<CoinPriceItem>?>?, t: Throwable?) {
                Toast.makeText(context, "Error when call api", Toast.LENGTH_SHORT).show()
            }
        })

    }


    private fun addToList(name: String, id: String, price: String, priceLast24h: String, imageURL: String) {
        nameList.add(name)
        idList.add(id)
        priceList.add(price + "$")
        priceLast24hList.add(priceLast24h + "%")
        imageURLList.add(imageURL)


    }


}