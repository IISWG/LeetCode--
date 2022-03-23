import sort.ListNode;

import java.util.*;

public class offer {
    /*
    001. 整数除法
     */
    static class Solution {
        public static void main(String[] args) {
            System.out.println(divide(1,1));
        }
        public static int divide(int a, int b) {
            int is = (a > 0) ^ (b > 0)  ? -1 : 1;
            if (a == Integer.MIN_VALUE && b == -1) {
                return Integer.MAX_VALUE;
            }

            a = Math.abs(a);
            b = Math.abs(b);
            if (b == 1) {
                return is == 1 ? a : -a;
            }
            int result = 0;
            for (int i = 31; i >=0; i--) {

                if ((a >>> i) >= b) {
                    a -= (b << i);

                    result += (1 << i);
                }
            }
            return is == 1 ? result : -result;

        }
    }
/*

 */
    class Solution1 {
        public String addBinary(String a, String b) {
            return "";
        }
    }
    class Solution2 {
        public int[] twoSum(int[] numbers, int target) {
            int lift = 0;
            int right = numbers.length -1;
            int[] ints = new int[2];
            while(lift <= right){
                int he = numbers[lift] + numbers[right];
                if(he == target){
                    ints[0] = lift;
                    ints[1] = right;
                    return ints;
                } else if (he < target) {
                    lift++;
                } else {
                    right++;
                }
            }
            return ints;

        }
    }
/*
数组中和为 0 的三个数
 */
    class Solution3 {
        public List<List<Integer>> threeSum(int[] nums) {
            ArrayList<List<Integer>> result = new ArrayList<>();
            if (nums.length < 3) {
                return result;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length-2; i++) {
                if (nums[i] > 0) {
                    return result;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int biaozun = -nums[i];
                int lift = i+1;
                int right = nums.length-1;
                while (lift < right) {
                    int zong = nums[lift] + nums[right];
                    if (zong == biaozun) {
                        result.add(Arrays.asList(nums[i], nums[lift], nums[right]));
                        while (lift < right && nums[lift] == nums[lift + 1]) {
                            lift++;
                        }
                        while (lift < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        lift++;
                        right--;
                    } else if (zong < biaozun) {
                        lift++;
                    } else {
                        right--;
                    }
                }
            }
            return result;
        }
    }
    /*
    和大于等于 target 的最短子数组
     */
    class Solution4 {
        public int minSubArrayLen(int target, int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int start = 0, end = 0;
            int sum = 0;
            while (end < nums.length) {
                sum += nums[end];
                while (sum >= target) {
                    ans = Math.min(ans, end - start + 1);
                    sum -= nums[start];
                    start--;
                }
                end++;
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
    class Solution5 {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int left = 0;
            int ret = 0;
            int total = 1;
            for (int right = 0; right < nums.length; right++) {
                total *= nums[right];
                while (left <= right && total >= k) {
                    total /= nums[left++];
                }
                if (left <= right) {
                    ret += right - left + 1;
                }
            }
            return ret;

        }
    }
    class Solution6 {
        public int subarraySum(int[] nums, int k) {
            int pre_sum = 0;
            int ret = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i : nums) {
                pre_sum += i;
                ret += map.getOrDefault(pre_sum - k, 0);
                map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1);
            }
            return ret;
        }
    }

    class Solution7 {
        public int findMaxLength(int[] nums) {
            HashMap<Integer, Integer> qianzuihe = new HashMap<>();
            int max = 0;
            int sum = 0;
            qianzuihe.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i] == 0 ? -1 : 1;
                if (qianzuihe.containsKey(sum)) {
                    max = Math.max(i - qianzuihe.get(sum), max);
                } else {
                    qianzuihe.put(sum, i);
                }
            }
            return max;
        }
    }

