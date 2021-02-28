package ght.myAnswer.code.class01;

public class MinWindowLength {
    /**
     * 给定str1和str2，求str1中包含str2的最小子串长度
     */
    public static int minWindowLength(String str1, String str2) {
        int[] num =new int[128];
        char[] char1=str1.toCharArray(),char2=str2.toCharArray();
        int all=char2.length;
        for(char c:char2){
            num[c]++;
        }
        int l=0,r=0,n=char1.length,res=Integer.MAX_VALUE;
        while(r<n){
            num[char1[r]]--;
            if(num[char1[r]]>=0){
                all--;
            }
            if (all==0){
                while(num[char1[l]]<0){
                    num[char1[l++]]++;
                }
                res =Math.min(res,r-l+1);
                all++;
                num[char1[l++]]++;
            }
            r++;
        }
        return res==Integer.MAX_VALUE?0:res;
    }
    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acb";
        System.out.println(minWindowLength(str1, str2));

    }
}
