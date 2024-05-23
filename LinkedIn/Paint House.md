# Paint House

**Solution grade:** Optimal  
**Concepts:** Dynamic Programming, Arrays
**Time complexity:** O(n\*m)  
**Space complexity:** O(n\*m)
**LeetCode Link:** [Paint House](https://leetcode.com/problems/paint-house)<br>
**[Youtube Link]** : [Youtube](https://www.youtube.com/watch?v=-w67-4tnH5U)

## Logic

NOTE  : CAN USE THIS EXAMPLE. NOT ANYWHRE ON INTERNET.

input : [[2,4,2],[6,3,3],[6,6,7]]
    
|       | **H1** | **H2** | **H3** |
|-------|--------|--------|--------|
| **R** | 2      | 6      | 6      |
| **G** | 4      | 3      | 6      |
| **B** | 12     | 3      | 7      |


Create a 2D matrix to store the total cost. We will be computing cost for each color for each house and ensure that a colour used just before is not being used again.

    R.                     B.                    G.
H1  2                      4                     12  
H2  4 + min(4 +12) = 8     3 + min(2, 12) =  5   6 +min(2,4) = 8
H3  12 + min(5,8) = 17     3 + min(8,8) = 11     7 + min(5 + 8) = 20


|       | **R**         | **G**            | **B**                     |
|-------|----------------|-------------------|----------------------------|
| **H1** | 2              | 4   | 12       |
| **H2** | 4 + min(4 +12) = 8               | 3 + min(2, 12) =  5  |6 +min(2,4) = 8          |
| **H3** | 12 + min(5,8) = 17              |  3 + min(8,8) = 11   | 7 + min(5 + 8) = 20|

**Simple Formula** 

 prices[home][red]    = costs[home][red]    + minimum _of(prices[home -1 ][green] , prices[home -1 ][blue]) <br>
 prices[home][blue]   = costs[home][blue]   + minimum _of(prices[home -1 ][red] , prices[home -1 ][blue]) <br>
 prices[home][green]  = costs[home][green]  + minimum _of(prices[home -1 ][red] , prices[home -1 ][blue]) <br>

minimum in the last row will be the answer. (11 in above case)


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


