import java.util.*;

public class lru{
    public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    
    System.out.println("Enter the no of frames");
    int frameCount=sc.nextInt();
    
    System.out.println("Enter no of pages");
    int pageCount=sc.nextInt();
    
    int[] pages = new int[pageCount];
    System.out.println("Enter the reference string");
    for(int i=0; i<pageCount; i++){
        pages[i] = sc.nextInt();
    }
    
    ArrayList<Integer> frames=new ArrayList<>();
    int pagefaults=0, pagehits=0;
    
    for(int i=0; i<pageCount; i++){
        int currentPage=pages[i];
        
        if(frames.contains(currentPage)){
            pagehits++;
            frames.remove(Integer.valueOf(currentPage));
            frames.add(currentPage);
            System.out.println("Page"+currentPage+"->hit");
        }
        else
        {
            pagefaults++;
            if(frames.size()==frameCount){
                int removed= frames.remove(0);
                System.out.println("Page"+removed+"removed");
            }
            frames.add(currentPage);
            System.out.println("page"+currentPage+"->fault");
        }
    }
    System.out.println();
    System.out.println("total page faults"+pagefaults);
        System.out.println("total page hits"+pagehits);
        sc.close();
    }
}