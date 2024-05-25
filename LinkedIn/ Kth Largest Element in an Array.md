#  Kth Largest Element in an Array

**Solution grade:** Optimal  
**Concepts:** Sorting, Min Heap, Max Heap as well.<br>
**Time complexity:** O(n)  <br>
**Space complexity:** O(n)  
**LeetCode Link:** [ Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)


### All solution discussion : 

- Solution 1 : Use Max heap (Poor complexity but valid for sure solution)
- Solution 2 : Use Sorting
- Solution 3 : Use Min Heap


### Solution 1 : (Using Max Heap) (No need to use this in solution, quite poor complexities)

Push everythhing in Max Heap and then pop it K times. the Kth pop will be the Kth largest element.

- Time Complexity : O(n*log(n) + k*log(n)) {This is becausing pushing n items and popping k items)
- Space Complexity : O(n)

#### Code
```java
import java.util.*;
class Solution {
    public int findKthLargest(int[] nums, int k) {
      PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a,b) -> (b -a));
      for(int i:  nums)
      {
          maxHeap.add(i);
      }
      int result = 0;
      for(int i =0; i < k ; i++)
      {
        result = maxHeap.poll();
      }
        return result;
    }
}


```
### Solution 2 : (Using Sorting)

To get Kth largest Element in an array. Sort the array and get the n - k element and you will have your answer.

- Time complexity: O(n*Logn)
- Space complexity: O(1)

#### Code

```java
import java.util.*;
class Solution {
    public int findKthLargest(int[] nums, int k) {
      Arrays.sort(nums);
      return nums[ nums.length - k];
    // Note we could have sorted in descending order and given k th elemnt as well but Arrays.sort(nums) 
    // does not support passing comparator for primitive types, hence ascending sort karke n -k th element
    }
}
```

### OPTIMAL Solution 3 : (Using Min Heap) 

kth largest element is n - kth smallest element. So lets <br>
Imagine if I had to use minHeap for this, I would have had added n elements and popped out n-k elements.<br>
I could simply pop n - k elements, and n - kth pop will be kth largest element.<br>


So this means, I will make a heap and once it is of size more than k, I will pop k and insert the next element until n.
So at the top there will now be n - k th smallest element.

#### Algorithm 
```
    for element in elements
       minHeap.add(element)
       if(minHeap.size() > k)  
            minHeap.poll()   // beacuase we do not need to maintain the heap for more than k size.
    return minHeap.peek();
```
- Time Complexity : O(n*log(k)) {pushing n items in heap of size k)
- Space Complexity : O(k)
#### Code
```java
import java.util.*;
class Solution {
    public int findKthLargest(int[] nums, int k) {
      PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
      for(int i:  nums)
      {
          minHeap.add(i);
          if (minHeap.size() > k)
          {
              minHeap.poll();
          }
      }
       return minHeap.poll();
    }
}

```
