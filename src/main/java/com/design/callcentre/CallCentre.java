package com.design.callcentre;

import java.util.ArrayList;
import java.util.List;

// TODO : some items cleanup
public class CallCentre {

	private List<List<Employee>> handlers = new ArrayList<List<Employee>>();
	private List<List<Call>> callQueue = new ArrayList<List<Call>>();

	private static CallCentre instance;

	// return singleton instance of Call Centre
	public static CallCentre getInstance() {
		if (instance == null) {
			instance = new CallCentre();
		}
		return instance;
	}

	public void dispatchCall(Caller caller) {
		Call call = new Call(caller); // Add additional atttribute like call-in time
		dispatchCall(call);
	}

	public void dispatchCall(Call call) {
		Rank rank = call.getRank();

		// Try to route the call to an employee with minimal rank.
		List<Employee> rep = handlers.get(rank.getRank());
		for (Employee emp : rep) {
			if (emp.isFree()) {
				emp.assignNewCall(call);
				call.setHandler(emp);
				break;
			}
		}

		// if no handlers able to be obtained then place the call in the corresponding call queue according to its rank
		if (call.getHandler() == null) {
			call.reply("Please wait for the next available representative..");
			callQueue.get(rank.getRank()).add(call);
		}
	}

	/**
	 * An employee got free. Look for a waiting call that emp. can serve. Return true if we assigned a call, false
	 * otherwise
	 */
	public boolean assignCall(Employee emp) {
		// TODO. complete it.
		return true;
	}

}
