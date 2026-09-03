class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // merge -> sort -> middle 
        int n = nums1.length + nums2.length;
        int []ans = new int[n];
        System.arraycopy(nums1, 0, ans, 0, nums1.length);
        System.arraycopy(nums2, 0, ans, nums1.length, nums2.length);
        Arrays.sort(ans);
        if(n%2==0){
        return (ans[n/2 - 1]+ans[n/2])/2.0;
        }else{
            return ans[n/2];
        }


    }

}