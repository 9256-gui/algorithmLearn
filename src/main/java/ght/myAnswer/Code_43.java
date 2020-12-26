package ght.myAnswer;

public class Code_43 {
    public int countDigitOne(int n) {
        int length=getNumLength(n);
        if(length==1)return 1;
        int tmp=(int)Math.pow(10,length-1);
        int first=n/tmp;
        int firstCount= first==1?n%tmp+1:tmp;
        int otherCount= first*(length-1)*(int)(Math.pow(10,length-2));
        return countDigitOne(n-tmp)+firstCount+otherCount;
    }
    int getNumLength(int n){
        int length=0;
        while(n!=0){
            n%=10;
            length++;
        }
        return length;
    }
}
