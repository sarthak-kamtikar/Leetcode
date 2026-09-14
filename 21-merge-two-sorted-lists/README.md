# 🔗 Merge Two Sorted Lists

## 📝 Problem

<h2><a href="https://leetcode.com/problems/merge-two-sorted-lists">Merge Two Sorted Lists</a></h2>

<img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' />

You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one **sorted** list. The list should be made by **splicing together the nodes** of the first two lists.

Return **the head of the merged linked list**.

### Example 1

```text
Input:
list1 = [1,2,4]
list2 = [1,3,4]

Output:
[1,1,2,3,4,4]
```

### Example 2

```text
Input:
list1 = []
list2 = []

Output:
[]
```

### Example 3

```text
Input:
list1 = []
list2 = [0]

Output:
[0]
```

### Constraints

- The number of nodes in both lists is in the range `[0, 50]`.
- `-100 <= Node.val <= 100`
- Both `list1` and `list2` are sorted in **non-decreasing** order.

---

# 💡 Approach

Since both linked lists are already sorted, compare the current node of each list.

Example:

```text
list1: 1 → 3 → 5
list2: 2 → 4 → 6
```

Compare:

```text
1 vs 2 → take 1
3 vs 2 → take 2
3 vs 4 → take 3
5 vs 4 → take 4
5 vs 6 → take 5
```

After one list becomes `null`, attach the remaining list because it is already sorted.

### Steps

1. Create a dummy node.
2. Create a `current` reference pointing to the dummy node.
3. Compare `list1.val` and `list2.val`.
4. Attach the smaller node using `current.next`.
5. Move the pointer of the list from which the node was taken.
6. Move `current` forward.
7. Continue until one list becomes `null`.
8. Attach the remaining list.
9. Return `dummy.next`.

---

# 🧠 `ListNode` and References

When we write:

```java
ListNode node = new ListNode();
```

`new ListNode()` **creates a new `ListNode` object**.

If the class is:

```java
class ListNode {
    int val;
    ListNode next;
}
```

then the default values are:

```text
val  = 0
next = null
```

Conceptually:

```text
node
 ↓
[ val = 0 | next = null ]
```

`node` is a **reference variable** pointing to the `ListNode` object.

---

# 🟢 Is `node` Automatically the Head?

**No.**

Creating a `ListNode` does not automatically make it the head.

For example:

```java
ListNode node = new ListNode(10);
ListNode second = new ListNode(20);

node.next = second;
```

gives:

```text
node
 ↓
[10] → [20] → null
```

Here `node` is the **head reference** because it points to the first node of the linked list.

### Important

> **Head is a role/position, not a special type of node.**

There is nothing magical about the variable name `head`.

This:

```java
ListNode xyz = new ListNode(10);
```

can also be the head reference if `xyz` points to the first node.

So:

```text
ListNode → type/class
node     → reference variable
head     → reference to the first node
```

---

# 🧱 `ListNode` vs `LinkedList`

These are different things.

## `ListNode`

```java
ListNode node = new ListNode();
```

Creates **one `ListNode` object**.

You directly work with its fields:

```java
node.val
node.next
```

Conceptually:

```text
node
 ↓
[10 | next] → [20 | next] → null
```

## Java's `LinkedList`

```java
LinkedList<Integer> list = new LinkedList<>();
```

creates an object of Java's built-in `java.util.LinkedList` class.

You normally interact with it using methods:

```java
list.add(10);
list.add(20);
list.get(0);
list.remove();
```

Java manages its internal linked-list implementation for you.

### Main difference

```text
ListNode
   ↓
ONE node/object that you manipulate directly

LinkedList
   ↓
A complete linked-list data structure managed by Java
```

For LeetCode linked-list problems using `ListNode`, you normally work directly with `ListNode`, not Java's `LinkedList`.

---

# 🧱 Dummy Node

We create:

```java
ListNode dummy = new ListNode();
```

This creates:

```text
dummy
  ↓
[0 | null]
```

The `0` is just the default value of the `int val` field.

We could also write:

```java
ListNode dummy = new ListNode(-1);
```

The actual value does **not matter**.

The node is only a **temporary/helper node**.

---

# 👉 `ListNode current = dummy`

This is very important.

```java
ListNode current = dummy;
```

does **not** create another node.

Both references point to the **same object**:

```text
dummy ───┐
         ↓
        [0 | null]
         ↑
current ─┘
```

So:

```java
ListNode dummy = new ListNode();
ListNode current = dummy;
```

means:

1. Create one `ListNode` object.
2. `dummy` points to that object.
3. `current` also points to that exact same object.

---

# 🚶 Why Do We Need Both `dummy` and `current`?

Because we want:

```text
dummy   → stays at the beginning
current → moves while building the list
```

