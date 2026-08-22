<h1><strong>LEETCODE 34 – FIND FIRST AND LAST POSITION OF ELEMENT IN SORTED ARRAY</strong></h1>

<h2>PROBLEM</h2>

<p>
Given a sorted integer array <code>nums</code>, find the <strong>first</strong> and
<strong>last</strong> position of a given <code>target</code>.
</p>

<p>
If the target is not found, return <code>[-1, -1]</code>.
</p>

<p>
The important restriction is that the algorithm must run in
<strong><code>O(log n)</code></strong>.
</p>

<hr>

<h2>MISTAKE 1 – CONFUSING MID WITH NUMS[MID]</h2>

<p>
One of my initial mistakes was confusing <code>mid</code> with
<code>nums[mid]</code>.
</p>

<pre>
mid       → INDEX
nums[mid] → VALUE
</pre>

<p>For example:</p>

<pre>
nums = [5, 7, 8, 8, 10]
              ↑
            mid = 2

nums[mid] = 8
</pre>

<p>
So comparing:
</p>

<pre><code>mid == target
</code></pre>

<p>is incorrect because <code>mid</code> is an index.</p>

<p>The correct comparison is:</p>

<pre><code>nums[mid] == target
</code></pre>

<p><strong>LESSON:</strong></p>

<pre>
MID IS AN INDEX.
NUMS[MID] IS THE VALUE.
</pre>

<hr>

<h2>MISTAKE 2 – MY FIRST APPROACH BECAME O(N)</h2>

<p>My initial approach was:</p>

<pre>
BINARY SEARCH
      ↓
FIND ONE OCCURRENCE OF TARGET
      ↓
SCAN LEFT FOR FIRST OCCURRENCE
      ↓
SCAN RIGHT FOR LAST OCCURRENCE
</pre>

<p>
The logic was correct for finding the answer. However, after finding the target,
I used linear scanning on both sides.
</p>

<p>For example:</p>

<pre>
[2, 2, 2, 2, 2, 2, 2, 2, 2]
</pre>

<p>
Binary search can find one <code>2</code> in <code>O(log n)</code>, but scanning
left and right can still take <code>O(n)</code>.
</p>

<pre>
BINARY SEARCH = O(log n)

LEFT + RIGHT SCANNING = O(n)

TOTAL = O(log n) + O(n)
      = O(n)
</pre>

<p>
Therefore, even though I started with binary search, my overall worst-case
time complexity became <strong><code>O(n)</code></strong>, which violated the
problem requirement.
</p>

<hr>

<h2>MISTAKE 3 – TRYING TO FIND BOTH BOUNDARIES INSIDE ONE BINARY SEARCH</h2>

<p>
After realizing that linear scanning was not allowed, I tried to manually
change <code>lp</code>, <code>hp</code>, and <code>mid</code> inside the same
binary search to find both boundaries.
</p>

<pre>
FIND TARGET
    ↓
CHANGE LP
    ↓
CALCULATE NEW MID
    ↓
CHECK RIGHT SIDE
    ↓
CHANGE HP
    ↓
CALCULATE NEW MID
    ↓
CHECK LEFT SIDE
</pre>

<p>
This became confusing because finding the first occurrence and finding the last
occurrence are actually two different binary search problems.
</p>

<pre>
BINARY SEARCH #1 → FIND FIRST OCCURRENCE

RESET LP AND HP

BINARY SEARCH #2 → FIND LAST OCCURRENCE
</pre>

<hr>

<h2>HOW TO FIND THE FIRST OCCURRENCE</h2>

<p>Use normal binary search rules for values that are not equal to the target.</p>

<pre>
IF nums[mid] &lt; target:
    MOVE LP RIGHT

IF nums[mid] &gt; target:
    MOVE HP LEFT
</pre>

<p>
The special case happens when <code>nums[mid] == target</code>.
</p>

<p>
I found a possible first occurrence, so I save <code>mid</code>:
</p>

