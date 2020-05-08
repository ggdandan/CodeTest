import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
        next = null;
    }
}


class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.key = key;
    }
}


public class Solution {

    int gN;

    void helper(ListNode head) {
        if (head == null || head.next == null) return;
        helper(head.next);
        gN--;
        if (gN == 0) {
            ListNode n = head.next;
            head.next = n.next;
            n.next = null;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Write your solution here
        gN = n;
        helper(head);
        return gN == 1 ? head.next : head;
    }

    public ListNode cycleNode(ListNode head) {
        // write your solution here
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    void dfs(TreeNode root, ArrayList<String> output, String path) {
        if(root.left == null && root.right == null){
            path += root.key;
            output.add(path);
            return;
        }
        if(root.left != null) {
            dfs(root.left, output, path + root.key + "->");
        }
        if(root.right != null) {
            dfs(root.right, output, path + root.key + "->");
        }
    }

    public String[] binaryTreePaths(TreeNode root) {
        if(root == null) return new String[0];
        // Write your solution here
        ArrayList<String> arr = new ArrayList<String>();
        dfs(root, arr, new String());
        String[] ret = new String[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            ret[i] = arr.get(i);
        }
        return ret;
    }
    // int[2] 0 -> sub max, 1->longest path
    public int[] diameterDfs(TreeNode root){
        if(root == null) return new int[]{0, 0};
        int[] l = diameterDfs(root.left);
        int[] r = diameterDfs(root.right);
        int dia = l[1] + r[1] + 1;
        int[] ret = new int[2];
        ret[0] = Math.max(Math.max(l[0], r[0]), dia);
        ret[1] = Math.max(l[1], r[1]) + 1;
        return ret;
    }


    TreeNode cur;
    TreeNode head;
    void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        if(head == null){
            head = root;
            cur = head;
        }else{
            root.left = cur;
            cur.right = root;
        }
        inOrder(root.right);
    }

    public TreeNode toDoubleLinkedList(TreeNode root) {
        // Write your solution here.
        cur = null;
        head = null;
        inOrder(root);
        head.left = cur;
        cur.right = head;
        return head;
    }
    void subSets(String set, List<String> ret, int k, StringBuilder sb){
        if(k == set.length()){
            ret.add(sb.toString());
            return;
        }
        subSets(set, ret, k+1, sb.append(set.charAt(k)));
        sb.setLength(sb.length() - 1);
        subSets(set, ret, k+1, sb);
    }
    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> ret = new ArrayList<String>();
        subSets(set, ret, 0, new StringBuilder());
        return null;
    }
}


public class ListNodeRelated {
    public static void main(String args[]) {

    }
}
