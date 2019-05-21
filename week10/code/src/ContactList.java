import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ContactList {
    //static int c;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            int numOfContacts = scanner.nextInt();
            Trie trie = new Trie();
            for (int contact = 0; contact < numOfContacts; contact++) {
                String name = scanner.next();
                trie.insert(name);
            }

            int c = trie.traverse(trie.root);
            System.out.format("Case #%d: %d\n", testcase, c);

        }

    }
}


class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }
}

class Trie { //Adapted from https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            //set leaf node
            if (i == word.length() - 1)
                t.isLeaf = true;
        }
    }



    public static int traverse(TrieNode root) {
        Queue queue = new LinkedList<TrieNode>();
        queue.add(root);
        int res = 0;
        while(!queue.isEmpty()){
            //System.out.println("1");
            TrieNode n = (TrieNode)((LinkedList) queue).pop();
            //System.out.println(n.c);
            //if (n.isLeaf && n.children.isEmpty()) break;
            if (n.isLeaf && !n.children.isEmpty()) {
                res++;
                for(TrieNode child : n.children.values()){
                    // queue.add(n.children.values());
                    queue.add(child);
                }

            } else {
                for(TrieNode child : n.children.values()){
                    // queue.add(n.children.values());
                    queue.add(child);
                }
            }

        }
        return res;
    }

}
