# Minimum Keypresses

**Solution grade:** Optimal  
**Concepts:** Frequency Counting, Sorting  
**Time complexity:** O(n)  <br>
**Space complexity:** O(1)  <br>
**LeetCode Link:** [Minimum Keypresses](https://leetcode.com/problems/minimum-keypresses)

## Logic

To determine the minimum keypresses needed to type a given string, we count the frequency of each character and sort these frequencies. The characters are assigned keypress weights based on their sorted positions:

- First 9 characters: 1 keypress
- Next 9 characters: 2 keypresses
- Remaining characters: 3 keypresses

### Solution Steps

1. Count the frequency of each character in the input string using an integer array.
2. Convert the frequency array to an `Integer[]` for sorting.
3. Sort the array in descending order to prioritize high-frequency characters.
4. Calculate the total keypresses based on the sorted frequencies.

### Example Walkthrough:

Suppose `s = "aaabbc"`.

1. Frequency count:
   - `characterFreqInWord = [3, 2, 1, 0, ..., 0]` (for 'a', 'b', 'c', ...)
  
2. After converting and sorting:
   - `freqArray = [3, 2, 1]`
   
3. Calculating keypresses:
   - Keypresses = `3 * 1 (for 'a') + 2 * 1 (for 'b') + 1 * 2 (for 'c') = 3 + 2 + 2 = 7`

### Code

```java
import java.util.*;

class Solution {
    public int minimumKeypresses(String s) {
        int[] characterFreqInWord = new int[26];
        
        // Count frequency of each character
        for (char c : s.toCharArray()) {
            characterFreqInWord[c - 'a']++;
        }

        // Convert to Integer array for sorting
        Integer[] freqArray = Arrays.stream(characterFreqInWord).boxed().toArray(Integer[]::new);
        
        // Sort in descending order
        Arrays.sort(freqArray, Collections.reverseOrder());

        int count = 0;
        // Calculate keypresses based on frequency
        for (int i = 0; i < 26; i++) {
            if (i < 9) {
                count += freqArray[i]; // 1 keypress for first 9
            } else if (i < 18) {
                count += freqArray[i] * 2; // 2 keypresses for next 9
            } else if (i < 26) {
                count += freqArray[i] * 3; // 3 keypresses for remaining
            }
        }

        return count;
    }
}
