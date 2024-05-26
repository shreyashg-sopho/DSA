# Nested List Weight Sum II 

**Solution grade:** Optimal  
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Nested List Weight Sum II](https://leetcode.com/problems/KADASBDVBDSVBKJDS)


## Logic

Can use this example : 

nested list                         =  [ 1, 2, [1 ,2], [2, [1]]] 
nesting level                       =    1  1   2  2    2   3
max Level                           =    3  3   3  3    3   3
weight = max - their ownlevel  + 1  =    3  3   2  2    2   1




Sum = Σ((weight)* value )
    = 3*1 +  3*2 + 2*1 + 2*2 + 2*2 + 1*1
    = 3 + 6 + 2 + 4 + 4 + 1
    = 20



### Optimal (but requires 2 pass, there exists one with one pass but unnecessarily complex)

```
calc max depth ()
    maxDepth = 1;
    for (NestedInteger nested : list) {
        if (if is a list) {
            maxDepth = max(maxDepth, 1 + findMaxDepth(nested.getList()));
        }
    }

calc weighted sum()
  


```


- Time complexity: O(n) [single pass through all]
- Space complexity: O(n) [Recursion stack]


### Optimal Code

```java
import java.util.*;

class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = findMaxDepth(nestedList);
        return weightedSum(nestedList, 1, maxDepth);
    }

    private int findMaxDepth(List<NestedInteger> list) {
        int maxDepth = 1;
        
        for (NestedInteger nested : list) {
            if (!nested.isInteger() && nested.getList().size() > 0) {
                maxDepth = Math.max(maxDepth, 1 + findMaxDepth(nested.getList()));
            }
        }
        
        return maxDepth;
    }
    
    private int weightedSum(List<NestedInteger> list, int depth, int maxDepth) {
        int answer = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                answer += nested.getInteger() * (maxDepth - depth + 1);
            } else {
                answer += weightedSum(nested.getList(), depth + 1, maxDepth);
            }
        }
        return answer;
    }
}
```
