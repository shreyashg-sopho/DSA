/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

// TIME AND SPACE
// In the findCelebrity method, there is a loop that iterates over each person once, resulting in O(n) time complexity.
// Inside the isCelebrity method, there is another loop nested within the first loop, resulting in O(n^2) time complexity.
// Each iteration of the nested loop involves calling the knows method, which has constant time complexity.
// Therefore, the overall time complexity is O(n^2).

public class Solution extends Relation {
    // Ye use hi nahi ho raha hai 
    //private Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>(); 
    

    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++) {
            if (isCelebrity(i, n)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean isCelebrity(int candidate, int n) {
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue; // Skip if it's the same person.
            if (knows(candidate, i) || !knows(i, candidate)) {
                return false;
            }
        }
        return true;
    }
}


