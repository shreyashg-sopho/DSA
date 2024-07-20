# Min Eating Speed

**Solution grade:** Optimal  
**Concepts:** Binary Search, Greedy  
**Time complexity:** O(n log m)  
**Space complexity:** O(1)  
**LeetCode Link:** [Min Eating Speed](https://leetcode.com/problems/koko-eating-bananas/)

## Logic

To determine the minimum eating speed `K` that allows Koko to eat all the bananas within `h` hours, we can use binary search between the minimum and maximum possible speeds. The minimum speed would be the ceiling of the average number of bananas per hour (`ceil(total bananas / h)`) and the maximum speed would be the maximum number of bananas in any pile.

### Detailed Steps:

```
1. **Determine the Boundaries**:
   - **Max Bound**: The maximum number of bananas in any pile.
   - **Min Bound**: The ceiling of the total number of bananas divided by `h`.

2. **Binary Search**:
   - Initialize `left` to the minimum bound and `right` to the maximum bound.
   - Use binary search to determine the minimum workable speed.
   - For each midpoint value, calculate the total hours Koko would spend eating bananas at that speed.
   - Adjust the search bounds based on whether the calculated hours are within the allowed time `h`.
```

### Example Walkthrough:
#### Self Example
```
Suppose `piles = [4, 7, 1, 9, 3]` and `h = 6`.
```
#### Dry run

```
1. Calculate the maxBound and minBound:
   - `maxBound = 9` (maximum in piles)
   - `sum = 4 + 7 + 1 + 9 + 3 = 24`
   - `minBound = ceil(24 / 6) = 4`

2. Perform binary search:
   - `left = 4`, `right = 9`
   - Calculate `middle = (left + right) / 2 = 6`
   - Check if `middle` is a valid eating speed by summing the required hours for all piles.
     - `hourSpent = ceil(4 / 6) + ceil(7 / 6) + ceil(1 / 6) + ceil(9 / 6) + ceil(3 / 6) = 1 + 2 + 1 + 2 + 1 = 7`
     - Since 7 > 6, adjust `left = middle + 1 = 7`
   - Calculate `middle = (left + right) / 2 = 8`
     - `hourSpent = ceil(4 / 8) + ceil(7 / 8) + ceil(1 / 8) + ceil(9 / 8) + ceil(3 / 8) = 1 + 1 + 1 + 2 + 1 = 6`
     - Since 6 <= 6, adjust `right = middle = 8`
   - Calculate `middle = (left + right) / 2 = 7`
     - `hourSpent = ceil(4 / 7) + ceil(7 / 7) + ceil(1 / 7) + ceil(9 / 7) + ceil(3 / 7) = 1 + 1 + 1 + 2 + 1 = 6`
     - Since 6 <= 6, adjust `right = middle = 7`
   - At this point, `left` and `right` converge at 7.
````

### Code

```java
import java.util.*;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Find out the min and max Bound possible for the result
        int maxBound = 0;
        int sum = 0;
        for (int pile : piles) {
            maxBound = Math.max(maxBound, pile);
            sum += pile;
        }
        int left = (int) Math.ceil((double) sum / h);
        int right = maxBound;

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middle = (left + right) / 2;
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middle);
            }

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return right;
    }
}
