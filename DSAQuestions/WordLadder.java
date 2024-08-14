package DSAQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class WordLadder {
    
    public static void main(String[] args) {
        shortestDistanceAfterQueries(8, new int[][]{{0,4},{3,6},{2,5},{0,3}});
    }
    
     public static int findTheCity(int n, int[][] edges, int dt) {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] arr = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            arr[edges[i][0]][edges[i][1]] = edges[i][2];
            arr[edges[i][1]][edges[i][0]] = edges[i][2];
        }

        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            visited.add(i);
            queue.add(new Node(i, 0));
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    Node node = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (arr[node.index][j] != 0 && node.distance + arr[node.index][j] <= dt && !visited.contains(j)) {
                          queue.add(new Node(j, node.distance + arr[node.index][j]));
                          visited.add(j);
                          list.add(j);
                        }
                    }
                    size--;
                }
            }
            ans.add(list);
        }
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
          if(ans.get(i).size() <= min){
            min = i;
          }
        }
        return min;
    }
     
    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            arr[i][i + 1] = 1;
        }
        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; k++) {
            arr[queries[k][0]][queries[k][1]] = 1;
            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, Integer> visited = new HashMap<>();
            visited.put(0, 0);
            for (int i = 0; i < n; i++) {
                if (arr[0][i] != 0) {
                    queue.add(i);
                    visited.put(i, 1);
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        while (size > 0) {
                            int node = queue.poll();
                            int distance = visited.get(node);
                            for (int j = 0; j < n; j++) {
                              if(arr[node][j] != 0 && (!visited.containsKey(j) || visited.get(j) > (distance + 1))){
                                    queue.add(j);
                                visited.put(j, distance + 1);
                              }
                            }
                            size--;
                        }
                    }
                }
            }
            ans[k] = visited.get(n - 1);
        }
        return ans;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int count = 1;
        a: while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
              String word = queue.poll();
              for(int i = 0; i < word.length(); i++){
                char[] warr = word.toCharArray();
                for(char c = 'a'; c <= 'z'; c++){
                 warr[i] = c;
                 if(warr.toString().equals(endWord)){
                    break a;
                 }
                 if(words.contains(warr.toString())){
                    queue.add(warr.toString());
                 }
                }
              }
              size--;
            }
            count++;
        }
        return count;
    }
}

class Node {
    int index;
    int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
}
