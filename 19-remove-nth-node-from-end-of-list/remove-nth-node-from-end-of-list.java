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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode f = head;
        ListNode s= head;
        int size =0;
        while(f.next != null & size < n){
            f = f.next;
            size++;
        }

        while(f.next != null){
            s=s.next;
            f=f.next;
        }

        if(size == 0){
            return null;
        }

        else if(size == n-1){
            return head.next;
        }
     
        s.next = s.next.next;
        
        return head;
    }
}