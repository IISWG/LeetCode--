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

}
