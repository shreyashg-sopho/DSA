# Max Area Of Island

**Solution grade:** Optimal  
**Concepts:** DFS , BFS <br>
**Time complexity:** O(n * m)  <br>
**Space complexity:**  O(n * m) <br>
**LeetCode Link:** [Max Area Of Island](https://leetcode.com/problems//max-area-of-island)


## Logic
We can use standard DFS. Whenever we find consicutive neighbouring 1s we keep on adding. 
Both solutions are optimal. Prefer DFS though it is simple and efficient.  Can go with BFS as well. No difference as such.



### Optimal DFS Solution 

Standard DFS traversal

- Time complexity: O(n*m)
- Space complexity: O(n*m)


####  Code

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int area = 0; 
        int maxArea = 0;
        for(int i = 0 ; i < grid.length; i++)
        {
            for(int j = 0 ; j < grid[0].length;j++)
            {
                if(grid[i][j] == 1)
                {
                    area = dfs( grid, i,j);
                    maxArea = Math.max(area, maxArea);
                }
                
            }
        }
        return maxArea;
    }
    
    
    private int dfs(int[][] grid,  int row, int col)
    {
        if(!isValidRow(row, col, grid.length, grid[0].length)) return 0;
        if(grid[row][col] ==  -1 || grid[row][col] == 0 ) return 0;
        grid[row][col] = -1; // marking visited
        int area = 1;
        //call dfs on all the nodes
        area += dfs( grid,  row + 1,  col);
        area += dfs( grid,  row,  col + 1);
        area += dfs( grid,  row - 1,  col);
        area += dfs( grid,  row,  col - 1);
        return area;
    }
    
    
    private boolean isValidRow(int row, int col, int maxRow, int maxCol)
    {
        if(row >= maxRow || row < 0 || col >= maxCol || col < 0)  return false;
        return true;
    }
}
```



###  BFS Solution 

Standard BFS traversal

- Time complexity: O(n*m)
- Space complexity: O(n*m)


####  Code

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int area = 0; 
        int maxArea = 0;
        for(int i = 0 ; i < grid.length; i++)
        {
            for(int j = 0 ; j < grid[0].length;j++)
            {
                if(grid[i][j] == 1)
                {
                    area = bfs( grid, i,j);
                    maxArea = Math.max(area, maxArea);
                }
                
            }
        }
        return maxArea;
    }
    
    
    private int bfs(int [][] grid, int row, int col)
    {
        int area = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int [] {row,col});
        // call bfs for all child of curr row col
        while(!queue.isEmpty())
        {
            int[] rowcol = queue.poll();
            int curRow = rowcol[0];
            int curCol = rowcol[1];
            
            
            if(!isValidRowCol(curRow, curCol, grid.length, grid[0].length)) continue;
            if(grid[curRow][curCol] == 0 || grid[curRow][curCol] == -1 ) continue;
            
            grid[curRow][curCol] = -1;
            area++;

            queue.add(new int[] {curRow + 1, curCol});
            queue.add(new int[] {curRow, curCol + 1});
            queue.add(new int[] {curRow - 1, curCol});
            queue.add(new int[] {curRow, curCol - 1});
            
        }
        return area;
    }
    
    
    private boolean isValidRowCol(int row, int col, int maxRow, int maxCol)
    {
        if(row >= maxRow || row < 0 || col >= maxCol || col < 0)  return false;
        return true;
    }
}
```
