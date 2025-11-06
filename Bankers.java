import java.util.*;

public class Bankers{
    private int allocate[][], max[][], need[][], avail[][];
    private int np, nr;
    
    private void input(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of processes and resources");
        np=sc.nextInt();
        nr=sc.nextInt();
        
        allocate=new int[np][nr];
        max=new int[np][nr];
        need=new int[np][nr];
        avail=new int[1][nr];
        
        System.out.println("Enter the allocated matrix:");
        for(int i=0;i<np;i++){
            for(int j=0; j<nr; j++){
                allocate[i][j]=sc.nextInt();
            }
        }
        
        System.out.println("Enter the max matrix:");
        for(int i=0;i<np;i++){
            for(int j=0; j<nr; j++){
                max[i][j]=sc.nextInt();
            }
        }
        
        System.out.println("Enter the available matrix:");
        for(int j=0; j<nr; j++){
                avail[0][j]=sc.nextInt();
        }
    }
    
    private int[][] calc_need(){
        for(int i=0;i<np;i++){
            for(int j=0; j<nr; j++)
                need[i][j]=max[i][j] - allocate[i][j];
        }
        return need;
    }
    
    private boolean check(int i){
    for(int j=0; j<nr; j++){
        if(need[i][j] > avail[0][j])
            return false;
    }
    return true;
    }
    
    public void isSafe(){
        input();
        calc_need();
        
        boolean[] done=new boolean[np];
        int[] safeSeq= new int[np];
        int Count=0;
        
        while (Count < np) {
    boolean allocated = false;

    for (int i = 0; i < np; i++) {
        if (!done[i] && check(i)) {
            for (int j = 0; j < nr; j++) {
                avail[0][j] += allocate[i][j];
            }
            safeSeq[Count++] = i;
            done[i] = true;
            allocated = true;
            System.out.println("Process P" + i + " executed.");
        }
    }

    if (!allocated)
        break; // no process could be executed → exit
}

if (Count == np) {
    System.out.println("System is in a SAFE STATE!");
    System.out.print("Safe Sequence: ");
    for (int i = 0; i < np; i++)
        System.out.print("P" + safeSeq[i] + " ");
    System.out.println();
    } else {
    System.out.println("System is NOT in a safe state!");
    }
}
    public static void main(String[] args) {
        new Bankers().isSafe();
    }
}