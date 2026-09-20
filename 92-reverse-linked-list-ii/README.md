<h2><a href="https://leetcode.com/problems/reverse-linked-list-ii">Reverse Linked List II</a></h2> <img src='https://img.shields.io/badge/Difficulty-Medium-orange' alt='Difficulty: Medium' /><hr>

<p>Given the <code>head</code> of a singly linked list and two integers <code>left</code> and <code>right</code>, reverse the nodes from position <code>left</code> to position <code>right</code>.</p>

<h3>My Approach</h3>

<p>I used a <code>Stack&lt;ListNode&gt;</code> to reverse the required section.</p>

<ol>
    <li>Use <code>temp2</code> to reach the node at position <code>right</code>.</li>
    <li>Use <code>temp</code> to reach the node at position <code>left</code>.</li>
    <li>Push all nodes from <code>left</code> to <code>right</code> into a stack.</li>
    <li>Since a stack follows LIFO, popping the nodes gives them in reverse order.</li>
    <li>If <code>left == 1</code>, the first popped node becomes the new <code>head</code>.</li>
    <li>Otherwise, find the node at <code>left - 1</code> and connect it to the reversed section.</li>
    <li>After reversing, connect the last reversed node to the node after <code>right</code>.</li>
</ol>

<h3>Working Example</h3>

<pre>
Input:
1 → 2 → 3 → 4 → 5

left = 2
right = 4
</pre>

<p>First, <code>temp2</code> is moved to position <code>right</code>:</p>

<pre>
1 → 2 → 3 → 4 → 5
            ↑
          temp2
</pre>

<p>Then <code>temp</code> is moved to position <code>left</code>:</p>

<pre>
1 → 2 → 3 → 4 → 5
    ↑
  temp
</pre>

<p>Nodes from <code>left</code> to <code>right</code> are pushed into the stack:</p>

<pre>
Stack:

TOP
 ↓
4
3
2
</pre>

<p>Because the stack is LIFO, popping gives:</p>

<pre>
4 → 3 → 2
</pre>

<p>So the selected section is reversed.</p>

<pre>
1 → 4 → 3 → 2
</pre>

<p>The node after <code>right</code> is already saved using <code>temp2</code>:</p>

<pre>
temp2 → 5 → 6 → 7 → ...
</pre>

<p>Therefore, only one connection is required:</p>

<pre><code>temp.next = temp2;</code></pre>

<p>Final list:</p>

<pre>
1 → 4 → 3 → 2 → 5
</pre>

<h3>Mistakes I Made</h3>

<h4>1. Wrong Stack Type</h4>

<p>Initially I used:</p>

<pre><code>Stack&lt;Integer&gt; stack = new Stack&lt;&gt;();</code></pre>

<p>But I was pushing <code>ListNode</code> objects:</p>

<pre><code>stack.push(temp);</code></pre>

<p>So the correct declaration is:</p>

<pre><code>Stack&lt;ListNode&gt; stack = new Stack&lt;&gt;();</code></pre>

<h4>2. EmptyStackException</h4>

<p>I initially tried to use <code>stack.peek()</code> even after the stack had become empty.</p>

<pre><code>temp.next = stack.peek();</code></pre>

<p>This caused:</p>

<pre>
java.util.EmptyStackException
</pre>

<p>The solution was to check the stack using:</p>

<pre><code>while (!stack.isEmpty())</code></pre>

<p>and connect the final node separately.</p>

<h4>3. Reversal Loop Was Stopping Too Early</h4>

<p>I initially used a condition based on <code>count &lt; right</code>.</p>

<p>This could leave a node inside the stack without processing it.</p>

<p>Since the stack itself tells me when all selected nodes have been processed, I changed the reversal loop to:</p>

<pre><code>while (!stack.isEmpty())</code></pre>

<h4>4. Problem When <code>left == 1</code></h4>

<p>I originally tried to find the node at <code>left - 1</code>:</p>

<pre><code>while (count != left - 1) {
    temp = temp.next;
    count++;
}</code></pre>

<p>But when <code>left == 1</code>, there is no node before the first node.</p>

<p>Therefore, the first node popped from the stack has to become the new <code>head</code>:</p>

<pre><code>if (left == 1) {
    head = stack.pop();
    temp = head;

    while (!stack.isEmpty()) {
        temp.next = stack.pop();
        temp = temp.next;
    }
}</code></pre>

<h4>5. I Thought I Needed Another Loop for the Remaining Nodes</h4>

<p>For example:</p>

<pre>
1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
        ↑       ↑
       left    right
</pre>

<p>After reversing:</p>

<pre>
1 → 4 → 3 → 2
</pre>

<p>I initially thought I needed to traverse <code>5 → 6 → 7 → 8</code> again.</p>

<p>But that is unnecessary because <code>temp2</code> already points to the complete remaining chain:</p>

<pre>
temp2 → 5 → 6 → 7 → 8
</pre>

<p>So this single statement is enough:</p>

<pre><code>temp.next = temp2;</code></pre>

<h3>Final Code</h3>

<pre><code>class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int count = 1;
        ListNode temp = head;
        ListNode temp2 = head;
        Stack&lt;ListNode&gt; stack = new Stack&lt;&gt;();

        if (left == right) {
            return head;
        }

        // Move temp2 to right
        while (count &lt; right) {
            temp2 = temp2.next;
            count++;
        }

        count = 1;

        // Move temp to left
        while (count &lt; left) {
            temp = temp.next;
            count++;
        }

        // Push left to right into stack
        while (count &lt;= right) {
            stack.push(temp);
            temp = temp.next;
            count++;
        }

        // Save the node after right
        temp2.next = temp;
        temp2 = temp2.next;

        // Reverse
        temp = head;
        count = 1;

        if (left == 1) {
            head = stack.pop();
            temp = head;

            while (!stack.isEmpty()) {
                temp.next = stack.pop();
                temp = temp.next;
            }
        } else {
            // Move temp to left - 1
            while (count != left - 1) {
                temp = temp.next;
                count++;
            }

            // Connect node before left to reversed section
            temp.next = stack.pop();
            temp = temp.next;

            // Continue reversing
            while (!stack.isEmpty()) {
                temp.next = stack.pop();
                temp = temp.next;
            }
        }

        // Connect reversed section to remaining list
        temp.next = temp2;

        return head;
    }
}</code></pre>

<h3>Complexity</h3>

<ul>
    <li><strong>Time:</strong> <code>O(n)</code></li>
    <li><strong>Space:</strong> <code>O(right - left + 1)</code> because of the stack.</li>
</ul>

<h3>Key Learning</h3>

<p>The important part I learned from this problem was to identify the three sections of the linked list:</p>

<pre>
Before left → Reversed section → After right
</pre>

<p>My stack handles the reversal, while <code>temp</code> and <code>temp2</code> are used to reconnect the three sections.</p>