    class Solution8 {
        public int pivotIndex(int[] nums) {
            int sum = 0;
            int totalNum = 0;
            for (int num : nums) {
                totalNum += num;
            }
            for (int i = 0; i < nums.length; i++) {
                if (2 * sum + nums[i] == totalNum) {
                    return i;
                } else {
                    sum += nums[i];
                }
            }
            return -1;
        }
    }
    class NumMatrix {
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
        }
    }
    /*字符串中的变位词
     */
    class Solution9 {
        /*
            滑动窗口方式
         */
        public boolean checkInclusion1(String s1, String s2) {
            int length1 = s1.length();
            int length2 = s2.length();
            if (length1 > length2) {
                return false;
            }
            int[] stongji1 = new int[26];
            int[] stongji2 = new int[26];
            for (int i = 0; i < length1; i++) {
                stongji1[s1.charAt(i) - 'a']++;
                stongji2[s2.charAt(i) - 'a']++;
            }
            if (Arrays.equals(stongji1, stongji2)) {
                return true;
            }
            for (int i = length1; i < length2; i++) {
                stongji2[s2.charAt(i-length1) - 'a']--;
                stongji2[s2.charAt(i) - 'a']++;
                if (Arrays.equals(stongji1, stongji2)) {
                    return true;
                }

            }
            return false;

        }
        /*双指针
         */
        public boolean checkInclusion(String s1, String s2) {
            int length1 = s1.length();
            int length2 = s2.length();
            if (length1 > length2) {
                return false;
            }
            int[] stongji = new int[26];
            for (int i = 0; i < length1; i++) {
                stongji[s1.charAt(i) - 'a']--;
            }
            int start = 0;
            int end = 0;
            while (end < length2) {
                stongji[s2.charAt(end) - 'a']++;
                while (stongji[s2.charAt(end) - 'a'] > 0) {
                    stongji[s2.charAt(start) - 'a']--;
                    start++;
                }

                if (end - start + 1 == length1) {
                    return true;
                }
                end++;
            }
            return false;

        }
    }

    class Solution10 {
        public List<Integer> findAnagrams(String s, String p) {
            int length1 = p.length();
            int length2 = s.length();
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (length1 > length2) {
                return arrayList;
            }
            int[] stongji1 = new int[26];
            int[] stongji2 = new int[26];
            for (int i = 0; i < length1; i++) {
                stongji1[p.charAt(i) - 'a']++;
                stongji2[s.charAt(i) - 'a']++;
            }
            if (Arrays.equals(stongji1, stongji2)) {
                arrayList.add(0);
            }
            for (int i = length1; i < length2; i++) {
                stongji2[s.charAt(i-length1) - 'a']--;
                stongji2[s.charAt(i) - 'a']++;
                if (Arrays.equals(stongji1, stongji2)) {
                    arrayList.add(i-length1+1);
                }

            }
            return arrayList;
        }
    }
