package com.misc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Database {

	private LinkedList<TransactionBlock> transactionBlocks;

	// initialize db with empty transaction
	public Database() {
		transactionBlocks = new LinkedList<>();
		transactionBlocks.add(new TransactionBlock());
	}

	public void set(String name, Integer value) {
		transactionBlocks.getLast().set(name, value);
	}

	public Integer get(String name) {
		return transactionBlocks.getLast().get(name);
	}

	public Integer getCount(Integer value) {
		return transactionBlocks.getLast().getCount(value);
	}

	public boolean commit() {
		if (transactionBlocks.size() < 1)
			return false;

		// holder for name-value pairs
		Map<String, Integer> nameValues = new HashMap<>();
		// holder for value counter
		Map<Integer, Integer> valueCounts = new HashMap<>();

		Iterator<TransactionBlock> iterator = transactionBlocks.iterator();
		while (iterator.hasNext()) {
			TransactionBlock next = iterator.next();
			nameValues.putAll(next.getNameValues());
		}

		for (Map.Entry<String, Integer> entry : nameValues.entrySet()) {
			Integer value = entry.getValue();
			if (valueCounts.get(value) == null) {
				valueCounts.put(value, 1);
			} else {
				valueCounts.put(value, valueCounts.get(value) + 1);
			}
			// nameValues.put(entry.getKey(), entry.getValue());
		}

		transactionBlocks = new LinkedList<>();
		transactionBlocks.add(new TransactionBlock(nameValues, valueCounts));

		return true;
	}

	public boolean rollback() {
		if (transactionBlocks.size() < 1)
			return false;
		transactionBlocks.removeLast();
		return true;
	}

	public void begin() {
		TransactionBlock block = new TransactionBlock();
		block.setPrevious(transactionBlocks.getLast());
		transactionBlocks.add(block);
	}

	public static void main(String[] args) {
		Database db = new Database();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String cmd = sc.nextLine();
			String[] tokens = cmd.split(" ");
			String command = tokens[0];
			String name;
			Integer val;
			switch (command) {
			case "GET":
				name = tokens[1];
				System.out.println(db.get(name) != null ? db.get(name) : "NULL");
				break;
			case "SET":
				name = tokens[1];
				val = Integer.parseInt(tokens[2]);
				db.set(name, val);
				break;
			case "DELETE":
				name = tokens[1];
				db.set(name, null);
				break;

			case "COUNT":
				val = Integer.parseInt(tokens[1]);
				System.out.println(db.getCount(val));
				break;

			case "BEGIN":
				db.begin();
				break;
			case "ROLLBACK":
				if (!db.rollback())
					System.out.println("NO TRANSACTION");
				break;
			case "COMMIT":
				if (!db.commit())
					System.out.println("NO TRANSACTION");
				break;
			case "END":
				return;
			case "":
				break;
			default:
				System.out.println("invalid command");
			}
		}

		sc.close();
	}
}

class TransactionBlock {

	// to keep track of the previous block
	private TransactionBlock previous;

	// holder for name-value pairs
	private Map<String, Integer> nameValues = new HashMap<>();
	// holder for value counter
	private Map<Integer, Integer> valueCounts = new HashMap<>();

	// default constructor
	public TransactionBlock() {
		// TODO Auto-generated constructor stub
	}

	public TransactionBlock(Map<String, Integer> nameValues, Map<Integer, Integer> valueCounts) {
		this.valueCounts = valueCounts;
		this.nameValues = nameValues;
	}

	public TransactionBlock(Map<String, Integer> values) {
		this.nameValues = values;
	}

	public void setPrevious(TransactionBlock prev) {
		this.previous = prev;
	}

	public TransactionBlock getPrevious() {
		return previous;
	}

	public Map<String, Integer> getNameValues() {
		return nameValues;
	}

	public Map<Integer, Integer> getValueCounts() {
		return valueCounts;
	}

	public void set(String name, Integer value) {

		// get the count value and update in map. decrease count of old name value
		Integer prevValue = get(name);
		if (prevValue != null) {
			Integer prevValueCount = getCount(prevValue);
			valueCounts.put(prevValue, --prevValueCount);
		}

		Integer currValueCount = getCount(value);
		if (value != null) {
			if (currValueCount != null)
				valueCounts.put(value, ++currValueCount);
			else
				valueCounts.put(value, 1);
		}

		nameValues.put(name, value);
	}

	public Integer get(String name) {
		Integer value = getNameValues().get(name);

		// scan backwards through the transaction blocks
		TransactionBlock block = this;
		while (!block.getNameValues().containsKey(name) && block.getPrevious() != null) {
			block = block.previous;
			value = block.getNameValues().get(name);
		}

		return value;
	}

	public Integer getCount(Integer value) {
		if (value == null)
			return 0;

		TransactionBlock block = this;
		Integer count = block.getValueCounts().get(value);
		while (block.previous != null) {
			block = block.previous;
			count = block.getValueCounts().get(value);
		}

		return (count == null) ? 0 : count;
	}

}
