import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    static class TrieNode {
        Map<Character, TrieNode> children;

        TrieNode(){
            children=new HashMap<>();
        }
    }

    static class Trie {
        TrieNode root;

        Trie(){
            root=new TrieNode();
        }
    }

    static final int[] dy={-1,1,0,0,-1,-1,1,1};
    static final int[] dx={0,0,-1,1,-1,1,-1,1};
    static StringBuilder sb=new StringBuilder();
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static Trie t;
    static char[][] arr;
    static boolean[][] visited;
    static Set<String> found;
    static int score;
    static String maxString="";
    static int count;

    public static void main(String[] args)throws IOException {
        t=new Trie();
        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            String s=br.readLine();
            TrieNode temp=t.root;
            for(int j=0;j<s.length();j++){
                temp=temp.children.computeIfAbsent(s.charAt(j),a->new TrieNode());
            }
            temp.children.put('*',new TrieNode());
        }

        br.readLine();
        int b=Integer.parseInt(br.readLine());
        for(int i=0;i<b;i++){
            score=0;
            maxString="";
            count=0;
            arr=new char[4][4];
            found=new HashSet<>();
            for(int j=0;j<4;j++){
                arr[j]=br.readLine().toCharArray();
            }
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++){
                    if(!t.root.children.containsKey(arr[j][k]))continue;

                    visited=new boolean[4][4];
                    visited[j][k]=true;
                    play(j,k,arr[j][k]+"",t.root.children.get(arr[j][k]));
                }
            }
            br.readLine();
            sb.append(score).append(" ").append(maxString).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void play(int y,int x,String s,TrieNode temp){
        if(temp.children.containsKey('*')&&found.add(s)){
            score+=cal(s);
            maxString=compare(maxString, s);
            count++;
        }
        for(int i=0;i<8;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];

            if(ny<0||nx<0||ny>=4||nx>=4)continue;
            if(visited[ny][nx])continue;
            if(!temp.children.containsKey(arr[ny][nx]))continue;

            visited[ny][nx]=true;
            play(ny,nx,s+arr[ny][nx],temp.children.get(arr[ny][nx]));
            visited[ny][nx]=false;
        }
    }

    private static int cal(String s) {
        int size=s.length();
        if(size<3)return 0;
        else if(size<5)return 1;
        else if(size<6)return 2;
        else if(size<7)return 3;
        else if(size<8)return 5;
        return 11;
    }

    private static String compare(String s1, String s2) {
        if(s1.length()<s2.length())return s2;
        else if(s1.length()>s2.length())return s1;
        else {
            int res=s1.compareTo(s2);
            if(res<0)return s1;
            else return s2;
        }
    }
}