/*不含重复字符的最长子字符串

 */
    class Solution11 {
        public int lengthOfLongestSubstring(String s) {
            int length = s.length();
            if (length <= 1) {
                return length;
            }
            HashMap<Character, Integer> HashMap = new HashMap<>();
            int maxlength = 0;
            int left = 0;
            for (int i = 0; i < length; i++) {
                if (HashMap.get(s.charAt(i)) != null) {
                    left = Math.max(left, HashMap.get(s.charAt(i)) + 1);
                }
                HashMap.put(s.charAt(i), i);
                maxlength = Math.max(maxlength, i - left + 1);

            }
            return maxlength;
        }
    }
    /*
    剑指 Offer II 017. 含有所有字符的最短字符串
     */
    class Solution12 {
        public String minWindow(String s, String t) {
            HashMap<Character,Integer> charToCount = new HashMap<>();
            //将字符串t中的字符加入HashMap计数
            for (char ch : t.toCharArray()) {
                charToCount.put(ch,charToCount.getOrDefault(ch,0) + 1);
            }
            //count记录HashMap中字符种类的数量
            int count = charToCount.size();
            //滑动窗口
            int start = 0, end = 0;
            //记录最短字符串的起止点和长度
            int minStart = 0, minEnd = 0;
            int minLength = Integer.MAX_VALUE;
            //滑动窗口法
            while (end < s.length() || (count == 0 && end == s.length())) {
                if (count > 0) {
                    char endCh = s.charAt(end);
                    if (charToCount.containsKey(endCh)) {
                        charToCount.put(endCh, charToCount.get(endCh) - 1);
                        if(charToCount.get(endCh) == 0) {
                            count--;
                        }
                    }
                    end++;
                } else {
                    if(end - start < minLength) {
                        minLength = end - start;
                        minStart = start;
                        minEnd = end;
                    }
                    char startCh = s.charAt(start);
                    if (charToCount.containsKey(startCh)) {
                        charToCount.put(startCh, charToCount.get(startCh) + 1);
                        if(charToCount.get(startCh) == 1) {
                            count++;
                        }
                    }
                    start++;
                }
            }
            return minLength < Integer.MAX_VALUE ? s.substring(minStart,minEnd) : "";

        }
    }
    /*剑指 Offer II 018. 有效的回文

     */
    class Solution13 {
        public boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    left++;
                }
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    right--;
                }
                if (left < right && !(Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)))) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

    }
    /*
    剑指 Offer II 019. 最多删除一个字符得到回文
     */
    class Solution14 {
        public boolean validPalindrome(String s) {
            char[] chars = s.toCharArray();
            int left = 0;
            int right = chars.length - 1;
            while (left < right) {
                if (chars[left] != chars[right]) {
                    return is(chars, left + 1, right) || is(chars, left, right - 1);
                }
                left++;
                right--;
            }
            return true;
        }

        public boolean is(char[] str, int left, int right) {
            while (left < right) {
                if (str[left] != str[right]) {
                    return false;
                }
                left++;
                right--;

            }
            return true;
        }
    }
