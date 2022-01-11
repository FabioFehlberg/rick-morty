package com.fehlves.rickmorty.common

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.fehlves.rickmorty.R


abstract class BaseActivity : AppCompatActivity() {

    protected abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else super.onOptionsItemSelected(item)
    }

    protected fun setupAppBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeActionContentDescription(R.string.arrow_back_description)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

}