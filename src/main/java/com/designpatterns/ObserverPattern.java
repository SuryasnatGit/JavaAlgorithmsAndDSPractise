package com.designpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Use the Observer pattern in any of the following situations:
 * 
 * When an abstraction has two aspects, one dependent on the other. Encapsulating these aspects in separate objects lets
 * you vary and reuse them independently.
 * 
 * When a change to one object requires changing others, and you don’t know how many objects need to be changed.
 * 
 * When an object should be able to notify other objects without making assumptions about who these objects are. In
 * other words, you don’t want these objects tightly coupled.
 *
 * TODO: uml pending
 */
public class ObserverPattern {

	/**
	 * implemented by news broadcaster to communicate with observers
	 */
	interface Subject {
		public void registerObserver(Observer ob);

		public void unregisterObserver(Observer ob);

		public void notifyObservers();

		public String getSubjectDetails();
	}

	/**
	 * commentary object to update sports commentary
	 */
	interface Commentary {
		public void setDesc(String comm);
	}

	class CommentaryObject implements Subject, Commentary {

		private final List<Observer> observers;
		private String description;
		private String subjectDetails;

		public CommentaryObject(String subDetails) {
			this.observers = new ArrayList<>();
			this.subjectDetails = subDetails;
		}

		@Override
		public void setDesc(String comm) {
			this.description = comm;
			notifyObservers();
		}

		@Override
		public void registerObserver(Observer ob) {
			observers.add(ob);
		}

		@Override
		public void unregisterObserver(Observer ob) {
			int index = observers.indexOf(ob);
			observers.remove(index);
		}

		@Override
		public void notifyObservers() {
			for (Observer ob : observers) {
				ob.update(description);
			}
		}

		@Override
		public String getSubjectDetails() {
			return subjectDetails;
		}
	}

	/**
	 * This interface is implemented by all those classes that are to be updated whenever there is an update from
	 * CricketData
	 */
	interface Observer {
		public void update(String desc);

		public void subscribe();

		public void unsubscribe();
	}

	class SMSUser implements Observer {

		private Subject subject;
		private String desc;
		private String userInfo;

		public SMSUser(Subject sub, String userInfo) {
			this.subject = sub;
			this.userInfo = userInfo;
		}

		@Override
		public void update(String desc) {
			this.desc = desc;
			display();
		}

		private void display() {
			System.out.println("SMS Desc :" + desc);
		}

		@Override
		public void subscribe() {
			System.out.println("Subscribing " + userInfo + " to " + subject.getSubjectDetails());
			subject.registerObserver(this);
			System.out.println("Subscribed successfully");
		}

		@Override
		public void unsubscribe() {
			System.out.println("UnSubscribing " + userInfo + " to " + subject.getSubjectDetails());
			subject.unregisterObserver(this);
			System.out.println("UnSubscribed successfully");
		}
	}

	class VoiceUser implements Observer {

		private Subject subject;
		private String desc;
		private String userInfo;

		public VoiceUser(Subject sub, String userInfo) {
			this.subject = sub;
			this.userInfo = userInfo;
		}

		@Override
		public void update(String desc) {
			this.desc = desc;
			display();
		}

		@Override
		public void subscribe() {
			System.out.println("Subscribing " + userInfo + " to " + subject.getSubjectDetails());
			subject.registerObserver(this);
			System.out.println("Subscribed successfully");
		}

		@Override
		public void unsubscribe() {
			System.out.println("UnSubscribing " + userInfo + " to " + subject.getSubjectDetails());
			subject.unregisterObserver(this);
			System.out.println("UnSubscribed successfully");
		}

		private void display() {
			System.out.println("Voice Desc :" + desc);
		}

	}

	public static void main(String[] args) {
		CommentaryObject sub = new ObserverPattern().new CommentaryObject("weather update");

		SMSUser smsUser = new ObserverPattern().new SMSUser(sub, "smsuser");
		VoiceUser voiceUser = new ObserverPattern().new VoiceUser(sub, "voiceuser");

		sub.registerObserver(smsUser);
		sub.registerObserver(voiceUser);

		sub.setDesc("alert. winds rushing at 50MPH");

		sub.unregisterObserver(voiceUser);

		sub.setDesc("warning. things back to normal");

	}
}
