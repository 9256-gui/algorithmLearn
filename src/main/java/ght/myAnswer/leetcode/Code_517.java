package ght.myAnswer.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Code_517 {
    public static int findMinMoves(int[] machines) {
        int n=machines.length;
        int[] sum = new int[n];
        for(int i=0;i<n;i++){
            sum[i]=machines[i]+(i>0?sum[i-1]:0);
        }
        if(sum[n-1]%n!=0){
            return -1;
        }
        int num=sum[n-1]/n;
        int left=0;
        int right=0;
        int res=0;
        for(int i=0;i<n;i++){
            if(i>0){
                left=sum[i-1]-num*i;
            }
            right=sum[n-1]-sum[i]-num*(n-i);
            if(left<0&&right<0){
                res=Math.max(Math.abs(left+right),res);
            }else{
                res=Math.max(Math.max(Math.abs(left) ,Math.abs(right)),res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> list =new ArrayList<>();
        list.remove(1);
        System.out.println(findMinMoves(new int[]{3,1,0,4}));
    }

}
