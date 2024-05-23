# Nested List Weight Sum

**Solution grade:** Optimal  
**Concepts:** DFS, Recursion
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum)


## Logic
```
nested list   =  [ 1, 2, [1 ,2], [2, [1]] ] 
nesting level =    1  1   2  2    2   3

Sum = Σ(value * nesting level)
    = 1*1 +  2*1 + 1*2 + 2*2 + 2*2 + 1*3 
    = 16
```

### Optimal Solution

We better use DFS (or BFS can be used as well) to find the depth of each of them .

```
def int dfs ( nestedList,  currentNestedLevel,  sum)
       for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * currentNestedLevel;
            } else {
                sum = dfs(nestedInteger.getList(), currentNestedLevel + 1, sum); // Pass currentNestedLevel + 1 for nested elements
            }
        }
        return sum;
       
```


### Optimal Code

```java

class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1, 0); // Start from nested level 1 with initial sum 0
    }

    private int dfs(List<NestedInteger> nestedList, int currentNestedLevel, int sum) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * currentNestedLevel;
            } else {
                sum = dfs(nestedInteger.getList(), currentNestedLevel + 1, sum); // Pass currentNestedLevel + 1 for nested elements
            }
        }
        return sum;
    }
}
