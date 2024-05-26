# Problem Name

**Solution grade:** Optimal  
**Concepts:** Level Order Traversal, BFS
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Binary-Tree-Level-Order-Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal)





### Optimal Solution
Use simple BFS add store . Standard Level Order traversing is :
- Add all the nodes of current level in the queue.
- Take a note of how many are there in the queue at the moment. say k
- Pop out k nodes store there value in the current level,
- Also  while popping out , make sure too add all the children of the k pops done in queue for the next lecel.


####Psuedocode
```
Queue q = new Queue()
result = 2D array()
q.add(root)

while(queue is not empty)
{
   size = queue.size
   //All items in the queue from 0 to int size  are from the current level
   for(i = 0 ; i < size; i++)
   {
      node = queue.pop();
      curreLevel.add(node.val)
      // the popped nodes childer in the queue as they will be forming the next level
      if(node.left is not null) queue.add(node.left)
      if(node.right is not null) queue.add(node.right)
   }
   result.add(currLevel)
}


```


- Time complexity: O(n) Single iteration on all nodes
- Space complexity: O(n) [max items at any levels will  be n/2]


#### Code

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        return bfs( root);
       
    }
    
    private List<List<Integer>> bfs( TreeNode root)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        queue.add(root);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for(int i = 0 ; i < size; i++)
            {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                // add it's on it's children to the queue
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(currentLevel);
            
        }
        return result;
    }
}
```
