# LCA Binary Tree -  Least Common Ancestor of a Binary Tree

**Solution grade:** Optimal  
**Concepts:** Binary Tree, Trees, Traversal
**Time complexity:** O(n) [all nodes traversed once] <br>
**Space complexity:** O(n) [recursion stack] <br>
**LeetCode Link:** [LCA Binary Tree -  Least Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=cOjLyASDJcc)

## Logic
Reach the first node. Add all the nodes needed to reach it in a List.
Reach the second node. Add all the nodes needed to reach it in a List.

THen go through the list and find the first match.

But this is boring. And requires additional space.

Better solution is through the observation captured below




### Optimal Solution 


```
                   1
               /       \
              2         8
             / \      /  \
            3   4     7    9
               / \          \
              5   6         10
                            /
                          11

Ex 1 : LCA(6,10)  = 1
Ex 2 : LCA(2,6)   = 2
Ex 3 : LCA(8,11)  = 8


Case 1 : LCA lies in two different subtrees as in Ex 1.
Case 2 : LCA lies in the same subtree as in Ex 2.
Case 3 : One of the nodes itself is LCA  as in Ex 3.

These are the only 3 cases possible. And eventually all cases on recursive calls will turn to case 3.

```
So we can build the algo from this :

```
def Node LCA (root, n1, n2)
{
  if (root == null) return null;
  if( node1 == root || node2 == root) return root; //case 3
  Node leftLCA = LCA(root.left, node1, node2);
  Node rightLCA = LCA(root.right, node1, node2);
  if (leftLCA == null) return rightLCA; // left is null that means it must lie in right subtree.
  if (rightLCA == null) return leftLCA; // right is null that means it must lie in left subtree.
  return root;
 
}

```

Create a hashMap where we are storing all the indexes........

- Time complexity: O(n) [all nodes traversed once]
- Space complexity: O(n) [recursion stack]


### Optimal Code

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       return LCA(root, p,q);
    }
    
    
    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q)
    {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode leftLCA = lowestCommonAncestor(root.left, p,q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p,q);
        
        if(leftLCA == null) return rightLCA;
        if(rightLCA == null) return leftLCA;
        return root;
    }
}
