
/**
 * Proccess
 */
public class Proccess {

	private int id;
	private double arrivalTime;
	private double burstTime;
	private int priority;

	
	public Proccess(int id, double burstTime, int priority) {
		this.id = id;
		this.arrivalTime = 0;
		this.burstTime = burstTime;
		this.priotity = priority;
	}

	public Proccess(int id, double burstTime) {
		this.id = id;
		this.arrivalTime = 0;
		this.burstTime = burstTime;
		this.priority = 0;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(double burstTime) {
		this.burstTime = burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priotity = priority;
	}


}