package com.app.calculatorView

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.app.simplecalculator.R

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    //simple buttons
    lateinit var zero_btn: Button
    lateinit var one_btn: Button
    lateinit var two_btn: Button
    lateinit var three_btn: Button
    lateinit var four_btn: Button
    lateinit var five_btn: Button
    lateinit var six_btn: Button
    lateinit var seven_btn: Button
    lateinit var eight_btn: Button
    lateinit var nine_btn: Button
    lateinit var plus_btn: Button
    lateinit var minus_btn: Button
    lateinit var multiply_btn: Button
    lateinit var divide_btn: Button
    lateinit var perctage_btn: Button
    lateinit var plus_minus_btn: Button
    lateinit var ac_btn: Button
    lateinit var decimal_btn: Button
    lateinit var equals_btn: Button

    //Scientific buttons
    lateinit var brack_open: Button
    lateinit var brack_close: Button
    lateinit var mc: Button
    lateinit var mplus: Button
    lateinit var mminus: Button
    lateinit var mr: Button
    lateinit var second: Button
    lateinit var xPwrTwo: Button
    lateinit var xPwrThree: Button
    lateinit var xPwrY: Button
    lateinit var ePwrX: Button
    lateinit var tenPwrX: Button
    lateinit var oneDivX: Button
    lateinit var twoRootX: Button
    lateinit var threeRootX: Button
    lateinit var yRootx: Button
    lateinit var ln: Button
    lateinit var logTen: Button
    lateinit var xFact: Button
    lateinit var sin: Button
    lateinit var cos: Button
    lateinit var tan: Button
    lateinit var exponential: Button
    lateinit var doubleE: Button
    lateinit var rad: Button
    lateinit var sinh: Button
    lateinit var cosh: Button
    lateinit var tanh: Button
    lateinit var pi: Button
    lateinit var rand: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO: Swipe l/r to clear or reset calculator

        // Check for orientation
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onCreate: Landscape")
            // In landscape
        } else {
            Log.d(TAG, "onCreate: Portrait")
            // In portrait
        }


        // Scientific Buttons

    }

    fun initializeSimpleView() {
        // Simple Calculator Buttons
        zero_btn = findViewById<Button?>(R.id.zero_btn)
        one_btn = findViewById(R.id.one_btn)
        two_btn = findViewById(R.id.two_btn)
        three_btn = findViewById(R.id.three_btn)
        four_btn = findViewById(R.id.four_btn)
        five_btn = findViewById(R.id.five_btn)
        six_btn = findViewById(R.id.six_btn)
        seven_btn = findViewById(R.id.seven_btn)
        eight_btn = findViewById(R.id.eight_btn)
        nine_btn = findViewById(R.id.nine_btn)
        plus_btn = findViewById(R.id.plus_btn)
        minus_btn = findViewById(R.id.minus_btn)
        multiply_btn = findViewById(R.id.multiply_btn)
        divide_btn = findViewById(R.id.divide_btn)
        perctage_btn = findViewById(R.id.perctage_btn)
        plus_minus_btn = findViewById(R.id.plus_minus_btn)
        ac_btn = findViewById(R.id.ac_btn)
        decimal_btn = findViewById(R.id.decimal_btn)
        equals_btn = findViewById(R.id.equals_btn)

        //Initialize Listeners
        zero_btn.setOnClickListener(View.OnClickListener { })
    }

    fun initializeScientificButtons() {
        
        brack_open = findViewById(R.id.bracket_open_btn)
        brack_close = findViewById(R.id.bracket_close_btn)
        mc = findViewById(R.id.mc_btn)
        mplus = findViewById(R.id.mplus_btn)
        mminus = findViewById(R.id.m_minus_btn)
        mr = findViewById(R.id.mr_btn)
        second = findViewById(R.id.second_btn)
        xPwrTwo = findViewById(R.id.x_pwr_two_btn)
        xPwrThree = findViewById(R.id.x_pwr_three_btn)
        xPwrY = findViewById(R.id.x_pwr_y_btn)
        ePwrX = findViewById(R.id.e_pwr_x_btn)
        tenPwrX = findViewById(R.id.ten_pwr_x_btn)
        oneDivX = findViewById(R.id.one_div_x_btn)
        twoRootX = findViewById(R.id.two_unroot_x_btn)
        threeRootX = findViewById(R.id.three_unroot_x_btn)
        yRootx = findViewById(R.id.y_unroot_x_btn)
        ln = findViewById(R.id.ln_btn)
        logTen = findViewById(R.id.log_ten_btn)
        xFact = findViewById(R.id.factorial_btn)
        sin = findViewById(R.id.sin_btn)
        cos = findViewById(R.id.cos_btn)
        tan = findViewById(R.id.tan_btn)
        exponential = findViewById(R.id.exponential_btn)
        doubleE = findViewById(R.id.double_e_btn)
        rad = findViewById(R.id.rad_btn)
        sinh = findViewById(R.id.sinh_btn)
        cosh = findViewById(R.id.cosh_btn)
        tanh = findViewById(R.id.tanh_btn)
        pi = findViewById(R.id.pi_btn)
        rand = findViewById(R.id.rand_btn)

    }
}