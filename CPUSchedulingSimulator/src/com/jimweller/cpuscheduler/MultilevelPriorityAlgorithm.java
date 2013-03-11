/** MultilevelPriorityAlgorithm.java
 * 
 *
 * @author: Jon Masukawa (33128396), Yan Zhao (31018809)
 * Group #32
 * 
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

public class MultilevelPriorityAlgorithm extends RoundRobinSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {

    /** The boolean value that toggles preemptiveness. */
    private boolean preemptive;
    
    private RoundRobinSchedulingAlgorithm jobs1;
    private RoundRobinSchedulingAlgorithm jobs2;
    private FCFSSchedulingAlgorithm jobs3;
    
	public MultilevelPriorityAlgorithm() {
		this.jobs1 = new RoundRobinSchedulingAlgorithm();
	    this.jobs2 = new RoundRobinSchedulingAlgorithm();
	    this.jobs3 = new FCFSSchedulingAlgorithm();
	    this.jobs2.setQuantum(jobs1.getQuantum() * 2);
		this.activeJob = null;
	}
	
	@Override
	public void addJob(Process p) {
		if (p.getPriorityWeight() <= 3)
			jobs1.addJob(p);
		else if (p.getPriorityWeight() <= 6){
			jobs2.addJob(p);
		}
		else if (p.getPriorityWeight() <= 9){
			jobs3.addJob(p);
		}
	}

	@Override
	public boolean removeJob(Process p) {
		if (p.getPriorityWeight() <= 3)
			return jobs1.removeJob(p);
		else if (p.getPriorityWeight() <= 6) {
			return jobs2.removeJob(p);
		}
		else
			return jobs3.removeJob(p);
	}

	@Override
	public Process getNextJob(long currentTime) {
		// If no activeJob or job is finished, get the next one
		if(activeJob == null || activeJob.isFinished()) {
			activeJob = jobs1.getNextJob(currentTime);
		}
		if (activeJob == null || activeJob.isFinished()) {
			activeJob = jobs2.getNextJob(currentTime);
		}
		if (activeJob == null || activeJob.isFinished()) {
			activeJob = jobs3.getNextJob(currentTime);
		}

		// If preemptive
		if (preemptive) {
			activeJob = null;
			activeJob = jobs1.getNextJob(currentTime);
			if (activeJob == null || activeJob.isFinished()) {
				activeJob = jobs2.getNextJob(currentTime);
			}
			if (activeJob == null || activeJob.isFinished()) {
				activeJob = jobs3.getNextJob(currentTime);
			}
		} else {
			// If activeJob, do work
			if (activeJob.getPriorityWeight() <= 3) {
				activeJob = jobs1.getNextJob(currentTime);
			}
			else if (activeJob.getPriorityWeight() <= 6) {
				activeJob = jobs2.getNextJob(currentTime);
			}
			else if (activeJob.getPriorityWeight() <= 9) {
				activeJob = jobs3.getNextJob(currentTime);
			}
		}
		return activeJob;
	}

	@Override
	public String getName() {
		return "Multi-level Priority Algorithm";
	}

	@Override
	public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    	jobs1.transferJobsTo(otherAlg);
    	jobs2.transferJobsTo(otherAlg);
    	jobs3.transferJobsTo(otherAlg);
	}

	@Override
	public boolean isPreemptive() {
		return preemptive;
	}

	@Override
	public void setPreemptive(boolean v) {
		preemptive = v;
	}
}
