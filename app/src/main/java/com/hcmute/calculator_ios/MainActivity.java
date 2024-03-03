package com.hcmute.calculator_ios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    TextView calculatedResult;
    List<MaterialButton> numbersBtn = new ArrayList<>();
    MaterialButton acBtn, inverseBtn, percentageBtn, divideBtn, multiplyBtn, minusBtn, additionBtn, equalsBtn, dotBtn;
    String param1 = "", param2 = "", resultText = "0";
    Character currentCalculation = '\u002b';
    boolean isMathBtnClicking = false;
    boolean isCalculated = false;
    //
    private final String validNumberCharacters = "0124356789.";
    private final Map<Character, String> calculations = new HashMap<Character, String>() {{
        put('\u002b', "+");
        put('\u2212', "-");
        put('\u00d7', "*");
        put('\u00f7', "/");
        put('\u00b1', "inv");
        put('\u0025', "per");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        calculatedResult = findViewById(R.id.tv_result);
        assignId(acBtn, R.id.btn_ac);
        assignId(inverseBtn, R.id.btn_inverse);
        assignId(percentageBtn, R.id.btn_percentage);
        assignId(divideBtn, R.id.btn_divide);
        assignId(multiplyBtn, R.id.btn_multiply);
        assignId(minusBtn, R.id.btn_minus);
        assignId(additionBtn, R.id.btn_add);
        assignId(equalsBtn, R.id.btn_equals);
        assignId(dotBtn, R.id.btn_dot);
        //
        numbersBtn.add((MaterialButton) findViewById(R.id.btn_0));
        assignId(numbersBtn.get(0), R.id.btn_0);

        numbersBtn.add(findViewById(R.id.btn_1));
        assignId(numbersBtn.get(1), R.id.btn_1);

        numbersBtn.add(findViewById(R.id.btn_2));
        assignId(numbersBtn.get(2), R.id.btn_2);

        numbersBtn.add(findViewById(R.id.btn_3));
        assignId(numbersBtn.get(3), R.id.btn_3);

        numbersBtn.add(findViewById(R.id.btn_4));
        assignId(numbersBtn.get(4), R.id.btn_4);

        numbersBtn.add(findViewById(R.id.btn_5));
        assignId(numbersBtn.get(5), R.id.btn_5);

        numbersBtn.add(findViewById(R.id.btn_6));
        assignId(numbersBtn.get(6), R.id.btn_6);

        numbersBtn.add(findViewById(R.id.btn_7));
        assignId(numbersBtn.get(7), R.id.btn_7);

        numbersBtn.add(findViewById(R.id.btn_8));
        assignId(numbersBtn.get(8), R.id.btn_8);

        numbersBtn.add(findViewById(R.id.btn_9));
        assignId(numbersBtn.get(9), R.id.btn_9);


    }

    private void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this::onBtnClick);
    }

    private void onBtnClick(View view) {
        MaterialButton clickingBtn = (MaterialButton) view;
        String clickingBtnText = clickingBtn.getText().toString();
        if (validNumberCharacters.contains(clickingBtnText)) {
            if (isMathBtnClicking) {
                if(isCalculated) {
                    param2 = "";
                }
                param2 += clickingBtnText;
                resultText = param2;

            } else {
                if(isCalculated){
                   param1 = "";
                }
                param1 += clickingBtnText;
                resultText = param1;
            }

            if(isCalculated){
                isCalculated = false;
            }

        } else if (clickingBtnText.equalsIgnoreCase("AC")) {
            if(resultText.equalsIgnoreCase(param1)){
                param1 = "0";
            }
            else if(resultText.equalsIgnoreCase(param2)){
                param2 = "0";
            }
            resultText = "0";
        } else if (clickingBtnText.equalsIgnoreCase("=")) {
            resultText = calResult();
            param1 = resultText;
            isMathBtnClicking = false;
            isCalculated = true;
        }
        // inverse sign + / -
        else if(clickingBtnText.charAt(0) == '\u00b1'){
            if(resultText.equalsIgnoreCase(param1)){

                param1 = Float.toString(Float.parseFloat(param1) * -1);
                resultText = param1;
            }
            else if (resultText.equalsIgnoreCase(param2)){
                param2 = Float.toString(Float.parseFloat(param2) * -1);
                resultText = param2;
            }
        }
        // %
        else if(clickingBtnText.charAt(0) == '\u0025'){
            if(resultText.equalsIgnoreCase(param1)){
                param1 = Float.toString(Float.parseFloat(param1) / 100);
                resultText = param1;
            }
            else if (resultText.equalsIgnoreCase(param2)){
                param2 = Float.toString(Float.parseFloat(param2) / 100);
                resultText = param2;
            }
        }
        else {
            isCalculated = true;
            isMathBtnClicking = true;
            currentCalculation = clickingBtnText.charAt(0);
            return;

        }
        //
        calculatedResult.setText(resultText);
        //


    }
    private String formatResult(String finalResult){
        String formattedResult = finalResult;
        // removing leading zero
        formattedResult = formattedResult.replaceFirst("^(-)?0+(?=\\d)", "$1");
        // removing .0
        formattedResult = formattedResult.replaceFirst("\\.0$", "");
        return formattedResult;
    }
    private String calResult() {
        float num1 = Float.parseFloat(param1);
        float num2 = Float.parseFloat(param2);
        float finalResult = 0.f;
        String currentCalculationText = calculations.get(currentCalculation);
        switch (currentCalculationText){
            case "+": {
                finalResult = num1 + num2;
                break;
            }
            case "-": {
                finalResult = num1 - num2;
                break;
            }
            case "*": {
                finalResult = num1 * num2;
                break;
            }
            case "/": {
                finalResult = num1 / num2;
                break;
            }
            case "per": {
                finalResult = num1 + num2;
                break;
            }
            case "inv": {
                finalResult = num1 + num2;
                break;
            }
        }
            return formatResult(Float.toString(finalResult));
    }

}