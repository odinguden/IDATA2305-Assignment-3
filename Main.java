import java.util.Random;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running Preemptive Priority Scheduling");
		PriSim priSim = new PriSim(getProcesses(10));
		priSim.start();

		priSim.join();

		System.out.println("Running First Come First Served Scheduling");
		FirstComeFirstServed firstComeFirstServed = new FirstComeFirstServed(getProcesses(10));
		firstComeFirstServed.start();
	}

	public static ScheduledProcess[] getProcesses(int processCount) {
		Random random = new Random();
		ScheduledProcess[] processes = new ScheduledProcess[processCount];
		for (int i = 0; i < processes.length; i++) {
			//processes[i] = new ScheduledProcess(i, 1, 1);
			int arrivalTime = random.nextInt(1, 100);
			int burstTime = random.nextInt(1, 100);
			int pri = random.nextInt(0,9);
			processes[i] = new ScheduledProcess(i, arrivalTime, burstTime, pri);
			System.out.printf("PID: %d%n\tArrival: %d%n\tBurst: %d%n", i, arrivalTime, burstTime);
		}
		return processes;
	}
}
