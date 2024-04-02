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

			//System.out.println("---");

			for (int index = tasksToComplete - 1; index >= 0; index--) {
				ScheduledProcess process = processes[index];
				if (process != null && process.countDownArrivalTime() && !process.isCompleted()) {
					processes[index] = null;
					arrivedProcesses.add(process);
				}
			}

			if (currentProcess == null) {
				currentProcess = arrivedProcesses.poll();
				if (currentProcess != null) {
					System.out.printf("Started work on PID %d%n", currentProcess.getProcessId());
				}
			}

			if (currentProcess != null && currentProcess.countDownBurstTime()) {
				currentProcess.complete();
				tasksCompleted++;
				currentProcess = null;
			}
		}
	}
}
