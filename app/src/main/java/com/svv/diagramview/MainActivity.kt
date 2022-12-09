package com.svv.diagramview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.svv.diagramview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val diagram = binding.dialView
        binding.button.setOnClickListener{
            diagram.generate()
        }
    }


}