class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int count = 1;
        ListNode temp = head;
        ListNode temp2 = head;
        Stack<ListNode> stack = new Stack<>();

        if (left == right) {
            return head;
        }

        // Move temp2 to right
        while (count < right) {
            temp2 = temp2.next;
            count++;
        }

        count = 1;

        // Move temp to left
        while (count < left) {
            temp = temp.next;
            count++;
        }

        // Push left to right into stack
        while (count <= right) {
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

            temp.next = stack.pop();
            temp = temp.next;

            while (!stack.isEmpty()) {
                temp.next = stack.pop();
                temp = temp.next;
            }
        }

        // Connect reversed part to remaining list
        temp.next = temp2;

        return head;
    }
}