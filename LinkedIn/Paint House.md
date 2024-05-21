# Problem Name

**Solution grade:** Optimal  
**Concepts:** Dynamic Programming, Arrays
**Time complexity:** O(n*m)  
**Space complexity:** O(n*m)
**LeetCode Link:** [Paint House](https://leetcode.com/problems/paint-house)

## Logic

NOTE  : CAN USE THIS EXAMPLE. NOT ANYWHRE ON INTERNET.
    
|       | **H1** | **H2** | **H3** |
|-------|--------|--------|--------|
| **R** | 2      | 6      | 6      |
| **G** | 4      | 3      | 6      |
| **B** | 12     | 3      | 7      |


Create a 2D matrix to store the total cost. We will be computing cost for each color for each house and ensure that a colour used just before is not being used again.

|       | **H1**         | **H2**            | **H3**                     |
|-------|----------------|-------------------|----------------------------|
| **R** | 2              | 4 + min(4,12) = 8 | 12 + min(5,8) = 17         |
| **G** | 4              | 3 + min(2,12) = 5 | 3 + min(8,8) = 11          |
| **B** | 12             | 6 + min(2,4) = 8  | 7 + min(5,8) = 12 + 5 = 17 |

**Simple Formula** 
 prices[home][red]    = costs[home][red]    + minimum _of(prices[home -1 ][green] , prices[home -1 ][blue])
 prices[home][blue]   = costs[home][blue]   + minimum _of(prices[home -1 ][red] , prices[home -1 ][blue])
 prices[home][green]  = costs[home][green]  + minimum _of(prices[home -1 ][red] , prices[home -1 ][blue])

- Time complexity: O(n*m)  
- Space complexity: O(n*m)  


### Optimal Code

```java
class Solution {
    public int minCost(int[][] costs) {
        int [][] prices = new int[costs.length][3];
        for(int home = 0 ; home < costs.length; home++)
        {
                if(home == 0)
                {
                    prices[home][0] = costs[home][0];
                    prices[home][1] = costs[home][1];
                    prices[home][2] = costs[home][2];
                }
                else
                {
                    prices[home][0] = costs[home][0] + Math.min(prices[home -1 ][1] , prices[home -1 ][2]);
                    prices[home][1] = costs[home][1] + Math.min(prices[home -1 ][0] , prices[home -1 ][2]);
                    prices[home][2] = costs[home][2] + Math.min(prices[home -1 ][0] , prices[home -1 ][1]);
                }
        }
        return Math.min( prices[costs.length - 1][2] , Math.min( prices[costs.length - 1][0], prices[costs.length - 1][1]));
    }
}


