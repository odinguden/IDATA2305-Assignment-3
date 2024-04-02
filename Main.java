import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		new Proccess(0, 0, 0);
	}



	public Proccess[] createProccesses(int numberOfPrcesseses, boolean pri) {
		Random random = new Random();
		Proccess[] proccesses = new Proccess[numberOfPrcesseses];

		if (pri) {
			for (int i = 0; i < numberOfPrcesseses; i++){
				proccesses[i] = new Proccess(
					i,
					random.nextDouble(),
					random.nextInt(9)
					);
			}
		} else {
			for (int i = 0; i < numberOfPrcesseses; i++){
				proccesses[i] = new Proccess(
					i,
					random.nextDouble(10)
					);
			}
		}

		return proccesses;
	}

}
