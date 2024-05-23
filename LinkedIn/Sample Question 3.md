# Problem Name

**Solution grade:** Optimal  
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Shortest Word Distance II](https://leetcode.com/problems/KADASBDVBDSVBKJDS)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=AzER0wuL0QY)

## Logic

traingle has a property that for 3 lengths to find a triangle, some of any two must always be greater that the third one.


### Brute Force 1



### Optimal Code

```java
class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        for(int i = 0 ; i < nums.length - 2; i++)
        {
            {
                for(int j = i + 1; j < nums.length -1; j++)
                {
                    for(int k = j +1 ; k < nums.length ; k++)
                    {
                         if ((nums[i] + nums[j] > nums[k]) && (nums[j] + nums[k] > nums[i]) && (nums[i] + nums[k] > nums[j]))
                            {count ++;}
                    }
                }
            }
        }
        return count;
    }
}
```
