import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
<<<<<<< Updated upstream
		new Process(0, 0, 0);
	}



	public Process[] createProccesses(int numberOfProcesseses, boolean pri) {
		Random random = new Random();
		Process[] proccesses = new Process[numberOfProcesseses];

		if (pri) {
			for (int i = 0; i < numberOfProcesseses; i++){
				proccesses[i] = new Process(
					i,
					random.nextDouble(),
					random.nextInt(9)
					);
			}
		} else {
			for (int i = 0; i < numberOfProcesseses; i++){
				proccesses[i] = new Process(
					i,
					random.nextDouble(10)
					);
			}
=======
		Random random = new Random();
		ScheduledProcess[] processes = new ScheduledProcess[6];
		for (int i = 0; i < processes.length; i++) {
			//processes[i] = new ScheduledProcess(i, 1, 1);
			processes[i] = new ScheduledProcess(i, random.nextInt(1, 100), random.nextInt(1, 100));
>>>>>>> Stashed changes
		}

		new FirstComeFirstServed(processes).start();
	}


}
