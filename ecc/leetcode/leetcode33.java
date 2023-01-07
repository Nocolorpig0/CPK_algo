package ecc.leetcode;

class leetcode33{
    public int search(int[] nums, int target) {
        int len=nums.length-1;
        int pos=0,jugg=0;
        for(int i=0;i<len;i++)
        {
            if(nums[i]>nums[i+1])
            {
                pos=i;
                jugg=1;
                break;
            }
        }
        if(jugg==0)
        {
            pos=len;
        }
        int a=bs(0,pos,nums,target);
        if(a!=-1)
        {
            return a;
        }
        int b=bs(pos+1,len,nums,target);
        if(b!=-1)
        {
            return b;
        }
        return -1;
    }
    public int bs(int l,int r,int[]nums,int target)
    {
        int mid=0;
        while(l<=r)
        {
            mid=l+(r-l)/2;
            if(nums[mid]==target)
            {
                return mid;
            }
            if(nums[mid]>target)
            {
                r=mid-1;
            }else
            {
                l=mid+1;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        leetcode33 a=new leetcode33();
        int[] aa={4,5,6,7,0,1,2};
        int t=0;
        int ans=a.search(aa,t);
    }
}
