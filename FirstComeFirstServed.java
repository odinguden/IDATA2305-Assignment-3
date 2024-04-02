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

			for (int index = 0; index < tasksToComplete; index++) {
				ScheduledProcess process = processes[index];
				if (process.countDownArrivalTime() && !process.isWaiting()) {
					arrivedProcesses.add(process);
					process.startWaiting();
				}
			}

			if (currentProcess == null) {
				currentProcess = arrivedProcesses.poll();
				if (currentProcess != null) {
					currentProcess.startProcessing();
					System.out.printf("Started work on PID %d%n", currentProcess.getProcessId());
				}
			}

			for (ScheduledProcess process : processes) {
				process.countUpProcessingTime();
			}

			if (currentProcess != null && currentProcess.countDownBurstTime()) {
				currentProcess.complete();
				tasksCompleted++;
				System.out.printf("\tWaiting time: %d%n\tTurnaround time: %d%n",
					currentProcess.getWaitingTime(), currentProcess.getTurnaroundTime());
				currentProcess = null;
			}
		}
	}
}
