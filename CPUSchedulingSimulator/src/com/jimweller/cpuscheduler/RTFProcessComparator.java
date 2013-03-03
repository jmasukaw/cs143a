package com.jimweller.cpuscheduler;

import java.util.Comparator;

public class RTFProcessComparator implements Comparator<Process>{
	@Override
	public int compare(Process p1, Process p2) throws IllegalArgumentException{
		// Shoudn't compare any null processes
		if (p1 == null || p2 == null){
			throw new IllegalArgumentException();
		}

		// Compare by burst time
		if (p1.getBurstTime() < p2.getBurstTime()){
			return -1;
		}
		if (p1.getBurstTime() > p2.getBurstTime()){
			return 1;
		}
		return 0;
	}
}
