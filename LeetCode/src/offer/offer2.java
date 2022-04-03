package offer;

import com.sun.org.apache.bcel.internal.generic.I2B;
import org.w3c.dom.Node;
import sort.ListNode;

import java.util.*;

public class offer2 {
//    无重复字符的最长子串
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int start = 0;
            int max = 0;
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                Integer integer = hashMap.get(s.charAt(i));
                if (integer != null) {
                    //这里start指针是不能回退的，回退就会有重复的字符，不符合条件
                    start = Math.max(start,integer + 1);
                }
                max = Math.max(max, i - start + 1);
                hashMap.put(s.charAt(i), i);
            }
            return max;
        }
    }
    //划分字符串
    class Solution1 {

        public ArrayList<Integer> Substring(String s) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                hashMap.put(s.charAt(i), i);
            }
            ArrayList<Integer> arrayList = new ArrayList<>();
            int end = 0;
            int start = 0;
            for (int i = 0; i < s.length(); i++) {
                end = Math.max(end, hashMap.get(s.charAt(i)));
                if (i != end) {
                    i++;
                } else {
                    arrayList.add(i - start + 1);
                    start = ++i;
                }
            }
            return arrayList;
        }
    }

    //最长回文子串
    class Solution2 {
        public String longestPalindrome(String s) {
            int length = s.length();
            int max = 0;
            int start = 0;
            int end = 0;
            boolean[][] dp = new boolean[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = i; j >= 0 ; j--) {
                    if (s.charAt(j) != s.charAt(i)) {
                        continue;
                    }
                    if (i - j <= 2) {
                            dp[j][i] = true;
                        } else {
                            dp[j][i] = dp[j + 1][i - 1] && (s.charAt(j) == s.charAt(i));
                        }
                    if (dp[j][i] && (i - j + 1) > max) {
                        max = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
            return s.substring(start, end + 1);
        }
    }

    //递增的三元子序列
    class Solution3 {
        public boolean increasingTriplet(int[] nums) {
            int length = nums.length;
            if (length < 3) {
                return false;
            }
            int min = nums[0];
            int ermin = Integer.MAX_VALUE;
            for (int i = 1; i < length; i++) {
                if (nums[i] <= min) {
                    min = nums[i];
                } else if (nums[i] > min && nums[i] <= ermin) {
                    ermin = nums[i];
                } else {
                    return true;
                }
            }
            return false;
        }
    }
    class ListNode {
      int val;
      ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  //两数相加
    class Solution4 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode head = l1;
            ListNode next = l1;
            int i = l1.val + l2.val;
            head.val =  i% 10;
            int jinwei = i / 10;
            l1 = l1.next;
            l2 = l2.next;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    l2.val = l2.val + jinwei;
                    if ((jinwei = l2.val / 10) == 1) {
                        next.next = l2;
                        next = l2;
                        l2 = l2.next;
                    } else {
                        next.next = l2;
                        return head;
                    }
                    l2.val = l2.val % 10;
                } else if (l2 == null) {
                    l1.val = l1.val + jinwei;
                    if ((jinwei = l1.val / 10) == 1) {
                        next.next = l1;
                        next = l1;
                        l1 = l1.next;
                    } else {
                        next.next = l1;
                        return head;
                    }
                    l1.val %= 10;
                } else {
                    l1.val = l1.val + l2.val + jinwei;
                    jinwei = l1.val/10;
                    l1.val %= 10;
                    next.next = l1;
                    next = l1;
                    l1 = l1.next;
                    l2 = l2.next;
                }
            }
            if (jinwei != 0) {
                ListNode listNode = new ListNode(1);
                next.next = listNode;
            }
            return head;
        }
    }

    //奇偶链表
    class Solution5 {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
//            ListNode next = head;
//            ListNode ji = head;
//            ListNode ou = head.next;
//            ListNode ouhead = ou;

            //奇数链表的头节点
            ListNode oddHead = head;
            //奇数链表的当前节点
            ListNode oddCur = oddHead;

            //偶数链表的头节点
            ListNode evenHead = head.next;
            //偶数链表的当前节点
            ListNode evenCur = evenHead;

            while (evenCur != null && evenCur.next != null) {
                //奇数节点串一起
                oddCur.next = oddCur.next.next;
                //偶数节点串一起
                evenCur.next = evenCur.next.next;
                //奇偶指针往后移
                oddCur = oddCur.next;
                evenCur = evenCur.next;
            }
            //最后偶数链表和奇数链表需要串在一起
            oddCur.next = evenHead;
            return oddHead;

//            for (int i = 1; next != null; i++) {
//                if (i % 2 == 1) {
//                    ListNode tmp = next;
//                    next = next.next;
//                    ji.next = tmp;
//                    ji = ji.next;
//                } else {
//                    ListNode tmp = next;
//                    next = next.next;
//                    ou.next = tmp;
//                    ou = ou.next;
//                }
//            }
//            ou.next = null;
//            ji.next = ouhead;
//            return head;
        }
    }

    //相交链表
    public class Solution6 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode nexta = headA;
            ListNode nextb = headB;

            while (nexta != nextb) {
                nexta = nexta == null ? headB : nexta.next;
                nextb = nextb == null ? headA : nextb.next;
            }

            return nexta;
        }
    }
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
        TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    //二叉树的中序遍历
    class Solution7 {
        public List<Integer> inorderTraversal(TreeNode root) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            LinkedList<TreeNode> treeNodes = new LinkedList<>();
            if (root == null) {
                return arrayList;
            }
            while (root != null || !treeNodes.isEmpty()) {
                while (root != null) {
                    treeNodes.add(root);
                    root = root.left;
                }
                root = treeNodes.pollLast();
                arrayList.add(root.val);
                root = root.right;
                if (root!= null) {
                    while (root != null) {
                        treeNodes.add(root);
                        root = root.left;
                    }
                }
            }
            return arrayList;
        }
