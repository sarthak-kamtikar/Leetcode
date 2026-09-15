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
    public ListNode middleNode(ListNode head) {
       ListNode node = head;
       int size = 1;
       while(node.next != null){
            node = node.next;
            size++;
        } 

        ListNode temp = head;
        int siz = size/2;
        if(size % 2 == 0){
            while(siz > 0){
                temp = temp.next;
                siz--;
            }
        }else{
            while(siz>0){
                temp=temp.next;
                siz--;
            }
        }

        return temp;
    }
}