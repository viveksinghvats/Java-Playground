package DSAQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Mbouquets {

    public static void main(String[] args) {
        Mbouquets mbouquet = new Mbouquets();
        // System.out.println(mbouquet.minDays(new int[] { 1, 10, 3, 10, 2 }, 3, 1));
        System.out.println(mbouquet.canFinish(2, new int[][]{{1,0}}));
    }

    private int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < (m * k))
            return -1;
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < bloomDay.length; i++) {
            max = Math.max(bloomDay[i], max);
            min = Math.min(bloomDay[i], min);
        }
        int res = Integer.MAX_VALUE;
        while (max <= min) {
            int mid = min + (max - min) / 2;
            if (helper(bloomDay, m, k, mid)) {
                res = Math.min(res, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return res;
    }

    private boolean helper(int[] bloomDay, int m, int k, int days) {
        int count = 0;
        int each = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= days) {
                each++;
                if (each == k) {
                    count++;
                    each = 0;
                }
            } else {
                each = 0;
            }
        }
        return count >= m;
    }

    public boolean canFinish(int nums, int[][] prerequisites) {
        Stack<Integer> stack = new Stack<>();
        int[] indegree = new int[nums];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            List<Integer> nebhiour = map.getOrDefault(prerequisites[i][1], new ArrayList<>());
            nebhiour.add(prerequisites[i][0]);
            map.put(prerequisites[i][1], nebhiour);
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                stack.push(i);
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            count++;
            for (int i = 0; i < map.get(node).size(); i++) {
              indegree[map.get(node).get(i)]--;
              if(indegree[map.get(node).get(i)] == 0){
                stack.push(map.get(node).get(i));
              }
            }
        }
        return count == nums;
    }

}
