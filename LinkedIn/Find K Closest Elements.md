# Find K Closest Elements

**Solution grade:** Optimal  
**Concepts:** Sliding Window, 2 pointer, Binary Search, Priority Queue, Heap, Min Heap <br>
**Time complexity:** O(log n) + k   <br>
**Space complexity:** O(k)  [Only for storing the result, otherwise not reuired] <br>
**LeetCode Link:** [Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements) <br>

NOTE: The final answer (which is indexes of closest k elements to x) might be required in sorted format. <br>
Lets call it FINAL_SORTING_REQUIRED for the context of code readability.


### Brute Force 

 Sort on the basis of abs diffrence of each index with the target. the minimum difference will be at first after sorting.  <br>
```

  PriorityQueue<int[]> pq = new PriorityQueue((a, b) -> (a -b)); //MinHeap of int[] -> 0 index will be abs diff, 1st index will index in input array

  for (int i = 0; i < arr.length; i++) {
            diff = abs(arr[i] - x);
            pq.add(new int[] {diff, arr[i]});
        }
  result =  pop pq the top K time

```

This can be achieved by using a MinHeap. THis min heapo will be storing the minimum differences along with their indexes at the top. <br>


- Time complexity: O(n* log n + k* log n) [Construction of heap of size N and then popping K times) <br>
                   if  FINAL_SORTING_REQUIRED = true then more k*log k for final sorting is needed.
- Space complexity: O(n)


####  Code

```java

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0]; // Sort by difference first
            } else {
                return a[1] - b[1]; // Sort by index if differences are the same
            }
        });
      
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - x);
            pq.add(new int[] {diff, arr[i]});
        }
        
        for (int i = 0; i < k; i++) {
            list.add(pq.poll()[1]);
        }
        Collections.sort(list); // IF FINAL_SORTING_REQUIRED
        return list;
    }
}
```



### Optimal Solution 
Note that the above solution does not use the sorting feature. It will work fine even if unsorted beacuse your heap is taking care of that.
So if we want to use sorting to full potential, we must think about using binary search here.

#### Pseudo Code
```
Step 1 : Use Binary search to find the nearest element to target (could be target it self) 
         Time = O(logN)
Step 2 : Once we find target or index nearest to target, all we need to do is to mnake <br>
         a window of k closest one that are either on left or right of k.
         Time = O(K)
```

- Time complexity: O(log n) + k (log n for binary search and k for sliding window)
- Space complexity: O(k) result array size. (Had the question been to just print and not store, space would be O(1))

#### Code
``` java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        // Find the closest element to x using binary search
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                left = mid;
                break;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return findWindow(arr, x, left, k);
    }

    private List<Integer> findWindow(int[] arr, int target, int pointer, int k) {
        List<Integer> result = new ArrayList<>();

        int left = pointer - 1;
        int right = pointer;

        while (k > 0) {
            if (left < 0) {
                result.add(arr[right]);
                right++;
            } else if (right >= arr.length) {
                // IF FINAL_SORTING_REQUIRED == TRUE 
                result.add(0, Integer.valueOf(arr[left]));
                // IF FINAL_SORTING_REQUIRED == FALSE simply do result.add(arr[left])
                left--;
            } else {
                int leftDiff = Math.abs(target - arr[left]);
                int rightDiff = Math.abs(target - arr[right]);
                
                if (leftDiff <= rightDiff) {
                    // IF FINAL_SORTING_REQUIRED == TRUE 
                    result.add(0, Integer.valueOf(arr[left]));
                    // IF FINAL_SORTING_REQUIRED == FALSE simply do result.add(arr[left])
                    left--;
                } else {
                    result.add(arr[right]);
                    right++;
                }
            }
            k--;
        }


        return result;
    }
}

```
