package com.algo.ds.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.algo.ds.hashtable.RetainBestCacheByRank.Rankable;

/**
 * Category : Hard
 * 
 * @author M_402201
 *
 * @param <K>
 * @param <V>
 */

public class RetainBestCacheByRank<K, V extends Rankable> {

	// Add any fields you need here
	private DataSource<K, V> dataSource;
	private Map<K, V> cache;
	private int capacity;
	// since cache is ordered by rank(long) TreeMap can be used to main insertion order
	private TreeMap<Long, Set<V>> rank;

	/* Constructor with a data source (assumed to be slow) and a cache size */
	public RetainBestCacheByRank(DataSource<K, V> ds, int entriesToRetain) {
		// Implementation here
		this.capacity = entriesToRetain;
		this.dataSource = ds;
		this.cache = new HashMap<>(entriesToRetain);
		this.rank = new TreeMap<>();
	}

	/*
	 * Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
	 * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
	 * evicting the V with lowest rank among the ones that it has available If there is a tie, the cache
	 * may choose any T with lowest rank to evict.
	 */
	public V get(K key) {
		// Implementation here

		V value = null;
		if (cache.containsKey(key)) {
			value = cache.get(key);
		} else {
			value = dataSource.get(key);
			long currentRank = value.getRank();
			// add in cache
			cache.put(key, value);

			// add in rank
			if (!rank.containsKey(currentRank)) {
				rank.put(currentRank, new HashSet<V>());
			}
			rank.get(currentRank).add(value);

			if (cache.size() > capacity) {
				removeLowestRank();
			}
		}
		return value;
	}

	private void removeLowestRank() {
		Entry<Long, Set<V>> firstEntry = rank.firstEntry();
		Long firstKey = firstEntry.getKey();
		Set<V> firstValueSet = firstEntry.getValue();
		V elemToRemove = firstValueSet.iterator().next();

		// remove from rank
		firstValueSet.remove(elemToRemove);
		if (firstValueSet.isEmpty()) {
			rank.remove(firstKey);
		}

		// remove from cache map
		cache.remove(firstKey);

	}

	/*
	 * For reference, here are the Rankable and DataSource interfaces. You do not need to implement
	 * them, and should not make assumptions about their implementations.
	 */

	public interface Rankable {
		/**
		 * Returns the Rank of this object, using some algorithm and potentially the internal state of the
		 * Rankable.
		 */
		long getRank();
	}

	public interface DataSource<K, V extends Rankable> {
		V get(K key);
	}

}

