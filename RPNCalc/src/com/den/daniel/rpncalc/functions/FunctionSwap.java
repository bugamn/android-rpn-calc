package com.den.daniel.rpncalc.functions;

import java.util.ArrayList;
import java.util.Stack;

import android.content.res.Resources;

import com.den.daniel.rpncalc.Function;

public class FunctionSwap extends Function {

	public FunctionSwap(Stack<Double> stack, Resources resources) {
		super(stack, resources, 2);
	}

	@Override
	protected ArrayList<Double> operate(ArrayList<Double> args) {
		ArrayList<Double> result = new ArrayList<Double>();
		result.add(args.get(0));
		result.add(args.get(1));
		return result;
	}

}
