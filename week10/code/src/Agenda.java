import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
public class Agenda {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Ainput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; testcase++){
            int numOfContacts = scanner.nextInt();
            Trie trie = new Trie(new Node(scanner.next()));

            for(int contact=1 ; contact<numOfContacts ; contact++){
                String name = scanner.next();
                trie.insert(name);
            }
            System.out.println(trie);
        }

    }
}

class Node{
    boolean end;
    Map<Character, Node> next;
    String tag;

    public Node(String tag){
        this.tag = tag;
        this.next = new HashMap<>();
        this.end = true;
    }
    public Node(){
    }

    public boolean isEnd(){
        return end;
    }

    public void setEnd(boolean b){
        this.end = b;
    }

    public Map<Character, Node> getNext(){
        return next;
    }
}


class Trie{
    Node root;
    public Trie(Node root){
        this.root = root;

    }
    public void insert(String word) {
        Node current = root;

        for (int i = 0; i < word.length(); i++) {
            current = current.getNext()
                    .computeIfAbsent(word.charAt(i), c -> new Node());
        }
        current.setEnd(true);
    }

    public boolean find(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Node node = current.getNext().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEnd();
    }

    public boolean delete(Node current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEnd()) {
                return false;
            }
            current.setEnd(false);
            return current.getNext().isEmpty();

        }
        char ch = word.charAt(index);
        Node node = current.getNext().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEnd();

        if (shouldDeleteCurrentNode) {
            current.getNext().remove(ch);
            return current.getNext().isEmpty();
        }
        return false;
    }
    //https://www.baeldung.com/trie-java
}*/
