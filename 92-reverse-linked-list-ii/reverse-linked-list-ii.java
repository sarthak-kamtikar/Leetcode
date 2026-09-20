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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int count = 1;
        ListNode temp = head;
        ListNode temp2 = head;
        Stack <ListNode> stack = new Stack<>();

        if(left == right){
            return head;
        }






       // for putting temp2 at r 
       while(count < right){
            temp2 = temp2.next;
            count++;
       }
       // now temp2 is at r

       count = 1;
       //for putting in stack
       while(count < left){
        temp = temp.next;
        count++;
       }

       // now count is at left 
       while(count <= right ){
            stack.push(temp);
            temp = temp.next;
            count++;
       }

       // now temp is pointing to 5
       temp2.next = temp;
       temp2 = temp2.next;

       
       // fixed temp2.next here

       // now we do reverse
       // bring temp back at head 
       temp = head;
       count = 1;

       if(left == 1){
            head = stack.pop();
            temp = head;

            while(!stack.isEmpty()){
            temp.next = stack.pop();
            temp = temp.next;
            count++;
       }
       }else{
            while(count != left-1){
                temp = temp.next;
                count++; 
            }
             // now temp is at left-1
       if(count + 1 == left){
            temp.next = stack.pop(); 
            temp = temp.next;
       }
       count++;
       // count at left

       while(!stack.isEmpty()){
            temp.next = stack.pop();
            temp = temp.next;
            count++;
       }
       }

      
       //now count is right and stack is empty and temp is at  right
       
        temp.next = temp2;
        return head;  
       

       

       



    }
}