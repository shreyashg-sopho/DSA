# LRU Cache

**Solution grade:** Optimal  
**Concepts:** Doubly Linked List, DLL, HashMap, Hashing, Hash, LinkedHashMap<br>
**Time complexity:** O(1)<br>
**Space complexity:** O(1)<br>
**LeetCode Link:** [Shortest Word Distance II](https://leetcode.com/problems/lru-cache)<br>


## Logic
Use a doubly Linked List and Map.
Everytime something new is getting added :
- Add it to the front of the list. Thus resembling that this has been used the  most recently.

Everytime something is getting reused :
- Remove it from wherever it was in the liast and add it to the head, representing that <br>
  this has been used the  most recently.
  
Everytime something from the cache needs to be deleted to accomodate a new entry :
- We remove whatever is at the tail of the queue, because the tail resembles that it has been<>
  used least recently.

Both solutions are optimal, explain 1 for sure, but implement 2. 


### Solution 1 (Optimal , But this involves creating our own DLL)
Create a doubly Linked list to add and move items reasily in O(1).
Create a HashMap of key to Node, to randomly access any node in the Doubly LinkedList in O(1).


- Time complexity: O(1)
- Space complexity: O(1)


#### Code 

```java
class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, ListNode> dic;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!dic.containsKey(key)) {
            return -1;
        }

        ListNode node = dic.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (dic.containsKey(key)) {
            ListNode oldNode = dic.get(key);
            remove(oldNode);
        }

        ListNode node = new ListNode(key, value);
        dic.put(key, node);
        add(node);

        if (dic.size() > capacity) {
            ListNode nodeToDelete = head.next;
            remove(nodeToDelete);
            dic.remove(nodeToDelete.key);
        }
    }

    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
```



### Optimal Solution 2
We will use LinkedHashMap which internally does the same above. It also internally <br> 
is using a HashTable and a Doubly Linked List justa s we did above ourselves.
```
LinkedHashMap = LinkedHashMap(capacity, 0.75f, true)

1st param : capacity is initial size of linkedlist and map.
2nd param : 0.75f is the factor which determines that whenever the map reaches 75% of it's capacity,
It will double itself for future.
3rd param : true is that it needs to maintain order.


Also we need to override it's  removeEldestEntry method :
  we will put the ligic that whever size > cache capacity,
  we need to evict the oldest one in the queue.
```
#### Code
``` java



class LRUCache {
    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        
        this.cache = new LinkedHashMap<>(5, 0.75f, true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> oldest) {
                return size() > LRUCache.this.capacity;
            }
            
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

```
