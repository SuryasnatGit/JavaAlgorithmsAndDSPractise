package com.designpatterns;

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
 */
public class ObserverPattern {

	interface Subject {
		public void registerObserver(Observer ob);

		public void unregisterObserver(Observer ob);

		public void notifyObservers();

		public String getSubDetails();
	}

	interface Observer {
		public void update(String desc);

		public void subscribe();

		public void unsubscribe();
	}

	/**
	 * commentary object to update sports commentary
	 * 
	 * @author surya
	 *
	 */
	interface Commentary {
		public void setDesc(String comm);
	}

	class CommentaryObject implements Subject, Commentary {

		private final List<Observer> observers;
		private String description;
		private String subjectDetails;

		public CommentaryObject(List<Observer> observers, String subDetails) {
			this.observers = observers;
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
		public String getSubDetails() {
			return subjectDetails;
		}
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
			System.out.println("Desc :" + desc);
		}

		@Override
		public void subscribe() {
			System.out.println("Subscribing " + userInfo + " to " + subject.getSubDetails());
			subject.registerObserver(this);
			System.out.println("Subscribed successfully");
		}

		@Override
		public void unsubscribe() {
			System.out.println("UnSubscribing " + userInfo + " to " + subject.getSubDetails());
			subject.unregisterObserver(this);
			System.out.println("UnSubscribed successfully");
		}

	}
}
