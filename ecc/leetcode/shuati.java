package ecc.leetcode;

import java.util.HashMap;
import java.util.*;

public class shuati {
    public List<Integer> findAnagrams(String s, String p)
    {
        Map<Character,Integer> need= new HashMap<>();
        Map<Character,Integer> window= new HashMap<>();
        int lens=s.length();
        int lenp=p.length();
        for(int i=0;i<lenp;i++)
        {
            Character c=p.charAt(i);
            need.put(c,need.getOrDefault(c,0)+1);
        }
        int l=0,r=0;
        int valid= 0;
        List<Integer> ans=new ArrayList<>();
        while(lens-l>=lenp&&r<lens)
        {
            Character c=s.charAt(r);
            r++;
            if(need.getOrDefault(c,0)>0)
            {
                window.put(c,window.getOrDefault(c,0)+1);
                int a=window.getOrDefault(c,0);
                int b=need.getOrDefault(c,0);
                if(a==b)
                {
                    valid++;
                }
            }
            while(r-l>=lenp)
            {
                if(valid==need.size())
                {
                    ans.add(l);
                    Character cur=s.charAt(l);
                    window.put(cur,window.getOrDefault(cur,0)-1);
                    valid--;
                    l++;
                    break;
                }
                Character cur=s.charAt(l);
                l++;
                if(need.getOrDefault(cur,0)>0)
                {
                    int a=window.getOrDefault(cur,0);
                    int b=need.getOrDefault(cur,0);
                    if(a==b)
                    {
                        valid--;
                    }
                    window.put(cur,window.getOrDefault(cur,0)-1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> res=new ArrayList<>();
        shuati s=new shuati();
        res=s.findAnagrams("abaacbabc","abc");
        System.out.println(res);
    }
}
