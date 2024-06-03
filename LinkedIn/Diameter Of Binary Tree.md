# Problem Name

**Solution grade:** Optimal  
**Concepts:** DFS, Preorder
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Diameter Of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=-DzowlcaUmE&t=3242s)

## Logic
First of all, we will consider that diameter is eqult to  number of nodes in the diameter path .

Ex : Can use this in interviews :
```
Tree 1 :       1
              / \                      
             2   3
            / \     
           4   5    
              / \
             6   7
In this case the diameter is : 6 5 2 1 3. That It IS_PASSING through root node.
```

```
Tree 2 :      

                 0
                /
               1
              / \                      
             2   3
            / \   \  
           4   5   8
              / \
             6   7

In this case the diamter is : 6 5 2 1 3 8. That It IS_NOT_PASSING through root node.
```

So we can assume , the diameter formula will be :
```
Diamter = max(diameter_left_sub_tree, diameter_right_sub_tree, diameter passing through the root node)
        = max(diameter_left_sub_tree, diameter_right_sub_tree, height_left + height_right + 1)
```
 


### Not Optimal Solution 1 

Write a func for calculating heightOfTree. (O(n) complexity)
And use the above computed formula


- Time complexity: O(n^2) [Nested recursion]
- Space complexity: O(n) [worst case stack trace in case of skewed binary tree]


####  Code

```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        return dfsDiameterOfTree(root);
    }
    
    
    private int dfsDiameterOfTree(TreeNode root)
    {
        if (root == null) return 0;
        int leftSubTreeHeight = heightOfTree(root.left);
        int rightSubTreeHeight = heightOfTree(root.right);
        int diameterOfLeftSubTree = dfsDiameterOfTree(root.left);
        int diameterOfRightSubTree = dfsDiameterOfTree(root.right);
        return Math.max(leftSubTreeHeight + leftSubTreeHeight + 1, Math.max(diameterOfLeftSubTree ,  diameterOfRightSubTree));
    }
    
    private int heightOfTree(TreeNode root)
    {
        if (root == null) return 0;
        return 1 + Math.max(heightOfTree(root.left) , heightOfTree(root.right));
    }
}
```



### Optimal Solution (Merging the two height and diameter funcs)

Nothing is going to chnage except that instead of calculating height in a separate function, let's calc height and diameter of a node in a single call.

- Time complexity: O(n) [Single recursion]
- Space complexity: O(n) [worst case stack trace in case of skewed binary tree]

#### Code

```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        return heightAndDiameterOfTree(root)[1];
    }
    
 
    private int [] heightAndDiameterOfTree(TreeNode root)
    {
       if (root == null) return new int [] {0,0};
       else
       {
           int [] heightAndDiameterOfLeftSubTree = heightAndDiameterOfTree(root.left);
           int [] heightAndDiameterOfRightSubTree = heightAndDiameterOfTree(root.right);
           
           int heightLeftSubTree = heightAndDiameterOfLeftSubTree[0];
           int heightRightSubTree = heightAndDiameterOfRightSubTree[0];

           int diameterLeftSubTree = heightAndDiameterOfLeftSubTree[1];    
           int diameterRightSubTree = heightAndDiameterOfRightSubTree[1];
           int diamterThroughMe =  heightLeftSubTree + heightRightSubTree + 1;
           
           
           int heightOfTree = 1 + Math.max(heightLeftSubTree , heightRightSubTree);
           int diameterOfTree = Math.max(Math.max(diameterLeftSubTree, diameterRightSubTree),  diamterThroughMe); //max of all 3 diams
           return new int[] {heightOfTree,diameterOfTree };
       }
    }
}
```
