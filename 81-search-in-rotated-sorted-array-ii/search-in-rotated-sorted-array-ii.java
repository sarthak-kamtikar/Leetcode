class Solution {
    public boolean search(int[] nums, int target) {

         int pivot =0 ;
        for(int i =1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                pivot = i;
            }
        }

        // for 0 -> pivot index
        int lp =0;
        int hp =pivot-1;
        while(lp<=hp){
            int mid = lp + (hp-lp)/2 ;
            if(nums[mid]==target){
                return true;
            }else if(nums[mid]>target){
                hp = mid-1;
            }else {
                lp = mid+1;
            }
        }

        // for pivot -> last 
        int lpp = pivot;
        int hpp = nums.length -1;
        while(lpp<=hpp){
            int mid = lpp + (hpp-lpp)/2 ;
             if(nums[mid]==target){
                return true;
            }else if(nums[mid]>target){
                hpp = mid-1;
            }else {
                lpp = mid+1;
            }
        }

        return false;
    }
}