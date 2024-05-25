#  Kth Largest Element in an Array

**Solution grade:** Optimal  
**Concepts:** Sorting, Min Heap, Max Heap as well.
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [ Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=AzER0wuL0QY)

## All solution discussion : 




### Solution 1

To get Kth largest Element in an array. Sort the array and get the n - k element and you will have your answer.


### Code

```java
import java.util.*;
class Solution {
    public int findKthLargest(int[] nums, int k) {
      Arrays.sort(nums);
      return nums[ nums.length - k];
    
    }
}
```
