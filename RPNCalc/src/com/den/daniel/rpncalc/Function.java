package com.den.daniel.rpncalc;

import java.util.ArrayList;
import java.util.Stack;

import android.content.res.Resources;

public abstract class Function {
	private Stack<Double> stack;
	private int arity;
	private Resources resources;
	
	protected abstract ArrayList<Double> operate(ArrayList<Double> args);
	
	public Function(Stack<Double> stack, Resources resources, int arity) {
		this.stack = stack;
		this.resources = resources;
		this.arity = arity;
	}
	
	private ArrayList<Double> popArgs() throws IllegalArgumentException {
		ArrayList<Double> args = new ArrayList<Double>();
		
		if (stack.size() < arity) {
			throw new IllegalArgumentException(resources.getString(R.string.fewArgError));
		}
		
		for (int i = 0; i < arity; i++) {
			args.add(stack.pop());
		}
		
		return args;
	}
	
	public void work() throws IllegalArgumentException {
		ArrayList<Double> args = popArgs();
		ArrayList<Double> result;
		
		result = operate(args);
		
		for (Double double1 : result) {
			stack.push(double1);
		}
	}
	

	public int getArity() {
		return arity;
	}
}
