import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String,Integer> map=new HashMap<>();
        for(String s:participant){
            map.merge(s,1,Integer::sum);
        }
        for(String s:completion){
            map.merge(s,-1,Integer::sum);
        }
        String answer="";
        for(String s:map.keySet()){
            if(map.get(s)!=0){
                answer=s;
                break;
            }
        }
        return answer;
    }
}