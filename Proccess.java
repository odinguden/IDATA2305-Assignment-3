
/**
 * Proccess
 */
public class Proccess {

	private int id;
	private double arrivalTime;
	private double burstTime;
	private int priotity;

	public Proccess(int id, double arrivalTime, double burstTime, int priotity) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priotity = priotity;
	}

	public Proccess(int id, double arrivalTime, double burstTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priotity = 0;
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

	public int getPriotity() {
		return priotity;
	}

	public void setPriotity(int priotity) {
		this.priotity = priotity;
	}


}