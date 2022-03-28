package offer;

import javax.swing.tree.TreeNode;
import java.util.*;

public class offer1 {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    /*
    剑指 Offer II 029. 排序的循环链表,与 708 题相同
     */
    class Solution {
        public Node insert(Node head, int insertVal) {
            if (head == null) {
                Node node = new Node(insertVal);
                return node.next = node;
            }
            Node change = head;
            while (change.next != head) {
                Node next = change.next;
                /*
                当insertVal在两点之间，就可以插入了，这是一种情况
                 */
                if (next.val > change.val ) {
                    if (insertVal <= next.val && insertVal >= change.val) {
                        break;
                    }
                }
                /*
                比最大的大，或比最小的小
                 */
                if (next.val < change.val && (insertVal <= next.val || insertVal >= change.val)) {
                    break;
                }
                change = next;
            }
            /*
            如果都没找到说明原链表节点都相等，或者此时才是最大最小分界
             */
            change.next = new Node(insertVal, change.next);
            return head;
        }
    }
    /*
    * 思路
用动态数组存储val,用HashMap存储val和val在数组中的下标idx。

当插入元素时,向HashMap和动态数组插入元素,时间复杂度为O(1);
当删除元素时,可以通过HashMap获取到元素所在的下标,将数组中最后一个元素的值赋予当前下标,并删除最后一个元素，即等同于删除当前元素,即可保证删除为O(1)复杂度；
随机访问,只需要通过随机生成下标,再访问数组,复杂度也为O(1)
。*/
    class RandomizedSet {

        List<Integer> list;
        Map<Integer, Integer> map;
        Random random;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            list = new ArrayList<Integer>();
            map = new HashMap<Integer, Integer>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            /*
            * 判断map中是否存在该值*/
            if(map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size()); // map添加元素数组中的下标
            list.add(val); // list添加元素
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            //判断该值是否存在
            if(!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val); // 通过HashMap获取该元素在数组中的下标idx
            int lastNum = list.get(list.size() - 1); // 获取数组的最后一个元素
            list.set(idx, lastNum); // 将idx的值设为最后一个元素的值
            list.remove(list.size() - 1); // 删除最后一个元素,即等同于删除该元素
            map.put(lastNum, idx);//需要修改换了位子的最后一个值得下标
            map.remove(val);//移除该值
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int idx = random.nextInt(list.size());
            return list.get(idx);
        }
    }
/*

 */
    class Solution2 {
        public int longestPalindromeSubseq(String s) {
            int length = s.length();
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++) {
                dp[i][i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (s.charAt(j) == s.charAt(i)) {
                        dp[j][i] = dp[j + 1][i - 1] + 2;
                    } else {
                        dp[j][i] = Math.max(dp[j + 1][i], dp[j][i - 1]);
                    }
                }
            }
            return dp[0][length - 1];
        }
    }
/*
剑指 Offer 12. 矩阵中的路径 - 解决方案
 */
    class Solution3 {
        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if (dfs(board, words, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }
        boolean dfs(char[][] board, char[] word, int i, int j, int k) {
            //越界，不匹配，已被访问,注意先判断是否越界
            if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word[k] != board[i][j] || board[i][j] == '/') {

                return false;
            }
            //
            if (k == word.length - 1) {
                return true;
            }
            char temp = board[i][j];
            board[i][j] = '/';
            boolean result = dfs(board, word, i+1, j, k+1) ||
                    dfs(board, word, i-1, j, k+1)||
                    dfs(board, word, i, j+1, k+1)||
                    dfs(board, word, i, j-1, k+1);
            board[i][j] = temp;
            return result;
        }
    }


    /*
    剑指 Offer 13. 机器人的运动范围
     */
    class Solution4 {
        public int movingCount(int m, int n, int k) {
            boolean[][] visited = new boolean[m][n];
            return dfs(0, 0, m, n, k, visited);
        }

        private int dfs(int i, int j, int m, int n, int k, boolean visited[][]) {
            if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || (i / 10 + i % 10 + j / 10 + j % 10) > k) {
                return 0;
            }
            visited[i][j] = true;
            return dfs(i + 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited) + 1;
        }
    }
    /*剑指 Offer 27. 二叉树的镜像

     */
    public class TreeNode {
      int val;
     TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    class Solution5 {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode left = mirrorTree(root.left);
            TreeNode right = mirrorTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }
    }
