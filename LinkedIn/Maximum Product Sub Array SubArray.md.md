# Maximum Product Subarray

**Solution grade:** Optimal  
**Concepts:** Suffix Prefix Product, Arrays, Kadane's Algo<br>
**Time complexity:** O(n)  <br>
**Space complexity:** O(1)  
**LeetCode Link:** [Maximum Product Sub Array](https://leetcode.com/problems/maximum-product-subarray)<br>
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=hnswaLJvr6g)





### Brute Force


generate all combinitions and calc max.

```
[1 2 3 4 -1 5 9 -3 5]
So say currently we are at i = 3

 i = 3      [1 2 3  4 -1 5 9 -3 5]

 j = 4              ↑__↑
 j = 5              ↑____↑
 j = 6              ↑______↑
 j = 7              ↑_________↑
 j = 8              ↑____________↑
                    i              j

```


- Time complexity: O(n^2)
- Space complexity: O(1)

### Code
```java
class Solution {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int currProd =1;
        for(int i = 0 ;  i < nums.length; i++)
        {
            currProd = 1;
            for(int j = i ; j < nums.length;j++)
            {
               currProd *= nums[j];
               result = Math.max(result, currProd);
            }
        }
        return result;
    }
}
```

### Optimal Solution

```
Forget about 0's for now.
If array has even all positives                      : ans is entire sub array.
If array has even number of negatives                : ans is entire array again.
Only case remaining is where there are odd negatives :

Example nums = [ 1 2 4 5 -1 3 2 5 6 -2  5 -2]
                          ↑          ↑    ↑
                 |______|   |______________|
                    p1              p2
                 |_________________|   |_|
                    p1                  p2
When we remove one negative, the array is divided into two parts 
where part 1 has no negatives and part 2 has even negatives. 
part 1 is nothing but prefix product before ith element and part 2 is prefix product as part 2.
So max product is nothing but max of (prefixProd, SuffixProd)


Note Corner case : When you encounter 0, your predfix/suffix prod will become zero 
and no matter what you multiply it with later it will alwys remian zero. So whenever 
we encoutner , we reset our prefix and suffix products to 1. So that 0 is not carry forwarded.

- Time complexity: O(n)
- Space complexity: O(1)

              

```
### Code

```java
class Solution {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int prefix = 1;
        int suffix = 1;
       
        for(int i = 0; i < nums.length; i++)
        {
            if (prefix == 0) prefix = 1; // required for corner case handling
            if(suffix == 0) suffix = 1; // required for corner case handling
            prefix *= nums[i];
            suffix *= nums[nums.length - i - 1];
            result = Math.max(result, Math.max(prefix, suffix));
        
        }
        return result;
    }
}
```



### Solution 3 : Exactly same complexities as Optimal , but by using Kadane's algorithm.
This solution is not very intutive so very difficult to come up with this in interview unless you memorize it.
So better is above solution only. Pasting the code for it though.

```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxProd = nums[0];
        int minProd = nums[0];
        int globalMax = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // If the current number is negative, swap maxProd and minProd
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }
            
            // Update maxProd and minProd
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);
            
            // Update the global maximum product
            globalMax = Math.max(globalMax, maxProd);
        }
        
        return globalMax;
    }
}

```
