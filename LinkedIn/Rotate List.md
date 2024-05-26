# Rotate List

**Solution grade:** Optimal  
**Concepts:** Linked List
**Time complexity:** O(n)  
**Space complexity:** O(1)  
**LeetCode Link:** [Rotate List](https://leetcode.com/problems/rotate-list)

### Optimal Solution

#### Logic : 
```
Make it circular Linked List. By identifying tail and then setting tail.next = head.
Hopefully we would have calculated the lenght while reaching the tail.
given rotation k could be greater than the size of Linked List itself. Hence :
    k = k % n
    new head : (n - k )th node
    new tail : (n - k - 1)th node


```
- Time complexity: O(n)
- Space complexity: O(1)

#### Code

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for (n = 1; old_tail.next != null; n++) old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        k = (k % n)
        ListNode new_tail = head;
        for (int i = 0; i < n - k - 1; i++) new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }
}
```
