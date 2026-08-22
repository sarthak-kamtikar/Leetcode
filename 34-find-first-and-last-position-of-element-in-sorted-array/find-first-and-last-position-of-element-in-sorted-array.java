class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lp = 0;
        int hp = nums.length - 1;
        int [] ans = {-1,-1};

        while(lp<=hp && hp>=0){
            int mid = (lp+hp)/2;
            if(nums[mid]==target){
                ans[0] = mid;
                hp = mid-1;
            }else if(nums[mid]>target){
                hp = mid-1;
            }else{
                lp = mid+1;
            }
            
        }

        lp = 0;
        hp = nums.length - 1;
        
        // 
        while(lp<=hp && lp<nums.length){
            int mid = (lp+hp)/2;
            if(nums[mid]==target){
                ans[1] = mid;
                lp = mid+1;
            }else if(nums[mid]>target){
                hp = mid-1;
            }else{
                lp = mid+1;
            }
            
        }

        return ans;

    }
}