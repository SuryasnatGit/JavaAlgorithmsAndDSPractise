package com.misc;

public interface DataSource<K, V extends Rankable> {
	V get(K key);
}
