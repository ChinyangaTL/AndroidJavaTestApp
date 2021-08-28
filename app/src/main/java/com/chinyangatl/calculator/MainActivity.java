package com.chinyangatl.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView inputArea;
    private TextView redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputArea = findViewById(R.id.inputArea);
        redirect = findViewById(R.id.redirect);
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        initButtons();

    }

    private void initButtons() {
        ((Button)findViewById(R.id.clear)).setOnClickListener(this);
        ((Button)findViewById(R.id.backspace)).setOnClickListener(this);
        ((Button)findViewById(R.id.divide)).setOnClickListener(this);
        ((Button)findViewById(R.id.multiply)).setOnClickListener(this);
        ((Button)findViewById(R.id.seven)).setOnClickListener(this);
        ((Button)findViewById(R.id.eight)).setOnClickListener(this);
        ((Button)findViewById(R.id.nine)).setOnClickListener(this);
        ((Button)findViewById(R.id.minus)).setOnClickListener(this);
        ((Button)findViewById(R.id.four)).setOnClickListener(this);
        ((Button)findViewById(R.id.five)).setOnClickListener(this);
        ((Button)findViewById(R.id.six)).setOnClickListener(this);
        ((Button)findViewById(R.id.plus)).setOnClickListener(this);
        ((Button)findViewById(R.id.one)).setOnClickListener(this);
        ((Button)findViewById(R.id.two)).setOnClickListener(this);
        ((Button)findViewById(R.id.three)).setOnClickListener(this);
        ((Button)findViewById(R.id.equals)).setOnClickListener(this);
        ((Button)findViewById(R.id.percent)).setOnClickListener(this);
        ((Button)findViewById(R.id.zero)).setOnClickListener(this);
        ((Button)findViewById(R.id.decimal)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                clear();
                break;
            case R.id.backspace:
                delete();
                break;
            case R.id.equals:
                equate();
            default:
                inputNumber(v);
                break;
        }
    }

    private void equate() {
        Expression expression = new Expression(inputArea.getText().toString());
        String answer = String.valueOf(expression.calculate());
        inputArea.setText((answer));
    }

    private void inputNumber(View v) {
        if(inputArea.getText().toString().equals("0")) clear();
        Button button = findViewById(v.getId());
        inputArea.setText(inputArea.getText() + button.getText().toString());
    }


    private int delete() {
        Log.d(TAG, "delete: delete called");
        if(inputArea.getText().equals("")) return -1;
        String input = inputArea.getText().toString();
        inputArea.setText(input.substring(0, input.length()-1));
        return 1;
    }

    private void clear() {
        Log.d(TAG, "clear: clear called");
        inputArea.setText("");
    }
}