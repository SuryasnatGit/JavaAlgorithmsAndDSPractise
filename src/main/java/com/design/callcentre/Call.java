package com.ooad.callcentre;

public class Call {
	// Minimal rank of employee who can handle this call
	private Rank rank;

	// Person who is calling
	private Caller caller;

	// Employee who is handling the call
	private Employee handler;

	private StringBuilder chatTranscript;

	public Call() {
		this.caller = null;
	}

	public Call(Caller caller) {
		this.caller = caller;
		// by default call goes to respondent
		this.rank = Rank.RESPONDENT;
		this.chatTranscript = new StringBuilder();
	}

	public void setHandler(Employee e) {
		this.handler = e;
	}

	public Employee getHandler() {
		return handler;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public void reply(String message) {
		chatTranscript.append(message);
	}

	public void incrementRank() {

	}

	public void disconnectCall(String message) {
		chatTranscript.append(message);
	}
}