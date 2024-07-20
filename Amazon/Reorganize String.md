# Reorganize String

**Solution grade:** Optimal  
**Concepts:** Frequency Counting, Priority Queue , Max heap
**Time complexity:** O(n log n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Reorganize String](https://leetcode.com/problems/reorganize-string/)

## Logic

To reorganize a string so that no two adjacent characters are the same, we can use a priority queue to arrange characters based on their frequencies. The idea is to always place the highest frequency character first, but ensure that it doesn't repeat consecutively.

### optimal Solution 

1. Count the frequency of each character in the input string using an integer array.
2. Use a priority queue to store characters sorted by their frequencies in descending order.
3. Construct the result string by repeatedly adding the highest frequency character, and ensure that no two adjacent characters are the same by using the next available character when necessary.

#### Example Walkthrough:
```
Suppose `s = "aabbac"`.

1. Frequency count:
   - `charFreq = [3, 2, 1, 0, ..., 0]` (for 'a', 'b', 'c', ...)
  
2. After populating the priority queue:
   - `pq = [(a, 3), (b, 2), (c, 1)]`
   
3. Constructing the result:
   - Add 'a', then 'b', then 'a', then 'c', then 'a', then 'b'.
   - Result: `"abacab"`
```
#### Time and Space Complexities 

Space complexity: O(n)
Time complexity : O(n log n)   (PQ/Max heap making dominates the overall time complecity)



#### Code

```java
import java.util.*;

class Solution {
    public String reorganizeString(String s) {
        int[] charFreq = new int[26];
        for (char c : s.toCharArray()) {
            charFreq[c - 'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (charFreq[i] > 0) {
                pq.offer(new int[]{i, charFreq[i]});
            }
        }

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] top = pq.poll();

            // Check if last added character is the same as current character
            if (result.length() == 0 || result.charAt(result.length() - 1) != (char)('a' + top[0])) {
                result.append((char)('a' + top[0]));
                top[1]--;
                if (top[1] > 0) {
                    pq.offer(top);
                }
            } else {
                // If the last added character is the same, we need to use the next available character
                if (pq.isEmpty()) {
                    return "";
                }
                int[] next = pq.poll();
                result.append((char)('a' + next[0]));
                next[1]--;
                if (next[1] > 0) {
                    pq.offer(next);
                }
                pq.offer(top); // Re-offer the top character back to the priority queue
            }
        }

        return result.toString();
    }
}
