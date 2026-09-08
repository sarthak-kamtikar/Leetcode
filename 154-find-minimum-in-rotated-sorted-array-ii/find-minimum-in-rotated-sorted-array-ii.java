class Solution {
    public int findMin(int[] nums) {
         int pivot =0 ;
        for(int i =1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                pivot = i;
            }
        }
        return nums[pivot];

    }
}