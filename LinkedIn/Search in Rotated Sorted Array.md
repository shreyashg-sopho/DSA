# Search in Rotated Sorted Array

**Solution grade:** Optimal  
**Concepts:** Binary Search , Arrays, Two pointer <br>
**Time complexity:** O(log N)   <br>
**Space complexity:** O(1)   <br>
**LeetCode Link:** [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)


## Logic
Linear search ca be done but that will be O(n)>
And that will be disrespecting the fact that the array is sorted.
So sorted array and search only menas one thing. 
 ```
BINARY SEARCH !!!!!!!!!!! 
```



### Solution 1

Create a hashMap where we are storing all the indexes........


- Pseudo Code
```
Function search(nums, target):
    left = 0
    right = size - 1
    While left <= right:
        mid = (left + right) / 2
        If nums[mid] equals target:
            Return mid // we found it 

        //If left side is sorted
        If nums[mid] >= nums[left]:
            If target is between nums[left] and nums[mid]:
                right = mid - 1  //search in left sub array
            Else: 
                left = mid + 1   //search in right sub array
        
        Else:
            If target is between nums[mid] and nums[right]:
                left = mid + 1
            Else:
                right = mid - 1

    Return -1

```
- Time complexity: O(log N)
- Space complexity: O(1)
#### Optimal Code

```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Case 1: find target
            if (nums[mid] == target) {
                return mid;
            }
            // Case 2: subarray on mid's left is sorted
            else if (nums[mid] >= nums[left]) {
                //If left side is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Case 3: subarray on mid's right is sorted
            else {
                //If right side is sorted
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
```
