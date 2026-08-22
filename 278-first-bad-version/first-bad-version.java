/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lp = 1;
        int hp = n;
        int ans = 0;
        while(lp<=hp){
            int mid = lp+(hp-lp)/2;

            if(isBadVersion(mid)==true){
                ans = mid;
                hp = mid-1;
            }else if (isBadVersion(mid) == false){
                lp = mid+1;
            }
        }
        return ans;
    }
}