/*101. 对称二叉树

 */
    class Solution6 {
        public boolean isSymmetric(TreeNode root) {
            return check(root.left, root.right);
        }

        public boolean check(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }
    }
/*
 二叉搜索树的第k大节点
 */
    class Solution7 {
        int curK, key;
        public int kthLargest(TreeNode root, int k) {
            curK = k;
            dfs(root);
            return key;
        }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (curK == 0) {
            return;
        }
        curK--;
        if (curK == 0) {
            key = root.val;
            return;
        }
        dfs(root.left);

    }
    }

    /*剑指 Offer 32 - I. 从上到下打印二叉树

     */
    class Solution8 {
        public int[] levelOrder(TreeNode root) {

            ArrayList<Integer> arrayList = new ArrayList<>();
            LinkedList<TreeNode> treeNodes = new LinkedList<>();
            if (root != null) {
               treeNodes.push(root);
                while (!treeNodes.isEmpty()) {
                    TreeNode curNode = treeNodes.pop();
                    arrayList.add(curNode.val);
                    TreeNode leftNode = curNode.left;
                    TreeNode rightNode = curNode.right;
                    if (leftNode != null) {
                        treeNodes.add(leftNode);
                    }
                    if (rightNode != null) {
                        treeNodes.add(rightNode);
                    }
                }
            }
            int[] ints = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                ints[i] = arrayList.get(i);
            }
            return ints;
        }
    }
/* 剑指 Offer 32 - II. 从上到下打印二叉树 II

 */
    class Solution9 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> arrayLists = new ArrayList<>();
            if (root == null) {
                return arrayLists;
            }
            LinkedList<TreeNode> qian = new LinkedList<>();
            qian.add(root);
            while (!qian.isEmpty()) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i = qian.size(); i > 0; i--) {
                    TreeNode pop = qian.pop();
                    arrayList.add(pop.val);
                    TreeNode left = pop.left;
                    TreeNode right = pop.right;
                    if (left != null) {
                        qian.add(left);
                    }
                    if (right != null) {
                        qian.add(right);
                    }
                }

                arrayLists.add(arrayList);
            }
            return arrayLists;
        }

    }
/*
剑指 Offer 32 - III. 从上到下打印二叉树 III
 */
    class Solution10 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> arrayLists = new ArrayList<>();
            if (root == null) {
                return arrayLists;
            }
            LinkedList<TreeNode> qian = new LinkedList<>();
            qian.add(root);
            int cength = 0;
            while (!qian.isEmpty()) {
                LinkedList<Integer> linkedList = new LinkedList<>();
                for (int i = qian.size(); i > 0; i--) {
                    TreeNode pop = qian.pop();
                    //注意这里的添加的顺序
                    if (cength % 2 == 0) {
                        linkedList.add(pop.val);
                    } else {
                        linkedList.addFirst(pop.val);
                    }
                    TreeNode left = pop.left;
                    TreeNode right = pop.right;

                    if (left != null) {
                        qian.add(left);
                    }
                    if (right != null) {
                        qian.add(right);
                    }

                }
                cength++;
                arrayLists.add(linkedList);
            }
            return arrayLists;
        }
    }
    /*
    从先序遍历还原二叉树

     */
    class Solution11 {
        public TreeNode recoverFromPreorder(String traversal) {
            LinkedList<TreeNode> treeNodes = new LinkedList<>();
            int zhizheng = 0;
            while (zhizheng < traversal.length()) {
                int cength = 0;
                while (zhizheng < traversal.length() && traversal.charAt(zhizheng) == '-') {
                    cength++;
                    zhizheng++;
                }
                int value = 0;
                while (zhizheng < traversal.length() && Character.isDigit(traversal.charAt(zhizheng))) {
                    value = value * 10 + traversal.charAt(zhizheng) - '0';
                    zhizheng++;
                }
                TreeNode cur = new TreeNode(value);
                if (cength == treeNodes.size()) {
                    if (!treeNodes.isEmpty()) {
                        TreeNode last = treeNodes.peek();
                        last.left = cur;
                    }
                } else {
                    while (cength != treeNodes.size()) {
                        treeNodes.pop();
                    }
                    if (!treeNodes.isEmpty()) {
                        TreeNode last = treeNodes.peek();
                        last.right = cur;
                    }
                }
                treeNodes.push(cur);
            }
            while (treeNodes.size() > 1) {
                treeNodes.pop();
            }
            return treeNodes.peek();


        }
    }

    /*
剑指 Offer 10- I. 斐波那契数列     */

    class Solution12 {
        public int fib(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            int[] ints = new int[n+1];
            ints[0] = 0;
            ints[1] = 1;
            for (int i = 2; i <= n; i++) {
                ints[i] = (ints[i - 1] + ints[i - 2])%1000000007;
            }
            return ints[n];
        }
    }
    /*
     剑指 Offer 10- II. 青蛙跳台阶问题
     */
    class Solution13 {
        public int numWays(int n) {
            if (n <= 1) {
                return 1;
            }
            int qian = 1;
            int hou = 1;
            int cur = 1;
            for (int i = 2; i <= n; i++) {
                qian = hou;
                hou = cur;
                cur = (qian + hou) % 1000000007;
            }
            return cur;

        }
    }

