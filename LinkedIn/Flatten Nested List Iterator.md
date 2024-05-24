# Problem Name

**Solution grade:** Near Optimal (Multiple solns exist but they are all nearly the same complexities)  
**Concepts:** Flattening, Arrays
**Time complexity:** O(n)  
**Space complexity:** O(n)  
**LeetCode Link:** [Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator)







### Near Optimal 

Flatten the list using DFS as did in  Nested List Weight Problem :
```
def void dfs ( nestedList,  flatList)
       for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                flatList.add( nestedInteger.getInteger());
            } else {
                dfs(nestedInteger.getList(),flatList ); 
            }
        }

```

- Space complexity: O(n)
- Time complexity :
  - Constructor  NestedIterator(List<NestedInteger> nestedList) : O(n)
  - Next : O(1)
  - hasNext : O(1)

  


###  Code

```java
public class NestedIterator implements Iterator<Integer> {
    int count = 0;
    List<Integer> flatList = new ArrayList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList(flatList, nestedList);
    }
    
    private void flattenList( List<Integer> flatList,List<NestedInteger> nestedList )
    {
       for(NestedInteger nestedInteger : nestedList)
       {
           if(nestedInteger.isInteger()) flatList.add(nestedInteger.getInteger());
           else
           {
               flattenList(flatList, nestedInteger.getList());
           }
       }
    }

    @Override
    public Integer next() {
        int result =  flatList.get(count);
        count++;
        return result;
    }

    @Override
    public boolean hasNext() {
        return count < flatList.size();
        
    }
}
```
NOTE : Instead of using a separate varible to keep track of index to give out, we can also use a Queue and pop one item each time. SLight modification in terms of DS used but no improvement in terms of performance or Time, Space complexities as such

```java
public class NestedIterator implements Iterator<Integer> {
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        prepareQueue(nestedList);
    }

    private void prepareQueue(List<NestedInteger> nestedList) {
        for (NestedInteger nested : nestedList) {
            if (nested.isInteger()) {
                queue.add(nested.getInteger());
            } else {
                prepareQueue(nested.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

```
