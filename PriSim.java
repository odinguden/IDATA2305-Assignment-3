import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.PriorityQueue;

public class PriSim extends Thread {
	private ArrayList<ScheduledProcess> finishedTasks;
	private ScheduledProcess[] processes;
	private PriorityQueue<ScheduledProcess> queue;
	private ScheduledProcess currentProcess;
	private int tasksToComplete;
	private int tasksCompleted;

	public PriSim(ScheduledProcess[] processes) {
		this.processes = processes;
		tasksToComplete = processes.length;
		tasksCompleted = 0;
		finishedTasks = new ArrayList<>();
		initQueue();
	}

	private void initQueue() {
		queue = new PriorityQueue<>();
		for (ScheduledProcess scheduledProcess : processes) {
			if (scheduledProcess.countDownArrivalTime()) {
				queue.add(scheduledProcess);
			}
		}
		currentProcess = queue.poll();
	}

	private void addToQueue(ScheduledProcess process) {
		process.startWaiting();;
		queue.add(process);
	}

	private void sleepOrThrow(int ms) {
		//System.out.println("Slept");
		try {
			sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException("What am I supposed to do to handle this shit");
		}
	}

	private void countUpProcessing() {
		for (ScheduledProcess scheduledProcess : processes) {
			if (scheduledProcess != null) {
				scheduledProcess.countUpProcessingTime();
			}
		}
		for (ScheduledProcess scheduledProcess : queue) {
			scheduledProcess.countUpProcessingTime();
		}
		if (currentProcess != null) {
			currentProcess.countUpProcessingTime();
		}
		System.out.println("count?");
	}

	/**
	 * Counts down the arrival time of all waiting processes by one.
	 * Adds them to the queue if they arrive.
	 */
	private void countDownArrivalTime () {
		//System.out.println("Decreased arrival time");
		for (int index = tasksToComplete - 1; index >= 0; index--) {
			ScheduledProcess process = processes[index];
			if (process != null && process.countDownArrivalTime() && !process.isCompleted()) {
				processes[index] = null;
				addToQueue(process);
			}
		}
	}

	/**
	 * Counts down the burst time of the current process by 1
	 */
	private void countDownCurrentBurstTime() {
		if (currentProcess == null) {
			currentProcess = queue.poll();
			System.out.println( "Added new process");
		}
		if (currentProcess != null && currentProcess.countDownBurstTime() && !currentProcess.isCompleted()) {
			System.out.println("Finished task: " + currentProcess.getProcessId());
			currentProcess.complete();
			finishedTasks.add(currentProcess);
			currentProcess = queue.poll();
			if (currentProcess!= null) {
				currentProcess.startProcessing();
			}
			tasksCompleted++;
		}
	}

	/**
	 * Does all the things.
	 */
	private void tick() {
		sleepOrThrow(1);
		countDownArrivalTime();
		countDownCurrentBurstTime();
		countUpProcessing();
	}


	@Override
	public void run() {
		int ticks = 0;
		while (tasksCompleted < tasksToComplete) {
			tick();
			ticks++;
			System.out.println(ticks + " Ticks completed");
		}

		for (ScheduledProcess scheduledProcess : finishedTasks) {
			System.out.println(
				scheduledProcess.getProcessId() + " : Wait: " + scheduledProcess.getWaitingTime() + " : Turnaround:" + scheduledProcess.getTurnaroundTime()
			);
		}
	}
}
