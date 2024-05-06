package com.design.eventbus;

import java.util.HashMap;
import java.util.Map;

/**
 * The electric side is to write an EventBus to complete the following three functions to listen for events. An event
 * can have multiple listeners, and multiple threads may call the functions at the same time. Some listener callback
 * functions may run for a long time, causing other listeners to wait for a long time before being triggered. These
 * problems must be solved. The interview was for a mobile position in the Uber pool group.
 * 
 * The EventBus allows for objects to subscribe for or publish events, without having explicit knowledge of each other.
 * The EventBus is not meant to be a general purpose publish/subscribe system or support interprocess communication. The
 * EventBus is very flexible and can be used as a singleton, or an application can have several instances to accomodate
 * transferring events in different contexts. The EventBus will dispatch all events serially, so it is important to keep
 * the event handling methods lightweight. If you need to do heavier processing in the event handlers, there is another
 * flavor of the EventBus, AsyncEventBus. The AsyncEventBus is identical in functionality, but takes an ExecutorService
 * as a constructor argument to allow for asynchronous dispatching of events.
 *
 */
public class EventBus {
	Map<String, Listener> map = new HashMap<>();
	// Add Lock to Listener level

	public void register(String eventName, Listener listener) {
		map.put(eventName, listener);
	}

	public void unregister(String eventName, Listener listener) {
		map.remove(eventName);
	}

	public void postEvent(String eventName, Object data) {
		map.get(eventName).process(data);
	}
}

class PurchaseListener implements Listener {
	public void process(Object object) {
		System.out.println(object);
	}
}

class SellListener implements Listener {
	public void process(Object object) {
		System.out.println(object);
	}
}

interface Listener {
	void process(Object object);
}

class TestMain {
	public static void main(String[] args) {
		EventBus bus = new EventBus();
		bus.register("PurchaseEvent", new PurchaseListener());
		bus.register("SellEvent", new SellListener());

		bus.postEvent("SellEvent", "SellEvent---Data");
	}
}