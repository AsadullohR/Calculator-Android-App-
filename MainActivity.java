

package com.asadulloh.calculator;

/**
 * Created by Asadulloh Rakhimov on 20/02/2020**/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText newNumber;
    private EditText result;
    private TextView operation;

    private Double operand1 = null;
    private String pendingOperation = "=";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNumber = findViewById(R.id.newNumber);
        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonModule = findViewById(R.id.buttonModule);
        Button buttonEquals = findViewById(R.id.buttonEquals);


        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonDel = findViewById(R.id.buttonDel);
        Button buttonHistory = findViewById(R.id.buttonHistory);

        // This listener is for Deleting last entered digit
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentNumbers = newNumber.getText().toString();
                if (!currentNumbers.isEmpty())
                for (int i = 0; i < currentNumbers.length() ; i++) {
                    newNumber.setText(currentNumbers.substring(0,(currentNumbers.length()-1)));
                }
            }
        });

        // This listener is for Deleting ALL entered digits
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNumber.setText("");
                result.setText("");
                operation.setText("");
                operand1 = null;
                pendingOperation = "=";
            }
        });

        View.OnClickListener numbersListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(numbersListener);
        button1.setOnClickListener(numbersListener);
        button2.setOnClickListener(numbersListener);
        button3.setOnClickListener(numbersListener);
        button4.setOnClickListener(numbersListener);
        button5.setOnClickListener(numbersListener);
        button6.setOnClickListener(numbersListener);
        button7.setOnClickListener(numbersListener);
        button8.setOnClickListener(numbersListener);
        button9.setOnClickListener(numbersListener);
        buttonDot.setOnClickListener(numbersListener);

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                operation.setText(pendingOperation);
            }
        };

        buttonMinus.setOnClickListener(operationListener);
        buttonPlus.setOnClickListener(operationListener);
        buttonDivide.setOnClickListener(operationListener);
        buttonMultiply.setOnClickListener(operationListener);
        buttonModule.setOnClickListener(operationListener);
        buttonEquals.setOnClickListener(operationListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("PendingOperation",pendingOperation);
        if (operand1 != null){
            outState.putDouble("Operand1",operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString("PendingOperation");
        operand1 =savedInstanceState.getDouble("Operand1");
        operation.setText(pendingOperation);
    }

    public void performOperation(Double value, String operation) {
        if (null == operand1) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "÷":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "x":
                    operand1 *= value;
                    break;
                case "Mod":
                    operand1 %= value;
                    break;

            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");

    }

}
