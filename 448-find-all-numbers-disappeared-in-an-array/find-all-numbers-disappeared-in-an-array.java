class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        
        Set<Integer> set = new HashSet<>();
        for(int i =0;i<nums.length;i++){
            set.add(nums[i]);
        }

        Arrays.sort(nums);
        int max = nums[nums.length-1];
        List <Integer> ans = new ArrayList<>();
        for(int i = 1; i<=nums.length;i++){
            if(!set.contains(i)){
                ans.add(i);
            }
        }

        return ans;

    }
}