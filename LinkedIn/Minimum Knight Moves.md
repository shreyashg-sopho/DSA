# Minimum Knight Moves

**Solution grade:** Optimal  
**Concepts:** BFS traversal <br>
**Time complexity:** O(x * y)  [where x and y are limits of the board] <br>
**Space complexity:** O(x * y)   <br>
**LeetCode Link:** [Minimum Knight Moves](https://leetcode.com/problems/minimum-knight-moves)


## Logic

Simple BFS is needed. DFS can be used but it will search only in  one direction first leading to late result identification.
So hence prefering BFS otherwise DFS also can give the answer.


### Solution 1
Note that being able to reach x, y in the 2D graph means we can reach <br>
(x, y) (-x, y) (x, -y) (-x, -y) (-x, -y) (-x, -y) as well.

So whatever x, y input is given , we can max it abs(x) and abs(y) and search the code.
This will dramatically reduce the score of searching to 1/4.

We will be using simple BFS.


- Time complexity: O(x * y)
- Space complexity: O(x * y)


### Optimal Code

```java
class Solution {
    public int minKnightMoves(int x, int y) {
        // Convert coordinates to their absolute values
        //chess boards are symmteric so reaching  x,y is same as reaching :
        // (x, y) (-x, y) (x, -y) (-x, -y) (-x, -y) (-x, -y)
        x = Math.abs(x);
        y = Math.abs(y);
        return bfs(0, 0, x, y);
    }
    
    private int bfs(int row, int col, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        Set<String> visited = new HashSet<>();
        visited.add(row + "_" + col);
        int level = 0; 

        int[][] directions = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int count = 0; count < size; count++) {
                int[] rowCol = queue.poll();
                if (rowCol[0] == x && rowCol[1] == y) return level;
                
                for (int[] dir : directions) {
                    int newRow = rowCol[0] + dir[0];
                    int newCol = rowCol[1] + dir[1];
                    String newPos = newRow + "_" + newCol;

                     // checking on -1 because we want to keep our search limited to first quadrant only. 
                     // we are searching for abs value. so we do not want to go beyond first quadrant
                    if (newRow >= -1 && newCol >= -1 && !visited.contains(newPos)) { 
                        queue.add(new int[]{newRow, newCol});
                        visited.add(newPos);
                    }
                }
            }
            level++;
        }
        return -1; // This should never be reached since the problem guarantees a solution.
    }
}
```
