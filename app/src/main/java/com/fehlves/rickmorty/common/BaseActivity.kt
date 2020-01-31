package com.fehlves.rickmorty.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getContentLayoutId() != 0) {
            setContentView(getContentLayoutId())
        }
    }

}