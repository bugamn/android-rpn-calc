package com.den.daniel.rpncalc;

import java.util.HashMap;
import java.util.Stack;

import com.den.daniel.rpncalc.functions.FunctionAddition;
import com.den.daniel.rpncalc.functions.FunctionDivision;
import com.den.daniel.rpncalc.functions.FunctionMultiplication;
import com.den.daniel.rpncalc.functions.FunctionSubtraction;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends Activity {
	private Stack<Double> stack;
	public final static String errorPrefix = "ERROR:";
	private HashMap<Integer, Function> functionsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        stack = new Stack<Double>();
        functionsMap = new HashMap<Integer, Function>();
        
        loadFunctions();
    }

    private void loadFunctions() {
		functionsMap.put(R.id.buttonPlus, new FunctionAddition(stack, getResources()));
		functionsMap.put(R.id.buttonMinus, new FunctionSubtraction(stack, getResources()));
		functionsMap.put(R.id.buttonMultiply, new FunctionMultiplication(stack, getResources()));
		functionsMap.put(R.id.buttonDivide, new FunctionDivision(stack, getResources()));
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
        return true;
    }
    
    public void addNum(View view) {
		String alg = ((Button)view).getText().toString();
		TextView textView = (TextView) findViewById(R.id.textView1);
		String previous = textView.getText().toString();
		if (previous.startsWith(errorPrefix)) {
			textView.setText(alg);
		} else {
			textView.setText(previous + alg);
		}
	}
    
    private void updateScreen() {
		TextView display = (TextView) findViewById(R.id.display);
		display.setText("");
    	for (int i = 0; i < stack.size(); i++) {
    		display.append(String.valueOf(i) + ":" + stack.get(stack.size() - (i+1)) + "\n");
		}
    }
    
    public void push(View view) {
		TextView textView = (TextView) findViewById(R.id.textView1);
		try {
			Double value = Double.valueOf(textView.getText().toString());
			stack.push(value);
			updateScreen();
			textView.setText("");
		} catch (NumberFormatException e) {
			textView.setText(errorPrefix + "Invalid Entry");
		}
	}
    
    public void add(View view) {
    	
		TextView textView = (TextView) findViewById(R.id.textView1);
		Double arg1, arg2;
		if (textView.getText().length() > 0) {
			try {
				arg1 = Double.valueOf(textView.getText().toString());
				textView.setText("");
			} catch (NumberFormatException e) {
				textView.setText(errorPrefix + "Invalid Entry");
				return;
			}
		} else {
			arg1 = stack.pop();
		}
		arg2 = stack.pop();
		stack.push(arg1 + arg2);
		updateScreen();
	}
    
    public void runFunction(View view) {
		TextView textView = (TextView) findViewById(R.id.textView1);
		int operation = ((Button)view).getId();
		Function function = functionsMap.get(operation);
		Double temp;
		
		if (textView.getText().length() > 0) {
			try {
				temp = Double.valueOf(textView.getText().toString());
				textView.setText("");
				stack.push(temp);
			} catch (NumberFormatException e) {
				// TODO move to strings.xml
				textView.setText(errorPrefix + "Invalid Entry");
				return;
			}
		}
		if (function == null) {
			throw new Error("Olhe-me!");
		}
		try {
			function.work();
		} catch (IllegalArgumentException e) {
			// TODO move string to here
			textView.setText(errorPrefix + e.getMessage());
		}
		updateScreen();
	}
}
