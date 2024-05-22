# Problem Name

**Solution grade:** Optimal  
**Concepts:** Stack
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Exclusive Time Of Functions](https://leetcode.com/problems/exclusive-time-of-functions)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=CBJI_lZxYU8&t=25s)

## Logic




### Solution 1

Intution : The function call in real world as well use a stack so we will also mimic the same.

The logic will be:
- Whenever some function is starting:
  - if some previous function was running prior to this:
    - We will update for that previous function that it was being executed till now. (Not completed yet)
  - We will put this function in the stack.
  - Set time_passed_till_now = start_time
- Whenever some function is ending:
  - The previous function in the stack will be => end_time - time_passed_till_now + 1             
          (Note :  we will always pop the previous one and update for it and not for the curr_id reason being that in a single processor the one at the top will end first for sure right.)
  - Set time_passed_till_now = end_time + 1


**Input:**

CAN USE THIS EXAMPLE. BUT IF THEY ASK FOR A DEMO, USE A SIMPLE EXAMPLE PLEASE
n = 3<br>
logs = ["1:start:0", "0:start:2", "1:start:3", "2:start:4", "2:end:4", "0:end:6", "1:end:7", "1:end:8"]

       
| Step            | Command Executed | Stack (Function ID) | ans (Exclusive Time) | Calculation for ans | Prev End  |
|-----------------|-------------------|---------------------|----------------------|---------------------|----------|
| "1:start:0"     | Push 1            | 1                   | [0, 0, 0]            | -                   | 0 (curr)        |
| "0:start:2"    | Push 0            | 1, 0                | [0, 0, 0]            | -                   | 2  (curr)        |
| "1:start:3"    | Push 1            | 1, 0, 1             | [0, 0, 0]            | -                   | 3  (curr)        |
| "2:start:4"    | Push 2            | 1, 0, 1, 2          | [0, 0, 0]            | -                   | 4  (curr)        |
| "2:end:4"      | Pop 2             | 1, 0, 1             | [0, 0, 1]            | (curr - prev_end + 1) = (4 - 4 + 1) | 5 (curr + 1)  |
| "0:end:6"     | Pop 1             | 1, 0                | [2, 2, 1]            | (curr - prev_end + 1) = (6 - 5 + 1) | 7 (curr + 1) |
| "1:end:7"     | Pop 0             | 1                   | [2, 6, 1]            | (curr - prev_end + 1) = (7 - 6 + 1) | 8 (curr + 1)|
| "1:end:8"     | Pop 1             |                     | [2, 6, 1]            | (curr - prev_end + 1) = (8 - 7 + 1) | 9 (curr + 1)|


**Output:**

[6,2,1]



    
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

