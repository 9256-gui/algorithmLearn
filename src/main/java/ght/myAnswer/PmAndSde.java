package ght.myAnswer;

import lombok.AllArgsConstructor;

import java.util.*;

public class PmAndSde {
    @AllArgsConstructor
    static class Program {
        int index;
        int pm;
        int start;
        int rank;
        int cost;
    }

    static class BigQueue {
        private List<PriorityQueue<Program>> pmQueues;
        private Program[] sdeHeap;
        private int[] indexes;
        private int heapSize;

        public BigQueue(int pmSize) {
            pmQueues = new ArrayList<>();
            heapSize = 0;
            indexes = new int[pmSize + 1];
            Arrays.fill(indexes, -1);
            sdeHeap = new Program[pmSize];
            for (int i = 0; i <= pmSize; i++) {
                pmQueues.add(new PriorityQueue<>((o1, o2) -> {
                    if (o1.rank != o2.rank) return o1.rank - o2.rank;
                    else if (o1.cost != o2.cost) return o1.cost - o2.cost;
                    else return o1.start - o2.start;
                }));
            }
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void add(Program program) {
            PriorityQueue<Program> pmQueue = pmQueues.get(program.pm);
            pmQueue.add(program);
            Program head = pmQueue.peek();
            int index = indexes[head.pm];
            if (index == -1) {
                sdeHeap[heapSize] = head;
                indexes[head.pm] = heapSize;
                heapInsert(heapSize++);
            } else {
                sdeHeap[index] = head;
                heapInsert(index);
                heapify(index);
            }
        }

        public Program pop() {
            Program head = sdeHeap[0];
            PriorityQueue<Program> pmQueue = pmQueues.get(head.pm);
            pmQueue.poll();
            if (pmQueue.isEmpty()) {
                swap(0, --heapSize);
                indexes[head.pm] = -1;
            } else {
                sdeHeap[0] = pmQueue.peek();
            }
            heapify(0);
            return head;
        }

        private void heapInsert(int index) {
            while (index > 0 && sedRule(sdeHeap[index], sdeHeap[(index - 1) >>> 1]) < 0) {
                swap(index, (index - 1) >>> 1);
                index = (index - 1) >>> 1;
            }
        }

        private void heapify(int index) {
            int left = (index << 1) + 1;
            while (left < heapSize) {
                int min = left + 1 < heapSize && sedRule(sdeHeap[left + 1], sdeHeap[left]) < 0 ? left + 1 : left;
                min = sedRule(sdeHeap[index], sdeHeap[min]) < 0 ? index : min;
                if (index == min) break;
                swap(index, min);
                index = min;
                left = (index << 1) + 1;
            }
        }

        private void swap(int index1, int index2) {
            Program p1 = sdeHeap[index1], p2 = sdeHeap[index2];
            sdeHeap[index1] = p2;
            sdeHeap[index2] = p1;
            indexes[p1.pm] = p2.pm;
            indexes[p2.pm] = p1.pm;
        }

        private int sedRule(Program p1, Program p2) {
            if (p1.cost != p2.cost) return p1.cost - p2.cost;
            else return p1.pm - p2.pm;
        }
    }

    public static int[] workFinish(int pmNum, int sdeNum, int[][] programs) {
        PriorityQueue<Program> startQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        for (int i = 0; i < programs.length; i++) {
            Program program = new Program(i, programs[i][0], programs[i][1], programs[i][2], programs[i][3]);
            startQueue.add(program);
        }
        PriorityQueue<Integer> wakeQueue = new PriorityQueue<>();
        for (int i = 0; i < sdeNum; i++) {
            wakeQueue.add(1);
        }
        BigQueue bigQueue = new BigQueue(pmNum);
        int finish = 0;
        int[] ans = new int[programs.length];
        while (finish != programs.length) {
            int workTime = wakeQueue.poll();
            while (!startQueue.isEmpty()) {
                if (startQueue.peek().start > workTime) {
                    break;
                }
                bigQueue.add(startQueue.poll());
            }
            if (bigQueue.isEmpty()) {
                wakeQueue.add(startQueue.peek().start);
            } else {
                Program program = bigQueue.pop();
                ans[program.index] = workTime + program.cost;
                wakeQueue.add(workTime + program.cost);
                finish++;
            }
        }

        return ans;
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.println(j);
        }
    }

    public static void main(String[] args) {
        int pms = 2;
        int sde = 2;
        int[][] programs = {{1, 1, 1, 2}, {1, 2, 1, 1}, {1, 3, 2, 2}, {2, 1, 1, 2}, {2, 3, 5, 5}};
        int[] ans = workFinish(pms, sde, programs);
        printArray(ans);
    }
}
