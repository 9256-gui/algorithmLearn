package ght.myAnswer.weeklycontest._01_23;

import java.util.HashSet;
import java.util.Set;

public class Code_medium {
    /**
     * 在一个由m个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
     * <p>
     * 给你一个整数n，数组languages和数组friendships，它们的含义如下：
     * <p>
     * 总共有n种语言，编号从1 到n。
     * languages[i]是第 i位用户掌握的语言集合。
     * friendships[i] = [ui, vi]表示ui 和vi为好友关系。
     * 你可以选择 一门语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少需要教会多少名用户。
     * 请注意，好友关系没有传递性，也就是说如果x 和y是好友，且y和z是好友，x 和z不一定是好友。
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int[] arr = new int[n + 1];
        int m = languages.length;//用户数
        Set<Integer> set = new HashSet<>();
        for (int[] friendship : friendships) {
            int person1 = friendship[0] - 1;
            int person2 = friendship[1] - 1;
            if (!haveSameLanguage(person1, person2, languages)) {
                set.add(person1);
                set.add(person2);
            }
        }
        for (int person : set) {
            for (int lang : languages[person]) {
                arr[lang]++;
            }
        }
        int most = 0;
        for (int i = 1; i <= n; i++) {
            most = Math.max(most, arr[i]);//统计set中使用人数最多的语言
        }
        return set.size() - most;
    }
    private boolean haveSameLanguage(int person1, int person2, int[][] languages) {
        for (int x : languages[person1]) {
            for (int y : languages[person2]) {
                if (x == y) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 给你一个整数数组perm，它是前n个正整数的排列，且n是个 奇数。
     * 它被加密成另一个长度为 n - 1的整数数组encoded，满足encoded[i] = perm[i] XOR perm[i + 1]。比方说，如果perm = [1,3,2]，那么encoded = [2,1]。
     * 给你encoded数组，请你返回原始数组perm。题目保证答案存在且唯一。
     */
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) sum ^= i;
        for (int i = n - 2; i >= 0; i -= 2) sum ^= encoded[i];
        int[] res = new int[n];
        res[0] = sum;
        for (int i = 0; i < n - 1; i++) res[i + 1] = res[i] ^ encoded[i];
        return res;
    }
}
