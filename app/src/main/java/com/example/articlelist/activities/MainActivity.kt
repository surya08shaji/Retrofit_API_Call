package com.example.articlelist.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.articlelist.Adapter
import com.example.articlelist.data.ApiInterface
import com.example.articlelist.databinding.ActivityMainBinding
import com.example.articlelist.model.DataModelItem
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    var data = ArrayList<DataModelItem>()
    var isLoading = false
    private val apiCall = ApiInterface.create()
    var currentPage = 1

    private var backPressed: Long = 0
    //var lastPress: Long = 0
    //var backpressToast: Toast? = null

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initScrollListener()
        loadPageList()


        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = Adapter(data)
        binding.recyclerView.adapter = adapter

    }

    private fun loadPageList() {
        apiCall.getData(10, "Latest", currentPage)
            .enqueue(object : retrofit2.Callback<ArrayList<DataModelItem>> {
                override fun onFailure(call: Call<ArrayList<DataModelItem>>?, t: Throwable?) {
                    t?.printStackTrace()
                    Log.e("scroll", "exception", t)
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ArrayList<DataModelItem>>,
                    response: Response<ArrayList<DataModelItem>>
                ) {
                    if (response.isSuccessful) {
                        val dataList = response.body()
                        if (dataList != null) {
                            data.addAll(dataList)
                            adapter.notifyDataSetChanged()
                        }
                    }

                }

            })
        fun onDestroy() {
            super.onDestroy()
            _binding = null

        }
    }

    private fun initScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager
                            .findLastCompletelyVisibleItemPosition() == data.size - 1
                    ) {
                        currentPage += 1
                        loadPageList()
                    }
                }
            }
        })
    }


    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) super.onBackPressed() else Toast.makeText(
            baseContext, "Press once again to exit!", Toast.LENGTH_SHORT
        ).show()
        backPressed = System.currentTimeMillis()

    }

}



//    override fun onBackPressed() {
//        val currentTime = System.currentTimeMillis()
//        if (currentTime - lastPress > 2000) {
//            backpressToast = Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_LONG)
//            backpressToast.show()
//            lastPress = currentTime
//        } else {
//            if (backpressToast != null) backpressToast!!.cancel()
//            super.onBackPressed()
//        }
//    }

//    private var exitToast: Toast? = null
//
//    override fun onBackPressed() {
//        if (exitToast == null || exitToast!!.view == null || exitToast!!.view!!.windowToken == null) {
//            exitToast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG)
//            exitToast!!.show()
//        } else {
//            exitToast!!.cancel()
//            finish()
//        }
//    }






















//    private lateinit var recyclerview: RecyclerView
//    lateinit var adapter: Adapter
//    var data = ArrayList<DataModelItem>()
//    var isLoading = false
//    private val apiCall = ApiInterface.create()
//    var currentPage = 1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
//            R.layout.activity_main
//        )
////        setContentView(R.layout.activity_main)
//
//        recyclerview = binding.recyclerView
//        //recyclerview.setHasFixedSize(true)
//
//
//
////        recyclerview = findViewById(R.id.recycler_view)
//
//
//        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
//        adapter = Adapter(data)
//        recyclerview.adapter = adapter
//
//
//        //recyclerview.setLayoutManager(LinearLayoutManager(this))
////        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
////        employeeDataAdapter = EmployeeDataAdapter()
////        recyclerView.setAdapter(employeeDataAdapter)
//
////        adapter = Adapter(data)
////        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
////        recyclerview.adapter = adapter
//
//
//       //initAdapter()
//        initScrollListener()
//        loadPageList()
//    }
//
//    private fun initScrollListener() {
//        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
//                if (!isLoading) {
//                    if (linearLayoutManager != null && linearLayoutManager
//                            .findLastCompletelyVisibleItemPosition() == data.size - 1) {
//                        currentPage += 1
//                        loadPageList()
//                    }
//                }
//            }
//        })
//    }
//
////    private fun initAdapter() {
////        adapter = Adapter(data)
////        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
////        recyclerview.adapter = adapter
////    }
//
//    private fun loadPageList() {
//        apiCall.getQuotes(10,"Latest",currentPage)
//            .enqueue(object : retrofit2.Callback<ArrayList<DataModelItem>> {
//                override fun onFailure(call: Call<ArrayList<DataModelItem>>?, t: Throwable?) {
//                    t?.printStackTrace()
//                    Log.e("scroll", "exception", t)
//                }
//                @SuppressLint("NotifyDataSetChanged")
//                override fun onResponse(
//                    call: Call<ArrayList<DataModelItem>>,
//                    response: Response<ArrayList<DataModelItem>>
//                ) {
//                    if (response.isSuccessful){
//                        val dataList = response.body()
//                        if (dataList != null){
//                            data.addAll(dataList)
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//
//                }
//
//            })
//
//    }
//}