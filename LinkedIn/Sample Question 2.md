# Problem Name

**Solution grade:** Optimal  
**Concepts:** ABCD waLA
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Shortest Word Distance II](https://leetcode.com/problems/KADASBDVBDSVBKJDS)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=AzER0wuL0QY)

## Logic




### Solution 1

Create a hashMap where we are storing all the indexes........

- Time complexity: O(n)
- Space complexity: O(n)


### Optimal Code

```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n]; // Initialize result array to store exclusive times
        Stack<Integer> stack = new Stack<>(); // Stack to store function IDs
        int prev = 0; // Variable to store the previous timestamp
        
        for (String log : logs) {
            String[] strs = log.split(":"); // Split the log into its components
            int id = Integer.parseInt(strs[0]); // Get function ID
            int curr = Integer.parseInt(strs[2]); // Get current timestamp
            
            if (strs[1].equals("start")) { // If the log is a 'start' log
                if (!stack.isEmpty()) { // If there is a function already running
                    res[stack.peek()] += curr - prev; // Add the elapsed time to the current function
                }
                stack.push(id); // Push the new function ID onto the stack
                prev = curr; // Update the previous timestamp to current timestamp
            } else { // If the log is an 'end' log
                res[stack.pop()] += curr - prev + 1; // Add the inclusive time for the ending function
                prev = curr + 1; // Update previous timestamp to the next timestamp
            }
        }
        
        return res; // Return the result array
    }
}