<pre><code>ans[0] = mid;
</code></pre>

<p>
But I do not stop because another occurrence might exist further left.
</p>

<pre><code>hp = mid - 1;
</code></pre>

<p><strong>KEY IDEA:</strong></p>

<pre>
TARGET FOUND
      ↓
SAVE MID AS A POSSIBLE FIRST OCCURRENCE
      ↓
CONTINUE SEARCHING LEFT
</pre>

<hr>

<h2>HOW TO FIND THE LAST OCCURRENCE</h2>

<p>
The second binary search follows the same normal binary search rules.
</p>

<pre>
IF nums[mid] &lt; target:
    MOVE LP RIGHT

IF nums[mid] &gt; target:
    MOVE HP LEFT
</pre>

<p>
When <code>nums[mid] == target</code>, I save the index:
</p>

<pre><code>ans[1] = mid;
</code></pre>

<p>
But instead of stopping, I continue searching right:
</p>

<pre><code>lp = mid + 1;
</code></pre>

<p><strong>KEY IDEA:</strong></p>

<pre>
TARGET FOUND
      ↓
SAVE MID AS A POSSIBLE LAST OCCURRENCE
      ↓
CONTINUE SEARCHING RIGHT
</pre>

<hr>

<h2>MISTAKE 4 – NOT RESETTING LP AND HP</h2>

<p>
After the first binary search finishes, the search space becomes empty:
</p>

<pre>
lp &gt; hp
</pre>

<p>
Therefore, if I directly start the second binary search using the same
<code>lp</code> and <code>hp</code>, the second loop may never run.
</p>

<p>
Before the second binary search, I need to reset:
</p>

<pre><code>lp = 0;
hp = nums.length - 1;
</code></pre>

<p>
I should not redeclare them with <code>int</code> because they were already
declared earlier in the same method.
</p>

<hr>

<h2>MISTAKE 5 – INITIALIZING THE ANSWER</h2>

<p>
If the target does not exist, the required answer is:
</p>

<pre>
[-1, -1]
</pre>

<p>
Therefore, I initialize:
</p>

<pre><code>int[] ans = {-1, -1};
</code></pre>

<p>
This automatically handles cases where the target is not found.
</p>

<hr>

<h2>CORE BINARY SEARCH PATTERN</h2>

<pre>
IF nums[mid] &lt; target:
    lp = mid + 1

IF nums[mid] &gt; target:
    hp = mid - 1

IF nums[mid] == target:

    FOR FIRST OCCURRENCE:
        SAVE MID
        SEARCH LEFT

    FOR LAST OCCURRENCE:
        SAVE MID
        SEARCH RIGHT
</pre>

<hr>

<h2>TIME COMPLEXITY</h2>

<pre>
FIRST BINARY SEARCH  = O(log n)

SECOND BINARY SEARCH = O(log n)

TOTAL = O(log n) + O(log n)
      = O(log n)
</pre>

<h2>SPACE COMPLEXITY</h2>

<pre>
O(1)
</pre>

<hr>

<h2>FINAL TAKEAWAY</h2>

<p>
My biggest learning from this problem was:
</p>

<pre>
FINDING THE TARGET DOES NOT ALWAYS MEAN BINARY SEARCH IS OVER.
</pre>

<p>In a normal binary search:</p>

<pre>
TARGET FOUND → STOP
</pre>

<p>But when finding boundaries:</p>

<pre>
FIRST OCCURRENCE:
TARGET FOUND → SAVE → SEARCH LEFT

LAST OCCURRENCE:
TARGET FOUND → SAVE → SEARCH RIGHT
</pre>

<p>My initial approach was:</p>

<pre>
BINARY SEARCH + LINEAR SCANNING = O(n) ❌
</pre>

<p>The correct approach is:</p>

<pre>
BINARY SEARCH FOR FIRST OCCURRENCE
+
BINARY SEARCH FOR LAST OCCURRENCE
=
O(log n) ✅
</pre>
