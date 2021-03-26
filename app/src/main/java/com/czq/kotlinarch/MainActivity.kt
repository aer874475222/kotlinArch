package com.czq.kotlinarch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.czq.kotlinarch.example.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBasePageNoCover.setOnClickListener (onClick)
        btnBasePage.setOnClickListener (onClick)
        btnPagingList.setOnClickListener (onClick)
        btnFragmentActivity.setOnClickListener (onClick)
        btnFragmentBaseActivity.setOnClickListener (onClick)
        btnGameList.setOnClickListener (onClick)
        btnFeedArticle.setOnClickListener(onClick)
    }

    var onClick = View.OnClickListener(){
        when(it.id){
            btnFeedArticle.id-> startActivity(Intent(this@MainActivity, FeedArticleActivity::class.java))
            btnGameList.id-> startActivity(Intent(this@MainActivity, GameListActivity::class.java))
            btnFragmentBaseActivity.id-> startActivity(Intent(this@MainActivity, CoverFragmentActivity::class.java))
            btnFragmentActivity.id-> startActivity(Intent(this@MainActivity, NormalFragmentActivity::class.java))
            btnPagingList.id-> startActivity(Intent(this@MainActivity, PagingListActivity::class.java))
            btnBasePage.id-> startActivity(Intent(this@MainActivity, CoverActivity::class.java))
            btnBasePageNoCover.id-> startActivity(Intent(this@MainActivity, NoCoverActivity::class.java))
        }
    }
}
