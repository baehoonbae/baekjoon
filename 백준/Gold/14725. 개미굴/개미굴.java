import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Trie t = new Trie();

    static class TrieNode {
        TreeMap<String, TrieNode> children = new TreeMap<>();
        boolean isEndOfWord = false;
        String value;

        TrieNode(String value) {
            this.value = value;
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode("");
        }

        void insert(String[] words) {
            TrieNode cur = root;
            for (String word : words) {
                cur = cur.children.computeIfAbsent(word, a -> new TrieNode(word));
            }
            cur.isEndOfWord = true;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String[] words = new String[num];
            for (int j = 0; j < num; j++) {
                words[j] = st.nextToken();
            }
            t.insert(words);
        }
        Map<String, TrieNode> nodeMap = t.root.children;
        for (String s : nodeMap.keySet()) {
            print(0, nodeMap.get(s));
        }
        System.out.print(sb);
    }

    private static void print(int depth, TrieNode node) {
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(node.value).append("\n");
        if (node.isEndOfWord) {
            return;
        }
        for (String s : node.children.keySet()) {
            print(depth + 1, node.children.get(s));
        }
    }
}