# Nested List Weight Sum II

**Solution grade:** Optimal  
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Nested List Weight Sum II](https://leetcode.com/problems/KADASBDVBDSVBKJDS)


## Logic




### Solution 1



- Time complexity: O(n)
- Space complexity: O(n)


### Optimal Code

```java
import java.util.*;

class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Map<Integer, Integer> depthSumMap = new HashMap<>();
        dfs(nestedList, depthSumMap, 1); // Start DFS traversal with depth 1
        if(depthSumMap.size() == 0) return 0;
        int maxDepth = Collections.max(depthSumMap.keySet()); // Get the maximum depth
        
        int depthSumInverse = 0;
        for (int depth : depthSumMap.keySet()) {
            depthSumInverse +=  (maxDepth - depth + 1) * depthSumMap.get(depth);
        }
        
        return depthSumInverse;
    }
    
    private void dfs(List<NestedInteger> nestedList, Map<Integer, Integer> depthSumMap, int depth) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                depthSumMap.put(depth, depthSumMap.getOrDefault(depth, 0) + nestedInteger.getInteger());
            } else {
                dfs(nestedInteger.getList(), depthSumMap, depth + 1); // Recursively call DFS with incremented depth
            }
        }
    }
}
```
