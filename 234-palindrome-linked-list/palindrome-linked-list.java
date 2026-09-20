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
    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        Stack <Integer> stack = new Stack<>();
        int size = 1;

        while(temp.next != null){
            temp = temp.next;
            size++;
        }

        if(size == 1){
            return true;
        }
        
        temp = head;
        // size of list
        if(stack.isEmpty()){
            stack.push(temp.val);
        }
        temp = temp.next;
        int size1 = 1;
        while(size1 < (size/2)){
            stack.push(temp.val);
            temp = temp.next;
            size1++;
        }

        while(size1 <= size && temp != null && !stack.isEmpty()){
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
}