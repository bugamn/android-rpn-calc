package com.den.daniel.rpncalc;

import java.util.ArrayList;
import java.util.Stack;

import android.content.res.Resources;

public class FunctionAddition extends Function {

	public FunctionAddition(Stack<Double> stack, Resources resources) {
		super(stack, resources, 2);
	}

	@Override
	protected ArrayList<Double> operate(ArrayList<Double> args) {
		ArrayList<Double> result = new ArrayList<Double>();
		result.add(args.get(0) + args.get(1));
		return result;
	}

}
