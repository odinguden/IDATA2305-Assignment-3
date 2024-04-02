import java.util.LinkedList;
import java.util.Queue;

public class FirstComeFirstServed extends Thread {
	private ScheduledProcess[] processes;

	public FirstComeFirstServed(ScheduledProcess[] processes) {
		this.processes = processes;
	}

	private void sleepOrThrow(int ms) {
		try {
			sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException("What am I supposed to do to handle this shit");
		}
	}

	@Override
	public void run() {
		int tasksCompleted = 0;
		int tasksToComplete = processes.length;
		Queue<ScheduledProcess> arrivedProcesses = new LinkedList<>();
		ScheduledProcess currentProcess = null;

		while (tasksCompleted < tasksToComplete) {
			sleepOrThrow(1);

			/**
			 * Iterates over all the processes and checks if they have arrived.
			 */
			for (int index = 0; index < tasksToComplete; index++) {
				ScheduledProcess process = processes[index];
				if (process.countDownArrivalTime() && !process.isWaiting()) {
					arrivedProcesses.add(process);
					process.startWaiting();
				}
			}

			/**
			 * Checks the arrived processes and checks if they can be processed.
			 */
			if (currentProcess == null) {
				currentProcess = arrivedProcesses.poll();
				if (currentProcess != null) {
					currentProcess.startProcessing();
					System.out.printf("Started work on PID %d%n", currentProcess.getProcessId());
				}
			}

			/**
			 * Increments the processing time counter for all processes
			 */
			for (ScheduledProcess process : processes) {
				process.countUpProcessingTime();
			}

			/**
			 * Checks if the process being processed is done, and marks it as complete.
			 */
			if (currentProcess != null && currentProcess.countDownBurstTime()) {
				currentProcess.complete();
				tasksCompleted++;
				System.out.printf("\tWaiting time: %d%n\tTurnaround time: %d%n",
					currentProcess.getWaitingTime(), currentProcess.getTurnaroundTime());
				currentProcess = null;
			}
		}

		/**
		 * Calculate average waiting and turnaround time.
		 */

		int sumWaitingTime = 0;
		int sumTurnaroundTime = 0;

		for (ScheduledProcess process : this.processes) {
			sumWaitingTime += process.getWaitingTime();
			sumTurnaroundTime += process.getTurnaroundTime();
		}

		double averageWaitingTime = (double) sumWaitingTime / this.processes.length;
		double averageTurnaroundTime = (double) sumTurnaroundTime / this.processes.length;

		System.out.printf("%nAverage waiting time: %.2f%nAverage turnaround time: %.2f%n", averageWaitingTime, averageTurnaroundTime);
	}
}
