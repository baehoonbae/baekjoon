import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class TrieNode{
        Map<Character,TrieNode> children;

        TrieNode(){
            children=new HashMap<>();
        }
    }

    static class Trie{
        TrieNode root;

        Trie(){
            root=new TrieNode();
        }
    }

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Trie t=new Trie();

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++){
            String s=br.readLine();
            TrieNode temp=t.root;
            for(int j=0;j<s.length();j++){
                temp=temp.children.computeIfAbsent(s.charAt(j),a->new TrieNode());
            }
        }
        int count=0;
        for(int i=0;i<m;i++){
            String s=br.readLine();
            boolean flag=true;
            TrieNode temp=t.root;
            for(int j=0;j<s.length();j++){
                char key=s.charAt(j);
                if(!temp.children.containsKey(key)){
                    flag=false;
                    break;
                }else{
                    temp=temp.children.get(key);
                }
            }
            if(flag)count++;
        }
        System.out.println(count);
    }
}