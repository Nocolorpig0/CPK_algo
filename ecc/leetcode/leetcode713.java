package ecc.leetcode;
import java.util.*;
public class leetcode713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<1)
        {
            return 0;
        }
        int ans=0;
        int curmul=1;
        int l=0;
        int r=0;
        int len=nums.length;
        while(r<len)
        {
            while(r<len) {
                curmul = curmul * nums[r];
                r++;
                if (curmul < k) {
                    ans++;
                }else
                {

                    break;
                }
            }
            while(l<r)
            {
                int tmp=nums[l];
                curmul=curmul/tmp;
                if(curmul<k&&l!=r-1)
                {
                    ans++;
                }
                l++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        leetcode713 l=new leetcode713();
        int[] a={1,2,3};
        int k=2;
        int ans=l.numSubarrayProductLessThanK(a,k);
    }
}
