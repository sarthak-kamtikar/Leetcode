<h2><a href="https://leetcode.com/problems/find-peak-element">Find Peak Element</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>A peak element is an element that is strictly greater than its neighbors.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, find a peak element, and return its index. If the array contains multiple peaks, return the index to <strong>any of the peaks</strong>.</p>

<p>You may imagine that <code>nums[-1] = nums[n] = -&infin;</code>. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.</p>

<p>You must write an algorithm that runs in <code>O(log n)</code> time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 3 is a peak element and your function should return the index number 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,3,5,6,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
</pre>

<p>&nbsp;</p>

<h2>💡 Approach: Binary Search Using Slopes</h2>

<p>At first, I thought I needed to explicitly check whether <code>mid</code> was greater than both of its neighbours:</p>

<pre>
nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]
</pre>

<p>However, this makes the binary search unnecessarily complicated because of boundary conditions and checking both sides.</p>

<p>The key insight is that we only need to compare:</p>

<pre>
nums[mid] and nums[mid + 1]
</pre>

<h3>📉 Case 1: We Are on a Descending Slope</h3>

<pre>
nums[mid] > nums[mid + 1]
</pre>

<p>For example:</p>

<pre>
[1, 3, 5, 4, 2]
       ↑
      mid
</pre>

<p>Since <code>5 > 4</code>, we are on a descending slope.</p>

<p>This guarantees that at least one peak exists in the range:</p>

<pre>
[lp ... mid]
</pre>

<p><strong>Why?</strong></p>

<p><code>mid</code> itself could be the peak:</p>

<pre>
[1, 3, 5, 4, 2]
       ↑
      peak
</pre>

<p>Or <code>mid</code> might not be the peak, but there will be a peak somewhere to its left:</p>

<pre>
[15, 14, 13, 5, 4, 6, 2]
             ↑
            mid
</pre>

<p>Here:</p>

<pre>
5 > 4
</pre>

<p>But <code>5</code> is not a peak because <code>5 < 13</code>. Still, there is a peak on the left:</p>

<pre>
[15, 14, 13, 5, 4, 6, 2]
 ↑
peak
</pre>

<p>Therefore, we keep <code>mid</code> inside our search space:</p>

<pre>
hp = mid;
</pre>

<p>We cannot do <code>hp = mid - 1</code> because <code>mid</code> itself might be the peak.</p>

<h3>📈 Case 2: We Are on an Ascending Slope</h3>

<pre>
nums[mid] < nums[mid + 1]
</pre>

<p>For example:</p>

<pre>
[1, 2, 3, 4, 2]
       ↑
      mid
</pre>

<p>Since <code>3 < 4</code>, we are on an ascending slope.</p>

<p>This guarantees that a peak exists somewhere in:</p>

<pre>
[mid + 1 ... hp]
</pre>

<p><code>mid</code> itself cannot be a peak because its right neighbour is greater.</p>

<p>Therefore:</p>

<pre>
lp = mid + 1;
</pre>

<h2>🧠 Important Understanding</h2>

<p>There can be peaks on <strong>both sides</strong> of <code>mid</code>.</p>

<pre>
[11, 10, 13, 5, 4, 6, 2]
 ↑       ↑        ↑
peak    peak      peak
</pre>

<p>If:</p>

<pre>
nums[mid] > nums[mid + 1]
</pre>

<p>we are <strong>not saying that there are no peaks on the right</strong>.</p>

<p>We are only saying that there is <strong>definitely at least one peak on the left including mid</strong>, so we can safely choose that half.</p>

<p>Since the problem asks us to return <strong>any peak</strong>, we do not care if another peak exists in the half we discard.</p>

<h2>❌ Where I Initially Went Wrong</h2>

<h3>1. Checking Both Neighbours</h3>

<p>I initially tried to explicitly find the peak using:</p>

<pre>
nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]
</pre>

<p>This required extra boundary checks and made the solution more complicated.</p>

