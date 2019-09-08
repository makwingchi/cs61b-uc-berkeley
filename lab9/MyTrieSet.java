import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        private char c;
        private boolean isKey;
        private HashMap<Character, Node> map;

        private Node(Character letter, boolean ik) {
            c = letter;
            isKey = ik;
            map = new HashMap<>();
        }
    }


    public MyTrieSet() {
        root = new Node('!', false);
    }

    @Override
    public void clear() {
        root.map.clear();
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i += 1) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();

        if (prefix == null || prefix.length() < 1) {
            return result;
        }
        Node curr = root;
        for (int i = 0, N = prefix.length(); i < N; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return result;
            }
            curr = curr.map.get(c);
        }

        return keysWithPrefixHelper(prefix, curr, result);
    }

    private List<String> keysWithPrefixHelper(String s, Node n, List<String> result) {
        for (char thisKey : n.map.keySet()) {
            Node thisNode = n.map.get(thisKey);
            String newStr = s + thisNode.c;
            if (thisNode.isKey) {
                result.add(newStr);
            }
            keysWithPrefixHelper(newStr, thisNode, result);
        }
        return result;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("Method longestPrefixOf is not available!");
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hi");
        t.clear();
        t.add("hello");
        t.add("help");
        t.add("zebra");

        System.out.println(t.contains("hello"));
        System.out.println(t.contains("hi"));
        System.out.println(t.keysWithPrefix("he"));
        System.out.println(t.keysWithPrefix("z"));
        System.out.println(t.keysWithPrefix("hi"));
    }
}
