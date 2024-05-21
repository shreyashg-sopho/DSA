# Shortest Word Distance II

**Solution grade:** Optimal  
**Concepts:** HashMap
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Shortest Word Distance II](https://leetcode.com/problems/shortest-word-distance-ii/)

## Logic




### Solution 1

Create a hashMap where we are storing all the indexes of each word in a list. Note that since we will be creating this list through linear iteration so the list will be created and will naturally be sorted.

Now while calculating the shortest distance, Simply take the listOfOccurences from the above hashMap for the two words and find the minimum distance in between the two. Since lists are sorted so we can find the min diff in a single iteration only.

- Time complexity: O(n)
- Space complexity: O(n)


### Optimal Code

```java
//OPTIMAL
import java.util.*;

class WordDistance {
    HashMap<String, List<Integer>> wordMap;

    public WordDistance(String[] wordsDict) {
        wordMap = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            if (!wordMap.containsKey(wordsDict[i])) {
                wordMap.put(wordsDict[i], new ArrayList<>());
            }
            wordMap.get(wordsDict[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = wordMap.get(word1);
        List<Integer> list2 = wordMap.get(word2);

        int minDistance = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);
            // Calculate the distance between the occurrences and update the minimum distance
            minDistance = Math.min(minDistance, Math.abs(index1 - index2));

            // Move to the next occurrence with smaller index

            if (index1 < index2) {
                i++;
            } else {
                j++;
            }
        }

        return minDistance;
    }
}

