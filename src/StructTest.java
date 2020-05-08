import javafx.collections.ArrayChangeListener;
import javafx.util.Pair;
import java.util.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

class TestA{
    TestA(StructTest st){
        this.st = st;
    }
    public void fn(){
        st.number = 5;
    }
    public StructTest st;
}

public class StructTest {
    private final String name;
    StructTest(){
        name = "abc";
    }

    @Override
    public String toString() {
        return "StructTest{" +
                "name='" + name + '\'' +
                '}';
    }
    static int number = 4;

    public void fun(){
        int c = number;
        System.out.println(c);
    }
    /*
    void dfsRandomGetPath(int[][] mat, ArrayList<Pair<Integer,Integer>> output,
                          ArrayList<Pair<Integer,Integer>> path, int i, int j) {
        if(i == mat.)
    }


    ArrayList<Pair<Integer,Integer>> getPath(int[][] mat){

    }
    */

    static void insertionSort(int[] arr){
        if(arr == null) return ;
        for(int i = 0, k = -1; i < arr.length; i++, k++){
            int j = k;
            int val = arr[i];
            for(; j >= 0; j--){
                if(val < arr[j]){
                    arr[j + 1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = val;
        }
    }

    static void selectionSort(int[] arr){
        if(arr == null || arr.length <= 1) {
            return;
        }
        for(int i = 0; i < arr.length; i++){
            int mi = i;
            for(int j = i; j < arr.length; j++){
                if(arr[j] < arr[mi]){
                    mi = j;
                }
            }
            int temp = arr[mi];
            arr[mi] = arr[i];
            arr[i] = temp;
        }
    }

    static void merge(int[] arr, int s1, int e1, int s2, int e2, int start, int[] output){
        int k = start;
        while(s1 <= e1 && s2 <= e2){
            if(arr[s1] < arr[s2]){
                output[k] = arr[s1];
                s1++;
            }else {
                output[k] = arr[s2];
                s2++;
            }
            k++;
        }
        while(s1 <= e1){
            output[k] = arr[s1];
            s1++;
            k++;
        }
        while(s2 <= e2){
            output[k] = arr[s2];
            s2++;
            k++;
        }
    }

    static void mergeSortHelper(int[] arr, int[] output, int start, int end){
        if(start >= end) return;
        int mid = start + (end - start) / 2;
        mergeSortHelper(arr, output, start, mid);
        mergeSortHelper(arr, output, mid+1, end);
        merge(arr, start, mid, mid+1, end, start, output);
    }

    static int[] mergeSort(int[] arr){
        if(arr == null || arr.length == 0) return null;
        int[] output = new int[arr.length];
        mergeSortHelper(arr, output, 0, arr.length - 1);
        return output;
    }

    static ArrayList<Integer> getKClosest(int T, int K, int[] arr){
        int l = 0, r = arr.length;
        // lower bound
        while(l < r){
            int mid = l + (r - l) / 2;
            if(T <= arr[mid]){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        int i = l - 1;
        int j = l;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while(i >= 0 && j < arr.length && ret.size() < K){
            if(Math.abs(arr[i] - T) < Math.abs(arr[j] - T)){
                ret.add(arr[i]);
                i--;
            }else{
                ret.add(arr[j]);
                j++;
            }
        }
        if(ret.size() == K) return ret;
        while(i >= 0 && ret.size() < K) {
            ret.add(arr[i]);
            i--;
        }
        while(j < arr.length && ret.size() < K){
            ret.add(arr[j]);
            j++;
        }
        return ret;
    }

    static String addBinary(String a, String b) {
        // Write your solution here
        StringBuilder c = new StringBuilder();
        int i = a.length();
        int j = b.length();
        int carry = 0;
        int sum = 0;
        while(i >= 0 || j >= 0){
            sum += carry;
            if(i >= 0){
                sum += a.charAt(i) - '0';
            }
            if(j >= 0){
                sum += b.charAt(j) - '0';
            }
            carry = sum / 10;
            sum = sum % 10;
            c.insert(0, sum);
            sum = 0;
        }
        if(carry > 0){
            c.insert(0, 1);
        }
        return c.toString();
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        // Write your solution here
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int v : nums1){
            int count = m.containsKey(v) ? m.get(v) : 0;
            m.put(v, count + 1);
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        for(int v : nums2){
            if(m.containsKey(v) && m.get(v) > 0){
                al.add(v);
                int count = m.get(v) - 1;
                m.put(v, count);
            }
        }
        int[] out = new int[al.size()];
        for(int i = 0; i < al.size(); i++){
            out[i] = al.get(i);
        }
        return out;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> hs1 = new HashSet<Integer>();
        HashSet<Integer> hs2 = new HashSet<Integer>();
        for(int v : nums1){
            hs1.add(v);
        }
        for(int v : nums2){
            hs2.add(v);
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        Iterator<Integer> iter = hs1.iterator();
        while(iter.hasNext()){
            int v = iter.next();
            if(hs2.contains(v)){
                al.add(v);
            }
        }
        int[] out = new int[al.size()];
        for(int i = 0; i < al.size(); i++){
            out[i] = al.get(i);
        }
        return out;
    }
    public static List<List<Integer>> allTriples(int[] array, int target) {
        // Write your solution here
        Arrays.sort(array);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for(int i = array.length - 1; i > 1; i--){
            if(i < array.length - 1 && array[i] == array[i+1]){
                continue;
            }
            int l = 0, r = i - 1;
            while(l < r){
                int val = array[l] + array[r];
                if(val == target - array[i]){
                    List<Integer> can= new ArrayList<Integer>();
                    can.add(array[i]);
                    can.add(array[l]);
                    can.add(array[r]);
                    ret.add(can);
                    int j = l + 1;
                    while(array[j] == array[l] && j < r){
                        j++;
                    }
                    l = j;
                    j = r - 1;
                    while(array[j] == array[r] && j > l){
                        j--;
                    }
                    r = j;
                }else if(val > target - array[i]){
                    r--;
                }else{
                    l++;
                }
            }
        }
        return ret;
    }

    public boolean validPalindromeHelper(String input, int start, int end){
        while(start < end){
            if(input.charAt(start) != input.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean validPalindrome(String input) {
        // Write your solution here
        int start = 0, end = input.length() - 1;
        while(start < end){
            if(input.charAt(start) != input.charAt(end)){
                return validPalindromeHelper(input, start, end - 1) ||
                        validPalindromeHelper(input, start + 1, end);
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isValid(char c){
        if((c >= '0' && c <= '9')
                || (c >= 'a' && c<= 'z')
                || (c >= 'A' && c <= 'Z')){
            return true;
        }
        return false;
    }
    public static boolean valid(String input) {
        // Write your solution here
        int i = 0, j = input.length() - 1;
        while(i < j){
            if(!isValid(input.charAt(i))){
                i++;
                continue;
            }
            if(!isValid(input.charAt(j))){
                j--;
                continue;
            }
            if(i >= j){
                break;
            }
            if(input.charAt(i) != input.charAt(j)){
                System.out.println(i + " " + j);
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

    public static int minSubArrayLen(int num, int[] nums) {
        // Write your solution here
        int j = 0;
        int sum = 0;
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            while(sum > num){
                sum -= nums[j];
                j++;
            }
            if(sum == num){
                ret = Math.min(i - j + 1, ret);
            }
        }
        return ret;
    }

    public int maxSubArrayLen(int[] nums, int k) {
        // Write your solution here
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        int sum = 0;
        m.put(0, -1);
        int preSum = 0;
        int ret = 0;
        for(int i = 0; i < nums.length; i++){
            preSum += nums[i];
            if(m.containsKey(preSum - k)){
                ret = Math.max(ret, i - m.get(preSum - k));
            }
            if(!m.containsKey(preSum)){
                m.put(preSum, i);
            }
        }
        return ret;
    }

    public boolean isValid(String input) {
        // Write your solution here
        Stack<Character> st = new Stack<Character>();
        char[] ca = input.toCharArray();
        for(char v : ca){
            if(v == '(' || v == '[' || v == '{'){
                st.add(v);
            }else{
                if(st.size() == 0) return false;
                if(v == ')' && st.peek() == '('
                    || v == ']' && st.peek() == '['
                    || v == '}' && st.peek() == '{'){
                    st.pop();
                }else{
                    return false;
                }
            }
        }
        return st.size() == 0;
    }

    public static void main(String args[]){
        int[] nums = new int[]{1,2,3,4,5};
        minSubArrayLen(11, nums);
    }
}
