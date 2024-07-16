# Plates Between Candles

**Solution grade:** Optimal  
**Concepts:** Prefix sum, Two-pointer technique<br>
**Time complexity:** O(n + q)  <br>
**Space complexity:** O(n)  <br>
**LeetCode Link:** [Plates Between Candles](https://leetcode.com/problems/plates-between-candles)

## Logic

The problem requires counting the plates between two candles efficiently for multiple queries. The solution involves:

- Preprocessing the string to find the nearest candles on both sides for each position.
- Using a prefix sum array to count the number of plates.

This approach ensures that each query can be answered in constant time after an initial linear pass through the string.

### Solution Overview

1. **Left and Right Candle Arrays:** 
   - `leftCandle[i]` stores the index of the nearest candle to the left of index `i`.
   - `rightCandle[i]` stores the index of the nearest candle to the right of index `i`.

2. **Prefix Sum Array:**
   - `prefix[i]` keeps a cumulative count of plates up to index `i`, allowing quick calculations for any segment.
  
3. **Query answer:**
   - for query [a,b] meaning for "a" index to "b" index
   - nearest candle of "a" to the right = rightCandle[a]
   - nearest candle of "b" to the left = leftCandle[b]
   - result (plates between a and b where each plate is surrounded by candles on both side ) = prefix[ leftCandle[b] ]  -  prefix[ rightCandle[a] ] 

- Time complexity:
  - Preprocessing: O(n)
  - Each query: O(1)
  - There entirely for  q quries = O(n + q)
- Space complexity: O(n) (for the candle and prefix arrays)

#### Dry run (Self Example)

##### Pre Processing
```
Suppose s = "|*|**|" and queries = [[1, 7], [0, 8]].

The prefix sum of plates is calculated:

s = "**|***|**|"
prefixSum = [0, 1, 1, 2, 3, 3, 4, 5, 5, 6]

The nearest left and right candle arrays are populated:
leftCandle = [-1, -1, 2, 2, 2, 5, 5, 5, 5, 9]
rightCandle = [2, 2, 2, 5, 5, 5, 9, 9, 9, 9]
For each query, determine the range of plates using the nearest candles:
```
##### Querying part
```
Query [1, 7]:

Nearest candles: rightCandle[1] = 2 and leftCandle[7] = 5
Plates between candles: prefixSum[5] - prefixSum[2] = 3 - 1 = 2
Query [0, 8]:

Nearest candles: rightCandle[0] = 2 and leftCandle[8] = 5
Plates between candles: prefixSum[5] - prefixSum[2] = 3 - 1 = 2

```
#### Optimal Code

```java
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] leftCandle = new int[n];
        int[] rightCandle = new int[n];
        char[] table = s.toCharArray();
        int[] result = new int[queries.length];
        
        // Initialize leftCandle and rightCandle
        int lastCandle = -1;
        for (int i = 0; i < n; i++) {
            if (table[i] == '|') {
                lastCandle = i;
            }
            leftCandle[i] = lastCandle;
        }

        lastCandle = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (table[i] == '|') {
                lastCandle = i;
            }
            rightCandle[i] = lastCandle;
        }

        // Initialize prefix sum to avoid calculating candles again and again for each query
        int[] prefix = new int[n];
        int plateCount = 0;
        for (int i = 0; i < n; i++) {
            if (table[i] == '*') {
                plateCount++;
            }
            prefix[i] = plateCount;
        }
        
        // Process each query
        for (int q = 0; q < queries.length; q++) {
            int left = queries[q][0];
            int right = queries[q][1];
            int leftBound = rightCandle[left];
            int rightBound = leftCandle[right];

            // Calculate the number of plates between the nearest candles
            if (leftBound == -1 || rightBound == -1 || leftBound >= rightBound) {
                result[q] = 0;
            } else {
                // Correct the indices for prefix sum calculation
                result[q] = prefix[rightBound] - prefix[leftBound];
            }
        }

        return result;
    }
}
