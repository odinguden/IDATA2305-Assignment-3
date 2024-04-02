/**
 * A class that calculates the FCFS (First Come, First Served) scheduling algorithm.
 */
public class FCFSCalculator {

    /**
     * Calculates the waiting time for a process.
     *
     * @param processes the process to calculate the waiting time for.
     */
    static void calculateWaitingTime(ScheduledProcess[] processes) {
        //Start waiting time count at 0.
        processes[0].setWaitingTime(0);

        for (int index = 1; index < processes.length; index++) {
            int waitingTime = processes[index-1].getInitialBurstTime() + processes[index-1].getWaitingTime();
            processes[index].setWaitingTime(waitingTime);
        }
    }

    /**
     * Calculates the turnaround time for a process.
     *
     * @param processes the process to calculate the turnaround time for.
     */
    static void calculateTurnAroundTime(ScheduledProcess[] processes) {

        for (int index = 0; index < processes.length; index++) {
            int turnaroundTime = processes[index].getInitialBurstTime() + processes[index].getWaitingTime();
            processes[index].setTurnaroundTime(turnaroundTime);
        }
    }

    /**
     * Calculates the average time for a process.
     *
     * @param processes the process to calculate the average time for.
     */
    static void calculateAverageTime(ScheduledProcess[] processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        calculateWaitingTime(processes);
        calculateTurnAroundTime(processes);

        for (ScheduledProcess process : processes) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
        }

        System.out.println("Average waiting time = " +
            totalWaitingTime / processes.length);
        System.out.println("Average turnaround time = " +
            totalTurnaroundTime / processes.length);
    }

    public static void main(String[] args) {
        ScheduledProcess[] processes = {
            new ScheduledProcess(1, 0, 10),
            new ScheduledProcess(2, 0, 5),
            new ScheduledProcess(3, 0, 8)
        };

        calculateAverageTime(processes);
    }
}

