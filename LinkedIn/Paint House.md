# Problem Name

**Solution grade:** Optimal  
**Concepts:** Dynamic Programming, Arrays
**Time complexity:** O(n*m)  
**Space complexity:** O(n*m)
**LeetCode Link:** [Paint House](https://leetcode.com/problems/paint-house)

## Logic
    
|       | **H1** | **H2** | **H3** |
|-------|--------|--------|--------|
| **R** | 2      | 6      | 6      |
| **G** | 4      | 3      | 6      |
| **B** | 12     | 3      | 7      |



|       | **H1**         | **H2**            | **H3**                     |
|-------|----------------|-------------------|----------------------------|
| **R** | 2              | 4 + min(4,12) = 8 | 12 + min(5,8) = 17         |
| **G** | 4              | 3 + min(2,12) = 5 | 3 + min(8,8) = 11          |
| **B** | 12             | 6 + min(2,4) = 8  | 7 + min(5,8) = 12 + 5 = 17 |



### Solution 1

Create a hashMap where we are storing all the indexes........

- Time complexity: O(n*m)  
- Space complexity: O(n*m)  


### Optimal Code

```java
//OPTIMAL
import java.util.*;

class WordDistance {
   .
   .
   .
   .

 
}

