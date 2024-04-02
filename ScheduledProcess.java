public class ScheduledProcess {
	private int initialArrivalTime;
	private int initialBurstTime;

	private int arrivalTime;
	private int burstTime;

	private int priority;

	private int processId;

	private int waitingTime; // Turnaround time - burst time (Time spent not processing)
	private int turnaroundTime; // Completion time - arrival time (Time spent total)

	private boolean isCompleted = false;

	public ScheduledProcess(int processId, int arrivalTime, int burstTime) {
		this.processId = processId;

		this.initialArrivalTime = arrivalTime;
		this.initialBurstTime = burstTime;

		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;

		this.isCompleted = false;
	}

	public ScheduledProcess(int processId, int arrivalTime, int burstTime, int priority) {
		this.processId = processId;

		this.initialArrivalTime = arrivalTime;
		this.initialBurstTime = burstTime;

		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;

		this.priority = priority;

		this.isCompleted = false;
	}

	public boolean countDownArrivalTime() {
		if (this.arrivalTime > 0) {
			this.arrivalTime--;
			if (this.arrivalTime == 0) {
				System.out.printf("Process PID %d arrived%n", this.processId);
			}
		}

		return this.arrivalTime <= 0;
	}

	public boolean countDownBurstTime() {
		if (this.burstTime > 0) {
			this.burstTime--;
			if (this.burstTime == 0) {
				System.out.printf("Process PID %d completed%n", this.processId);
			}
		}

		return this.burstTime <= 0;
	}

	public int getInitialArrivalTime() {
		return initialArrivalTime;
	}

	public int getInitialBurstTime() {
		return initialBurstTime;
	}

	public int getPriority() {
		return priority;
	}

	public int getProcessId() {
		return processId;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public boolean isCompleted() {
		return this.isCompleted;
	}

	public void complete() {
		this.isCompleted = true;
	}
}
