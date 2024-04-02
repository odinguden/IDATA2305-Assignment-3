import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		new Process(0, 0, 0);
	}



	public Process[] createProccesses(int numberOfPrcesseses, boolean pri) {
		Random random = new Random();
		Process[] proccesses = new Process[numberOfPrcesseses];

		if (pri) {
			for (int i = 0; i < numberOfPrcesseses; i++){
				proccesses[i] = new Process(
					i,
					random.nextDouble(),
					random.nextInt(9)
					);
			}
		} else {
			for (int i = 0; i < numberOfPrcesseses; i++){
				proccesses[i] = new Process(
					i,
					random.nextDouble(10)
					);
			}
		}

		return proccesses;
	}

}
