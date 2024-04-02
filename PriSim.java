import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class PriSim extends Thread {
	private ScheduledProcess[] processes;
	private PriorityQueue<ScheduledProcess> queue;
	private ScheduledProcess currentProcess;
	private int tasksToComplete;
	private int tasksCompleted;

	public PriSim(ScheduledProcess[] processes) {
		this.processes = processes;
		tasksToComplete = processes.length;
		tasksCompleted = 0;
		queue = new PriorityQueue<>();
	}

	private void initQueue() {
		for (ScheduledProcess scheduledProcess : processes) {
			if (scheduledProcess.countDownArrivalTime()) {
				queue.add(scheduledProcess);
			}
		}
		currentProcess = queue.poll();
	}

	private void addToQueue(ScheduledProcess process) {
		queue.add(process);
	}

	private void sleepOrThrow(int ms) {
		try {
			sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException("What am I supposed to do to handle this shit");
		}
	}

	/**
	 * Counts down the arrival time of all waiting processes by one.
	 * Adds them to the queue if they arrive.
	 */
	private void countDownArrivalTime () {
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
		if (currentProcess != null && currentProcess.countDownBurstTime() && !currentProcess.isCompleted()) {
			currentProcess.complete();
			currentProcess = queue.poll();
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
	}


	@Override
	public void run() {
		int ticks = 0;
		while (tasksCompleted > tasksToComplete) {
			tick();
			}
			ticks++;
			System.out.println(ticks + " Ticks completed");
		}
}
