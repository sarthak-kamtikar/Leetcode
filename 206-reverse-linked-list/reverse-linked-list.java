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
    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        Stack <ListNode> stack = new Stack<>();
        if(temp == null){
            return head;
        }
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        temp = head;
        while(!stack.isEmpty()){
            if(temp == head){
                head = stack.pop();
                if(stack.isEmpty() == true){
                    head.next = null;
                }else{
                 head.next = stack.peek();
                }
                
                
            }else{
                temp = stack.pop();
                if(stack.isEmpty() == true){
                    temp.next = null;
                }else{
                temp.next = stack.peek();
                }
            }
            
        }
    

        return head;
        
        
        
    }
}