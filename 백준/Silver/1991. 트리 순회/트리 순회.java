import java.io.*;
import java.util.*;

class Main {
	static class Node {
		char value;
		Node left;
		Node right;

		Node(char value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static Node[] tree;

	public static void preorder(Node node) throws IOException {
		if (node == null)
			return;
		bw.write(node.value);
		preorder(node.left);
		preorder(node.right);
	}

	public static void inorder(Node node) throws IOException {
		if (node == null)
			return;
		inorder(node.left);
		bw.write(node.value);
		inorder(node.right);
	}

	public static void postorder(Node node) throws IOException {
		if (node == null)
			return;
		postorder(node.left);
		postorder(node.right);
		bw.write(node.value);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		tree = new Node[n + 1];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			if (tree[parent - 'A'] == null) {
				tree[parent - 'A'] = new Node(parent);
			}
			if (left != '.') {
				tree[left - 'A'] = new Node(left);
				tree[parent - 'A'].left = tree[left - 'A'];
			}
			if (right != '.') {
				tree[right - 'A'] = new Node(right);
				tree[parent - 'A'].right = tree[right - 'A'];
			}
		}
		preorder(tree[0]);
		bw.write("\n");
		inorder(tree[0]);
		bw.write("\n");
		postorder(tree[0]);
		bw.write("\n");

		bw.flush();
		bw.close();
		br.close();
	}
}