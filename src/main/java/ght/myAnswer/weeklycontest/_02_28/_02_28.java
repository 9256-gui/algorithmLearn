package ght.myAnswer.weeklycontest._02_28;

import java.util.List;

public class _02_28 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int res = 0;
        for (List<String> item : items) {
            if (ruleKey.equals("type") && item.get(0).equals(ruleValue)) {
                res++;
            } else if (ruleKey.equals("color") && item.get(1).equals(ruleValue)) {
                res++;
            } else if (ruleKey.equals("name") && item.get(2).equals(ruleValue)) {
                res++;
            }
        }
        return res;
    }
    int ans = Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for(int i=0;i<baseCosts.length;i++){
            int sum =baseCosts[i];
            if(sum==target)return target;
            dfs(toppingCosts,sum,target,0);
            if(ans==target)return target;
        }
        return ans;
    }
    public void dfs(int[] toppingCosts, int sum, int target, int index) {
        if (Math.abs(sum - target) < Math.abs(ans - target) || (Math.abs(sum - target) == Math.abs(ans - target) && sum < ans)) {
            ans = sum;
        }
        if(sum>=target||index==toppingCosts.length)return;
        dfs(toppingCosts,sum,target,index+1);
        dfs(toppingCosts,sum+toppingCosts[index],target,index+1);
        dfs(toppingCosts,sum+toppingCosts[index]*2,target,index+1);
    }
}