<p>Instead, comparing only <code>nums[mid]</code> and <code>nums[mid + 1]</code> is enough to decide which direction to search.</p>

<h3>2. Overcomplicated Boundary Conditions</h3>

<p>I initially used conditions involving:</p>

<pre>
mid + 1 < nums.length
mid - 1 >= 0
</pre>

<p>But with:</p>

<pre>
while (lp < hp)
</pre>

<p>and:</p>

<pre>
mid = lp + (hp - lp) / 2;
</pre>

<p>we are guaranteed that <code>mid < hp</code>, meaning <code>mid + 1</code> will always be a valid index.</p>

<h3>3. Using an Unnecessary <code>ans</code> Variable</h3>

<p>I initially tried to store the peak in an <code>ans</code> variable.</p>

<p>This is not needed because we maintain the invariant:</p>

<blockquote>
<p>The current search range always contains at least one peak.</p>
</blockquote>

<p>Eventually:</p>

<pre>
lp == hp
</pre>

<p>Only one index remains, and that index must be a peak.</p>

<p>Therefore, we simply return:</p>

<pre>
return lp;
</pre>

<h3>4. Unnecessary Equality Case</h3>

<p>The constraints guarantee:</p>

<pre>
nums[i] != nums[i + 1]
</pre>

<p>Therefore, we only need two cases:</p>

<pre>
if (nums[mid] > nums[mid + 1])
</pre>

<p>or:</p>

<pre>
nums[mid] < nums[mid + 1]
</pre>

<p>An equality case is unnecessary.</p>

<h2>🔄 Dry Run</h2>

<p><strong>Input:</strong></p>

<pre>
nums = [1, 2, 3, 1]
</pre>

<p><strong>Initial values:</strong></p>

<pre>
lp = 0
hp = 3
</pre>

<h3>Iteration 1</h3>

<pre>
mid = 0 + (3 - 0) / 2 = 1
</pre>

<pre>
nums[mid] = 2
nums[mid + 1] = 3
</pre>

<p>Since:</p>

<pre>
2 < 3
</pre>

<p>We are ascending, so a peak is guaranteed on the right:</p>

<pre>
lp = mid + 1 = 2
</pre>

<p>Now:</p>

<pre>
lp = 2
hp = 3
</pre>

<h3>Iteration 2</h3>

<pre>
mid = 2 + (3 - 2) / 2 = 2
</pre>

<pre>
nums[mid] = 3
nums[mid + 1] = 1
</pre>

<p>Since:</p>

<pre>
3 > 1
</pre>

<p>We are descending, so a peak is guaranteed in the left range including <code>mid</code>:</p>

<pre>
hp = mid = 2
</pre>

<p>Now:</p>

<pre>
lp = 2
hp = 2
</pre>

<p>The loop stops because:</p>

<pre>
lp == hp
</pre>

<p>Therefore:</p>

<pre>
return lp;
</pre>

<p><strong>Answer:</strong> <code>2</code></p>

<h2>📌 Algorithm</h2>

<ol>
    <li>Initialize <code>lp = 0</code> and <code>hp = nums.length - 1</code>.</li>
    <li>While <code>lp < hp</code>, calculate <code>mid</code>.</li>
    <li>If <code>nums[mid] > nums[mid + 1]</code>, search the left half including <code>mid</code> by setting <code>hp = mid</code>.</li>
    <li>Otherwise, search the right half by setting <code>lp = mid + 1</code>.</li>
    <li>When the loop ends, <code>lp == hp</code>, and that index is a peak.</li>
</ol>

<h2>💻 Code</h2>

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int lp = 0;
        int hp = nums.length - 1;

        while (lp < hp) {
            int mid = lp + (hp - lp) / 2;

            if (nums[mid] > nums[mid + 1]) {
                hp = mid;
            } else {
                lp = mid + 1;
            }
        }

        return lp;
    }
}
