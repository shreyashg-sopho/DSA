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
    public List<Integer> partitionLabels(String s) {
        Map<Character,Integer> endMap = new HashMap<>();
        List result = new ArrayList<>();

        //find last occurenece of all chars.
        for(int i = 0 ; i < s.length(); i++)
        {
            Character c = s.charAt(i);
            endMap.put(c, i);
        }
        
        
        int start = 0;
        int i = 0;
        int j = 0;
        while(i < s.length())
        {
            Character c = s.charAt(i);
            j = Math.max(j,endMap.get(c));
            if (i == j)
            {
                result.add(i - start + 1);
                start = i + 1;
            }
            i++; 
        }
        return result ;
        
    }
}

