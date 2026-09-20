<h2><a href="https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list">Maximum Twin Sum of a Linked List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr>

<p>In a linked list of size <code>n</code>, where <code>n</code> is <strong>even</strong>, the <code>i<sup>th</sup></code> node (<strong>0-indexed</strong>) of the linked list is known as the <strong>twin</strong> of the <code>(n-1-i)<sup>th</sup></code> node.</p>

<p>The <strong>twin sum</strong> is the sum of a node and its twin.</p>

<p>Given the <code>head</code> of a linked list with even length, return the <strong>maximum twin sum</strong> of the linked list.</p>

<h3>Approach</h3>

<p>I used a <strong>Stack</strong> to store the values of the first half of the linked list.</p>

<p>The important observation is that the twin of the first node is the last node, the twin of the second node is the second-last node, and so on.</p>

<p>For example:</p>

<pre><code>5 → 4 → 2 → 1

Twin pairs:

5 ↔ 1
4 ↔ 2
</code></pre>

<p>A stack is useful because it follows <strong>LIFO (Last In, First Out)</strong> order. Therefore, after storing the first half:</p>

<pre><code>First half:

5 → 4

Stack:
[5, 4]

Second half:

2 → 1
</code></pre>

<p>When we start removing values from the stack, we get:</p>

<pre><code>4
5
</code></pre>

<p>This allows the values from the first half to be matched with the values in the second half in reverse order.</p>

<h3>Steps</h3>

<ol>
    <li>Traverse the linked list once to find its <strong>size</strong>.</li>
    <li>Reset <code>temp</code> to the head.</li>
    <li>Traverse the first half of the list and push every value into the stack.</li>
    <li>After the first half, <code>temp</code> points to the beginning of the second half.</li>
    <li>For every node in the second half:
        <ul>
            <li>Take the top value from the stack using <code>peek()</code>.</li>
            <li>Add it to <code>temp.val</code>.</li>
            <li>Compare the sum with <code>max</code>.</li>
            <li>Remove the processed value using <code>pop()</code>.</li>
        </ul>
    </li>
    <li>Return <code>max</code>.</li>
</ol>

<h3>Code</h3>

<pre><code>/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        Stack &lt;Integer&gt; stack = new Stack&lt;&gt;();
        ListNode temp = head;

        int size = 1;

        // Find the size of the linked list
        while(temp.next != null){
            temp = temp.next;
            size++;
        }

        // Move temp back to the head
        temp = head;

        int size1 = 1;

        // Store the first half in the stack
        while(size1 &lt;= size/2){
            stack.push(temp.val);
            temp = temp.next;
            size1++;
        }

        int max = 0;

        // Compare the second half with the first half
        // using the stack in reverse order
        while(temp != null){
            if(temp.val + stack.peek() &gt; max){
                max = temp.val + stack.peek();
                stack.pop();
                temp = temp.next;
            }else{
                stack.pop();
                temp = temp.next;
            }
        }

        return max;
    }
}</code></pre>

<h3>Working Example 1</h3>

<pre><code>Input:
[5, 4, 2, 1]
</code></pre>

<p>First, find the size:</p>

<pre><code>size = 4
</code></pre>

<p>Since <code>size / 2 = 2</code>, we store the first two values in the stack.</p>

<pre><code>Linked List:

5 → 4 → 2 → 1
↑
temp

Push 5:

Stack:
[5]

Push 4:

Stack:
[5, 4]
</code></pre>

<p>After pushing the first half, <code>temp</code> is now pointing to:</p>

<pre><code>5 → 4 → 2 → 1
        ↑
       temp
</code></pre>

<p>Now we process the second half.</p>

<h4>First twin pair</h4>

<pre><code>temp.val = 2
stack.peek() = 4

Twin sum:
2 + 4 = 6
</code></pre>

<p>Since <code>6 &gt; max</code>:</p>

<pre><code>max = 6

Stack:
[5]
</code></pre>

<p>Move <code>temp</code> to the next node.</p>

<h4>Second twin pair</h4>

<pre><code>temp.val = 1
stack.peek() = 5

Twin sum:
1 + 5 = 6
</code></pre>

<p>Since:</p>

<pre><code>6 == max
</code></pre>

<p><code>max</code> remains <code>6</code>.</p>

<p>Therefore:</p>

<pre><code>return 6;
</code></pre>

<p><strong>Answer = 6</strong></p>

<h3>Working Example 2</h3>

<pre><code>Input:
[4, 2, 2, 3]
</code></pre>

<p>First half:</p>

<pre><code>4 → 2
</code></pre>

<p>Stack:</p>

<pre><code>[4, 2]
</code></pre>

<p>Second half:</p>

<pre><code>2 → 3
</code></pre>

<p>Now process the twin pairs.</p>

<pre><code>2 + 2 = 4

3 + 4 = 7
</code></pre>

<p>The maximum is:</p>

<pre><code>max = 7
</code></pre>

<p>Therefore:</p>

<pre><code>return 7;
</code></pre>

<h3>Working Example 3</h3>

<pre><code>Input:
[1, 100000]
</code></pre>

<p>The list contains only two nodes.</p>

<pre><code>First half:
1

Second half:
100000
</code></pre>

<p>Push <code>1</code> into the stack:</p>

<pre><code>Stack:
[1]
</code></pre>

<p>Now calculate:</p>

<pre><code>100000 + 1 = 100001
</code></pre>

<p>Therefore:</p>

<pre><code>return 100001;
</code></pre>

<h3>Why Does the Stack Work?</h3>

<p>The stack reverses the order of the first half.</p>

<p>For example:</p>

<pre><code>First half:

5 → 4

Push into stack:

[5, 4]

Pop order:

4 → 5
</code></pre>

<p>The second half is:</p>

<pre><code>2 → 1
</code></pre>

<p>So the values are paired as:</p>

<pre><code>4 ↔ 2
5 ↔ 1
</code></pre>

<p>Which gives the correct twin pairs:</p>

<pre><code>5 + 1
4 + 2
</code></pre>

<h3>Complexity</h3>

<ul>
    <li><strong>Time Complexity:</strong> <code>O(n)</code> — the linked list is traversed a constant number of times.</li>
    <li><strong>Space Complexity:</strong> <code>O(n)</code> — the stack stores <code>n/2</code> elements, which is <code>O(n)</code>.</li>
</ul>

<h3>Key Takeaway</h3>

<p>The main trick is recognizing that the twin relationship requires comparing the first half of the list with the <strong>reverse of the second half</strong>.</p>

<pre><code>Linked List:

5 → 4 → 2 → 1

Twin pairs:

5 ↔ 1
4 ↔ 2

Stack makes the first half available in reverse:

5, 4
 ↓
4, 5

Then compare:

4 + 2
5 + 1

Take the maximum.
</code></pre>

<p><strong>Note:</strong> This solution satisfies the <code>O(n)</code> time requirement but uses <code>O(n)</code> extra space because of the stack. The follow-up/optimal approach can achieve <code>O(1)</code> extra space by manipulating the linked list itself.</p>
