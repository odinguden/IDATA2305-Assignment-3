/**
 * A class that calculates the FCFS (First Come, First Served) scheduling algorithm.
 */
public class FCFSCalculator {

    /**
     * Finds the waiting time for a process.
     * @param processes     the processes in an array to calculate.
     * @param number        the number of processes in the process array.
     * @param burstTime     the burst time of the corresponding process.
     * @param waitingTime   the waiting time for a process.
     */
    static void findWaitingTime(int processes[], int number, 
    int burstTime[], int waitingTime[]) {
        //Start waiting time for the first process.
        waitingTime[0] = 0;

        for (int index = 1; index < number; index++) {
            waitingTime[index] = burstTime[index - 1] + waitingTime[index - 1];
        }
    }

    /**
     * Calculates the turnaround time for a process.
     *
     * @param processes     the processes in an array to calculate.
     * @param number        the number of processes in the process array.
     * @param burstTime     the burst time of the corresponding process.
     * @param waitingTime   the waiting time of the corresponding process.
     * @param turnaround    the turnaround time for each process.
     */
    static void turnAroundTime(int processes[], int number, 
    int burstTime[], int waitingTime[], int turnaround[]) {

        //Turnaround = burst time + waiting time.
        //I.e. time it takes to complete a process.
        for (int index = 0; index < number; index++) {
            turnaround[index] = burstTime[index] + waitingTime[index];
        }
    }

    static void averageTime(int process[], int number, int burstTime[]) {
        int waitingTime[] = new int[number], turnaround[] = new int[number];
        
        int totalWaitingTime = 0, totalTurnaround = 0; 

        findWaitingTime(process, number, burstTime, waitingTime);
        turnAroundTime(process, number, burstTime, waitingTime, turnaround);

        for (int index = 0; index < number; index++) {
            totalWaitingTime = totalWaitingTime + waitingTime[index];
            totalTurnaround = totalTurnaround + turnaround[index];
        }

        //Average waiting time
        int averageWaiting = totalWaitingTime / number;
        int averageTurnaround = totalTurnaround / number;

        System.out.println("Average waiting time: " + averageWaiting);
        System.out.println("Average turn around time: " + averageTurnaround);
    }

    public static void main(String[] args) {
        int processes[] = {1, 2, 3};
        int number = processes.length;

        int burstTime[] = {10, 5, 8};

        averageTime(processes, number, burstTime);
    }
}
