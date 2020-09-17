package com.android.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view);

        fragmentManager.beginTransaction().replace(R.id.content_frame,
                                                   WebViewFragment(), "TAG_LOGIN_FRAGMENT").commit();
    }
}