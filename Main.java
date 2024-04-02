import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		ScheduledProcess[] processes = new ScheduledProcess[6];
		for (int i = 0; i < processes.length; i++) {
			//processes[i] = new ScheduledProcess(i, 1, 1);
			processes[i] = new ScheduledProcess(i, random.nextInt(1, 100), random.nextInt(1, 100));
		}

		new FirstComeFirstServed(processes).start();
	}


	
}
