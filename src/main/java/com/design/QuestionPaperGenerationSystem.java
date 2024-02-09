package com.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class QuestionPaperGenerationSystem {

	private Set<Integer> stateNums = new HashSet<>();
	private Set<Integer> capitalNums = new HashSet<>();

	// contains mapping of state / capital of US states
	class DataStore {

		private Map<String, String> stateCapMap;

		public DataStore() {
			stateCapMap = new HashMap<>();
			init();
		}

		private void init() {
			stateCapMap.put("California", "Sacramento");
			stateCapMap.put("Alabama", "Montogomery");
			stateCapMap.put("Arizona", "Phoenix");
			stateCapMap.put("Colorado", "Denver");
			stateCapMap.put("Delaware", "Dover");
			stateCapMap.put("Kansas", "Topeka");
			stateCapMap.put("Montana", "Helena");
			stateCapMap.put("Ohio", "Columbus");
		}

		public List<String> getStateList() {
			List<String> states = new ArrayList<>();
			stateCapMap.keySet().forEach(s -> states.add(s));
			return states;
		}

		public List<String> getCapitalList() {
			List<String> capitalList = new ArrayList<>();
			stateCapMap.values().forEach(c -> capitalList.add(c));
			return capitalList;
		}

		public Map<String, String> getStateCapMap() {
			return stateCapMap;
		}
	}

	static class Util {

		public static int getRandomNumber() {
			Random r = new Random();
			return r.nextInt(8);
		}
	}

	// each question has 5 random answers
	class Question {

		private DataStore ds;
		private String state;
		private List<String> capitals;

		public Question() {
			ds = new DataStore();
			state = null;
			capitals = new ArrayList<>();
		}

		public void setState() {
			int num = checkUniqueStateNum();
			System.out.println("State random number :" + num);
			state = ds.getStateList().get(num);
		}

		private int checkUniqueStateNum() {
			int num = Util.getRandomNumber();
			if (!stateNums.contains(num))
				stateNums.add(num);
			else {
				return checkUniqueStateNum();
			}
			return num;
		}

		public void setCapitals() {
			// first clear the capital set
			capitalNums.clear();

			String trueCap = ds.getStateCapMap().get(state);
			int count = 0;
			while (count++ < 4) {
				int num = checkUniqueCapitalNumber();
				System.out.println("Capital random number :" + num);
				String randomCap = ds.getCapitalList().get(num);
				if (randomCap.equalsIgnoreCase(trueCap))
					count--;
				else
					capitals.add(randomCap);
			}
			System.out.println(capitalNums);

			capitals.add(trueCap);

			Collections.shuffle(capitals);
		}

		private int checkUniqueCapitalNumber() {
			int num = Util.getRandomNumber();
			if (!capitalNums.contains(num)) {
				capitalNums.add(num);
			} else
				return checkUniqueCapitalNumber();

			return num;
		}

		@Override
		public String toString() {
			return "Question [state=" + state + ", capitals=" + capitals + "]";
		}
	}

	class QuestionPaper {

		private List<Question> list;

		public QuestionPaper() {
			list = new ArrayList<>(5);
		}

		public void formQuestions() {
			for (int i = 0; i < 5; i++) {
				Question q = new Question();
				q.setState();
				q.setCapitals();
				list.add(q);
			}
			list.forEach(a -> System.out.println(a));
		}
	}

	public static void main(String[] args) {
		QuestionPaper ques = new QuestionPaperGenerationSystem().new QuestionPaper();
		ques.formQuestions();
	}

}