//        public List<Integer> inorderTraversal(TreeNode root) {
//            ArrayList<Integer> arrayList = new ArrayList<>();
//            inOrder(root, arrayList);
//            return arrayList;
//        }
//
//        public void inOrder(TreeNode root, ArrayList<Integer> arrayList) {
//            if (root == null) {
//                return;
//            }
//            inOrder(root.left, arrayList);
//            arrayList.add(root.val);
//            inOrder(root.right, arrayList);
//        }
    }

    //二叉树的锯齿形层次遍历
    class Solution8 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> arrayLists = new ArrayList<>();
            if (root == null) {
                return arrayLists;
            }
            LinkedList<TreeNode> rootList = new LinkedList<>();
            rootList.add(root);
            int ceng = 1;
            while (rootList.size() != 0) {
                int size = rootList.size();
                ArrayList<Integer> arrayList = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    TreeNode pop = rootList.pop();
                    if (pop.left != null) {
                        rootList.add(pop.left);
                    }
                    if (pop.right != null) {
                        rootList.add(pop.right);
                    }
                    if (ceng == 1) {
                        arrayList.add(pop.val);
                    } else {
                        arrayList.add(0, pop.val);
                    }
                }
                arrayLists.add(arrayList);
                ceng = ceng == 1 ? 0 : 1;
            }
            return arrayLists;
        }
    }

    //从前序与中序遍历序列构造二叉树
    class Solution9 {
        public HashMap<Integer, Integer> hashMap = new HashMap<>();
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++) {
                hashMap.put(inorder[i], i);
            }
          return  dgui(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode dgui(int[] preorder, int[] inorder, int pleft, int pright, int ileft, int iright) {
            if (pleft > pright) {
                return null;
            }
            int root = preorder[pleft];
            TreeNode rootNode = new TreeNode(root);
            Integer index = hashMap.get(root);
            int zuonum = index - ileft;
            TreeNode lefttree = dgui(preorder, inorder, pleft + 1, pleft + zuonum, ileft, index - 1);
            TreeNode righttree = dgui(preorder, inorder, pleft + zuonum + 1, pright, index + 1, iright);
            rootNode.left = lefttree;
            rootNode.right = righttree;
            return rootNode;
        }
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

//    填充每个节点的下一个右侧节点指针
    class Solution10 {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            Node cur = root;
            while (cur != null) {
                Node head = new Node();
                Node next = head;
                head.next = cur.left;
                while (cur != null && cur.left != null) {
                    next.next = cur.left;
                    cur.left.next = cur.right;
                    next = cur.right;
                    cur = cur.next;
                }
                cur = head.next;
            }
            return root;
        }

    public Node connect1(Node root) {
            if (root == null) {
                return root;
            }
            LinkedList<Node> nodes = new LinkedList<>();
            nodes.add(root);
            while (nodes.size() != 0) {
                int size = nodes.size();
                Node head = null;
                for (int i = 0; i < size; i++) {
                    Node p = nodes.pop();
                    if (p.left != null) {
                        nodes.add(p.left);
                        p.left.next = p.right;
                    }
                    if (p.right != null) {
                        nodes.add(p.right);
                    }
                    if (head == null) {
                        head = p.right;
                    } else {
                        head.next = p.left;
                        head = p.right;
                    }

                }
            }
            return root;
    }
    }

    //二叉搜索树中第K小的元素
    class Solution11 {
        int re = 0;
        int count;
        //方法一，用中序遍历统计还剩多少个节点没找到
        public int kthSmallest1(TreeNode root, int k) {
            count = k;
            inOrder(root);
            return re;
        }

        public void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            //如果剩余还没找到的节点数
            if (count == 0) {
                return;
            }
            if (--count == 0) {
                re = root.val;
                return;
            }
            inOrder(root.right);
        }
        //方法二，统计节点个数，然后定位
        public int kthSmallest(TreeNode root, int k) {
            //先统计左子节点的个数
            int leftCount = countNodes(root.left);
            if (leftCount >= k) {
                //如果左子节点的个数大于等于k，说明我们要找的元素就在左子节点中，
                //直接在左子节点中查找即可
                return kthSmallest(root.left, k);
            } else if (leftCount + 1 == k) {
                //如果左子节点的个数加当前节点（1）正好等于k，说明根节点
                //就是要找到元素
                return root.val;
            } else {
                //否则要找的元素在右子节点中，到右子节点中查找
                return kthSmallest(root.right, k - 1 - leftCount);
            }
        }

        //统计节点个数
        public int countNodes(TreeNode n) {
            if (n == null) {
                return 0;
            }
            return 1 + countNodes(n.left) + countNodes(n.right);
        }

    }
    //岛屿数量
    class Solution12 {


        public int numIslands(char[][] grid) {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        bfs(grid, i, j);
                    }
                }
            }
            return count;
        }

        public void bfs(char[][] grid, int i, int j) {
            grid[i][j] = '0';
            LinkedList<Integer> linkedList = new LinkedList<>();
            int row = grid.length;
            int cow = grid[0].length;
            int index = i * cow + j;
            linkedList.add(index);
            while (!linkedList.isEmpty()) {
                Integer poll = linkedList.poll();
                int hang = poll / cow;
                int lie = poll % cow;
                if (hang > 0 && grid[hang - 1][lie] == '1') {
                    grid[hang - 1][lie] = '0';
                    linkedList.add((hang - 1) * cow + lie);
                }
                if (lie > 0 && grid[hang][lie - 1] == '1') {
                    grid[hang][lie - 1] = '0';
                    linkedList.add(hang * cow + lie - 1);
                }
                if (lie < cow-1 && grid[hang][lie +1] == '1') {
                    grid[hang][lie + 1] = '0';
                    linkedList.add(hang * cow + lie + 1);
                }
                if (hang < row - 1 && grid[hang + 1][lie] == '1') {
                    grid[hang + 1][lie] = '0';
                    linkedList.add((hang + 1) * cow + lie);
                }

            }
        }

        //深度优先搜索
        public int numIslands1(char[][] grid) {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        dfs(grid, i, j);
                    }
                }
            }
            return count;
        }
        //这里目的就是遍历起点开始的到可以到达的所有点，将其赋值为0，表示已被访问
        public void dfs(char[][] grid, int i, int j) {
            //如果越界或者不是‘1’，表示不能走，回溯
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            dfs(grid, i, j + 1);
            dfs(grid, i+ 1, j );
            dfs(grid, i, j - 1);
            dfs(grid, i - 1, j);

        }
    }

    //电话号码的字母组合
    class Solution13 {
        public List<String> letterCombinations(String digits) {
            LinkedList<String> ans = new LinkedList<>();
            if (digits.length() == 0) {
                return ans;
            }
            ans.add("");
            Map<Character, String> map = new HashMap<>();
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
            while (ans.peek().length() != digits.length()) {
                String pop = ans.pop();
                String s = map.get(digits.charAt(pop.length()));
                for (int i = 0; i < s.length(); i++) {
                    ans.add(pop + s.substring(i, i + 1));
                }
            }
            return ans;
        }
        public List<String> letterCombinations1(String digits) {
            List<String> ans = new ArrayList<>();
            if (digits.length() == 0) {
                return ans;
            }
            char[] characters = new char[digits.length()];
            Map<Character, String> map = new HashMap<>();
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
            zhuhe(digits, 0, map, characters, ans);
            return ans;
        }

        public void zhuhe(String digihts, int num, Map<Character, String> map, char[] characters,List<String> ans) {
            if (num == digihts.length()) {
                ans.add(String.valueOf(characters));
                return;
            }
            String s = map.get(digihts.charAt(num));
            for (int i = 0; i < s.length(); i++) {
                characters[num] = s.charAt(i);
                zhuhe(digihts, num + 1, map, characters, ans);
            }
        }
    }
    //括号生成
    class Solution14 {
        public List<String> generateParenthesis(int n) {
            ArrayList<String> strings = new ArrayList<>();
            char[] chars = new char[n * 2];
            chars[0] = '(';
            huisuo(strings, chars, 1, n - 1, n);
            return strings;
        }

        public void huisuo(List<String> list,char[] chars,int size, int zuo, int you) {
            if (size == chars.length) {
                list.add(String.valueOf(chars));
                return;
            }
            if (zuo < you && zuo > 0) {
                chars[size] = '(';
                huisuo(list, chars, size + 1, zuo - 1, you);
                chars[size] = ')';
                huisuo(list, chars, size + 1, zuo, you - 1);
            } else if (zuo > 0 && zuo == you) {
                chars[size] = '(';
                huisuo(list, chars, size + 1, zuo - 1, you);
            } else if (zuo == 0) {
                chars[size] = ')';
                huisuo(list, chars, size + 1, zuo, you - 1);
            }
        }
    }

    //全排列
    class Solution15 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> arrayLists = new ArrayList<>();
            LinkedList<Integer> linkedList = new LinkedList<>();
            backtrack(arrayLists, linkedList, nums);
            return arrayLists;
        }

        public void backtrack(List<List<Integer>> list, LinkedList<Integer> tempList, int[] nums) {
            if (tempList.size() == nums.length) {
                list.add(new ArrayList<>(tempList));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;
                }
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.removeLast();
            }
        }
    }
    //子集
    class Solution16 {
        //方法一，回溯方法
        public List<List<Integer>> subsets(int[] nums) {

            List<List<Integer>> list = new ArrayList<>();
            backtrack(list, nums, 0, new ArrayList<Integer>());
            return list;
        }

        public void backtrack(List<List<Integer>> list, int[] nums, int num, ArrayList<Integer> result) {
            list.add(new ArrayList<>(result));
            for (int i = num; i < nums.length; i++) {
                result.add(nums[i]);
                backtrack(list, nums, i + 1, result);
                result.remove(result.size() - 1);
            }
        }
        //方法二，位运算方法
        public static List<List<Integer>> subsets2(int[] nums) {
            //子集的长度是2的nums.length次方，这里通过移位计算
            int length = 1 << nums.length;
            List<List<Integer>> res = new ArrayList<>(length);
            //遍历从0到length中间的所有数字，根据数字中1的位置来找子集
            for (int i = 0; i < length; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    //如果数字i的某一个位置是1，就把数组中对
                    //应的数字添加到集合
                    if (((i >> j) & 1) == 1) {
                        list.add(nums[j]);
                    }
                }
                res.add(list);
            }
            return res;
        }

    }

    //单词搜索
    class Solution17 {

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean dfs(char[][] board, int row, int cow, String word, int k) {

            if (row < 0 || row >= board.length
                    || cow < 0 || cow >= board[0].length
                    || board[row][cow] != word.charAt(k)) {

                return false;
            }
            if (word.length()-1 == k) {
                return true;
            }
            char temp = board[row][cow];
            board[row][cow] = '#';
            boolean res = dfs(board, row, cow + 1, word, k + 1) ||
                    dfs(board, row + 1, cow, word, k + 1) ||
                    dfs(board, row, cow - 1, word, k + 1) ||
                    dfs(board, row - 1, cow, word, k + 1);
            board[row][cow] = temp;
            return res;
        }

    }

}