/*
剑指 Offer II 020. 回文子字符串的个数
 */
    class Solution15 {
        /*
        * 动态规划
        * */
        public int countSubstrings(String s) {
            int length = s.length();
            byte[][] bytes = new byte[length][length];
            int res = 0;
            for (int i = 0; i < length; i++) {
                for (int j = i; j >=0 ; j--) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (i - j <= 1) {
                            bytes[i][j] = 1;
                        } else {
                            bytes[i][j] = bytes[i - 1][j + 1];
                        }
                    }
                    res += bytes[i][j];
                }

            }
            return res;
        }
        /*
        中心向两边探测
         */
        public int countSubstrings1(String s) {
            int length = s.length();
            int left = 0;
            int right = 0;
            int result = 0;
            for (int i = 0; (i / 2 + i % 2) < length; i++) {
                left = i / 2;
                right = left + i % 2;
                while (left >= 0 && right < length) {
                    if (s.charAt(left) == s.charAt(right)) {
                        result++;
                    }
                    else{
                        break;
                    }
                    left--;
                    right++;
                }
            }
            return result;

        }
    }

    class Solution16 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode qian = new ListNode();
            ListNode hou = new ListNode();
            qian.next = head;
            hou.next = head;
            for (int i = 0; i < n; i++) {
                qian = qian.next;
            }
            if (qian.next == null) {
                return head.next;
            }
            while (qian.next != null) {
                qian = qian.next;
                hou = hou.next;
            }
            ListNode next = hou.next;
            hou.next = next.next;
            return head;
        }
    }
    public class Solution17 {
        public ListNode detectCycle(ListNode head) {
            HashMap<Integer, ListNode> hashMap = new HashMap<>();
            int i = 0;
            while (head != null) {
                int hashCode = head.hashCode();
                if (hashMap.get(hashCode) == null) {
                    hashMap.put(hashCode, head);
                } else {
                    return head;
                }

                head = head.next;
            }
            return null;
        }
    }

    public class Solution18 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {


            Set<ListNode> visited = new HashSet<ListNode>();
            ListNode temp = headA;
            while (temp != null) {
                visited.add(temp);
                temp = temp.next;
            }
            temp = headB;
            while (temp != null) {
                if (visited.contains(temp)) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;

            /*
            //双指针解法
             if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;

             */
           /* HashMap<Integer, ListNode> hashMap = new HashMap<>();
            while (headA != null || headB != null) {
                if (headA != null) {
                    int hashCodea = headA.hashCode();
                    if (hashMap.get(hashCodea) == null) {
                        hashMap.put(hashCodea, headA);
                    } else {
                        return headA;
                    }
                    headA = headA.next;
                }
                if (headB != null) {
                    int hashCodeb = headB.hashCode();
                    if (hashMap.get(hashCodeb) == null) {
                        hashMap.put(hashCodeb, headB);
                    } else {
                        return headB;
                    }
                    headB = headB.next;
                }
            }
            return null;*/
        }
    }

    class Solution19 {
        public ListNode reverseList(ListNode head) {
            ListNode listNode = new ListNode();
            listNode.next = head;
            ListNode cur = head;
            ListNode next;
            while (cur.next != null) {
                next = cur.next;
                cur.next = next.next;
                next.next = listNode.next;
                listNode.next = next;
            }
            return listNode.next;
        }
    }
    /*

     */
    class Solution20 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Deque<Integer>stack1=new LinkedList<>();
            Deque<Integer>stack2=new LinkedList<>();
            while (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            while (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }
            ListNode dh = new ListNode();

            ListNode head = new ListNode();
            dh.next = head;
            head.val= stack2.pop()+stack1.pop();
            while (!stack1.isEmpty() || !stack2.isEmpty()) {

                int sum = 0;
                if (dh.next.val >= 10) {
                    sum = 1;
                    dh.next.val = dh.next.val % 10;
                }
                if (!stack1.isEmpty()) {
                    sum += stack1.pop();
                }
                if (!stack2.isEmpty()) {
                    sum += stack2.pop();
                }
                ListNode listNode = new ListNode();
                listNode.val = sum;
                listNode.next = dh.next;
                dh.next = listNode;
            }
            if (dh.next.val >= 10) {
                ListNode listNode = new ListNode();
                dh.next.val = dh.next.val % 10;
                listNode.val = 1;
                listNode.next = dh.next;
                dh.next = listNode;
            }
            return dh.next;
        }
    }
    class Solution21 {
        private ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            return pre;
        }

        public void reorderList(ListNode head) {
            ListNode pre = new ListNode();
            ListNode slow = pre;
            ListNode fast = pre;
            pre.next = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode half = slow.next;
            slow.next = null;
            half = reverseList(half);
            fast = head;
            while (fast != null ) {
                if (fast != null) {
                    pre.next = fast;
                    pre = fast;
                    fast = fast.next;
                }
                if (half != null) {
                    pre.next = half;
                    pre = half;
                    half = half.next;
                }
            }

        }
    }
    /*
    回文链表
     */
    class Solution22 {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<Integer>();

            // 将链表的值复制到数组中
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.val);
                currentNode = currentNode.next;
            }

            // 使用双指针判断是否回文
            int left = 0;
            int right = vals.size() - 1;
            while (left < right) {
                if (!vals.get(left).equals(vals.get(right))) {
                    return false;
                }
                left++;
                right--;

            }
            return true;
        }
    }
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
/*
展平多级双向链表
 */
    class Solution23 {
        public Node flatten(Node head) {
            dfs(head);
            return head;
        }

        public Node dfs(Node node) {
            if (node == null) {
                return node;
            }
            Node cur = node;
            //需要记录最后一个节点
            Node last = null;
            while (cur != null) {
                Node next = cur.next;
                Node child = cur.child;
                //  如果有子节点，那么首先处理子节点
                if (child != null) {
                    Node dfs = dfs(child);
                    //cur 与 child 相连
                    cur.next = child;
                    child.prev = cur;
                    dfs.next = next;

                    if (next != null) {
                        next.prev = dfs;
                    }
                    last = dfs;
                    //要将child置为空，不然可能有问题
                    cur.child = null;
                } else {
                    last = cur;
                }
                cur = next;
            }
            return last;
        }
    }



}


