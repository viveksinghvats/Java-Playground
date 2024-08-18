package DSAQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RussainDoll {
    public static void main(String[] args) {
        int[][] envelopes = new int[][]{{3,1},{2,3},{2,4},{4,2},{2,2}};
        RussainDoll doll = new RussainDoll();
        doll.maxEnvelopes(envelopes);
    }

    private int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] != b[0]){
                return Integer.compare(a[0], b[0]);
            }else{
                return Integer.compare(b[1], a[1]);
            }
        });


        List<Integer> ans = new ArrayList<>();
        ans.add(envelopes[0][1]);

        for(int i = 1; i < envelopes.length; i++){
            if(ans.get(ans.size() - 1) < envelopes[i][1]){
                ans.add(envelopes[i][1]);
            }else{
                int index = Collections.binarySearch(ans, envelopes[i][1]);
                if(index < 0){
                    index = -(index + 1);
                }
                ans.set(index, envelopes[i][i]);
            }
        }
        return ans.size();
    }
}
