package ght.myAnswer;

import java.util.*;

public class Code_381 {
    static class RandomizedCollection {
        Map<Integer, Set<Integer>> map;
        List<Integer> nums;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            map = new HashMap<>();
            nums = new ArrayList<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            nums.add(val);
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(nums.size() - 1);
            map.put(val, set);
            return set.size() == 1;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Iterator<Integer> it = map.get(val).iterator();
            int i = it.next();
            int lastNum = nums.get(nums.size() - 1);
            nums.set(i, lastNum);
            map.get(val).remove(i);
            map.get(lastNum).remove(nums.size() - 1);
            if (i < nums.size() - 1) {
                map.get(lastNum).add(i);
            }
            if (map.get(val).size() == 0) {
                map.remove(val);
            }
            nums.remove(nums.size() - 1);
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }
    class RandomizedSet {
        Map<Integer,Integer>keyIndexMap;
        List<Integer> nums;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            keyIndexMap=new HashMap<>();
            nums=new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(!keyIndexMap.containsKey(val)){
                nums.add(val);
                keyIndexMap.put(val,nums.size()-1);
                return true;
            }
            return false;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!keyIndexMap.containsKey(val)){
                return false;
            }
            int lastNum = nums.get(nums.size()-1);
            nums.set(keyIndexMap.get(val),lastNum);
            nums.remove(nums.size()-1);
            keyIndexMap.put(lastNum,keyIndexMap.get(val));
            keyIndexMap.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }
    public static void main(String[] args) {
//        RandomizedCollection randomizedCollection= new RandomizedCollection();
//        randomizedCollection.insert(1);
//        randomizedCollection.insert(1);
//        randomizedCollection.insert(2);
//        randomizedCollection.insert(2);
//        randomizedCollection.insert(2);
//        randomizedCollection.remove(1);
//        randomizedCollection.remove(1);
//        randomizedCollection.remove(2);
//        randomizedCollection.insert(1);
//        randomizedCollection.remove(2);
//
//        System.out.println(randomizedCollection.getRandom());
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int i= stack.remove(1);
        System.out.println(i);
    }
}
