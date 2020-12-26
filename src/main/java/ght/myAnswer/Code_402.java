package ght.myAnswer;

import java.util.Deque;
import java.util.LinkedList;

public class Code_402 {
    public String removeKdigits1(String num, int k) {
        if(k>=num.length())return "0";
        StringBuilder s = new StringBuilder(num);
        while (k>0){
            int index=1;
            while (index<s.length()&&s.charAt(index)<s.charAt(index-1))index++;
            s.delete(index-1,index);
            k--;
        }
        while (s.length()>0&&s.charAt(0)=='0')s.delete(0,1);
        return s.length()==0 ?"0":s.toString();
    }
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        while(k-->0){
            deque.pollLast();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}
