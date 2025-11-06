import java.util.*;

public class priority{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter the no.of processes:");
        int n=sc.nextInt();
        
        int[] pid=new int[n];
        int[] pr=new int[n];
        int[] wt=new int[n];
        int[] bt=new int[n];
        int[] tat=new int[n];
        int[] ct=new int[n];
        
        for(int i=0; i<n; i++){
            pid[i]=i+1;
            System.out.println("Enter the priority for P"+pid[i]+": ");
            pr[i]=sc.nextInt();
            System.out.println("Enter the burst time for P"+pid[i]+": ");
            bt[i]=sc.nextInt();
        }
        
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(pr[i] > pr[j]){
                    int temp=pr[i];
                    pr[i]=pr[j];
                    pr[j]=temp;
                    
                    temp=bt[i];
                    bt[i]=bt[j];
                    bt[j]=temp;
                    
                    temp=pid[i];
                    pid[i]=pid[j];
                    pid[j]=temp;
                }
            }
        }
        
        ct[0]=bt[0];
        for(int i=1; i<n; i++){
            ct[i]=ct[i-1]+bt[i];
        }
        
        for(int i=0; i<n; i++){
            tat[i]=ct[i];
            wt[i]=tat[i]-bt[i];
        }
        
        System.out.println("PID \t PR \t BT \t CT \t TAT \t WT");
        for(int i=0; i<n; i++){
            System.out.println("P"+pid[i]+"\t"+pr[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]) ;
        }
        sc.close();
    }
}