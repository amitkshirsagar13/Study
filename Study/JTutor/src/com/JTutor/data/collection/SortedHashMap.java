/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.JTutor.data.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author amit_kshirsagar
 */
public class SortedHashMap extends HashMap {
	public Iterator iterator() {
		Collection collection = this.keySet();
		Object[] array = collection.toArray();
		Arrays.sort(array);
		return Arrays.asList(array).iterator();
	}
}
