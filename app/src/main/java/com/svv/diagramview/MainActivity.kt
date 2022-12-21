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
        var dataList = mutableListOf(DiagramData(80.0, "Stalin"),DiagramData(40.0, "Abobus"),DiagramData(100.0, "Lenin"),
            DiagramData(40.0, "Defefe"))
        binding.button.setOnClickListener{
            diagram.init(dataList)
        }
    }


}