# Valid Perfect Square

**Solution grade:** Optimal  
**Concepts:** Binary Search, Two pointer
**Time complexity:** O(logN)  
**Space complexity:** O(1)  
**LeetCode Link:** [Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square)


## Logic


### Solution 1
Some facts  :
- Sqaure Root of number n is always less than n/2.

Simple bro, do a binary search between 1 and n/2 .

- Time complexity: O(logN)  
- Space complexity: O(1)


### Optimal Code

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }
        
        long left = 2, right = num / 2, mid, guessSquared;
        
        while (left <= right) {
            mid = left + (right - left) / 2; // Avoid potential overflow
            guessSquared = mid * mid;     // Keeping this long to avoid overflow
            
            if (guessSquared == num) {
                return true;
            }
            
            if (guessSquared > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return false;
    }
}
```
