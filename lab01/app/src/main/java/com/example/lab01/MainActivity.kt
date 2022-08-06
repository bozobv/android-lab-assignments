package com.example.lab01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    private var asd = 4
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "error found")
        setContentView(R.layout.activity_main)
    }
}