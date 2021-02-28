package ght.myAnswer.weeklycontest._01_24;

import java.util.Arrays;

public class Code_medium {
    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        int index = time.indexOf("?");
        for (int i = 0; i < 3; i++) {
            chars = f(chars, index);
            index = String.valueOf(chars).indexOf("?");
        }

        return String.valueOf(f(chars, index));
    }

    public char[] f(char[] time, int index) {
        if (index == 0) {
            if (time[1] == '?' || time[1] <= '4') {
                time[0] = 2;
            } else {
                time[0] = 1;
            }
        } else if (index == 1) {
            if (time[0] == 2) {
                time[1] = 3;
            } else {
                time[1] = 9;
            }
        } else if (index == 3) {
            time[3] = 5;
        } else if (index == 4) {
            time[4] = 9;
        }
        return time;
    }

    /**
     * 给你两个字符串 a 和 b ，二者均由小写字母组成。一步操作中，你可以将 a 或 b 中的 任一字符 改变为 任一小写字母 。
     * <p>
     * 操作的最终目标是满足下列三个条件 之一 ：
     * <p>
     * a 中的 每个字母 在字母表中 严格小于 b 中的 每个字母 。
     * b 中的 每个字母 在字母表中 严格小于 a 中的 每个字母 。
     * a 和 b 都 由 同一个 字母组成。
     * 返回达成目标所需的 最少 操作数。
     */
    public int minCharacters(String a, String b) {
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int[] ca = new int[26];
        int[] cb = new int[26];
        for (char c : str1) {
            ca[c - 'a']++;
        }
        for (char c : str2) {
            cb[c - 'a']++;
        }
        int res1 = f1(ca, cb);
        int res2 = f1(cb, ca);
        int res3 = f3(str1, str2);
        return Math.min(Math.min(res1, res2), res3);
    }

    public int f1(int[] a, int[] b) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 25; i++) {
            int cur = 0;
            for (int j = 25; j > i; j--) cur += a[j];
            for (int j = 0; j <= i; j++) cur += b[j];
            ans = Math.min(cur, ans);
        }
        return ans;
    }

    public int f3(char[] a, char[] b) {
        int[] help = new int[26];
        int max = 0;
        for (char i : a) {
            help[i - 'a']++;
            max = Math.max(help[i - 'a'], max);
        }
        for (char i : b) {
            help[i - 'a']++;
            max = Math.max(help[i - 'a'], max);
        }
        return a.length + b.length - max;
    }

    /**
     * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为m x n 由非负整数组成。
     * <p>
     * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
     * <p>
     * 请你找出matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
     */

}