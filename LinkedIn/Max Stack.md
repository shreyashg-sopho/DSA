# Max Stack

**Solution grade:** Optimal  
**Concepts:** TreeMap, Tree Map<br>
**Time complexity:** O(log N)  <br>
**Space complexity:** O(n)  <br>
**LeetCode Link:** [Max Stack](https://leetcode.com/problems/max-stack)

## Logic


The max stack needs the following operations :
 - push()
 - pop()
 - top()
 - peekMax()
 - popMax()

Except for top() in O(1), rest all are needed to be done on log(n). This hints towards treeMap.
This is also not possible to achieve all this together in less than log(n)



### Solution 1


About TreeMap :

TreeMap is a  Data Structure that stores data that is accessible by Keys. The keys are sotred in some order.<br>
Internally it uses BST to manage the ordering. 

NOTE : Remove , Intsert, Read all have the same time complexities of O(logN).  (Pakka ekdum ! logN hi hai)


We use one tree map to store the "index" to "value". This treeMap will be sorted on index and it will act as out general stack.
Another treeMap is needed to store "values" to "indexes" mapping. This treeMap will be sorted by "values"  (which will be the key of the map).


- Time complexity:
 - push()    = O(logN)
 - pop()     = O(1)
 - top()     = O(logN)
 - peekMax() = O(logN)
 - popMax()  = O(logN)
- Space complexity: O(n) [2 maps with n elements in it]


#### #Optimal Code

```java
import java.util.*;

class MaxStack {
    private TreeMap<Integer, Integer> indexToVal; // Stack -> index to values
    private TreeMap<Integer, List<Integer>> valToIndices; // Max storing data structure. value -> [indexes]
    private int count;

    public MaxStack() {
        indexToVal = new TreeMap<>();
        valToIndices = new TreeMap<>();
        count = 0;
    }

    public void push(int x) {
        // add to stack
        indexToVal.put(count, x);
       // also add in the other DS for max key retrival later as it is sorting in terms of vals.
        if (valToIndices.containsKey(x))
            { valToIndices.get(x).add(count); }
        else
        {
            List<Integer> indices = new ArrayList<>();
            indices.add(count);
            valToIndices.put(x, indices);
            
        }
        count++;
    }

    public int pop() {
        if (indexToVal.isEmpty())
            return -1; // or throw an exception if desired
        
        int idx = indexToVal.lastKey();
        int value = indexToVal.remove(idx);
        List<Integer> indices = valToIndices.get(value);
        indices.remove(indices.size() - 1);
        if (indices.isEmpty()) {
            valToIndices.remove(value);
        }
        return value;
    }

    public int top() {
        if (indexToVal.isEmpty())
            return -1; // or throw an exception if desired
        
        return indexToVal.lastEntry().getValue();
    }

    public int peekMax() {
        if (valToIndices.isEmpty())
            return -1; // or throw an exception if desired
        
        return valToIndices.lastKey();
    }

    public int popMax() {
        if (valToIndices.isEmpty())
            return -1; // or throw an exception if desired
        
        int max = valToIndices.lastKey();
        List<Integer> indices = valToIndices.get(max);
        int idx = indices.remove(indices.size() - 1);
        if (indices.isEmpty()) {
            valToIndices.remove(max);
        }
        indexToVal.remove(idx);
        return max;
    }

  
}
``` 
