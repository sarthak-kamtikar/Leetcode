class Solution {
    public int findPeakElement(int[] nums) {
        int lp =0;
        int hp = nums.length -1;
        int mid = (lp+hp)/2;
        int ans = 1;
        while(lp<hp){
            mid = (lp+hp)/2;
            if(nums[mid]>nums[mid+1]){
                hp = mid;
                
            }
            else if (nums[mid]<nums[mid+1]){
                lp = mid+1;
                
            }
        }
        return lp;        
    }
}