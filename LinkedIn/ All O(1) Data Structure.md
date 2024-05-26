# All O(1) Data Structure

**Solution grade:** Optimal  
**Concepts:** Doubly Linked List, HashMap
**Time complexity:** O(1)  
**Space complexity:** O(n)  
**LeetCode Link:** [All O(1) Data StructureI](https://leetcode.com/problems/all-oone-data-structure)


## Logic
Very difficult. But a couple of things :
Since everything is required in O(1) :
Discard all DS/Algo that have higheer than this complexity : <br>
i,e NO Trees, Binary Search, Or TreeMaps, or Sorting.

Only O(1) DS/Algos such as :
Doubly Linked List, Arrays , HashMap etc.

Hint : Only Big O(1-) seach DS is hashMap. SO we need it.




### Solution 1
data Structures :
We need a doubly Linked List. <br>
```
The Node will be storing : <br>
- Set of strings.
- frequency Count.

Basically this node will store all the String that have the same count.
We will ensure that any node to the left has lesser frequency count than the present
and that the any node on the right has greater frequency count than the present one.
```

We also need a Map<String, Node> :
```
This map will store the String and the Node that it is currently mapped to in the Doubly Linked List.
```

Now for each method : 




func decrement(String key) :
    If the key exists in Map.
    Find the Node.
    freq = node.freuency, 
    if (freq > 1)
       We need to shift this String from this Node to the one on the left which has lessser frequency.
       So delete the String from the curre node.
       If left node has frequency = freq - 1 then we can add the String there and update in the map accordingly.
       Else if frequency != freq - 1, then we need to add a node between curr and curr.left which will have frequency = freq  -1 and this String  in the set.
       Update the corrct node accordingly in the map.
    else if (freq == w)
       Delete the String from the set. 
       Also Delete the String from the map.

func increment(String key) :
     Similar as decrement just that instead  we will move this String to the right.


func getMax();
     Get from Tail. (Extreme right pointer)

func getMin();
    Get from Head. (Extreme left pointer)



- Time complexity: O(1)
- Space complexity: O(n) [Map and Doubly Linked List]


### Optimal Code

```java
//OPTIMAL
import java.util.*;

class AllOne {
    // Inner class representing a node in the linked list
    class Node {
        int count; // Frequency count
        Set<String> keys = new HashSet<>(); // Set of keys with the same frequency
        Node prev, next; // References to previous and next nodes
        
        // Constructor to initialize a node with a key and count
        Node(String key, int count) {
            this.keys.add(key);
            this.count = count;
        }
    }

    // Map to store key-node pairs for quick access
    Map<String, Node> map = new HashMap<>();
    // Head and tail nodes of the doubly linked list
    Node head = new Node("", Integer.MIN_VALUE), tail = new Node("", Integer.MAX_VALUE);    

    // Constructor to initialize the data structure
    public AllOne() {
        head.next = tail;
        tail.prev = head;
    }
    
    // Method to increment the count of a key
    public void inc(String key) {
        // Get the node associated with the key, default to head if not found
        Node node = map.getOrDefault(key, head);
        // Remove the key from its current node's key set
        node.keys.remove(key);
        // Calculate the new count
        int count = node == head ? 1 : node.count + 1;
        
        // Check if the next node is tail or has a different count
        if (node.next == tail || node.next.count != count)
            // Insert a new node after the current node with the new count
            insertNodeAfter(new Node(key, count), node);
        
        // Add the key to the key set of the next node
        node.next.keys.add(key);
        // Update the map to associate the key with the next node
        map.put(key, node.next);
        
        // Remove the current node if it's not the head and its key set becomes empty
        if (node != head && node.keys.isEmpty())
            removeNode(node);
    }

    // Method to decrement the count of a key
    public void dec(String key) {
        // Return if the key doesn't exist in the map
        if (!map.containsKey(key)) return;
        
        // Get the node associated with the key
        Node node = map.get(key);
        // Remove the key from its current node's key set
        node.keys.remove(key);
        
        // Check if the count is greater than 1
        if (node.count > 1) {
            // Check if the previous node is head or has a different count
            if (node.prev == head || node.prev.count != node.count - 1)
                // Insert a new node before the current node with the decremented count
                insertNodeBefore(new Node(key, node.count - 1), node);
            
            // Add the key to the key set of the previous node
            node.prev.keys.add(key);
            // Update the map to associate the key with the previous node
            map.put(key, node.prev);
        } else {
            // Remove the key from the map if the count is 1
            map.remove(key);
        }
        
        // Remove the current node if its key set becomes empty
        if (node.keys.isEmpty())
            removeNode(node);
    }

    // Method to get the key with the maximum count
    public String getMaxKey() {
        // Return an empty string if the list is empty
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    // Method to get the key with the minimum count
    public String getMinKey() {
        // Return an empty string if the list is empty
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }
    
    // Method to insert a new node after a given node
    private void insertNodeAfter(Node newNode, Node prevNode) {
        newNode.prev = prevNode;
        newNode.next = prevNode.next;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }
    
    // Method to insert a new node before a given node
    private void insertNodeBefore(Node newNode, Node nextNode) {
        newNode.next = nextNode;
        newNode.prev = nextNode.prev;
        nextNode.prev.next = newNode;
        nextNode.prev = newNode;
    }
    
    // Method to remove a given node from the linked list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


//   AllOne allOne = new AllOne();
//   allOne.inc("hello");
//   allOne.inc("hello");
//   allOne.getMaxKey(); // return "hello"
//   allOne.getMinKey(); // return "hello"
//   allOne.inc("leet");
//   allOne.getMaxKey(); // return "hello"
//   allOne.getMinKey(); // return "leet"
}
```
