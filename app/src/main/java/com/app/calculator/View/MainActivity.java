package com.app.calculator.View;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.calculator.ViewModel.CalculatorViewModel;
import com.app.simplecalculator.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivityJava";
    private Button zero_btn, one_btn,two_btn, three_btn,four_btn,five_btn,
            six_btn, seven_btn, eight_btn, nine_btn, plus_btn, minus_btn, multiply_btn,
            divide_btn, percentage_btn, plus_minus_btn, ac_btn, decimal_btn,equals_btn;
    private Button brack_open, brack_close, mc, mplus, mminus, mr, second, xPwrTwo, xPwrThree,
            xPwrY, ePwrX, tenPwrX, oneDivX, twoRootX, threeRootX, yRootx, ln, logTen, xFact, sin,
            cos, tan, exponential, doubleE, rad, sinh, cosh, tanh, pi, rand;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int Orientation = getResources().getConfiguration().orientation;

        if(Orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d(TAG, "onCreate: Portrait");

            //Simple Buttons View
            zero_btn = findViewById(R.id.zero_btn);
            one_btn = findViewById(R.id.one_btn);
            two_btn = findViewById(R.id.two_btn);
            three_btn = findViewById(R.id.three_btn);
            four_btn = findViewById(R.id.four_btn);
            five_btn = findViewById(R.id.five_btn);
            six_btn = findViewById(R.id.six_btn);
            seven_btn = findViewById(R.id.seven_btn);
            eight_btn = findViewById(R.id.eight_btn);
            nine_btn = findViewById(R.id.nine_btn);
            plus_btn = findViewById(R.id.plus_btn);
            minus_btn = findViewById(R.id.minus_btn);
            multiply_btn = findViewById(R.id.multiply_btn);
            divide_btn = findViewById(R.id.divide_btn);
            percentage_btn = findViewById(R.id.perctage_btn);
            plus_minus_btn = findViewById(R.id.plus_minus_btn);
            ac_btn = findViewById(R.id.ac_btn);
            decimal_btn = findViewById(R.id.decimal_btn);
            equals_btn = findViewById(R.id.equals_btn);

            InitializeBtnListener();

        } else {
            Log.d(TAG, "onCreate: Landscape");
            //Scientific Buttons View
            brack_open = findViewById(R.id.bracket_open_btn);
            brack_close = findViewById(R.id.bracket_close_btn);
            mc = findViewById(R.id.mc_btn);
            mplus = findViewById(R.id.mplus_btn);
            mminus = findViewById(R.id.m_minus_btn);
            mr = findViewById(R.id.mr_btn);
            second = findViewById(R.id.second_btn);
            xPwrTwo = findViewById(R.id.x_pwr_two_btn);
            xPwrThree = findViewById(R.id.x_pwr_three_btn);
            xPwrY = findViewById(R.id.x_pwr_y_btn);
            ePwrX = findViewById(R.id.e_pwr_x_btn);
            tenPwrX = findViewById(R.id.ten_pwr_x_btn);
            oneDivX = findViewById(R.id.one_div_x_btn);
            twoRootX = findViewById(R.id.two_unroot_x_btn);
            threeRootX = findViewById(R.id.three_unroot_x_btn);
            yRootx = findViewById(R.id.y_unroot_x_btn);
            ln = findViewById(R.id.ln_btn);
            logTen = findViewById(R.id.log_ten_btn);
            xFact = findViewById(R.id.factorial_btn);
            sin = findViewById(R.id.sin_btn);
            cos = findViewById(R.id.cos_btn);
            tan = findViewById(R.id.tan_btn);
            exponential = findViewById(R.id.exponential_btn);
            doubleE = findViewById(R.id.double_e_btn);
            rad = findViewById(R.id.rad_btn);
            sinh = findViewById(R.id.sinh_btn);
            cosh = findViewById(R.id.cosh_btn);
            tanh = findViewById(R.id.tanh_btn);
            pi = findViewById(R.id.pi_btn);
            rand = findViewById(R.id.rand_btn);

            InitializeScientificBtnListeners();
        }

        CalculatorViewModel calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        

    }

    private void InitializeBtnListener(){
        //Initialize Listeners
        zero_btn.setOnClickListener(this);
        one_btn.setOnClickListener(this);
        two_btn.setOnClickListener(this);
        three_btn.setOnClickListener(this);
        four_btn.setOnClickListener(this);
        five_btn.setOnClickListener(this);
        six_btn.setOnClickListener(this);
        seven_btn.setOnClickListener(this);
        eight_btn.setOnClickListener(this);
        nine_btn.setOnClickListener(this);
        plus_btn.setOnClickListener(this);
        minus_btn.setOnClickListener(this);
        multiply_btn.setOnClickListener(this);
        divide_btn.setOnClickListener(this);
        percentage_btn.setOnClickListener(this);
        plus_minus_btn.setOnClickListener(this);
        ac_btn.setOnClickListener(this);
        decimal_btn.setOnClickListener(this);
        equals_btn.setOnClickListener(this);
    }

    private void InitializeScientificBtnListeners(){
        //Initialize Listeners
        brack_open.setOnClickListener(this);
        brack_close.setOnClickListener(this);
        mc.setOnClickListener(this);
        mplus.setOnClickListener(this);
        mminus.setOnClickListener(this);
        mr.setOnClickListener(this);
        second.setOnClickListener(this);
        xPwrTwo.setOnClickListener(this);
        xPwrThree.setOnClickListener(this);
        xPwrY.setOnClickListener(this);
        ePwrX.setOnClickListener(this);
        tenPwrX.setOnClickListener(this);
        oneDivX.setOnClickListener(this);
        twoRootX.setOnClickListener(this);
        threeRootX.setOnClickListener(this);
        yRootx.setOnClickListener(this);
        ln.setOnClickListener(this);
        logTen.setOnClickListener(this);
        xFact.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        exponential.setOnClickListener(this);
        doubleE.setOnClickListener(this);
        rad.setOnClickListener(this);
        sinh.setOnClickListener(this);
        cosh.setOnClickListener(this);
        tanh.setOnClickListener(this);
        pi.setOnClickListener(this);
        rand.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zero_btn:

                break;
            case R.id.one_btn:
                break;
            case R.id.two_btn:
                break;
            case R.id.three_btn:
                break;
            case R.id.four_btn:
                break;
            case R.id.five_btn:
                break;
            case R.id.six_btn:
                break;
            case R.id.seven_btn:
                break;
            case R.id.eight_btn:
                break;
            case R.id.nine_btn:
                break;
            case R.id.plus_btn:
                break;
            case R.id.minus_btn:
                break;
            case R.id.multiply_btn:
                break;
            case R.id.divide_btn:
                break;
            case R.id.ac_btn:
                break;
            case R.id.perctage_btn:
                break;
            case R.id.plus_minus_btn:
                break;
            case R.id.decimal_btn:
                break;
            case R.id.equals_btn:
                break;
            case R.id.bracket_close_btn:
                break;
            case R.id.bracket_open_btn:
                break;
            case R.id.mc_btn:
                break;
            case R.id.m_minus_btn:
                break;
            case R.id.mr_btn:
                break;
            case R.id.mplus_btn:
                break;
            case R.id.second_btn:
                break;
            case R.id.x_pwr_two_btn:
                break;
            case R.id.x_pwr_three_btn:
                break;
            case R.id.x_pwr_y_btn:
                break;
            case R.id.e_pwr_x_btn:
                break;
            case R.id.ten_pwr_x_btn:
                break;
            case R.id.one_div_x_btn:
                break;
            case R.id.two_unroot_x_btn:
                break;
            case R.id.three_unroot_x_btn:
                break;
            case R.id.ln_btn:
                break;
            case R.id.log_ten_btn:
                break;
            case R.id.factorial_btn:
                break;
            case R.id.sin_btn:
                break;
            case R.id.cos_btn:
                break;
            case R.id.tan_btn:
                break;
            case R.id.exponential_btn:
                break;
            case R.id.double_e_btn:
                break;
            case R.id.rad_btn:
                break;
            case R.id.rand_btn:
                break;
            case R.id.sinh_btn:
                break;
            case R.id.cosh_btn:
                break;
            case R.id.tanh_btn:
                break;
        }
    }
}