    package com.app.calculator.View;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.app.calculator.ViewModel.CalculatorViewModel;
import com.app.simplecalculator.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO: eg. 3 + 2 if again any sign btn pressed then add ab in a and spare b
    //  so that multiple ops can be perform, add voice input calculation

    private View decorView;
    private static final String TAG = "MainActivity";
    private ImageView plus_btn, minus_btn, multiply_btn, divide_btn, plus_minus_btn,equals_btn, percentage_btn;
    private Button zero_btn, one_btn,two_btn, three_btn,four_btn,five_btn,
            six_btn, seven_btn, eight_btn, nine_btn, ac_btn, decimal_btn;
    private Button brack_open, brack_close, mc, mplus, mminus, mr, second, xPwrTwo, xPwrThree,
            xPwrY, ePwrX, tenPwrX, oneDivX, twoRootX, threeRootX, yRootx, ln, logTen, xFact, sin,
            cos, tan, exponential, doubleE, rad, sinh, cosh, tanh, pi, rand;

    private TextView mDisplay, mCalculationView;
    private double a, b, result;
    private double percent=0;
    private boolean signButtonPressed = false;
    private boolean plus = false;
    private boolean minus = false;
    private boolean multiply = false;
    private boolean divide = false;
    private CalculatorViewModel calculatorViewModel;
    private Drawable backgroundDrawable_yellow, backgroundDrawable_white;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.###"); // Adjust the number of # symbols as needed
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFirebaseRemoteConfig();

        if(!getIsScientificValue()){
            Log.d(TAG, "onCreate: isScientific is false");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        mDisplay = findViewById(R.id.main_display);
        mCalculationView = findViewById(R.id.calculation_view);
        InitializeSimpleViewButtons();
        InitializeBtnListener();

        backgroundDrawable_yellow = getResources().getDrawable(R.drawable.round_btn_yellow);
        backgroundDrawable_white = getResources().getDrawable(R.drawable.round_btn_white);
        decorView = getWindow().getDecorView();

        int Orientation = getResources().getConfiguration().orientation;

        if(Orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d(TAG, "onCreate: Landscape");
            InitializeScientificViewButtons();
            InitializeScientificBtnListeners();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int i) {
                    if (i == 0){
                        decorView.setSystemUiVisibility(hideSystemBarsWithNotificationsBar());
                    }
                }
            });
        } else {
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int i) {
                    if (i == 0){
                        decorView.setSystemUiVisibility(hideSystemBars());
                    }
                }
            });
        }


        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        

    }

    private void initializeFirebaseRemoteConfig() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);

        mFirebaseRemoteConfig.fetch();

        mFirebaseRemoteConfig.addOnConfigUpdateListener(new ConfigUpdateListener() {
            @Override
            public void onUpdate(ConfigUpdate configUpdate) {
                Log.d(TAG, "Updated keys: " + configUpdate.getUpdatedKeys());

                if (configUpdate.getUpdatedKeys().contains("isScientific")) {
                    Log.d(TAG, "onUpdate: isScientific is true!");
                    mFirebaseRemoteConfig.activate()
                            .addOnCompleteListener(task -> getIsScientificValue());
                }
            }

            @Override
            public void onError(FirebaseRemoteConfigException error) {
                Log.w(TAG, "Config update error with code: " + error.getCode(), error);
            }
        });

    }

    private boolean getIsScientificValue() {
//        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        if(mFirebaseRemoteConfig.getBoolean("isScientific")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
        return mFirebaseRemoteConfig.getBoolean("isScientific");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    private int hideSystemBarsWithNotificationsBar(){
        return View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    @Nullable
    private String getResult(double a, double b, boolean pls, boolean mins, boolean divde, boolean multply){
        DecimalFormat decimalFormat = new DecimalFormat("#.###"); // Adjust the number of # symbols as needed

        if(pls){
            result = calculatorViewModel.performAddition(a,b);
            return decimalFormat.format(result);
        }
        else if(mins){
            result = calculatorViewModel.performSubtraction(a,b);
            return decimalFormat.format(result);
        } else if(divde){
            result = calculatorViewModel.performDivision(a,b);
            return decimalFormat.format(result);
        } else if(multply){
            result = calculatorViewModel.performMultiplication(a,b);
            return decimalFormat.format(result);
        }
        return null;
    }

    private void InitializeScientificViewButtons(){
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
    }

    private void InitializeSimpleViewButtons(){
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
                if(mDisplay.getText().toString().equals("0")){
                    Log.d(TAG, "onClick: zero" + mDisplay.getText());
                } else {
                    mDisplay.append("0");
                    if(mCalculationView.length()>=22){
                        mCalculationView.setText("");
                    } else {
                        mCalculationView.append("0");
                    }
                }
                break;
            case R.id.one_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("1");
                    mCalculationView.append("1");
                } else if (signButtonPressed) {
                    mDisplay.setText("1");
                    mCalculationView.append("1");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("1");
                    mCalculationView.append("1");
                }
                break;
            case R.id.two_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("2");
                    mCalculationView.append("2");
                } else if (signButtonPressed) {
                    mDisplay.setText("2");
                    mCalculationView.append("2");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("2");
                    mCalculationView.append("2");
                }
                break;
            case R.id.three_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("3");
                    mCalculationView.append("3");
                } else if (signButtonPressed) {
                    mDisplay.setText("3");
                    mCalculationView.append("3");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("3");
                    mCalculationView.append("3");
                }
                break;
            case R.id.four_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("4");
                    mCalculationView.append("4");
                } else if (signButtonPressed) {
                    mDisplay.setText("4");
                    mCalculationView.append("4");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("4");
                    mCalculationView.append("4");
                }
                break;
            case R.id.five_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("5");
                    mCalculationView.append("5");
                } else if (signButtonPressed) {
                    mDisplay.setText("5");
                    mCalculationView.append("5");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("5");
                    mCalculationView.append("5");
                }
                break;
            case R.id.six_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("6");
                    mCalculationView.append("6");
                } else if (signButtonPressed) {
                    mDisplay.setText("6");
                    mCalculationView.append("6");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("6");
                    mCalculationView.append("6");
                }
                break;
            case R.id.seven_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("7");
                    mCalculationView.append("7");
                } else if (signButtonPressed) {
                    mDisplay.setText("7");
                    mCalculationView.append("7");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("7");
                    mCalculationView.append("7");
                }
                break;
            case R.id.eight_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("8");
                    mCalculationView.append("8");
                } else if (signButtonPressed) {
                    mDisplay.setText("8");
                    mCalculationView.append("8");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("8");
                    mCalculationView.append("8");
                }
                break;
            case R.id.nine_btn:
                ac_btn.setText("C");
                if(mDisplay.getText().toString().equals("0")){
                    mDisplay.setText("9");
                    mCalculationView.append("9");
                } else if (signButtonPressed) {
                    mDisplay.setText("9");
                    mCalculationView.append("9");
                    signButtonPressed = false;
                } else {
                    mDisplay.append("9");
                    mCalculationView.append("9");
                }
                break;
            case R.id.plus_btn:
                a = Double.parseDouble(mDisplay.getText().toString());
                Log.d(TAG, "onClick plus: a: " + a + " b: " + b);
                setSignValues(true,false,false,false);
                signButtonPressed = true;
                mCalculationView.append("+");
                setSignBackground(plus_btn,minus_btn,divide_btn,multiply_btn,false);
                break;
            case R.id.minus_btn:
                a = Double.parseDouble(mDisplay.getText().toString());
                Log.d(TAG, "onClick minus: a: " + a + "b: " + b);
                setSignValues(false,true,false,false);
                signButtonPressed = true;
                mCalculationView.append("-");
                setSignBackground(minus_btn,divide_btn,multiply_btn,plus_btn,false);
                break;
            case R.id.multiply_btn:
                a = Double.parseDouble(mDisplay.getText().toString());
                Log.d(TAG, "onClick: multiply: " + a + "b: " + b);
                setSignValues(false,false,false,true);
                signButtonPressed = true;
                mCalculationView.append("*");
                setSignBackground(multiply_btn,minus_btn,divide_btn,plus_btn,false);
                break;
            case R.id.divide_btn:
                a = Double.parseDouble(mDisplay.getText().toString());
                Log.d(TAG, "onClick divide: a: " + a + "b: " + b);
                setSignValues(false,false,true,false);
                signButtonPressed = true;
                mCalculationView.append("÷");
                setSignBackground(divide_btn,minus_btn,multiply_btn,plus_btn,false);
                break;
            case R.id.ac_btn:
                ac_btn.setText("AC");
                mDisplay.setText("0");
                mCalculationView.setText("");
                setSignBackground(plus_btn,minus_btn,multiply_btn,divide_btn,true);
                signButtonPressed = false;
                break;
            case R.id.perctage_btn:
                mCalculationView.append("%");
                b = Double.parseDouble(mDisplay.getText().toString());
                Log.d(TAG, "percentage_btn: a: " + a + ": b: " + b);
                percent = (b / 100.0f);
                mDisplay.setText(decimalFormat.format(percent));
                break;
            case R.id.plus_minus_btn:
                break;
            case R.id.decimal_btn:
                mCalculationView.append(".");
                mDisplay.append(".");
                break;
            case R.id.equals_btn:
                setSignBackground(plus_btn,minus_btn,multiply_btn,divide_btn,true);
                Log.d(TAG, "on equals: pl: " +plus+ "mi: " +minus+ "di: " +divide+ "ml:" + multiply);
                Log.d(TAG, "onClick: equals " + mDisplay.getText());
                if(plus || minus || multiply || divide) {
                    if (!mDisplay.getText().equals("0")) {
                        if (!mDisplay.getText().equals("Error")) {
                            if(percent != 0){
                                calculatePercentage(percent);
                            } else {
                                b = Double.parseDouble(mDisplay.getText().toString());
                                Log.d(TAG, "onClick: a: " + a + "b: " + b);
                                mDisplay.setText(getResult(a, b, plus, minus, divide, multiply));
                                Log.d(TAG, "onClick: Result: " + result);
                                signButtonPressed = true;
                            }
                        }
                    } else if (divide && mDisplay.getText().equals("0")) { // This is how Error occurs in official iphone calculator
                        mDisplay.setText("Error");
                    }
                }
                setSignValues(false,false,false,false);
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

    private void calculatePercentage(double perc){
        if(plus){
            Log.d(TAG, "calculatePercentage: plus");
            percent = a * perc;
            result = a + percent;
            mDisplay.setText(decimalFormat.format(result));
        }
        else if (minus) {
            Log.d(TAG, "calculatePercentage: minus");
            percent = a * perc;
            result = a - percent;
            mDisplay.setText(decimalFormat.format(result));
        }
        else if (multiply) {
            Log.d(TAG, "calculatePercentage: multiply");
            result = a * perc;
            mDisplay.setText(decimalFormat.format(result));
        }
        else if (divide) {
            Log.d(TAG, "calculatePercentage: divide");
            percent = a * perc;
            result = a / percent;
            mDisplay.setText(decimalFormat.format(result));
        }
        percent = 0;
    }
    /** pass in first parameter to make it true and pass others to make them false **/
    private void setSignValues(boolean plus_sign, boolean minus_sign, boolean divide_sign, boolean multiply_sign){
        Log.d(TAG, "setOtherSignFalse: called!");
            plus = plus_sign;
            minus = minus_sign;
            divide = divide_sign;
            multiply = multiply_sign;
    }

    /**input desired imageview in first parameter to change the background,
     * set default to true to revert default background for all sign buttons**/
    private void setSignBackground(ImageView on, ImageView off1, ImageView off2, ImageView off3, boolean setDefault){
        if(!setDefault){
            on.setBackground(backgroundDrawable_white);
            on.setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_IN);
            off1.setBackground(backgroundDrawable_yellow);
            off1.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            off2.setBackground(backgroundDrawable_yellow);
            off2.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            off3.setBackground(backgroundDrawable_yellow);
            off3.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        } else {
            on.setBackground(backgroundDrawable_yellow);
            on.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            off1.setBackground(backgroundDrawable_yellow);
            off1.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            off2.setBackground(backgroundDrawable_yellow);
            off2.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            off3.setBackground(backgroundDrawable_yellow);
            off3.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        }
    }
}
