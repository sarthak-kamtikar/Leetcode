class Solution {
    public int[] sortedSquares(int[] nums) {
        int []sqr = new int[nums.length];
        for(int i =0;i<nums.length;i++){
            sqr[i] = nums[i]*nums[i];
        }
        Arrays.sort(sqr);
        return sqr;
    }
}