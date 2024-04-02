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

	/**
	 * Creates a new scheduled process without priority.
	 *
	 * @param processId the ID of the process
	 * @param arrivalTime the amount of time before the process arrives
	 * @param burstTime the amount of time the process will take
	 */
	public ScheduledProcess(int processId, int arrivalTime, int burstTime) {
		this.processId = processId;

		this.initialArrivalTime = arrivalTime;
		this.initialBurstTime = burstTime;

		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;

		this.isProcessing = false;
		this.isCompleted = false;
	}

	/**
	 * Creates a new scheduled process with priority.
	 *
	 * @param processId the ID of the process
	 * @param arrivalTime the amount of time before the process arrives
	 * @param burstTime the amount of time the process will take
	 * @param priority the priority of the process
	 */
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

	/**
	 * Counts down the process's arrival time.
	 *
	 * @return true if the arrival time is 0.
	 */
	public boolean countDownArrivalTime() {
		if (this.arrivalTime > 0) {
			this.arrivalTime--;
			if (this.arrivalTime == 0) {
				System.out.printf("Process PID %d arrived%n", this.processId);
			}
		}

		return this.arrivalTime <= 0;
	}

	/**
	 * Counts down the process's burst time.
	 *
	 * @return true if the burst time is 0.
	 */
	public boolean countDownBurstTime() {
		if (this.burstTime > 0) {
			this.burstTime--;
			if (this.burstTime == 0) {
				System.out.printf("Process PID %d completed%n", this.processId);
			}
		}

		return this.burstTime <= 0;
	}

	/**
	 * Counts up the process's processing time. Handles turnaround and waiting time separately.
	 */
	public void countUpProcessingTime() {
		if (this.isCompleted || !this.isWaiting) return;

		this.turnaroundTime++;
		if (!this.isProcessing) {
			this.waitingTime++;
		}
	}

	/**
	 * Gets the process's initial arrival time.
	 *
	 * @return the process's initial arrival time.
	 */
	public int getInitialArrivalTime() {
		return initialArrivalTime;
	}

	/**
	 * Gets the process's burst time.
	 *
	 * @return the process's burst time
	 */
	public int getInitialBurstTime() {
		return initialBurstTime;
	}

	/**
	 * Gets the process's priority.
	 *
	 * @return the process's priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Gets the process's id.
	 *
	 * @return the process's id
	 */
	public int getProcessId() {
		return processId;
	}

	/**
	 * Gets the process's waiting time.
	 *
	 * @return the process's waiting time
	 */
	public int getWaitingTime() {
		return waitingTime;
	}

	/**
	 * Gets the process's turnaround.
	 *
	 * @return the process's turnaround
	 */
	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	/**
	 * Sets the process's waiting time.
	 *
	 * @param the process's waiting time
	 */
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**
	 * Sets the process's turnaround time.
	 *
	 * @param the process's turnaround time
	 */
	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	/**
	 * Gets the process's completion flag.
	 *
	 * @return the process's completion flag
	 */
	public boolean isCompleted() {
		return this.isCompleted;
	}

	/**
	 * Sets the process's completion state to true.
	 */
	public void complete() {
		this.isCompleted = true;
	}

	/**
	 * Gets the process's processing flag.
	 *
	 * @return the process's processing flag
	 */
	public boolean isProccessing() {
		return this.isProcessing;
	}

	/**
	 * Sets the process's processing state to true.
	 */
	public void startProcessing() {
		this.isProcessing = true;
	}

	/**
	 * Gets the process's waiting state.
	 *
	 * @return the process's waiting state
	 */
	public boolean isWaiting() {
		return this.isWaiting;
	}

	/**
	 * Sets the process's waiting state to true.
	 */
	public void startWaiting() {
		this.isWaiting = true;
	}

	@Override
	public int compareTo(ScheduledProcess process) {
		if (priority < process.getPriority()) {
			return -1;
		} else if (priority > process.getPriority()) {
			return 1;
		}
		return 0;
	}
}
