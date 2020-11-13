package com.designpatterns;

/**
 * In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes
 * under behavior pattern. In Strategy pattern, we create objects which represent various strategies and a context
 * object whose behavior varies as per its strategy object. The strategy object changes the executing algorithm of the
 * context object.
 *
 */
public class StrategyPattern {

	public static void main(String[] args) {
		Context ctx = new Context(new AddStrategy());
		System.out.println(ctx.executeStrategy(7, 5));

		ctx = new Context(new SubtractStrategy());
		System.out.println(ctx.executeStrategy(7, 5));
	}
}

interface Strategy {
	int doOperation(int num1, int num2);
}

class AddStrategy implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}
}

class SubtractStrategy implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}
}

class Context {
	Strategy strategy;

	public Context(final Strategy strategy) {
		this.strategy = strategy;
	}

	public int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}