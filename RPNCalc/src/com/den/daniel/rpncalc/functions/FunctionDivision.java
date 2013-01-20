package com.den.daniel.rpncalc.functions;

import java.util.ArrayList;
import java.util.Stack;

import com.den.daniel.rpncalc.Function;

import android.content.res.Resources;

public class FunctionDivision extends Function {

	public FunctionDivision(Stack<Double> stack, Resources resources) {
		super(stack, resources, 2);
	}

	@Override
	protected ArrayList<Double> operate(ArrayList<Double> args) {
		ArrayList<Double> result = new ArrayList<Double>();
		result.add(args.get(1) / args.get(0));
		return result;
	}

}
