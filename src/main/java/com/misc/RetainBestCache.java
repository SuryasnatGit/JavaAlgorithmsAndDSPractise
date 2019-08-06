package com.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RetainBestCache<K, V extends Rankable> {

	private Map<K, V> cache; // actual cache data structure
	private TreeMap<Long, List<K>> rankMap; // contains mapping of rank to associated list of keys
	private DataSource<K, V> ds;
	private int capacity;

	/* Constructor with a data source (assumed to be slow) and a cache size */
	public RetainBestCache(DataSource<K, V> ds, int entriesToRetain) {
		this.cache = new HashMap<>(entriesToRetain);
		this.ds = ds;
		this.capacity = entriesToRetain;
		this.rankMap = new TreeMap<>();
	}

	/*
	 * Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
	 * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
	 * evicting the V with lowest rank among the ones that it has available If there is a tie, the cache
	 * may choose any T with lowest rank to evict.
	 */
	public V get(K key) {
		V value = null;
		if (cache.containsKey(key)) {
			value = cache.get(key);
		} else {
			// retrieve from data store
			value = ds.get(key);
			long rank = value.getRank();

			// update in cache
			cache.put(key, value);

			if (!rankMap.containsKey(rank)) {
				rankMap.put(rank, new ArrayList<>());
			}
			rankMap.get(rank).add(key);

			if (cache.size() > capacity) {
				removeLowestRank();
			}
		}

		return value;
	}

	private void removeLowestRank() {
		// Returns a key-value mapping associated with the least key in this map, or null if the map is
		// empty.
		Entry<Long, List<K>> firstEntry = rankMap.firstEntry();

		Long key = firstEntry.getKey();
		List<K> value = firstEntry.getValue();
		K remove = value.iterator().next();

		// remove from treemap
		value.remove(remove);
		if (value.isEmpty()) {
			rankMap.remove(key);
		}

		// remove from cache
		cache.remove(remove);
	}
}
