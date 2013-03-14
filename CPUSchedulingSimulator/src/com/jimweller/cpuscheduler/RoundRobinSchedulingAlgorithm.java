/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Jon Masukawa (33128396), Yan Zhao (31018809)
 * Group #32
 * 
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    /** the timeslice each process gets */
    protected int quantum;

    protected Process activeJob;

    private int counter;
    
    private LinkedList<Process> jobs;
    
    RoundRobinSchedulingAlgorithm() {
    	this.jobs = new LinkedList<Process>();
    	this.activeJob = null;
    	this.counter = 0;
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
    	jobs.add(p);
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
    	return jobs.remove(p); // Just a place holder to get things to compile, remove this when implementing
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    	ArrayList<Process> temp = new ArrayList<Process>();
    	Iterator<Process> iterator = jobs.iterator();
    	
    	while (iterator.hasNext()) {
    		temp.add(iterator.next());
    	}
    	
    	if (activeJob != null) {
    		otherAlg.addJob(activeJob);
    	}
    	
    	for (Process process : temp) {
			otherAlg.addJob(process);
			this.removeJob(process);
		}
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
    	return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
    	this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
    	
    	//If there is a job running currently, increase the time quantum, check
    	//if the job has run over the time quantum, re-add the job and then
    	//get a new job to start executing.
    	if (activeJob != null) {
    		counter++;
    		if (counter >= quantum) {
    			counter = 0;
    			jobs.add(activeJob);
    			activeJob = jobs.remove();
    		}
    	}
    	
    	//If there is no active job running, check if it is finished. If there
    	//are jobs in the job queue, remove them and reset the quantum for that
    	//particular job.
    	if (activeJob == null || activeJob.isFinished()) {
    		if(jobs.size() > 0) {
    			activeJob = jobs.remove();
    			counter = 0;    			
    		}
    	}
    	
    	return activeJob; // Just a place holder to get things to compile, remove this when implementing
    }

    public String getName() {
    	return "Round Robin";
    }
}