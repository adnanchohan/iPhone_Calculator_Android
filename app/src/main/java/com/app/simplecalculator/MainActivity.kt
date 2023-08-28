package com.app.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO: Swipe l/r to clear or reset calculator
        
        // Simple Calculator Buttons
        val zero_btn = findViewById<Button>(R.id.zero_btn)
        val one_btn = findViewById<Button>(R.id.one_btn)
        val two_btn = findViewById<Button>(R.id.two_btn)
        val three_btn = findViewById<Button>(R.id.three_btn)
        val four_btn = findViewById<Button>(R.id.four_btn)
        val five_btn = findViewById<Button>(R.id.five_btn)
        val six_btn = findViewById<Button>(R.id.six_btn)
        val seven_btn = findViewById<Button>(R.id.seven_btn)
        val eight_btn = findViewById<Button>(R.id.eight_btn)
        val nine_btn = findViewById<Button>(R.id.nine_btn)
        val plus_btn = findViewById<Button>(R.id.plus_btn)
        val minus_btn = findViewById<Button>(R.id.minus_btn)
        val multiply_btn = findViewById<Button>(R.id.multiply_btn)
        val divide_btn = findViewById<Button>(R.id.divide_btn)
        val perctage_btn = findViewById<Button>(R.id.perctage_btn)
        val plus_minus_btn = findViewById<Button>(R.id.plus_minus_btn)
        val ac_btn = findViewById<Button>(R.id.ac_btn)
        val decimal_btn = findViewById<Button>(R.id.decimal_btn)
        val equals_btn = findViewById<Button>(R.id.equals_btn)

        // Scientific Buttons

    }
}