//    53. 最大子数组和
        class Solution14 {
            public int maxSubArray(int[] nums) {

                int ans = nums[0], s = 0;
                for(int i : nums) {
                    s += i;
                    if (s > ans) {
                        ans = s;
                    }
                    if (s < 0) {
                        s = 0;
                    }
                }
                return ans;
//                int pre = 0;
//                int max = nums[0];
//                for (int i = 0; i < nums.length; i++) {
//                   pre =nums[i]+ Math.max(pre,0);
//                    max = Math.max(pre, max);
//                }
//                return max;
            }
        }

        /*
        剑指 Offer 46. 把数字翻译成字符串
         */
        class Solution15 {
            public int translateNum(int num) {
                int qian = 1;
                int hou = 1;
                int x =0, y = num % 10;
                int cur = 1;
                while (num != 0) {
                    num /= 10;
                    x = num % 10;
                    int sum = x * 10 + y;
                    if (sum >= 10 && sum <= 25) {
                        cur = qian + hou;
                    } else {
                        cur = hou;
                    }
                    y = x;
                    qian = hou;
                    hou = cur;

                }
                return cur;
            }
        }
        /*
        剑指 Offer 47. 礼物的最大价值
         */
        class Solution16 {
            public int maxValue(int[][] grid) {
                int rows = grid.length;
                int cows = grid[0].length;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cows; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        if (i == 0) {
                            grid[i][j] = grid[i][j - 1] + grid[i][j];
                        } else if (j == 0) {
                            grid[i][j] = grid[i - 1][j] + grid[i][j];
                        } else {
                            grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1])+grid[i][j];
                        }

                    }
                }
                return grid[rows - 1][cows - 1];
            }
        }

        /*
        剑指 Offer 49. 丑数
         */

    class Solution17 {
        public int nthUglyNumber(int n) {
            int a = 0, b = 0, c = 0;
            int[] choushu = new int[n];
            choushu[0] = 1;
            for (int i = 1; i < n; i++) {
                int ashu = choushu[a] * 2;
                int bshu = choushu[b] * 3;
                int cshu = choushu[c] * 5;
                int min = Math.min(Math.min(ashu, bshu), cshu);
                choushu[i] = min;
                if (ashu == min) {
                    a++;
                }
                if (bshu == min) {
                    b++;
                }
                if (cshu == min) {
                    c++;
                }
            }
            return choushu[n - 1];
        }
    }

    /*
    剑指 Offer 62. 圆圈中最后剩下的数字
     */
    class Solution18 {
        public int lastRemaining(int n, int m) {
            int ans = 0;
            // 最后一轮剩下2个人，所以从2开始反推
            for (int i = 2; i <= n; i++) {
                ans = (ans + m) % i;
            }
            return ans;


        }
    }

    /*
    剑指 Offer 63. 股票的最大利润
     */

    class Solution19 {
        public int maxProfit(int[] prices) {
            int max = 0;
            int sum = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                int num = prices[i + 1] - prices[i];
                sum += num;
                if (sum > 0) {
                    max = Math.max(max, sum);
                } else {
                    sum = 0;
                }
            }
            return max;

//            int min = Integer.MAX_VALUE;
//            int max = 0;
//            for (int price : prices) {
//                min = Math.min(min, price);
//                max = Math.max(max, price - min);
//            }
//            return max;

        }
    }
    /*
剑指 Offer 68 - II. 二叉树的最近公共祖先     */
    class Solution20 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            if (root.val == p.val) {
                return p;
            }
            if (root.val == q.val) {
                return q;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null && right == null) {
                return null;
            } else if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            }
            return root;

        }
    }
    /*
从前序与中序遍历序列构造二叉树
     */
    class Solution21 {
        private Map<Integer, Integer> indexMap;

        public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            if (preorder_left > preorder_right) {
                return null;
            }
            TreeNode rootNode = new TreeNode(preorder[preorder_left]);
            int rootindex = indexMap.get(rootNode.val);
            int zuo = rootindex - inorder_left;
            TreeNode leftNode = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + zuo, inorder_left, rootindex - 1);
            TreeNode rightNode = myBuildTree(preorder, inorder, preorder_left + zuo + 1, preorder_right, rootindex+1, inorder_right);
            rootNode.left = leftNode;
            rootNode.right = rightNode;
            return rootNode;
        }

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int length = inorder.length;
            indexMap = new HashMap<>();
            for (int i = 0; i < length; i++) {
                indexMap.put(inorder[i], i);
            }
            TreeNode treeNode = myBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
            return treeNode;
        }
    }

    /*剑指 Offer 26. 树的子结构

     */
    class Solution22 {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (B == null || A == null) {
                return false;
            }
            if (A.val == B.val && (re(A.left, B.left) && re(A.right, B.right))) {
                return true;
            }
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
        public boolean re(TreeNode A, TreeNode B) {
            if (B == null) {
                return true;
            }
            if (A == null || A.val != B.val) {
                return false;
            }
            boolean left = re(A.left, B.left);
            boolean right = re(A.right, B.right);
            return left && right;

        }
    }

    /*
剑指 Offer 64. 求1+2+…+n
     */

    class Solution23 {
        public int sumNums(int n) {
            boolean temp = n>0 && (n += sumNums(n-1)) >0;
            return n;
        }
    }

