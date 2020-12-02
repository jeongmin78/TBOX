package org.kpu.ticketbox.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.kpu.ticketbox.payment.Receipt;

public class Statistics {
	public static double sum(HashMap<Integer, Receipt>map) {
		double sum = 0.0;
		Set<Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator();
		while(it.hasNext()) {
			int number = it.next();
			Receipt receipt = map.get(number);
			sum += receipt.getTotalAmount();
		}
		return sum;
	}
}
