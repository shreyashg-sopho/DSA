# LCA BST- Lowest Common Ancestor of a Binary Search Tree

**Solution grade:** Optimal  
**Concepts:** BST, Binary Search Tree, Recursion, Traversal<br>
**Time complexity:** O(h) [h is height of tree]  <br>
**Space complexity:** O(h)  [stack memory taken into account] otherwise O(1) <br>
**LeetCode Link:** [LCA BST - Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree)<br>
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=cX_kPV_foZc)

## Logic
BST , we can use the sorting property of BST here which states that less than curr will all be in the left of currnode.
And values more than the current will all be in the right subtree of currNode.



### Optimal Solution 


```
for two nodes p ad q :
if p and q, both are less than currNode :
      Then p and q exist in left subtree we need to go deeper in left subtree to find LCA.
if p and q, both are more than currNode :
      Then p and q exist in right subtree we need to go deeper in right subtree to find LCA.
else (this means one is greater and one is smaller than root) :
      The currNode itself is the LCA   

```
- Time complexity: O(h) [h is height of tree]
- Space complexity: O(h) [stack memory taken into account] otherwise O(1)


#### Code

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return LCABST(root,p,q);
    }
    
    
    private TreeNode LCABST(TreeNode root, TreeNode p, TreeNode q)
    {
        if (root == null ) return null;
        int currVal = root.val;
        if(currVal > p.val && currVal > q.val)
        {
             return LCABST(root.left, p,q);
        }
        else if (currVal < p.val && currVal < q.val)
        {
             return LCABST(root.right, p,q);
        }
        return root;
    }
}
```
