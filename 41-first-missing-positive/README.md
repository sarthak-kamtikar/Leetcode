# First Missing Positive

<h2><a href="https://leetcode.com/problems/first-missing-positive/">First Missing Positive</a></h2>
<img src='https://img.shields.io/badge/Difficulty-Hard-red' alt='Difficulty: Hard' />

<hr>

## 📝 Problem

Given an unsorted integer array `nums`, find the **smallest missing positive integer**.

For example:

```text
nums = [1, 2, 0]
```

`1` and `2` are present, so the first missing positive integer is:

```text
3
```

---

## 💡 Approach

* Add all elements of the array to a `HashSet`.
* Sort the array to find the maximum element.
* Start checking from `1`.
* If a number is not present in the `HashSet`, return it.
* If all numbers from `1` to `max` are present, return `max + 1`.
* If the maximum element is `0` or negative, return `1`.

---

## 💻 Code

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        /* Start counting from 1 and find the first missing positive number */

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        Arrays.sort(nums);
        int max = nums[nums.length - 1];

        for (int i = 1; i <= max; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        if (max <= 0) {
            return 1;
        }

        return max + 1;
    }
}
```

---

## ⏱️ Complexity

* **Time Complexity:** `O(n log n)`
* **Space Complexity:** `O(n)`
