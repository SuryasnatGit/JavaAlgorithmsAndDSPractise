package com.hackerrank;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelURLHitCounter {

	private Queue<Entry> queue;
	private EntryProducer producer = new EntryProducer();
	private ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> entryContainer = new ConcurrentHashMap<>();

	private static final int DEFAULT_CAPACITY = 1000;

	public ParallelURLHitCounter() {
		this.queue = new LinkedBlockingQueue<>(DEFAULT_CAPACITY);
	}

	public ParallelURLHitCounter(int capacity) {
		this.queue = new LinkedBlockingQueue<>(capacity);
	}

	public void consume(List<String> logs) {
		for (String log : logs) {
			String[] parts = log.split("|");
			long ts = Long.parseLong(parts[0]);
			String url = parts[1];
			Entry entry = new Entry(ts, url);

		}
	}

}

// Class Structure
class Entry {

	private long timestampInMs;
	private String url;

	public Entry(final long tsInMs, final String url) {
		this.timestampInMs = tsInMs;
		this.url = url;
	}

	public long getTimestampInMs() {
		return timestampInMs;
	}

	public String getUrl() {
		return url;
	}
}

class EntryProducer implements Runnable {

	private Queue<Entry> queue;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void setQueue(Queue<Entry> queue) {
		this.queue = queue;
	}

}

class EntryConsumer implements Runnable {

	private Queue<Entry> queue;
	private ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> entryContainer;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/YYYY");

	public EntryConsumer() {
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (queue.isEmpty()) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Entry polledEntry = queue.poll();
				processEntry(polledEntry);
			}
		}

	}

	private void processEntry(Entry polledEntry) {
		long ts = polledEntry.getTimestampInMs();
		String url = polledEntry.getUrl();
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(ts, 0, ZoneOffset.UTC);
		String formattedDate = localDateTime.format(FORMATTER);

		ConcurrentHashMap<String, Integer> urlCountMap = null;
		if (entryContainer.containsKey(formattedDate)) {
			urlCountMap = entryContainer.get(formattedDate);
			urlCountMap.put(url, urlCountMap.containsKey(url) ? urlCountMap.get(url) + 1 : 1);
		} else {
			urlCountMap = new ConcurrentHashMap<>();
			urlCountMap.put(url, 1);
		}
		entryContainer.put(formattedDate, urlCountMap);

	}

	public void setQueue(Queue<Entry> queue) {
		this.queue = queue;
	}

	public void setEntryContainer(ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> entryContainer) {
		this.entryContainer = entryContainer;
	}
}