package ght.myAnswer.weeklycontest._01_31;

import java.util.*;

public class Code {
    /**
     * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
     * <p>
     * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
     * <p>
     * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
     * <p>
     * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
     * <p>
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        int[] res = new int[n];
        Map<Integer,Set<Integer>> map =new HashMap<>();
        for(int[] a:adjacentPairs){
            int u = a[0], v = a[1];
            if(!map.containsKey(u)) map.put(u, new HashSet<>());
            if(!map.containsKey(v)) map.put(v, new HashSet<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        LinkedList<Integer> tmp = new LinkedList<>();
        int s=adjacentPairs[0][0],e=adjacentPairs[0][1];
        tmp.add(s);
        tmp.add(e);
        map.get(s).remove(e);
        map.get(e).remove(s);

        while(!map.get(s).isEmpty()){
            Integer u=map.get(s).iterator().next();
            tmp.addFirst(u);
            map.get(s).remove(u);
            map.get(u).remove(s);
            s = u;
        }
        while(!map.get(e).isEmpty()){
            Integer u=map.get(e).iterator().next();
            tmp.addLast(u);
            map.get(e).remove(u);
            map.get(u).remove(e);
            e = u;
        }
        int i=0;
        for(int num:tmp){
            res[i++]=num;
        }
        return res;
    }
}
