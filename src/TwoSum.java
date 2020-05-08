import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        int[] a = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if(m.containsKey(target - nums[i])) {
                a[0] = m.get(target - nums[i]);
                a[1] = i;
                break;
            }
            m.put(nums[i], i);
        }

        return a;
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> m = new HashMap<Character, Integer>();
        int ret = 0;
        for(int i = 0, j = 0; i < s.length(); i++)
        {
            if(m.get(s.charAt(i)) > 0) {
                j = m.get(s.charAt(i)) + 1;
                m.put(s.charAt(i), i);
                continue;
            }
            m.put(s.charAt(i), i);
            ret = Math.max(ret, i - j + 1);
        }
        return ret;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int ts = nums1.length + nums2.length;
        int hs = ts / 2;
        int l = 0, r = nums1.length + 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            int x1 = mid == 0 ? Integer.MIN_VALUE : mid - 1;
            int y2 = (hs - mid) == nums2.length ? Integer.MAX_VALUE : hs - mid + 1;
            if(x1 > y2){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        l--;
        int x1 = l == 0 ? Integer.MIN_VALUE : nums1[l - 1];
        int y1 = l == nums1.length ? Integer.MAX_VALUE : nums1[l];
        int x2 = hs - l == 0 ? Integer.MIN_VALUE : nums2[hs - l - 1];
        int y2 = hs - l == nums2.length ? Integer.MAX_VALUE : nums2[hs - l];
        if(ts % 2 == 0)
        {
            return (Math.max(x1, x2) + Math.min(y1, y2)) / 2.0;
        }
        return Math.max(x1,x2) ;
    }

    public String convert(String s, int numRows) {
        StringBuilder []vec = new StringBuilder[numRows];
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while(i < s.length())
        {
            for(int k = 0; k < numRows && i < s.length(); k++){
                vec[k].append(s.charAt(i));
                i++;
            }
            for(int k = numRows - 2; k > 0 && i < s.length(); k++)
            {
                vec[k].append(s.charAt(i));
                i++;
            }
        }
        for(int k = 0; k < numRows; k++)
        {
            ret.append(vec[k]);
        }
        return ret.toString();
    }

    public int myAtoi(String str) {
        int i = 0;
        while(str.charAt(i) == ' ') i++;
        int sign = 1;
        if(str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        int ret = 0;
        for(; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch < '0' || ch > '9')
                break;
            if(ret > Integer.MAX_VALUE || (ret == Integer.MAX_VALUE && ch > '7'))
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            ret = ret * 10 + ch - '0';
        }
        return ret;
    }
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i < p.length()+1; i++)
        {
            if(p.charAt(i-1) == '*'){
                dp[i][0] = dp[i - 2][0];
            }
        }
        for(int i= 1; i < p.length()+1; i++){
            for(int j = 1; j < s.length()+1; j++){
                if(s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '.')
                    dp[i][j] = dp[i-1][j-1];
                else
                {
                    if(p.charAt(i-1) == '*')
                    {
                        dp[i][j] = dp[i-2][j];
                        if(p.charAt(i-2) == s.charAt(j-1) || p.charAt(i-2) == '.'){
                            dp[i][j] |= dp[i][j-1];
                            dp[i][j] |= dp[i-2][j-1];
                        }
                    }
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    public void dfs(List<String> res, String digits, int idx, StringBuilder path,
                    String arr[]){
        if(idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        for(int i = 0; i < arr[idx].length(); i++)
        {
            path.append(arr[idx].charAt(i));
            dfs(res, digits, idx+1, path, arr);
            path.deleteCharAt(path.length() - 1);
        }
    }
    public List<String> letterCombinations(String digits) {
        String arr[] = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<String>();
        StringBuilder path = new StringBuilder();
        for(int i = 0; i < digits.length(); i++){
            dfs(res, digits, 0, path, arr);
        }
        return res;
    }

    public static void main(String args[]) {
       //TwoSum t = new TwoSum();
       //t.letterCombinations("23");
        try { System.out.println("try");
        } catch (Exception e) { System.out.println("catch");
        } finally {
            int k = 3 / 0;
            System.out.println("finally"); }
        System.out.println("main");
    }
}