Suppose:

```text
list1: 1 → 3 → 5
list2: 2 → 4 → 6
```

Initially:

```text
dummy/current
      ↓
     [0]
```

Attach `1`:

```java
current.next = list1;
```

Now:

```text
dummy
 ↓
[0] → [1] → [3] → [5]
 ↑
current
```

Then:

```java
current = current.next;
```

moves `current`:

```text
dummy
 ↓
[0] → [1] → [3] → [5]
       ↑
     current
```

`dummy` stays where it started.

We continue building the merged list.

Eventually:

```text
dummy
 ↓
[0] → [1] → [2] → [3] → [4] → [5] → [6]
```

---

# ❓ Why Is the Dummy Node Discarded?

The dummy node was **created by us**. It wasn't part of either input list.

Our temporary chain looks like:

```text
[0] → [1] → [2] → [3] → [4] → [5] → [6]
 ↑
dummy
```

But the actual answer should be:

```text
[1] → [2] → [3] → [4] → [5] → [6]
```

Therefore we return:

```java
return dummy.next;
```

which points to `[1]`.

We don't literally delete the dummy node.

We simply **don't return it**.

---

# 💻 Complete Solution

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } 
            else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } 
        else {
            current.next = list2;
        }

        return dummy.next;
    }
}
```

---

# 🔍 Understanding the Important Lines

## 1. Create dummy

```java
ListNode dummy = new ListNode();
```

Creates:

```text
dummy → [0 | null]
```

---

## 2. Create current

```java
ListNode current = dummy;
```

Both point to the same node:

```text
dummy ──┐
        ↓
       [0]
        ↑
current ─┘
```

---

## 3. Attach a node

```java
current.next = list1;
```

Means:

> Make the `next` of the node that `current` is pointing to point to the current `list1` node.

---

## 4. Move `list1`

```java
list1 = list1.next;
```

We already used the current `list1` node, so move `list1` forward.

---

## 5. Move `current`

```java
current = current.next;
```

Move `current` to the node we just attached.

---

## 6. Attach the remaining list

Suppose:

```text
list1: null
list2: 5 → 6 → 7
```

We don't need to compare anymore.

Simply:

```java
current.next = list2;
```

because `list2` is already sorted.

---

## 7. Return the actual head

```java
return dummy.next;
```

Because:

```text
dummy
 ↓
[0] → [1] → [2] → [3]
       ↑
   actual head
```

So `dummy.next` is the head of the actual merged list.

---

# 🖨️ `return` vs `System.out.println()`

If the method is:

```java
public ListNode mergeTwoLists(...)
```

then the method must return a `ListNode`.

Therefore:

```java
return dummy.next;
```

gives the result back to LeetCode.

Whereas:

```java
System.out.println(dummy.next);
```

only prints something to the console.

`System.out.println()` is useful for **debugging**, not for returning the answer.

For example:

```java
System.out.println(current.val);
```

prints the value of the current node.

---

# 🔎 Why Did `System.out.print(temp)` Give `ListNode@...`?

If you do:

```java
System.out.print(temp);
```

Java tries to print the `ListNode` object itself.

Since `ListNode` doesn't have a custom `toString()` method, you may see:

```text
ListNode@3e3abc88
```

Instead, print the value:

```java
System.out.print(temp.val);
```

which gives something like:

```text
123
```

So when debugging:

```java
System.out.println(node.val);
```

is usually much more useful.

---

# ⚠️ Empty Linked Lists

An empty linked list is represented by:

```java
head == null
```

For example:

```text
head = []
```

means:

```text
head → null
```

If you do:

```java
ListNode node = head;
```

then:

```text
node → null
```

Trying to do:

```java
node.next
```

causes a `NullPointerException` because there is no node.

This is why linked-list problems often need to consider the `null` case.

For the merge problem, the condition:

```java
while (list1 != null && list2 != null)
```

naturally handles the situation where either list is empty.

---

# 🧠 Final Mental Model

A `ListNode` is an **object**:

```text
[val | next]
```

A variable such as `head`, `node`, `dummy`, or `current` is a **reference pointing to an object**:

```text
node ─────→ [10 | next] ─────→ [20 | next] ─────→ null
```

If the reference points to the first node of the list, it functions as the **head reference**.

For the dummy-node technique:

```text
dummy ─────→ [0] → [1] → [2] → [3]
                         ↑
                      current
```

Remember:

> **`dummy` stays. `current` moves. `dummy.next` is the actual head of the result.**

### Most important pattern to memorize

```java
ListNode dummy = new ListNode();
ListNode current = dummy;

while (...) {
    current.next = ...;
    current = current.next;
}

return dummy.next;
```

This dummy-node pattern is extremely useful for linked-list problems because it avoids having to separately handle the first node of the result.
