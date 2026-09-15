<h2><a href="https://leetcode.com/problems/remove-linked-list-elements">Remove Linked List Elements</a></h2>

<img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' />

<hr>

<p>
Given the <code>head</code> of a linked list and an integer <code>val</code>, remove all the nodes of the linked list that has <code>Node.val == val</code>, and return <em>the new head</em>.
</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> head = [1,2,6,3,4,5,6], val = 6
<strong>Output:</strong> [1,2,3,4,5]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> head = [], val = 1
<strong>Output:</strong> []
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> head = [7,7,7,7], val = 7
<strong>Output:</strong> []
</pre>

<hr>

<h2>My Approach</h2>

<p>
I used a <code>temp</code> pointer to traverse the linked list. The main idea is to handle two different situations:
</p>

<ul>
    <li>If the <strong>current node</strong> contains <code>val</code>, then I need to move the <code>head</code> and <code>temp</code> to the next node.</li>
    <li>If the <strong>next node</strong> contains <code>val</code>, then I can remove it using <code>temp.next = temp.next.next</code>.</li>
</ul>

<p>
The important thing when deleting <code>temp.next</code> is that I should <strong>not move temp forward</strong>. This is because there could be consecutive nodes having the same value.
</p>

<p>
For example:
</p>

<pre>
1 → 6 → 6 → 6 → 2
</pre>

<p>
If I delete the first <code>6</code>:
</p>

<pre>
1 → 6 → 6 → 2
↑
temp
</pre>

<p>
I keep <code>temp</code> at <code>1</code> and check its new <code>next</code> again. This allows me to remove consecutive <code>6</code>s correctly.
</p>

<hr>

<h2>Problems I Faced</h2>

<h3>1. NullPointerException</h3>

<p>
My first approach was:
</p>

<pre>
while(temp.next != null){
    if(temp.next.val == val){
        temp.next = temp.next.next;
    }
    temp = temp.next;
}
</pre>

<p>
The problem was that after deleting a node, <code>temp</code> could eventually become <code>null</code>. Then the next loop condition would try to access:
</p>

<pre>
temp.next
</pre>

<p>
which becomes:
</p>

<pre>
null.next
</pre>

<p>
and causes a <code>NullPointerException</code>.
</p>

<p>
I fixed this by making sure that I only move <code>temp</code> forward when I have <strong>not deleted <code>temp.next</code></strong>.
</p>

<hr>

<h3>2. Consecutive Nodes Having the Same Value</h3>

<p>
I initially moved <code>temp</code> after deleting a node. This causes problems when there are consecutive nodes with the target value.
</p>

<p>
For example:
</p>

<pre>
1 → 6 → 6 → 6 → 2
</pre>

<p>
If I delete the first <code>6</code> and immediately move <code>temp</code>, I may skip checking the new node that came into the position of <code>temp.next</code>.
</p>

<p>
Therefore, when this condition is true:
</p>

<pre>
temp.next.val == val
</pre>

<p>
I do:
</p>

<pre>
temp.next = temp.next.next;
</pre>

<p>
but I <strong>do not move temp</strong>.
</p>

<hr>

<h3>3. Handling the Head Node</h3>

<p>
Another problem was that if the head itself has the value that needs to be removed, I cannot do:
</p>

<pre>
temp.next = temp.next.next;
</pre>

<p>
because the node I want to remove is <code>temp</code> itself.
</p>

<p>
For example:
</p>

<pre>
6 → 1 → 2 → 3
↑
temp
</pre>

<p>
There is no previous node before <code>6</code> whose <code>next</code> pointer I can modify.
</p>

<p>
So I move the head forward:
</p>

<pre>
head = temp.next;
temp = temp.next;
</pre>

<p>
Now:
</p>

<pre>
1 → 2 → 3
↑
head
↑
temp
</pre>

<p>
This effectively removes the old head from the list.
</p>

<hr>

<h3>4. Handling the Last Node</h3>

<p>
My loop condition is:
</p>

<pre>
while(temp.next != null)
</pre>

<p>
This means that the last node is never processed inside the loop because its <code>next</code> is <code>null</code>.
</p>

<p>
For example:
</p>

<pre>
1 → 2 → 3 → 7 → null
</pre>

<p>
When <code>temp</code> reaches <code>7</code>:
</p>

<pre>
temp.next == null
</pre>

<p>
so the loop stops.
</p>

<p>
Therefore, I added a final check:
</p>

<pre>
if(temp.next == null && temp.val == val){
    head = temp.next;
}
</pre>

<p>
If the final node contains <code>val</code>, then:
</p>

<pre>
head = temp.next;
</pre>

