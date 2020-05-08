import java.util.*;
import javafx.util.Pair;
public class TrieTree {
    static final int SIZE = 26;
    private TrieNode root;
    public TrieTree()
    {
        root = new TrieNode();
    }
    private class TrieNode
    {
        TrieNode[] children = new TrieNode[26];
        boolean isAWord;
        TrieNode()
        {
            isAWord = false;
            for(int i = 0; i < children.length; i++)
            {
                children[i] = null;
            }
        }
    }

    public void insert(String key)
    {
        TrieNode cur = root;
        for(int i = 0 ; i < key.length(); i++)
        {
            if(cur.children[key.charAt(i) - 'a'] == null)
            {
                cur.children[key.charAt(i) - 'a'] = new TrieNode();
            }
            cur = cur.children[key.charAt(i) - 'a'];
        }
        cur.isAWord = true;
    }

    public boolean search(String key)
    {
        TrieNode cur = root;
        for(int i = 0; i < key.length(); i++)
        {
            if(cur.children[key.charAt(i) - 'a'] == null)
                return false;
            cur = cur.children[key.charAt(i) - 'a'];
        }
        return cur.isAWord;
    }

    private void findAllMatchByDFS(TrieNode cur, String target,
                                   int index, StringBuilder curPath, List<String> result) {

        if(index == target.length())
        {
            if(cur.isAWord) {
                result.add(curPath.toString());
            }
            return;
        }
        if(target.charAt(index) != '?' && cur.children[target.charAt(index)] != null){
            return;
        }
        for(int i = 0; i < 26; i++)
        {
            if(cur.children[i] != null)
            {
                findAllMatchByDFS(cur.children[i], target, index+1 , curPath.append(target.charAt(index)), result);
            }
        }
    }
    public List<String> findALlMatchWildCard(String target)
    {
        List<String> results = new ArrayList<String>();
        findAllMatchByDFS(root, target, 0, new StringBuilder(), results);
        return results;
    }

    public static void main(String args[])
    {
        /*
        TrieTree tr = new TrieTree();
        tr.insert("abc");
        tr.insert("abcd");
        tr.insert("abcf");
        System.out.println(tr.search("abcd"));
        */
        HashSet<Pair<Integer,Integer>> hs = new HashSet<Pair<Integer, Integer>>();
        hs.add(new Pair<Integer, Integer>(1,1));
        hs.add(new Pair<Integer, Integer>(1,1));
        System.out.println(hs.size());
    }
}
