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