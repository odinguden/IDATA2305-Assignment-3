import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		ScheduledProcess[] processes = new ScheduledProcess[6];
		for (int i = 0; i < processes.length; i++) {
			//processes[i] = new ScheduledProcess(i, 1, 1);
			int arrivalTime = random.nextInt(1, 100);
			int burstTime = random.nextInt(1, 100);
			processes[i] = new ScheduledProcess(i, arrivalTime, burstTime);
			System.out.printf("PID: %d%n\tArrival: %d%n\tBurst: %d%n", i, arrivalTime, burstTime);
		}

		new FirstComeFirstServed(processes).start();
	}


	
}
