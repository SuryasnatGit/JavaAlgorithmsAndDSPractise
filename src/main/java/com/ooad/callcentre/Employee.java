package com.ooad.callcentre;

/**
 * Base class for all employees
 *
 */
public abstract class Employee {

	private Rank rank;
	private Call currentCall = null;

	void escalateAndReassign(Call call) {
		int escalatedRankNum = call.getRank().getRank() + 1;
		for (Rank rank : Rank.values()) {
			if (rank.getRank() == escalatedRankNum) {
				call.setRank(rank);
				break;
			}
		}

		new CallCentre().dispatchCall(call);
	}

	/**
	 * Assign a new call to an employee if the employee is free
	 */
	public void assignNewCall(Call call) {

	}

	/**
	 * Start the conversation
	 */
	public boolean pickUpCall(Call call) {
		this.currentCall = call;
		call.setHandler(this);
		return true;
	}

	/**
	 * Issue resolved. finish the call
	 * 
	 */
	public void finishCall(Call call) {
		this.currentCall = null;
		call.disconnectCall("Call over");
	}

	/**
	 * Returns if employee is free or not to take current call
	 */
	public boolean isFree() {
		return currentCall == null;
	}

	public Rank getRank() {
		return rank;
	}
}
