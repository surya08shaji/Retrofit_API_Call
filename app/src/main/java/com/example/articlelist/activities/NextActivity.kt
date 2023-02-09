package com.example.articlelist.activities

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.articlelist.ArticleAdapter
import com.example.articlelist.data.ApiInterface
import com.example.articlelist.databinding.ActivityNext2Binding
import com.example.articlelist.model.ShowModelItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response


class NextActivity : AppCompatActivity() {

    var isLoading = false
    var currentPage = 1
    private val apiCall = ApiInterface.create()
    var sub_data = ArrayList<ShowModelItem>()
    lateinit var adapter: ArticleAdapter
    private var value = 1

    private var _binding: ActivityNext2Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_next2)

        _binding = ActivityNext2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        if (intent.extras != null) {
            value = intent.getIntExtra("key", 0)
            println("value $value")

            apiCall.getDetails(value).enqueue(object : retrofit2.Callback<ShowModelItem> {
                override fun onFailure(call: Call<ShowModelItem>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("scroll", "exception", t)
                }

                override fun onResponse(
                    call: Call<ShowModelItem>,
                    response: Response<ShowModelItem>
                ) {

                    if (response.isSuccessful) {
                        val dataList = response.body()
                        if (dataList != null) {

                            binding.title.text = dataList.title
                            //binding.content.text = dataList.content
                           // binding.content.text = Html.fromHtml(Html.fromHtml(dataList.content).toString())
                            binding.author.text = dataList.author
                            Picasso.with(binding.image.context).load(dataList.image)
                                .into(binding.image)

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                binding.content.text = Html.fromHtml(dataList.content, Html.FROM_HTML_MODE_COMPACT)
                            } else {
                                binding.content.text = Html.fromHtml(dataList.content)
                            }

//                            val view = findViewById<View>(R.id.sampleText) as TextView
//                            val formattedText = getString(R.string.htmlFormattedText)
//                            val result = Html.fromHtml(formattedText)
//                            view.text = result

//                            val textView = findViewById<View>(R.id.textView) as TextView
//                            textView.text = Html.fromHtml(Html.fromHtml(mHtmlString).toString())
                        }
                    }
                }
            })

        }
    }
}





























//        apiCall.getDetails(value)
//            .enqueue(object : retrofit2.Callback<ShowModelItem> {
//                override fun onFailure(call: Call<ShowModelItem>, t: Throwable) {
//                    t?.printStackTrace()
//                    Log.e("scroll", "exception", t)
//                }
//
//                override fun onResponse(
//                    call: Call<ShowModelItem>,
//                    response: Response<ShowModelItem>
//                ) {
//
//                    if (response.isSuccessful) {
//                        val dataList = response.body()
//                        if (dataList != null) {
//
//                            binding.title.text = dataList.title
//                            binding.content.text = dataList.content
//                            binding.author.text = dataList.author
//                            Picasso.with(binding.image.context).load(dataList.image)
//                                .into(binding.image)
//                        }
//                    }
//                }
//            }

//                            sub_data.addAll(dataList)
//                            adapter.notifyDataSetChanged()

//
//                    binding.title.text = this.title
//                    binding.content.text = this.content
//                    binding.author.text = this.author
//                    Picasso.with(binding.image.context).load(this.image).into(binding.image)

//        initSubListener()
//        loadSubPageList()
//
//        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
//        binding.recyclerView.setLayoutManager(layoutManager)
//        adapter =ArticleAdapter(sub_data)
//        binding.recyclerView.adapter = adapter
//    }
//
//    private fun loadSubPageList() {
//        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
//                if (!isLoading) {
//                    if (linearLayoutManager != null && linearLayoutManager
//                            .findLastCompletelyVisibleItemPosition() == sub_data.size - 1) {
//                        currentPage += 1
//                        initSubListener()
//                    }
//                }
//            }
//        })
//    }
//
//    private fun initSubListener() {
//        apiCall.getDetails(value)
//            .enqueue(object : retrofit2.Callback<ArrayList<ShowModelItem>> {
//                override fun onFailure(call: Call<ArrayList<ShowModelItem>>?, t: Throwable?) {
//                    t?.printStackTrace()
//                    Log.e("scroll", "exception", t)
//                }
//
//                @SuppressLint("NotifyDataSetChanged")
//                override fun onResponse(
//                    call: Call<ArrayList<ShowModelItem>>,
//                    response: Response<ArrayList<ShowModelItem>>
//                ) {
//                    if (response.isSuccessful) {
//                        val dataList = response.body()
//                        if (dataList != null) {
//                            sub_data.addAll(dataList)
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//
//                }
//
//            })
//    }
//}