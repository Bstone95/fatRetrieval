import java.util.ArrayList;
import java.util.List;

public class problem5other {
    public static void main(String[] args) {
        ArrayList<Integer> roots = new ArrayList<>();
        roots.add(2);
        roots.add(3);
        roots.add(9);
        Integer[] firstRow = {2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] secondRow = {0, 4, 7, 0, 8, 0, 5, 0};

        ArrayList<Integer> result = traceBackToZero(roots, firstRow, secondRow);

        System.out.println("Result: " + result);
    }

    public static ArrayList<Integer> traceBackToZero(ArrayList<Integer> roots, Integer[] firstRow, Integer[] secondRow) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < secondRow.length; i++) {
            int current = secondRow[i];
            
            // Check if the current element is not in the roots and not 0
            if (!roots.contains(current) && current != 0) {
                int indexInFirstRow = -1;
                // Find the index of the current element in the first row
                for (int j = i; j >= 0; j--) {
                    if (firstRow[j] == current) {
                        indexInFirstRow = j;
                        
                    }
                }
                
                if (indexInFirstRow != -1) {
                    result.add(current);
                }
            }
        }
        
        return result;
    }
    
}