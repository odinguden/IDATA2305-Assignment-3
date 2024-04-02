
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


}