# Problem Name

**Solution grade:** Optimal  
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Shortest Word Distance II](https://leetcode.com/problems/KADASBDVBDSVBKJDS)

## Logic




### Brute force solution

Infinite lines pass through a point.
There will always be one line passing though any 2 random points on the graph.
To see if 3 points are on same line. We can use this math formula :

```
(y1 - y2)/(x1 - x2) = (y2 - y3)/(x2 - x3)
```
So we can generate all possible combinations and check the same.<br>
Run two loops to make a pair of 2 points i and j , calc their slope m = (y1 - y2)/(x1 - x2) and then run a third loop to find all such points that match the abive equation.

- Time complexity: O(n^3)
- Space complexity: O(n^3)


## Code

```java
class Solution {
    public int maxPoints(int[][] points) {
        int maxCount = 2;
        if(points.length == 1) return 1;
        for(int i = 0 ; i < points.length; i++)
        {
            for(int j = i + 1 ; j < points.length; j++)
            {
                int dy = points[i][1] - points[j][1];
                int dx = points[i][0] - points[j][0];
                int count = 2; // because these 2 points are definitely on a line
                // Now we run a loop and check what other points are on the same line
                for(int k = 0 ; k < points.length; k++)
                {
                    if(i != k && j != k) // we do not want to consider already taken points
                    {
                        int dy_ = points[k][1] - points[j][1];
                        int dx_ = points[k][0] - points[j][0];
                        
                        // if the 3 points are in line then dy/dx = dy_/dx_
                        // i.e dy*dx_ = dy_*dx (Do multiplication because division my floats wagera handle karna padega and so on
                        if(dy*dx_ == dy_*dx )
                        {
                            count++;
                            maxCount = Math.max(count, maxCount);
                        }
                    }
                }
            }
        }
        return maxCount;
    }
}
```

