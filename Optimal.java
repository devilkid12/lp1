import java.util.*;

public class Optimal{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frameCount = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        ArrayList<Integer> frames = new ArrayList<>();
        int pageFaults = 0, pageHit = 0;

        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // Single if-else chain
            if (frames.contains(page)) {
                pageHit++;
                System.out.println("P " + (i + 1) + " ->PAGE HIT->"+frames );
            } 
            else if (frames.size() < frameCount) {
                // Free space available
                frames.add(page);
                pageFaults++;
                System.out.println("P" + (i + 1) + " ->PAGE FAULT->"+frames );
            } 
            else {
                // Memory full -> find optimal page to replace
                int farthest = -1, indexToReplace = -1;

                for (int j = 0; j < frames.size(); j++) {
                    int currentPage = frames.get(j);
                    int k;
                    // Check when this page will be used next
                    for (k = i + 1; k < n; k++) {
                        if (pages[k] == currentPage)
                            break;
                    }

                    // If not used again, best to replace
                    if (k == n) {
                        indexToReplace = j;
                        break;
                    }

                    // Otherwise, replace the one used farthest in the future
                    if (k > farthest) {
                        farthest = k;
                        indexToReplace = j;
                    }
                }

                frames.remove(indexToReplace);
                frames.add(indexToReplace, page);
                pageFaults++;
                System.out.println("P" + (i + 1) + " ->PAGE FAULT->"+frames);
            }
        }

        System.out.println("Total Page Faults: " + pageFaults);
        sc.close();
    }
}
