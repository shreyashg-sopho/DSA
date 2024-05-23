# Letter Combinations Of A Phone Number.

**Solution grade:** Optimal  
**Concepts:** backtracking, Recursion, DFS, Hashing
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Letter Combinations Of A Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number)







### Optimal Solution 

Create a hashMap for nums to letters mapping first. Then just look at this :
```
Example : 2   3   5
         abc def jkl

                  ""
               /       \
              /         \
            /            \
           a              b      
         /  |  \          .
        d   e   f
       /|\  /|\  /|\
      j k l j k l j k l
          
  
```
basically we need to generate all the combnations and this similar to standard DFS or Backtracking call graph.

### Pseudocode :

```
backtrack(String madeTillNow, Array result, Map numsToAlpha, int index, String digits)
{
if (madeTillNow == digits.size()) result.add(madeTillNow);
else
  {
     for(char c :  numsToAlpha.get(digits.charAt(index).toCharArray())
     {
      backtrack(madeTillNow + c , result, numsToAlpha, index + 1, digits)
     }
  }
return result;
}
```


- Time complexity: O(n)
- Space complexity: O(1)


### Optimal Code

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> numsToAlpha = new HashMap<>();
        numsToAlpha.put('2',"abc");
        numsToAlpha.put('3',"def");
        numsToAlpha.put('4',"ghi");
        numsToAlpha.put('5',"jkl");
        numsToAlpha.put('6',"mno");
        numsToAlpha.put('7',"pqrs");
        numsToAlpha.put('8',"tuv");
        numsToAlpha.put('9',"wxyz");
       
        
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        backtrack(result, numsToAlpha, digits, "", 0);
        return result;
        
        
    }
    
    
    private void backtrack(List<String> result, Map<Character, String> numsToAlpha, String digits, String current, int index) {
        // base condition of return 
        if (index == digits.length())   result.add(current);
        else
        {
        String possibleLetters = numsToAlpha.get(digits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            backtrack(result, numsToAlpha, digits, current + letter, index + 1);
        }
        }
    }
}
