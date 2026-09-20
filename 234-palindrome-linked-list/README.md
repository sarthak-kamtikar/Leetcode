<h2><a href="https://leetcode.com/problems/palindrome-linked-list">Palindrome Linked List</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr>

<p>Given the <code>head</code> of a singly linked list, return <code>true</code><em> if it is a </em><span data-keyword="palindrome-sequence"><em>palindrome</em></span><em> or </em><code>false</code><em> otherwise</em>.</p>

<h3>Approach</h3>

<p>I used a <strong>Stack</strong> to store the first half of the linked list and then compared those values with the second half.</p>

<ol>
    <li>First, traverse the entire linked list to find its <strong>size</strong>.</li>
    <li>If the list contains only one node, it is automatically a palindrome.</li>
    <li>Reset <code>temp</code> to <code>head</code>.</li>
    <li>Push the first half of the linked list into a stack.</li>
    <li>Move <code>temp</code> to the beginning of the second half.</li>
    <li>Compare <code>stack.peek()</code> with <code>temp.val</code>.</li>
    <li>If they match, remove the value from the stack using <code>pop()</code> and move to the next node.</li>
    <li>If the stack becomes empty, all required values matched, so the linked list is a palindrome.</li>
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
    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        Stack &lt;Integer&gt; stack = new Stack&lt;&gt;();
        int size = 1;

        while(temp.next != null){
            temp = temp.next;
            size++;
        }

        if(size == 1){
            return true;
        }
        
        temp = head;

        // Push the first element
        if(stack.isEmpty()){
            stack.push(temp.val);
        }

        temp = temp.next;
        int size1 = 1;

        // Push the first half into the stack
        while(size1 &lt; (size/2)){
            stack.push(temp.val);
            temp = temp.next;
            size1++;
        }

        // Compare stack values with the second half
        while(size1 &lt;= size &amp;&amp; temp != null &amp;&amp; !stack.isEmpty()){
            if(stack.peek() == temp.val){
                stack.pop();
                temp = temp.next;
            }else{
                temp = temp.next;
            }
        }

        if(stack.isEmpty()){
            return true;
        }

        return false;
    }
}</code></pre>

<h3>Working Example</h3>

<p>Consider:</p>

<pre><code>head = [1, 2, 2, 1]</code></pre>

<p>First, we calculate the size:</p>

<pre><code>size = 4</code></pre>

<p>Now we start from the head and put the first half into the stack.</p>

<pre><code>Linked List:

1 → 2 → 2 → 1
↑
temp

Stack:
[]
</code></pre>

<p>Push <code>1</code>:</p>

<pre><code>Stack:
[1]

temp → 2 → 2 → 1
</code></pre>

<p>Push <code>2</code>:</p>

<pre><code>Stack:
[1, 2]

temp → 2 → 1
</code></pre>

<p>Now compare the remaining nodes with the stack.</p>

<p><strong>First comparison:</strong></p>

<pre><code>stack.peek() = 2
temp.val     = 2

2 == 2
</code></pre>

<p>They match, so we pop <code>2</code>.</p>

<pre><code>Stack:
[1]

temp → 1
</code></pre>

<p><strong>Second comparison:</strong></p>

<pre><code>stack.peek() = 1
temp.val     = 1

1 == 1
</code></pre>

<p>They match again, so we pop <code>1</code>.</p>

<pre><code>Stack:
[]

temp → null
</code></pre>

<p>The stack is now empty, meaning all required values matched.</p>

<pre><code>return true;</code></pre>

<h3>Example 2</h3>

<pre><code>head = [1, 2]</code></pre>

<p>Here:</p>

<pre><code>size = 2

Stack:
[1]

temp → 2
</code></pre>

<p>Comparison:</p>

<pre><code>stack.peek() = 1
temp.val     = 2

1 != 2
</code></pre>

<p>The values do not match, so the linked list is not a palindrome.</p>

<pre><code>return false;</code></pre>

<h3>Complexity</h3>

<ul>
    <li><strong>Time Complexity:</strong> <code>O(n)</code> — the list is traversed a constant number of times.</li>
    <li><strong>Space Complexity:</strong> <code>O(n)</code> — the stack stores approximately half of the nodes.</li>
</ul>

<p><strong>Note:</strong> The follow-up asks for <code>O(1)</code> extra space. This solution uses a stack, so it does not satisfy the follow-up's space requirement.</p>
