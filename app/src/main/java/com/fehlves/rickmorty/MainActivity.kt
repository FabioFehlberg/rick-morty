package com.fehlves.rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fehlves.rickmorty.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun getContentLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
