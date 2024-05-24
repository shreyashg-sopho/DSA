# Longest Palindromic Subsequence

**Solution grade:** Optimal  
**Concepts:** Dynamic Programming , DP <br>
**Time complexity:** O(n^2)  
**Space complexity:** O(n)  [Super optimal] <br>
**LeetCode Link:** [Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence)


## Logic


Longest Palindrom Subsequence is acutally nothing but Longest common sub sequence in the string and it's reverse. That is it, make a DP table for it and use the formula.

### Formula 
```
if(i == j)
   value[i][j] =  value[i-1][j-1] + 1;
else
   value[i][j] =  Max(value[i-1][j] ,  value[i][j-1]);

```

### Solution 1 (Good)

Create a 2D DP array and opulate the value using above formula.

|   | i row | b | a | b | b | b |
|---|---|---|---|---|---|---|
|  j col | 0 | 0 | 0 | 0 | 0 |0 |
| b | 0 | 1 | 1 | 1 | 1 | 1 |
| b | 0 | 1 | 1 | 2 | 2 | 2 |
| b | 0 | 1 | 1 | 2 | 3 | 3 |
| a | 0 | 1 | 2 | 2 | 3 | 3 |
| b | 0 | 1 | 2 | 3 | 3 | 4 |

- Time complexity: O(n^2)
- Space complexity: O(n^2)


###  Code

```java
public class Solution {
    public int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s, reversed);
    }
    
    private int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int[][] dp = new int[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[n][n];
    }
}
```


### Optimal Solution 

In the abice logic, if you see we created a 2D array, but at any given point we are using only i and i-1th row.
S0 basically we do not need more than 2 rows at any given point of time.

NOTE : Here we only want to return the lenght, we do not want to return the subsequence itself and hence we do not need more than 2 rows.

- Time complexity: O(n^2)
- Space complexity: O(n) [2 arrays of size n + 1]

###Code :
```

| Iteration 1 | 
|-------------|
| prev: [0, 0, 0, 0, 0, 0] |
| curr: [0, 1, 1, 1, 1, 1] |

---

| Iteration 2 | 
|-------------|
| prev: [0, 1, 1, 1, 1, 1] |
| curr: [0, 1, 1, 2, 2, 2] |

---

| Iteration 3 | 
|-------------|
| prev: [0, 1, 1, 2, 2, 2] |
| curr: [0, 1, 1, 2, 3, 3] |

---

| Iteration 4 | 
|-------------|
| prev: [0, 1, 1, 2, 3, 3] |
| curr: [0, 1, 2, 2, 3, 3] |

---

| Iteration 5 | 
|-------------|
| prev: [0, 1, 2, 2, 3, 3] |
| curr: [0, 1, 2, 3, 3, 4] |

```

Code :

```java
public class Solution {
    public int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s, reversed);
    }
    
    private int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
      
        
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            // Swap prev and curr
            printArray(prev);
            printArray(curr);
            System.out.println();
            int [] temp = new int [n+1];
            prev = curr;
            curr = temp;
    
        }
        
        return prev[n] ;
    }


    private void printArray(int [] arr ){
        for (int i : arr)
        { System.out.print(" " + i);}
        System.out.println();
        
    }
}

```
