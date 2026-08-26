# Missing Number

<h2><a href="https://leetcode.com/problems/missing-number">Missing Number</a></h2>
<img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' />

<hr>

## 📝 Approach

We use a **HashSet** to store all the numbers present in the array.

### Steps:

1. Add every element from `nums` into a `HashSet`.
2. Since the array contains numbers from `0` to `n` with one number missing, loop from `0` to `nums.length`.
3. For each number, check whether it exists using `set.contains(i)`.
4. The first number that is not present is the missing number.

## 💻 Code

```java
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        // Store all elements in the HashSet
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        // Check which number from 0 to n is missing
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return 0;
    }
}
```

## ⏱️ Complexity

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(n)`

## 💡 Key Idea

A `HashSet` allows us to check whether a number exists in **O(1)** average time. So, after storing all the elements, we simply check every number from `0` to `n` and return the one that is missing.
