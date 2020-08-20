package com.companyprep.amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO : some items cleanup
public class CallCentreDesign {

	private List<List<Representative>> handlers = new ArrayList<List<Representative>>();
	private List<LinkedList<Call>> calls = new ArrayList<LinkedList<Call>>();

	public void dispatchCall(Caller caller) {
		Call call = new Call(caller); // Add additional atttribute like call-in time
		dispatchCall(call);
	}

	public void dispatchCall(Call call) {
		Rank rank = call.getRank();

		List<Representative> rep = handlers.get(level);
		for (Representative r : rep) {
			if (r.currentCall == null) {
				r.currentCall = call;
				call.setHandler(r);
				break;
			}
		}

		if (call.handler == null) {
			call.setRank(+1);
			if (call.getRank() < 4) {
				dispatchCall(call);
			} else {
				// wait in queue
				calls.get(call.getRank()).offer(call);
			}
		}
	}

	abstract class Representative {
		private Rank rank;
		private String name;
		private Call currentCall; // This will also decide if this representative is available or not

		void escalateCallToNextLeval(Call call) {
			call.setRank(call.getRank().getRank() + 1);

			CallCenter.dispatchCall(call);
		}

		boolean pickUpCall(Call call) {
			this.currentCall = call;
			call.setHandler(this);
		}

		void finishCall(Call call) {
			this.currentCall = null;
			call.setResult(this, "Done OR Escalated");
		}
	}

	public class Director extends Representative {

		private Rank rank;

		public Director() {
			this.rank = Rank.DIRECTOR;
		}

		public void escalateCallToNextLeval(Call call) {
			call.setRank(this.rank.getRank() + 1);
		}
	}

	public enum Rank {
		RESPONDENT(3), MANAGER(2), DIRECTOR(1);

		private int value;

		private Rank(int value) {
			this.value = value;
		}

		public int getRank() {
			return value;
		}
	}

	public class Call {
		private Rank rank;
		private Caller caller;
		private Representative handler;

		public Call() {
			this.caller = null;
		}

		public Call(Caller caller) {
			this.caller = caller;
			this.rank = Rank.RESPONDENT;
		}

		public void setHandler(Representative handler) {
			this.handler = handler;
		}

		public Rank getRank() {
			return rank;
		}

		public void setRank(Rank rank) {
			this.rank = rank;
		}
	}

	class Caller {

	}
}
