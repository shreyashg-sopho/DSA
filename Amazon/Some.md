# Minimum Adjacent Swaps To Make a Valid Array

**Solution grade:** Optimal  
**Concepts:** Find min max, formula based, Math
**Time complexity:** O(n)  <br>
**Space complexity:** O(1)  <br>
**LeetCode Link:** [Plates Between Candles](https://leetcode.com/problems/plates-between-candles/) <br>



### Optimal Solution

 Find min_index_of_min_value and max_index_of_max_value and just use the formula.
 ```
 min swaps = min_index_of_min_value + (length - 1 - max_index_of_max_value) - (overlapping)
 ```
### Example Walkthrough:
```

 Self made Ex : nums => [ 3,5,6,8,1,8]

  Suppose nums = [3, 5, 6, 8, 1, 8].
  The minimum element is 1 at index 4 (minidx = 4).
  The maximum element is 8 at index 5 (maxidx = 5).
  Swaps required to move 1 to the front: minidx = 4.
  Swaps required to move 8 to the back: (len(nums) - maxidx - 1) = 6 - 5 - 1 = 0.
  Since minidx < maxidx, no need to subtract 1.
  Total swaps: 4 + 0 = 4.
```
### Code logic :
```
 Find min_index_of_min_value = min_index_of 1 = 4
 Find max_index_of_max_value = mx_index_of 8 = 5
 if max_index_of_max_value >= min_index_of_min_value : 
    that means that min and max will not cross while swapping, hence overlapping = 0
 else
     means  min and max will  cross while swapping, hence overlapping = 1
 
 Therefore, 
 number of swaps = min_index_of_min_value + (length - 1 - max_index_of_max_value) - (overlapping)
 number of swaps = 4 + (6 - 1 - 5) + (0) = 4

```




- Time complexity : O(n) 
- Space complexity: O(1)


####  Code

```java

class Solution {
    public int minimumSwaps(int[] nums) {
        int index_min = 0;
        int index_max = 0;
        
        for(int i = 0; i < nums.length ; i++)
        {
            //find max index of max value
            if(nums[i] >= nums[index_max])
            {
                index_max = i;
            }
            //find min index of min value
            if(nums[i] < nums[index_min])
            {
                index_min = i;
            }
        }
        int overlapping = 0;
        if(index_min > index_max) 
        {
            overlapping = 1;
        }
        return (nums.length - index_max - 1) + index_min - (overlapping);
        
    }
}


```
