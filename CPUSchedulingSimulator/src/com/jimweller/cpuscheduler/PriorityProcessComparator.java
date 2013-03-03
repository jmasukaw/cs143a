package com.jimweller.cpuscheduler;

import java.util.Comparator;
import java.util.Scanner;

public class PriorityProcessComparator implements Comparator<Process>{
	@Override
	public int compare(Process p1, Process p2) throws IllegalArgumentException{
		// Shoudn't compare any null processes
		if (p1 == null || p2 == null){
			throw new IllegalArgumentException();
		}

		// Compare by priority
		if (p1.getPriorityWeight() < p2.getPriorityWeight()){
			return -1;
		}
		if (p1.getPriorityWeight() > p2.getPriorityWeight()){
			return 1;
		}
		return 0;
	}
}

