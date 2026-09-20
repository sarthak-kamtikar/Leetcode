<h2><a href="https://leetcode.com/problems/next-greater-node-in-linked-list">Next Greater Node In Linked List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr><p>You are given the <code>head</code> of a linked list with <code>n</code> nodes.</p>

<p>For each node in the list, find the value of the <strong>next greater node</strong>. That is, for each node, find the value of the first node that is next to it and has a <code>strictly larger</code> value than it.</p>

<p>Return an integer array <code>answer</code> where <code>answer[i]</code> is the value of the next greater node of the <code>i<sup>th</sup></code> node (<strong>1-indexed</strong>). If the <code>i<sup>th</sup></code> node does not have a next greater node, set <code>answer[i] = 0</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>
<pre>
<strong>Input:</strong> head = [2,1,5]
<strong>Output:</strong> [5,5,0]
</pre>

<p><strong>Explanation:</strong></p>
<ul>
    <li>For <code>2</code>, the next greater value is <code>5</code>.</li>
    <li>For <code>1</code>, the next greater value is <code>5</code>.</li>
    <li>For <code>5</code>, there is no greater value after it, so the answer is <code>0</code>.</li>
</ul>

<p><strong class="example">Example 2:</strong></p>
<pre>
<strong>Input:</strong> head = [2,7,4,3,5]
<strong>Output:</strong> [7,0,5,5,0]
</pre>

<p><strong>Explanation:</strong></p>
<ul>
    <li><code>2</code> → next greater is <code>7</code>.</li>
    <li><code>7</code> → no greater value exists after it → <code>0</code>.</li>
    <li><code>4</code> → next greater is <code>5</code>.</li>
    <li><code>3</code> → next greater is <code>5</code>.</li>
    <li><code>5</code> → no greater value exists after it → <code>0</code>.</li>
</ul>

<p>&nbsp;</p>

<h3>Approach</h3>

<p>We first convert the linked list into an <code>ArrayList</code>. This makes it easier to work with the indexes of the nodes.</p>

<p>We then use a <strong>monotonic decreasing stack</strong>. The stack stores the <strong>indexes</strong> of elements whose next greater element has not been found yet.</p>

<p>For every index <code>i</code>:</p>

<ol>
    <li>While the stack is not empty and the current value is greater than the value at the index stored at the top of the stack, the current value is the next greater element for that index.</li>
    <li>Pop that index from the stack and store the current value in <code>ans[index]</code>.</li>
    <li>After resolving all possible elements, push the current index into the stack.</li>
</ol>

<p>The important part is that the stack stores <strong>indexes</strong>, not values. This allows us to directly update the correct position in the answer array.</p>

<h3>Code</h3>

<pre><code>class Solution {
    public int[] nextLargerNodes(ListNode head) {
        ArrayList&lt;Integer&gt; A = new ArrayList&lt;&gt;();

        ListNode temp = head;
        while(temp != null){
            A.add(temp.val);
            temp = temp.next;
        }

        int[] ans = new int[A.size()];
        Stack&lt;Integer&gt; stack = new Stack&lt;&gt;();

        for(int i = 0; i &lt; A.size(); i++){

            while(!stack.isEmpty() &amp;&amp; A.get(stack.peek()) &lt; A.get(i)){
                int index = stack.pop();
                ans[index] = A.get(i);
            }

            stack.push(i);
        }

        return ans;
    }
}</code></pre>

<h3>Working Example</h3>

<p>Consider:</p>

<pre><code>2 → 7 → 4 → 3 → 5</code></pre>

<p>After converting it to an array:</p>

<pre><code>Index:  0  1  2  3  4
Value:  2  7  4  3  5</code></pre>

<p><strong>i = 0:</strong></p>
<pre><code>Stack: []
Push 0

Stack: [0]</code></pre>

<p><strong>i = 1, value = 7:</strong></p>
<pre><code>A[0] &lt; A[1]
2 &lt; 7

Pop 0
ans[0] = 7

Push 1

Stack: [1]</code></pre>

<p><strong>i = 2, value = 4:</strong></p>
<pre><code>7 &lt; 4 → false

Push 2

Stack: [1, 2]</code></pre>

<p><strong>i = 3, value = 3:</strong></p>
<pre><code>4 &lt; 3 → false

Push 3

Stack: [1, 2, 3]</code></pre>

<p><strong>i = 4, value = 5:</strong></p>

<pre><code>3 &lt; 5
Pop 3
ans[3] = 5

4 &lt; 5
Pop 2
ans[2] = 5

7 &lt; 5 → false

Push 4

Stack: [1, 4]</code></pre>

<p>The indexes remaining in the stack (<code>1</code> and <code>4</code>) have no greater element after them, so their answers remain <code>0</code>.</p>

<pre><code>ans = [7, 0, 5, 5, 0]</code></pre>

<h3>Complexity</h3>

<ul>
    <li><strong>Time:</strong> <code>O(n)</code> — every index is pushed and popped from the stack at most once.</li>
    <li><strong>Space:</strong> <code>O(n)</code> — the ArrayList, answer array, and stack can each contain up to <code>n</code> elements.</li>
</ul>

<h3>Key Idea</h3>

<p>The stack contains indexes of elements that are still <strong>waiting for their next greater element</strong>.</p>

<pre><code>current value
      ↓
Is it greater than stack top?
      ↓
    YES
      ↓
pop index
      ↓
ans[index] = current value
      ↓
keep checking the stack</code></pre>
