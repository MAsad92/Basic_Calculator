package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result,solution;
    MaterialButton button_C,button_Open_Bracket,button_Close_Bracket;
    MaterialButton button_Divide,button_Multiply,button_Add,button_Minus,button_Equal;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_AC,button_Dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result =findViewById(R.id.result);
        solution=findViewById(R.id.solution);

        assignId(button_C,R.id.button_C);
        assignId(button_Open_Bracket,R.id.button_Open_Bracket);
        assignId(button_Close_Bracket,R.id.button_Close_Bracket);
        assignId(button_Divide,R.id.button_Divide);
        assignId(button_Multiply,R.id.button_Multiply);
        assignId(button_Add,R.id.button_Add);
        assignId(button_Minus,R.id.button_Minus);
        assignId(button_Equal,R.id.button_Equal);
        assignId(button_0,R.id.button_0);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_AC,R.id.button_AC);
        assignId(button_Dot,R.id.button_Dot);


    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("Error")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith("0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Error";
        }
    }

}