class Solution {
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> A = new ArrayList<>();

        ListNode temp = head;
        while(temp != null){
            A.add(temp.val);
            temp = temp.next;
        }

        int[] ans = new int[A.size()];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < A.size(); i++){

            while(!stack.isEmpty() && A.get(stack.peek()) < A.get(i)){
                int index = stack.pop();
                ans[index] = A.get(i);
            }

            stack.push(i);
        }

        return ans;
    }
}