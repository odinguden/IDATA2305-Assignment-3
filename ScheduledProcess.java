public class ScheduledProcess implements Comparable<ScheduledProcess> {
	private int initialArrivalTime;
	private int initialBurstTime;

	private int arrivalTime;
	private int burstTime;

	private int priority;

	private int processId;

	private int waitingTime; // Turnaround time - burst time (Time spent not processing)
	private int turnaroundTime; // Completion time - arrival time (Time spent total)

	private boolean isWaiting = false;
	private boolean isProcessing = false;
	private boolean isCompleted = false;

	public ScheduledProcess(int processId, int arrivalTime, int burstTime) {
		this.processId = processId;

		this.initialArrivalTime = arrivalTime;
		this.initialBurstTime = burstTime;

		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;

		this.isProcessing = false;
		this.isCompleted = false;
	}

	public ScheduledProcess(int processId, int arrivalTime, int burstTime, int priority) {
		this.processId = processId;

		this.initialArrivalTime = arrivalTime;
		this.initialBurstTime = burstTime;

		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;

		this.priority = priority;

		this.isProcessing = false;
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

	public void countUpProcessingTime() {
		if (this.isCompleted || !this.isWaiting) return;

		this.turnaroundTime++;
		if (!this.isProcessing) {
			this.waitingTime++;
		}
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

	public boolean isProccessing() {
		return this.isProcessing;
	}

	public void startProcessing() {
		this.isProcessing = true;
	}

	public boolean isWaiting() {
		return this.isWaiting;
	}

	public void startWaiting() {
		this.isWaiting = true;
	}

	@Override
	public int compareTo(ScheduledProcess process) {
		if (priority < process.getPriority()) {
			return -1;
		} else if (priority > process.getPriority()) {
			return -1;
		}
		return 0;
	}
}
