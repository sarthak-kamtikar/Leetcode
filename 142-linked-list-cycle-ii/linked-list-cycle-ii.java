/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode f = head;
        ListNode s = head;
        ListNode fast = head;
        ListNode slow = head;
        int length = 0;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            
            if(fast == slow){
                slow = slow.next;
                length++;
                while(slow != fast){
                    slow = slow.next;
                    length++;
                }
                break;
            }
            
        }

        if(length == 0){
            return null;
        }

        while(length > 0){
            s = s.next;
            length--;
        }

        while(f != s){
            f = f.next;
            s = s.next;
        }

        return s;

        
    }
    }
