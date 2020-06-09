package com.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelURLHitCounter {

	private static final int CAPACITY = 1000;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>> entryContainer;

	public ParallelURLHitCounter() {
		entryContainer = Collections
				.synchronizedSortedMap(new TreeMap<LocalDateTime, ConcurrentHashMap<String, Integer>>());
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
		ParallelURLHitCounter hitCounter = new ParallelURLHitCounter();

		Queue<Entry> queue = new LinkedBlockingQueue<>(CAPACITY);

		List<String> logs = hitCounter.generateLogs();

		EntryProducer producer = hitCounter.createProducerObject(queue, logs);

		Thread publisherThread = new Thread(producer);
		// System.out.println("publisher starting..");
		publisherThread.start();

		// System.out.println("publisher shutting..");
		try {
			publisherThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		EntryConsumer consumer = hitCounter.createConsumerObject(queue);

		// System.out.println("consumer starting..");
		ExecutorService consumerThread = Executors.newFixedThreadPool(100);
		SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>> map = null;
		while (!queue.isEmpty()) {
			Future<SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>>> future = consumerThread
					.submit(consumer);
			map = future.get();
		}
		// System.out.println("entryContainer :" + map);

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<LocalDateTime, ConcurrentHashMap<String, Integer>> entry : map.entrySet()) {
			ConcurrentHashMap<String, Integer> value = entry.getValue();
			List<ProcessedEntry> list = new ArrayList<>();
			for (Map.Entry<String, Integer> entry1 : value.entrySet()) {
				ProcessedEntry pe = new ProcessedEntry(entry1.getKey(), entry1.getValue());
				list.add(pe);
			}

			Comparator<ProcessedEntry> comp = Comparator.comparing(ProcessedEntry::getCount, (a, b) -> b - a)
					.thenComparing(ProcessedEntry::getUrl);
			Collections.sort(list, comp);
			// System.out.println("comparator list :" + list);
			LocalDateTime ldt = entry.getKey();
			sb.append(ldt.format(FORMATTER)).append(" ").append("GMT").append(System.lineSeparator());
			list.forEach(pe -> sb.append(pe.getUrl()).append(" ").append(pe.getCount()).append(System.lineSeparator()));
		}
		// System.out.println("entryContainer 1:" + map);

		System.out.println(sb.toString());

		// System.out.println("consumer shutting..");
		consumerThread.shutdown();

	}

	private EntryConsumer createConsumerObject(Queue<Entry> queue) {
		EntryConsumer consumer = new EntryConsumer();
		consumer.setEntryContainer(entryContainer);
		consumer.setQueue(queue);
		return consumer;
	}

	private EntryProducer createProducerObject(Queue<Entry> queue, List<String> logs) {
		EntryProducer producer = new EntryProducer();
		producer.setQueue(queue);
		producer.sendToProducer(logs);
		return producer;
	}

	private List<String> generateLogs() throws FileNotFoundException {
		List<String> l = new ArrayList<>();
		Scanner sc = new Scanner(new File(
				"/Users/M_402201/Developer/Code/personal/JavaAlgorithmsAndDSPractise/src/main/java/com/hackerrank/nyansadata.txt"));
		// Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			// System.out.println(line);
			l.add(line);
		}
		sc.close();

		return l;
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

class ProcessedEntry {
	String url;
	int count;

	public ProcessedEntry(String url, int count) {
		this.url = url;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "ProcessedEntry [url=" + url + ", count=" + count + "]";
	}
}

class EntryProducer implements Runnable {

	private Queue<Entry> queue;
	private List<String> logs;

	@Override
	public void run() {

		synchronized (this) {

			for (String log : logs) {
				// System.out.println("log :" + log);
				String[] parts = log.split("\\|");
				long ts = Long.valueOf(parts[0]);
				// System.out.println(ts);
				String url = parts[1];
				// System.out.println(url);
				Entry entry = new Entry(ts, url);

				if (queue.size() == 1000) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				queue.add(entry);
			}
			// System.out.println("Producer notifying..");
			this.notifyAll();
		}
	}

	public void setQueue(Queue<Entry> queue) {
		this.queue = queue;
	}

	public void sendToProducer(List<String> logs) {
		this.logs = logs;
	}

}

class EntryConsumer implements Callable<SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>>> {

	private Queue<Entry> queue;
	private SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>> entryContainer;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	public EntryConsumer() {
	}

	private SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>> processEntry(Entry polledEntry) {
		// System.out.println("consumer thread :" + Thread.currentThread().getName());
		long ts = polledEntry.getTimestampInMs();
		String url = polledEntry.getUrl();
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(ts, 0, ZoneOffset.UTC);
		String formattedDate = localDateTime.format(FORMATTER);
		localDateTime = LocalDate.parse(formattedDate, FORMATTER).atStartOfDay();

		ConcurrentHashMap<String, Integer> entryMap = null;
		if (entryContainer.containsKey(localDateTime)) {
			entryMap = entryContainer.get(localDateTime);
			entryMap.put(url, entryMap.containsKey(url) ? entryMap.get(url) + 1 : 1);
		} else {
			entryMap = new ConcurrentHashMap<String, Integer>();
			entryMap.put(url, 1);
		}
		entryContainer.put(localDateTime, entryMap);
		return entryContainer;
	}

	public void setQueue(Queue<Entry> queue) {
		this.queue = queue;
	}

	public void setEntryContainer(SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>> ec) {
		this.entryContainer = ec;
	}

	@Override
	public SortedMap<LocalDateTime, ConcurrentHashMap<String, Integer>> call() throws Exception {
		while (true) {
			Entry polledEntry = queue.poll();
			return processEntry(polledEntry);
		}
	}
}