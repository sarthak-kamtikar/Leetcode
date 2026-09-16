<h2><a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list">Remove Nth Node From End of List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr>

<p>Given the <code>head</code> of a linked list, remove the <code>nth</code> node from the end of the list and return its head.</p>

<h3>🧠 Approach: Two Pointers</h3>

<p>I used two pointers:</p>

<ul>
    <li><code>f</code> → fast pointer</li>
    <li><code>s</code> → slow pointer</li>
</ul>

<p>The idea is to first move <code>f</code> <code>n</code> positions ahead. Then move both <code>f</code> and <code>s</code> together until <code>f</code> reaches the last node.</p>

<p>At that point, <code>s</code> will point to the node immediately before the node that needs to be removed.</p>

<p>Then the node can be removed using:</p>

<pre><code>s.next = s.next.next;</code></pre>

<h3>🔍 Working of My Code</h3>

<p>Consider:</p>

<pre>
head = [1,2,3,4,5]
n = 2
</pre>

<p>Initially:</p>

<pre>
f → 1 → 2 → 3 → 4 → 5 → null
s → 1
size = 0
</pre>

<h4>Step 1: Move the fast pointer</h4>

<p>The first loop is:</p>

<pre><code>while(f.next != null &amp;&amp; size &lt; n){
    f = f.next;
    size++;
}</code></pre>

<p>Since <code>n = 2</code>, <code>f</code> moves twice:</p>

<pre>
Initial:

f → 1
s → 1
size = 0

After 1st movement:

f → 2
s → 1
size = 1

After 2nd movement:

f → 3
s → 1
size = 2
</pre>

<h4>Step 2: Move both pointers</h4>

<p>Now:</p>

<pre>
f → 3
s → 1
</pre>

<p>The second loop is:</p>

<pre><code>while(f.next != null){
    s = s.next;
    f = f.next;
}</code></pre>

<p>First movement:</p>

<pre>
f → 4
s → 2
</pre>

<p>Second movement:</p>

<pre>
f → 5
s → 3
</pre>

<p>Now <code>f.next == null</code>, so the loop stops.</p>

<pre>
1 → 2 → 3 → 4 → 5
        ↑
        s
</pre>

<p>The node after <code>s</code> is <code>4</code>, which is the node we need to remove.</p>

<h4>Step 3: Remove the node</h4>

<pre><code>s.next = s.next.next;</code></pre>

<p>Before:</p>

<pre>
3 → 4 → 5
    ↑
  remove
</pre>

<p>After:</p>

<pre>
3 → 5
</pre>

<p>Therefore the final list is:</p>

<pre>
[1,2,3,5]
</pre>

<h3>❌ Mistakes I Made While Solving</h3>

<h4>1. I initially used <code>&amp;</code> instead of <code>&amp;&amp;</code></h4>

<p>I initially wrote:</p>

<pre><code>while(f.next != null &amp; size &lt; n)</code></pre>

<p>For logical conditions, the correct operator is:</p>

<pre><code>while(f.next != null &amp;&amp; size &lt; n)</code></pre>

<p><code>&amp;&amp;</code> is the logical AND operator. It also uses short-circuit evaluation, meaning if the first condition is false, Java does not evaluate the second condition.</p>

<p><code>&amp;</code> is primarily the bitwise AND operator. Although Java allows it with boolean expressions, it is not what should normally be used for this type of condition.</p>

<h4>2. I misunderstood the meaning of <code>size</code></h4>

<p>I initially thought that <code>size</code> represented the total number of nodes in the linked list.</p>

<p>However, in my code:</p>

<pre><code>size++;</code></pre>

<p>is executed every time the fast pointer moves.</p>

<p>Therefore, <code>size</code> actually represents the number of times <code>f</code> moved in the first loop.</p>

<p>For example:</p>

<pre>
[1,2,3]
n = 3
</pre>

<p>The fast pointer can only move twice because after reaching <code>3</code>:</p>

<pre>
f.next == null
</pre>

<p>Therefore:</p>

<pre>
size = 2
n = 3
</pre>

<p>The actual list size is <code>3</code>, so <code>size</code> is not the list length.</p>

<h4>3. I initially added an incorrect hard-coded special case</h4>

<p>I initially tried:</p>

<pre><code>else if(size == 1 &amp;&amp; n == 2){
    return head.next;
}</code></pre>

<p>This worked for:</p>

<pre>
[1,2], n = 2
</pre>

<p>but failed for:</p>

<pre>
[1,2,3], n = 3
</pre>

<p>because in that case:</p>

<pre>
size = 2
n = 3
</pre>

<p>The important pattern is:</p>

<pre>
size = n - 1
</pre>

<p>Whenever this happens, the node that needs to be removed is the <strong>head itself</strong>.</p>

<p>Therefore the general condition is:</p>

<pre><code>else if(size == n - 1){
    return head.next;
}</code></pre>

<p>This works for all cases where the first node must be removed.</p>

<h4>4. I incorrectly checked <code>s.next.next != null</code></h4>

<p>I initially had:</p>

<pre><code>if(s.next.next != null){
    s.next = s.next.next;
}</code></pre>

<p>This caused a problem when the node to remove was the last node.</p>

<p>For example:</p>

<pre>
[1,2]
n = 1
</pre>

<p>We have:</p>

<pre>
s
↓
1 → 2 → null
</pre>

<p>Here:</p>

<pre>
s.next = 2
s.next.next = null
</pre>

<p>But we still want to remove <code>2</code>.</p>

<p>Therefore:</p>

<pre><code>s.next = s.next.next;</code></pre>

<p>simply becomes:</p>

<pre><code>s.next = null;</code></pre>

<p>and the resulting list is:</p>

<pre>
1 → null
</pre>

<p>So the additional <code>s.next.next != null</code> condition was unnecessary.</p>

<h3>⚠️ Edge Case 1: Single Node</h3>

<p>Consider:</p>

<pre>
head = [1]
n = 1
</pre>

<p>Initially:</p>

<pre>
f → 1
s → 1
size = 0
</pre>

<p>The first loop does not execute because:</p>

<pre>
f.next == null
</pre>

<p>Therefore:</p>

<pre>
size = 0
</pre>

<p>Our condition:</p>

<pre><code>if(size == 0){
    return null;
}</code></pre>

<p>returns an empty list:</p>

<pre>
[]
</pre>

<p>This handles the case where the only node in the list needs to be removed.</p>

<h3>⚠️ Edge Case 2: Removing the Head</h3>

<p>Consider:</p>

<pre>
head = [1,2,3]
n = 3
</pre>

<p>The 3rd node from the end is the first node:</p>

<pre>
1 → 2 → 3
↑
remove
</pre>

<p>After the first loop:</p>

<pre>
f → 3
s → 1
size = 2
