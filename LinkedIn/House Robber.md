# House Robber

**Solution grade:** Optimal  
**Concepts:** DP, Memorization, Single Pass
**Time complexity:** O(n)  
**Space complexity:** O(1)  
**LeetCode Link:** [House Robber](https://leetcode.com/problems/house-robber)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=AzER0wuL0QY)

## Logic




### Solution 
Upon observation, we can reach the followung equation. 
```
moneyMax[i] = Math.max( moneyMax[i -2 ] + moneyMax[i]  , moneyMax[i -1])

```

- Time complexity: O(n)
- Space complexity: O(n)


#### Code

```java
class Solution {
    public int rob(int[] nums) {
        int sum = 0;
        int []  money = new int[nums.length];
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        money[0] = nums[0];
        money[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++)
        {
            money[i] = Math.max(money[i -2] + nums[i] , money[i -1]);
            System.out.println( " " + money[i]);
        }
        return money[nums.length - 1];
    }
}
```


### Optimal Solution 
If we see we are never using the value of money array apart from the last two values.<br>
So we canu understadn from that, that we do not actually need an entire array, <br>
we just need the last two values.

- Time complexity: O(n)
- Space complexity: O(1)


#### Code

```java
class Solution {
    public int rob(int[] nums) {
        int sum = 0;
        int []  money = new int[nums.length];
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        money[0] = nums[0];
        money[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++)
        {
            money[i] = Math.max(money[i -2] + nums[i] , money[i -1]);
            System.out.println( " " + money[i]);
        }
        return money[nums.length - 1];
    }
}
```
