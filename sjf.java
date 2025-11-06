import java.util.*;

public class sjf{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter the no.of processes:");
        int n=sc.nextInt();
        
        int[] at=new int[n];
        int[] wt=new int[n];
        int[] bt=new int[n];
        int[] tat=new int[n];
        int[] rt=new int[n];
        
        for(int i=0; i<n; i++){
            System.out.println("Enter the arrival time for P"+(i+1)+": ");
            at[i]=sc.nextInt();
            System.out.println("Enter the burst time for P"+(i+1)+": ");
            bt[i]=sc.nextInt();
            rt[i]=bt[i];
        }
        
        int complete=0, time=0, shortest=-1;
        float totalWT=0, totalTAT=0;
        
        while(complete!=n){
            int min= Integer.MAX_VALUE;
            boolean found=false;
            
            for(int i=0; i<n; i++){
                if(at[i]<=time && rt[i]>0 && rt[i]<min){
                    min=rt[i];
                    shortest=i;
                    found=true;
                }
            }
            if(!found){
                time++;
                continue;
            }
            
            rt[shortest]--;
            
            if(rt[shortest]==0){
                complete++;
                int finish=time+1;
                wt[shortest]=finish-at[shortest]-bt[shortest];
                if(wt[shortest]<0){
                    wt[shortest]=0;
                }
            }
            time++;
        }
        System.out.println("P\tAT\tBT\tWT\tTAT");
        for(int i=0; i<n; i++){
            tat[i]=wt[i]+bt[i];
            totalWT += wt[i];
            totalTAT += tat[i];
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
        
        System.out.println("\nAverage Waiting Time = " + (totalWT / n));
        System.out.println("Average Turnaround Time = " + (totalTAT / n));
    }
}