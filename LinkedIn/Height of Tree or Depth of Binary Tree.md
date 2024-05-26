# Height of Tree or Depth of Binary Tree

**Solution grade:** Optimal  
**Concepts:** DFS, Traversal on Tree (PreOrder or any DFS based)
**Time complexity:** O(n)  
**Space complexity:** O(log n)  
**LeetCode Link:** [Height of Tree or Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=hyAqgckHUiA)

## Logic




### Solution 1
Height at a given node = 1 +  height of left subtree + height of right subtree
So, simply do DFS recursive traversal and we will get the height.
```
def int dfsHeightOfTree(root)
  if (root == null)   return 0;
  else return 1 +  Math.max(dfsHeightOfTree(root.left)  + dfsHeightOfTree(root.right))

```

- Time complexity: O(n)
- Space complexity: O(log n)  [Recursion tack size will not be greater than heigh of tree]


### Optimal Code

```java
class Solution {
    public int maxDepth(TreeNode root) {
        return heightOfTree( root);
    }
    
    
    private int heightOfTree(TreeNode root)
    {
        if(root == null) return 0;
        return 1 + Math.max(heightOfTree(root.left) ,heightOfTree(root.right) );
    }
}
```
