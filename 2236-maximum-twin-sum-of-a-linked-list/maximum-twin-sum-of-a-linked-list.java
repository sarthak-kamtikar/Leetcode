/**
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
        Stack <Integer> stack = new Stack<>();
        ListNode temp = head;

        int size = 1;
        while(temp.next != null){
            temp = temp.next;
            size++;
        }

        temp = head;
        int size1 =1;
        while(size1 <= size/2){
            stack.push(temp.val);
            temp = temp.next;
            size1++;
        }
        int max = 0;
        while(temp != null){
            if(temp.val + stack.peek() > max){
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
}