# Problem Name

**Solution grade:** Optimal  
**Concepts:** HashMap, Maths, Slope
**Time complexity:** O(n^2)  
**Space complexity:** O(n)  
**LeetCode Link:** [Max Points](https://leetcode.com/problems/max-points-on-a-line)

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


### Optimal solution


Above solution mei if we see, there were 3 points i,j and k. If slope(i,j) == slope(j,k) then it means i , j & k are all on the same line.
Keeping the above in mind what we can do is that we can fix and i. for that i iterate on all points j1, j2 and j3 and so on, calculate their slope and keep the count against the slope.
```
for example [[1,1] , [2,2], [2,3], [3,3]]
```
when i = [1,1]
```

map = {
  slope_1 = 2
  slope_2 = 1
}
```
This means there are atleast 3 ( 2 + 1) points on the same line with i whose slope is 1. <br>

Now, when i = [2,3]
```
map = {
  slope_2 = 1 ([1,1])
  slope_1 = 1 ([2,2])
  slope_0 = 1 ([3,3])
}
```
Similarly for others and so on.

Note : since slope (tan(theta) coule go to infinity at times, we rather store theta the angle using Math.atan2(dy,dx) in the map

- Time complexity: O(n^2)
- Space complexity: O(n) [New hashmap is being created each time with max size going not beyond n]



## Code

```java

class Solution {
    public int maxPoints(int[][] points) {
        int maxCount = 0 ; 
        if(points.length == 1) return 1;
        for(int i = 0 ;  i < points.length; i++)
        {
            HashMap<Double, Integer> countMap = new HashMap<>();
            for(int j = 0 ;  j < points.length; j++)
            {
                if (i != j)
                {
                    int dy = points[j][1] - points[i][1];
                    int dx = points[j][0] - points[i][0];
                    Double angle = Math.atan2(dy,dx);
                    countMap.put(angle, countMap.getOrDefault(angle,0) + 1);
                }
            }
           
            for(double angle : countMap.keySet())
            {
                maxCount = Math.max(maxCount, countMap.get(angle) + 1); // 1 extra because i ko consider kar lete hai
            }
        }
        return maxCount;
    }
}
```
