package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Question: Imagine you are UBER EAT and you have to give top k best selling food report. Find the top k best selling
 * food for each restaraunt.
 * 
 * Input#1; restaraunt_name, food_name "RestaurantA", "apple" "RestaurantA", "banana" "RestaurantA", "apple"
 * "RestaurantB", "coke" "RestaurantB", "coffee" "RestaurantB", "coffee" "RestaurantA", "banana" "RestaurantA", "orange"
 * 
 * input#2 int k
 * 
 * k = 2 output: RestaurantA => ["apple", "banana"] RestaurantB => ["coke", "coffee"]
 * 
 * k = 3 output: RestaurantA => ["apple", "banana", "orange"] RestaurantB => ["coke", "coffee"]
 * 
 * https://leetcode.com/discuss/interview-question/313432/UBER-TECHNICAL-PHONE-INTERVIEW-Find-the-top-k-best-selling-food-for-each-restaraunt.
 * 
 * Category : Medium
 */
public class UberEatsProblem {

	private Map<String, Map<String, Integer>> map = new HashMap<>();

	public void preprocess(List<Order> orders) {

		// get the food count for each restaurant
		for (Order order : orders) {
			String restaurant = order.restaurant;
			String food = order.food;
			if (!map.containsKey(restaurant)) {
				map.put(restaurant, new HashMap<>());
			}

			Map<String, Integer> foodCountMap = map.get(restaurant);
			foodCountMap.put(food, foodCountMap.getOrDefault(food, 0) + 1);
			map.put(restaurant, foodCountMap);
		}
	}

	public Map<String, List<String>> getTopKFoodsByRestaurant(int k) {
		Map<String, List<String>> result = new HashMap<>();

		// process each restaurant from map
		for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
			int c = k;

			String restaurant = entry.getKey();
			Map<String, Integer> foodMap = entry.getValue();

			PriorityQueue<FoodCount> pq = new PriorityQueue<>((fc1, fc2) -> fc2.count - fc1.count);
			for (Map.Entry<String, Integer> foodMapEntry : foodMap.entrySet()) {
				pq.add(new FoodCount(foodMapEntry.getKey(), foodMapEntry.getValue()));
			}

			List<String> foodList = new ArrayList<>();
			while (c-- > 0 && !pq.isEmpty()) {
				FoodCount fc = pq.poll();
				foodList.add(fc.food);
			}

			result.put(restaurant, foodList);
		}

		return result;
	}

	public static void main(String[] args) {
		UberEatsProblem u = new UberEatsProblem();
		List<Order> orders = new ArrayList<>();
		orders.add(new Order("A", "apple"));
		orders.add(new Order("A", "orange"));
		orders.add(new Order("A", "orange"));
		orders.add(new Order("B", "apple"));
		orders.add(new Order("B", "orange"));
		orders.add(new Order("B", "orange"));

		u.preprocess(orders);

		System.out.println(u.getTopKFoodsByRestaurant(1));
	}

}

class Order {
	String restaurant;
	String food;

	public Order(String r, String f) {
		this.restaurant = r;
		this.food = f;
	}
}

class FoodCount {
	String food;
	int count;

	public FoodCount(String f, int c) {
		this.food = f;
		this.count = c;
	}
}
