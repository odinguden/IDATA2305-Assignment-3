public class FirstComeFirstServed extends Thread {
	private Proccess[] processes;
	private int currentProcess = -1;

	public FirstComeFirstServed(Proccess[] processes) {
		this.processes = processes;
	}

	@Override
	public void run() {
		int tasksCompleted = 0;
		int tasksToComplete = processes.length;
		while (tasksCompleted < tasksToComplete) {
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int index = 0; index < tasksToComplete; index++) {
				Proccess process = processes[index];
				if (process.getArrivalTime() > 0) {
					process.setArrivalTime(process.getArrivalTime() - 1);
				} else if (currentProcess > -1) {
					
				} else {
					this.currentProcess = index;
				}
			}
		}
	}
}
