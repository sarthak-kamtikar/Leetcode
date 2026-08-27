class Solution {
    public int firstMissingPositive(int[] nums) {
        /*start counting from 1 what is the first number missing*/

        Set<Integer> set = new HashSet<>();
        for(int i =0;i<nums.length;i++){
            set.add(nums[i]);
        }

        Arrays.sort(nums);
        int max = nums[nums.length-1];

        for(int i = 1; i<=max;i++){
            if(!set.contains(i)){
                return i;
            }
        }

        if( max <=0){
            return 1;
        }
        return max+1;


    }




}