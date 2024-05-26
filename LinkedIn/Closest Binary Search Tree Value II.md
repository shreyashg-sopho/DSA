# Problem Name

**Solution grade:** Closest Binary Search Tree Value II
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Closest Binary Search Tree Value II](https://leetcode.com/problems/KADASBDVBDSVBKJDS)


## Logic




### Near Optimal

Create a hashMap where we are storing all the indexes........

- Time complexity: O(n * log K)
- Space complexity: O(K)


### Optimal Code

```java
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((b,a) -> Double.compare(Math.abs(target - a), Math.abs(target - b)));
        traverseBSTDFS(root, maxHeap, k);
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < k ; i++)
        {
            result.add( maxHeap.poll());
        }
        return result;
    }
    
    
    private void  traverseBSTDFS(TreeNode root, PriorityQueue maxHeap ,  int k )
    {
        if(root == null) return ;
        
        maxHeap.add(root.val );
        if(maxHeap.size() > k)
        {
            maxHeap.remove();
        }
        traverseBSTDFS(root.left, maxHeap, k);
        traverseBSTDFS(root.right, maxHeap, k);
        
        
    }
}
```






### Optimal Solution 

In the abiove solution, that solution would have worked on any tree regardless of it being sorted or not because our heap was taking care of maintaining the order at the end of the day. So we really did not use the sorting property to our advantage.


Know that travering the BST will help us access elemtns in ascending order.

So if let's maintain a queue and add k elements. (Intution is difficult but let's try with a queue).


```
Made up example : 
say BST traversal becomes : [2,3,7,8]
target = 6
k  = 2
```

Iteration 1 :
```
currVal = 2
since queue.size() < k , hence queue.add(2)

queue =  [2]

```

```
Iteration 2 :
currVal = 3
since queue.size() < k , hence queue.add(2)

queue =  [2, 3]
```

Iteration 3 :
```
currVal = 7
Can't add to the queue directly.

Let's compare this to the first element in the queue becuase it is the fartheest one from target.
min(Math.abs( 7 - target) , Math.abs( queue.peek() - target)
i.e min(Math.abs( 7 - target) , Math.abs( 2 - target)
i.e min(abs(7-6), abs(2-6))
i.e min(1, 4)
i.e min is 1 and hence we would like to keep 7 in the queue and pop out 2.

therefore pop : 2 and add 7 to the queue. 
queue =  [ 3, 7]
```

Iteration 4 :
```
currVal = 8
Can't add to the queue directly.
min(abs(8 - 6), ans(3 - 6))
i.e min(2, 3)
i.e 8 is closer to target than 3 is.

therefore pop : 3 and add 8 to the queue. 
queue =  [ 7, 8]
```
That's it. The queue is the result !!



#### Code



```java
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> queue = new LinkedList<>();
        dfs(root, queue, k, target);
        return new ArrayList<>(queue);
    }
    
    public void dfs(TreeNode node, Queue<Integer> queue, int k, double target) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, queue, k, target);
        
        if (queue.size() < k) {
            queue.add(node.val);
        } else {
            if (Math.abs(target - node.val) < Math.abs(target - queue.peek())) {
                queue.poll();
                queue.add(node.val);
            } else {
                return;
            }
        }
        
        dfs(node.right, queue, k, target);
    }
}
```
