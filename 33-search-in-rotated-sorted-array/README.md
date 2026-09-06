<h2><a href="https://leetcode.com/problems/search-in-rotated-sorted-array">Search in Rotated Sorted Array</a></h2>
<img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' />
<hr>

<h3>📝 Problem</h3>

<p>
There is an integer array <code>nums</code> sorted in ascending order with distinct values.
The array may be rotated at an unknown index.
</p>

<p>For example:</p>

<pre><code>Original:
[0, 1, 2, 4, 5, 6, 7]

After rotation:
[4, 5, 6, 7, 0, 1, 2]</code></pre>

<p>
Given the rotated array and a <code>target</code>, return the index of the target
if it exists. Otherwise, return <code>-1</code>.
</p>

<p>
The algorithm must have <strong>O(log n)</strong> runtime complexity.
</p>

<h3>🧪 Test Cases</h3>

<h4>Example 1</h4>

<pre><code>Input:
nums = [4,5,6,7,0,1,2]
target = 0

Output:
4</code></pre>

<h4>Example 2</h4>

<pre><code>Input:
nums = [4,5,6,7,0,1,2]
target = 3

Output:
-1</code></pre>

<h4>Example 3</h4>

<pre><code>Input:
nums = [1]
target = 0

Output:
-1</code></pre>

<h4>Additional Test Cases</h4>

<pre><code>Input:
nums = [5,6,7,0,1,2,4]
target = 0

Output:
3</code></pre>

<pre><code>Input:
nums = [1,2,3,4,5]
target = 4

Output:
3</code></pre>

<pre><code>Input:
nums = [6,7,8,1,2,3,4,5]
target = 8

Output:
2</code></pre>

<h3>💡 My Approach</h3>

<p>
Since the array was originally sorted in ascending order, I noticed that after
rotation there will be one point where the ascending order breaks.
</p>

<p>
If:
</p>

<pre><code>nums[i] &lt; nums[i - 1]</code></pre>

<p>
then <code>i</code> is the pivot — the index of the smallest element.
</p>

<p>For example:</p>

<pre><code>[4, 5, 6, 7, 0, 1, 2]
             ↑
           pivot</code></pre>

<p>
After finding the pivot, I can treat the array as two separate sorted portions:
</p>

<pre><code>[4, 5, 6, 7] | [0, 1, 2]
      sorted  |   sorted</code></pre>

<p>
I then perform binary search on the first sorted portion.
If the target is not found, I perform binary search on the second sorted portion.
</p>

<h3>⚠️ Mistake I Made</h3>

<p>
Initially, I used <code>pivot</code> as the end of the first sorted portion.
However, my <code>pivot</code> actually represents the
<strong>index of the smallest element</strong>.
</p>

<p>For:</p>

<pre><code>[4, 5, 6, 7, 0, 1, 2]
             ↑
           pivot = 4</code></pre>

<p>
The correct ranges are:
</p>

<pre><code>Left half:
0 → pivot - 1

Right half:
pivot → n - 1</code></pre>

<p>
I initially searched from <code>0 → pivot</code>, which produced:
</p>

<pre><code>[4, 5, 6, 7, 0]</code></pre>

<p>
This range is not sorted, so binary search cannot be correctly applied to it.
</p>

<h3>🔑 Key Takeaways</h3>

<ul>
    <li>Look for the point where <code>nums[i] &lt; nums[i - 1]</code>.</li>
    <li>This point is the pivot and represents the index of the smallest element.</li>
    <li>The pivot divides the rotated array into two sorted portions.</li>
    <li>Binary search should only be performed on a sorted range.</li>
    <li>Be careful with whether a boundary represents the start or end of a range.</li>
    <li>If the array is not rotated, no break is found and <code>pivot</code> remains <code>0</code>.</li>
</ul>

<h3>⚠️ Important Observation</h3>

<p>
My solution is correct and was accepted, but it does <strong>not</strong> satisfy
the required <code>O(log n)</code> runtime.
</p>

<p>
Finding the pivot using a linear scan takes <code>O(n)</code>.
The two binary searches each take <code>O(log n)</code>.
Therefore:
</p>

<pre><code>Finding Pivot     → O(n)
Binary Search #1  → O(log n)
Binary Search #2  → O(log n)

Overall           → O(n)</code></pre>

<p>
The next optimization would be to find the target in <code>O(log n)</code>
without first performing an <code>O(n)</code> scan.
</p>

<h3>⏱️ Complexity</h3>

<pre><code>Time:
O(n)

Space:
O(1)</code></pre>

<h3>💻 My Code</h3>

<pre><code>class Solution {
    public int search(int[] nums, int target) {

        int pivot = 0;

        // Find pivot
        for(int i = 1; i &lt; nums.length; i++){
            if(nums[i] &lt; nums[i - 1]){
                pivot = i;
            }
        }

        // Binary search: 0 → pivot - 1
        int lp = 0;
        int hp = pivot - 1;

        while(lp &lt;= hp){
            int mid = lp + (hp - lp) / 2;

            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] &gt; target){
                hp = mid - 1;
            }
            else{
                lp = mid + 1;
            }
        }

        // Binary search: pivot → last
        int lpp = pivot;
        int hpp = nums.length - 1;

        while(lpp &lt;= hpp){
            int mid = lpp + (hpp - lpp) / 2;

            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] &gt; target){
                hpp = mid - 1;
            }
            else{
                lpp = mid + 1;
            }
        }

        return -1;
    }
}</code></pre>
