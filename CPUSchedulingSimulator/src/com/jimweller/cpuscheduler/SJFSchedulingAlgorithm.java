/** SJFSchedulingAlgorithm.java
 * 
 * A shortest job first scheduling algorithm.
 *
 * @author: Jon Masukawa (33128396), Yan Zhao (31018809)
 * Group # 32
 * 
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;


public class SJFSchedulingAlgorithm extends BasePriorityAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {

    SJFSchedulingAlgorithm(){
    	this.preemptive = false;	// Default preemptive off
    	this.activeJob = null;
    	Comparator<Process> procComparator = new RTFProcessComparator();
    	this.jobs = new PriorityQueue<Process>(10, procComparator); 	
    }

    public String getName(){
    	return "Shortest job first";
    }
}