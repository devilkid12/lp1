import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of processes and time quantum
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        int[] at = new int[n];  // Arrival time
        int[] bt = new int[n];  // Burst time
        int[] rt = new int[n];  // Remaining time
        int[] wt = new int[n];  // Waiting time
        int[] tat = new int[n]; // Turnaround time

        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Arrival Time of P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time of P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i]; // initially remaining = burst
        }

        int time = 0;        // current time
        int completed = 0;   // count of completed processes
        float totalWT = 0, totalTAT = 0;
        Queue<Integer> q = new LinkedList<>();

        // start by adding processes that arrive at 0
        while (completed < n) {

            // add new arrivals to queue
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rt[i] > 0 && !q.contains(i))
                    q.add(i);
            }

            // if queue empty, move time forward
            if (q.isEmpty()) {
                time++;
                continue;
            }

            int i = q.poll(); // get process index from queue

            // execute for time quantum or remaining time
            int exec = Math.min(tq, rt[i]);
            rt[i] -= exec;
            time += exec;

            // check if process finished
            if (rt[i] == 0) {
                completed++;
                tat[i] = time - at[i];     // turnaround = finish - arrival
                wt[i] = tat[i] - bt[i];    // waiting = turnaround - burst
                totalWT += wt[i];
                totalTAT += tat[i];
            }
        }

        // display output
        System.out.println("\nProcess\tAT\tBT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage Turnaround Time = " + (totalTAT / n));
        System.out.println("Average Waiting Time = " + (totalWT / n));
    }
}
