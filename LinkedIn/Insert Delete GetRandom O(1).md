#  Insert Delete GetRandom O(1)

**Solution grade:** Optimal  
**Concepts:** Hashing, HashMap
**Time complexity:** O(1) for all functionalities (delete, add , getRandom)  
**Space complexity:** O(n)  
**LeetCode Link:** [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1)


## Logic

###  Solution 1 : (Brute Force)
We can use a hashSet to store the value and at the same time also mantain a list to store indexes. <br>
NOTE : We will have to update both list and hashSet together.<br><br>
Set will help me in adding and removing in O(1)
List will help in generating random numbers. (We will use inbuilt randomGenerators and not implement on our own, possible bhi nahi hai bhai)

- Time complexity:
  - insert : O(1)
  - remove : O(n)  Because removal_by_value in list is O(n) operation.
  - getRandom : O(1)
- Space complexity: O(n)

#### Code
```java
import java.util.*;

class RandomizedSet {
    Set<Integer> set;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        set = new HashSet<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (set.contains(val)) {
            return false;
        }
        set.add(val);
        list.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!set.contains(val)) {
            return false;
        }
        set.remove(val);
        list.remove((Integer) val); // Use (Integer) to ensure correct removal
         // It's time complexity is O(n) unfortunately
        return true;
    }
    
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
```

### Optimal Solution 

To make remove method also O(1), Do the dry run first, where we try to get the element to delete at the end of the list so we can use removal_by_index (O(1))rather than removal_by_value(O(n))

Store in hashMap with key as intputVal and value as index in array. And do the following dry run


```
Say, This is how it is right now :
list: [10, 20, 30, 40]
map:  {10 -> 0, 20 -> 1, 30 -> 2, 40 -> 3}


Request to remove 20

take 20 to the end of the list (by swapping it with last element): O(1) time
list: [10, 40, 30, 20]
We need to update the map accordingly O(1)
map: [10 -> 0, 20 -> 3, 30 -> 2, 40 -> 1]


Now we can remove 20 from both the map and list
list.remove(size - 1) => list :[10, 40, 30, ]
map: [10 -> 0,  30 -> 2, 40 -> 1]

That is it!!!
Done in O(1)

```
- Time complexity:
  - insert : O(1)
  - remove : O(1)
  - getRandom : O(1)
- Space complexity: O(n)

####  Code

```java

import java.util.*;

class RandomizedSet{
    Map<Integer,Integer> map;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        list.add(val);
        map.put(val,list.size() - 1); // storing val to index in map
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.isEmpty()) {
            return false;
        }
        int valCurrentInListAtIndex = map.get(val); //current index of val
        int lastElementInListAtPresent = list.get(list.size() - 1); // last element in list at the moment
        
        // swap elements in list for , currentval and last
         int temp = list.get(valCurrentInListAtIndex);
         list.set( valCurrentInListAtIndex, list.get(list.size() - 1));
         list.set(list.size() - 1, temp);
         // now val is at the end 
        
         // we can remove val
         map.put( lastElementInListAtPresent ,valCurrentInListAtIndex );

        // we can remove val
         list.remove(list.size() - 1);
         map.remove(val);
         return true;
    }
    
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```
