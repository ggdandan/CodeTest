import java.util.*;
import java.lang.*;
public class Test {
    public static int reverse(int x)
    {
        int sign = 0;
        if(x < 0) sign = -1;
        int ret = 0;
        while(x > 0)
        {
            int ori = ret;
            ret = ret*10 + x%10;
            if((ret - x%10) / 10 != ori)
                return 0;
            x /= 10;
        }
        return ret;
    }

    public static void InsertSpacePermutation(String target, List<String> results, int idx, StringBuilder sb)
    {
        // Base case;
        if(idx == target.length() - 1)
        {
            sb.append(target.charAt(idx));
            results.add(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        //StringBuilder nsb = sb;
        sb.append(target.charAt(idx));
        sb.append(' ');
        InsertSpacePermutation(target, results, idx + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        InsertSpacePermutation(target, results, idx + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
    public static void modify(StringBuilder sb)
    {
        sb = new StringBuilder("abcd");
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    public static void main(String args[])
    {
        /*List<String> arr = new ArrayList<String>();
        InsertSpacePermutation("abc", arr, 0, new StringBuilder());
        for(String str : arr)
        {
            System.out.println(str);
        }*/
        Map<String, List<String>> m = new HashMap<>();
        m.put("JFK", new ArrayList<String>());
        m.get("JFK").add("ABC");
        List<String> des = m.get("JFK");
        des.remove("ABC");
        System.out.println(m.size());
    }
}
