class Solution {
    public int findMin(int[] nums) {

        int min = nums[0];
        int lp = 0;
        int hp = nums.length-1;
        while(lp<=hp){
            if(nums.length == 2 && nums[hp]<nums[lp]){
                min = hp;
            }
            int mid = lp + (hp-lp)/2 ;
            if(nums[mid]<min){
                min = nums[mid];
            }else if (nums[mid]>min){
                lp = mid+1;
            }else{
                hp = mid-1;
            }
        }
        return min;


    }
}