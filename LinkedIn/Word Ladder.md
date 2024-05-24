# Problem Name

**Solution grade:** Optimal  
**Concepts:** BFS
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Word Ladder](https://leetcode.com/problems/word-ladder)
**Youtube link:** [Youtube](https://www.youtube.com/watch?v=tRPda0rcf8E)

## Logic

We will be using BFS here to find the end destination. Note BFS and not DFS. DFS will explore in depths first and even though we might find our transition but it won't be the shroted one for sure. Hence BFS.

LC Example : 
```
1. Convert wordList to a set for efficient lookup:
   wordSet = {"hot", "dot", "dog", "lot", "log", "cog"}

2. Check if endWord is in the wordSet:
   if endWord not in wordSet:
       return 0

3. Initialize a queue for BFS with beginWord:
   queue = [beginWord]
   Remove beginWord from wordSet to avoid revisiting
   Set level (number of transformations) to 1

4. Perform BFS:
   - While the queue is not empty:
     - For each word at the current BFS level:
       - Dequeue the current word
       - If it matches endWord, return the current level
       - Find all valid next words (one character different and in wordSet)
       - Add these next words to the queue and remove them from wordSet
     - Increment the level after processing all words at the current level

5. If the endWord is not found after BFS completes, return 0

Function getNextWords(currentWord, wordSet):
   - Initialize an empty list for nextWords
   - For each character in currentWord:
     - Try replacing it with every letter from 'a' to 'z'
     - If the new word is in wordSet, add it to nextWords and remove from wordSet
   - Return the list of nextWords
```


Dry Run :

```
Example Execution:

Initial State:
queue = ["hit"]
level = 1

Iteration 1:
currentWord = "hit"
nextWords from "hit" = ["hot"] (since "hot" is in wordSet and is one character change away)
queue = ["hot"]
wordSet = {"dot", "dog", "lot", "log", "cog"}
increment level to 2

Iteration 2:
currentWord = "hot"
nextWords from "hot" = ["dot", "lot"] (since "dot" and "lot" are in wordSet and are one character change away)
queue = ["dot", "lot"]
wordSet = {"dog", "log", "cog"}
increment level to 3

Iteration 3:
currentWords = "dot", "lot"
nextWords from "dot" = ["dog"] (since "dog" is in wordSet and is one character change away)
nextWords from "lot" = ["log"] (since "log" is in wordSet and is one character change away)
queue = ["dog", "log"]
wordSet = {"cog"}
increment level to 4

Iteration 4:
currentWords = "dog", "log"
nextWords from "dog" = ["cog"] (since "cog" is in wordSet and is one character change away)
nextWords from "log" = [] (no new words since "log" to "cog" already considered)
queue = ["cog"]
wordSet = {}
increment level to 5

Iteration 5:
currentWord = "cog"
"cog" equals endWord, return level = 5

Output: 5
```
Convert List to set and check what all possible steps from hot exists.

### Solution 1




- Time complexity: O(n)
- Space complexity: O(n)


### Optimal Code

```java
import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0; // what if endWord does not exist only
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);  // removing because this is already traversed and we do not wnat to come to this again.
        wordSet.remove(beginWord);  // Remove beginWord from the set to avoid revisiting
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return level;
                List<String> nextWords = getNextWords(word, wordSet);
                queue.addAll(nextWords);
            }
            level++;
        }
        return 0;
    }

    private List<String> getNextWords(String word, Set<String> wordSet) {
        List<String> nextWords = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                String nextWord = new String(chars);
                if (wordSet.contains(nextWord)) {
                    nextWords.add(nextWord);
                    wordSet.remove(nextWord);  // Remove the word from the set to avoid revisiting
                }
            }
            chars[i] = original;
        }
        return nextWords;
    }
}