<p>
becomes:
</p>

<pre>
head = null;
</pre>

<p>
when that node is the only remaining node.
</p>

<hr>

<h2>Final Code</h2>

<pre>
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode temp = head;
        
        if(head == null){
            return head;
        }
        
        while(temp.next != null){
            if (temp.val == val){
                head = temp.next;
                temp = temp.next;
            }
            else if(temp.next.val == val){
                temp.next = temp.next.next;
            }
            else{
                temp = temp.next;
            }
        }

        if(temp.next == null && temp.val == val){
            head = temp.next;
        }

        return head;
    }
}
</pre>

<hr>

<h2>Dry Run — Example 1</h2>

<pre>
head = [1,2,6,3,4,5,6]
val = 6

1 → 2 → 6 → 3 → 4 → 5 → 6
↑
temp
</pre>

<p>
Initially, <code>temp = 1</code>.
</p>

<p>
<code>temp.val</code> is not <code>6</code>, and <code>temp.next.val</code> is <code>2</code>, so we move:
</p>

<pre>
1 → 2 → 6 → 3 → 4 → 5 → 6
    ↑
   temp
</pre>

<p>
Now <code>temp.next.val == 6</code>, so:
</p>

<pre>
temp.next = temp.next.next;
</pre>

<p>
The first <code>6</code> is removed:
</p>

<pre>
1 → 2 → 3 → 4 → 5 → 6
    ↑
   temp
</pre>

<p>
We continue traversing until we reach <code>5</code>. Its next node is <code>6</code>, so we remove it:
</p>

<pre>
1 → 2 → 3 → 4 → 5
                ↑
               temp
</pre>

<p>
The final node was <code>6</code>, so it is removed by:
</p>

<pre>
temp.next = temp.next.next;
</pre>

<p>
Final result:
</p>

<pre>
1 → 2 → 3 → 4 → 5 → null
</pre>

<p>
Therefore:
</p>

<pre>
[1,2,3,4,5]
</pre>

<hr>

<h2>Dry Run — Example 2</h2>

<pre>
head = []
val = 1
</pre>

<p>
Here:
</p>

<pre>
head == null
</pre>

<p>
Therefore:
</p>

<pre>
if(head == null){
    return head;
}
</pre>

<p>
returns <code>null</code>.
</p>

<p>
Result:
</p>

<pre>
[]
</pre>

<hr>

<h2>Dry Run — Example 3</h2>

<pre>
head = [7,7,7,7]
val = 7
</pre>

<p>
Initially:
</p>

<pre>
7 → 7 → 7 → 7
↑
head
↑
temp
</pre>

<p>
Since <code>temp.val == val</code>:
</p>

<pre>
head = temp.next;
temp = temp.next;
</pre>

<p>
Now:
</p>

<pre>
7 → 7 → 7 → 7
    ↑
   head
    ↑
   temp
</pre>

<p>
Again the current node is <code>7</code>, so we move forward again:
</p>

<pre>
7 → 7 → 7 → 7
        ↑
       head
        ↑
       temp
</pre>

<p>
Again:
</p>

<pre>
7 → 7 → 7 → 7
             ↑
            head
             ↑
            temp
</pre>

<p>
Now <code>temp</code> is at the final <code>7</code>. The loop stops because:
</p>

<pre>
temp.next == null
</pre>

<p>
The final condition checks:
</p>

<pre>
temp.next == null && temp.val == val
</pre>

<p>
which is true.
</p>

<p>
Therefore:
</p>

<pre>
head = temp.next;
</pre>

<p>
Since <code>temp.next == null</code>:
</p>

<pre>
head = null;
</pre>

<p>
Final result:
</p>

<pre>
[]
</pre>

<hr>

<h2>Key Linked List Lessons From This Problem</h2>

<ul>
    <li>To delete a node after <code>temp</code>, use <code>temp.next = temp.next.next</code>.</li>
    <li>After deleting <code>temp.next</code>, don't immediately move <code>temp</code> because the new <code>temp.next</code> may also need to be deleted.</li>
    <li>The head is special because there is no previous node before it.</li>
    <li>If the head needs to be removed, move <code>head</code> to <code>head.next</code>.</li>
    <li>Always think about the empty list, head deletion, consecutive deletions, and last-node deletion.</li>
</ul>

<h2>Time and Space Complexity</h2>

<p>
<strong>Time Complexity:</strong> <code>O(n)</code>
</p>

<p>
Each node is processed at most a constant number of times.
</p>

<p>
<strong>Space Complexity:</strong> <code>O(1)</code>
</p>

<p>
Only a few pointers are used; no additional data structure is required.
</p>
