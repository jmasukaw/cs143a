/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Yan Zhao(), Jonathan Masukawa(33128396)
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PrioritySchedulingAlgorithm extends BasePriorityAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
    PrioritySchedulingAlgorithm(){
    	this.preemptive = false;	// Default preemptive off
    	activeJob = null;
    	Comparator<Process> procComparator = new ProcessComparator();
    	this.jobs = new PriorityQueue<Process>(10, procComparator);
    }
  
    public String getName(){
    	return "Single-queue Priority";
    }

}