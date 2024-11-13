import com.surge.Friday.algorithm.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.08.11
 */
public class DailyTest {

    public static void main(String[] args) {
        System.out.println(new DailyTest().generateParenthesis(3));

//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(2);
//        head.next.next.next.next = new ListNode(5);
//        System.out.println(judgeHuiWenLinkedList(head));  // 输出: true
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    public boolean judgeHuiWenString(String param) {
        // 1.判断字符串长度是奇数还是偶数
        // 2.如果是奇数，ab两个节点等于中间节点，如果是偶数, a= 中间节点 b = 中间节点+1
        // 3.a节点向字符起始遍历，b节点向字符串末尾遍历
        // 4.依次判断每个字符是否相等，如果存在不相等则不是回文字符串1
        int length = param.length();
        int a, b;
        if (length % 2 == 0) {
            a = length / 2 - 1;
            b = length / 2;
        } else {
            a = length / 2;
            b = length / 2 + 1;
        }
        while (a > 0) {
            if (param.charAt(a) != param.charAt(b)) {
                return false;
            }
            a--;
            b++;
        }
        return true;
    }

    public static boolean judgeHuiWenLinkedList(ListNode head) {

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode cur = slow;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        while (prev != null && head != null) {
            if (prev.val != head.val) {
                return false;
            }
            prev = prev.next;
            head = head.next;
        }
        return true;
    }

    static class Solution {
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int [][] dp = new int[n][n];
            for (int i =  n-1; i >=0; i--) {
                dp[i][i] = 1;
                for (int j = i +1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }

            }

            return dp[0][n-1];
        }
        public static void main(String[] args) {

        }
        //s(i) = s(j) dp(i,j) = dp[i+1][j-1] + 2; s(i) != s(j) dp(i,j) = Max(dp(i,j-1),dp(i+1,j)
        //dp(n)= Math.min ( dp(n-1) +cost[0] , dp(max(0,i-7)) + cost[1], dp(max(0,i-30)) +cost[2] )
    }
}
