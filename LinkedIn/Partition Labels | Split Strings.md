# Partition Labels

**Solution grade:** Optimal  
**Concepts:** hashMap, Hashing, 2 pointer
**Time complexity:** O(n)  
**Space complexity:** O(1)   {uses a hash of size 26, so constant)
**LeetCode Link:** [Partition Labels](https://leetcode.com/problems/partition-labels)


## Logic




### Solution 1

Create a hashMap where we are storing last occurence of each String.

Do dry run using the follwing logic, and you will get the intution.
```

start = 0 ; i = 0 , j = 0
while ( i < size)
{
   currChar = s.getCharAt(i); // curr character
   j = Max( j, lastOccurenceMap.get(currChar)) # maxEnd  = end of this or last character's maxEnd.
   if(i == j) // curr = maxEnd 
   {
     // this means we have found the end of a partition
     partitionLenght =  i - start + 1;
     result.add(partitionLenght);
     
   }
}


```

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

