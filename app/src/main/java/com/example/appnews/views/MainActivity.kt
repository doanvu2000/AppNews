package com.example.appnews.views

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.R
import com.example.appnews.adapters.NewsAdapter
import com.example.appnews.models.New
import com.example.appnews.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

private const val API_KEY = "f0b79d24f7fd4837b688d188cc304086"

class MainActivity : AppCompatActivity() {
    private var newAdapter: NewsAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBarLoad.visibility = View.VISIBLE
        rcvNewsEveryThing.layoutManager = LinearLayoutManager(this)
        getNewsTop("us", "business")
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchNews(query!!, "publishedAt")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText.isEmpty()) {
                    getNewsTop("us", "business")
                }
                return true
            }
        })
    }

    private fun setUpRecyclerView(list: List<New>) {
        newAdapter = NewsAdapter(list, this)
        rcvNewsEveryThing.adapter = newAdapter
        rcvNewsEveryThing.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        progressBarLoad.visibility = View.GONE
        newAdapter?.setOnClickItem { index ->
            val intent = Intent(baseContext, DetailNewActivity::class.java)
            intent.putExtra("url", list[index].url)
            startActivity(intent)
        }
    }

    private fun getNewsTop(country: String, category: String) {
        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.getNewsTopHeadlines(country, category, API_KEY)
        viewModel.newsTopHeadlines.observe(this, {
            if (it != null) {
                setUpRecyclerView(it!!)
            } else {
                progressBarLoad.visibility = View.GONE
                Toast.makeText(this, "Can not get data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun searchNews(query: String, sortBy: String) {
        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        val now = LocalDateTime.now()
        val year = now.year
        val month = now.month.value
        val day = now.dayOfMonth
        viewModel.getNewsEveryThing(query, "$year-$month-$day", sortBy, API_KEY)
        viewModel.newsEveryThing.observe(this, {
            if (it != null) {
                setUpRecyclerView(it!!)
            } else {
                progressBarLoad.visibility = View.GONE
                Toast.makeText(this, "Can not get data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (newAdapter != null) {
            newAdapter?.release()
        }
    }

//    override fun onRestart() {
//        super.onRestart()
//        finish()
//    }
}