//    剑指 Offer 44. 数字序列中某一位的数字
    class Solution24 {
        public int findNthDigit(int n) {
            int digit = 1;
            long start = 1;
            long count = 9;
            return 0;
        }
    }
    //990. 等式方程的可满足性
    class Solution25 {
        public boolean equationsPossible(String[] equations) {
            int[] parents = new int[26];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
            for (String equation : equations) {
                if (equation.charAt(1) == '=') {
                    union(parents, equation.charAt(0) - 'a', equation.charAt(3) - 'a');
                }
            }
            for (String equation : equations) {
                if (equation.charAt(1) == '!') {
                    int qian = find(parents, equation.charAt(3) - 'a');
                    int hou = find(parents, equation.charAt(0) - 'a');
                    if (qian == hou) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void union(int[] parent, int index1, int index2) {
            parent[find(parent, index1)] = find(parent, index2);
        }

        public int find(int[] parent, int index) {
            while (index != parent[index]) {
                index = parent[index];
            }
            return index;
        }

    }

//    剑指 Offer 03. 数组中重复的数字
    class Solution26 {
        public int findRepeatNumber(int[] nums) {
            HashSet<Integer> integers = new HashSet<>();
            for (int num : nums) {
                if (!integers.add(num)) {
                    return num;
                }
            }
            return 0;

        }
    }
    //剑指 Offer 48. 最长不含重复字符的子字符串
    class Solution27 {
        public int lengthOfLongestSubstring(String s) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            int max = 0;
            int start = -1;
            int length = s.length() - 1;
            if (length < 0) {
                return 0;
            }
            for (int i = 0; i < length; i++) {
                Integer integer = hashMap.get(s.charAt(i));
                if (integer != null) {
                    start =  Math.max(start,integer);
                }
                hashMap.put(s.charAt(i), i);
                max = Math.max(max, i - start);
            }
            return max;
        }
    }

}
