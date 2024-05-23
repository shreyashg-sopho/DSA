# Can Place Flowers

**Solution grade:** Optimal  
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(1)  
**LeetCode Link:** [Can Place Flowers](https://leetcode.com/problems/can-place-flowers)


### Optimal Solution 

Make a single pass. If i is ) at the moment and  i - 1 and i + 1 are 0, then flip the current one to 1.
(Handle edge cases though)

- Time complexity: O(n)
- Space complexity: O(1)


### Optimal Code

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //Just simply iterate over the aray and flip all the ) that you can, just make sure that while flipping the condition is not disturbed.
        int size = flowerbed.length - 1;
        int flipped = 0;
        if(flowerbed.length == 1 && flowerbed[0] == 0) flipped++; // if array is of length 1.
        if(flowerbed.length >= 2 && flowerbed[0] == 0 && flowerbed[1] == 0 ) // for 0th element we only will check the next element is 0.
        {
            flowerbed[0] = 1;
            flipped++;
        }
        for(int i = 1; i < flowerbed.length - 1; i++ )
        {
            if(flowerbed[i] == 0)
            {
                    if(flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0)
                    {
                        flowerbed[i] = 1;
                        flipped++;
                    }
            }
            
        }
        
        if(flowerbed.length >= 2 && flowerbed[size] == 0 && flowerbed[size - 1] == 0 ) for last element we only will check the previous element is 0.
        {
            flowerbed[size] = 1;
            flipped++;
        }
        return flipped >= n;
    }
}
