package everyday;


import java.util.LinkedList;
import java.util.List;

public class EveryDay {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*

     */
    /*
    递归思路
    该问题可以转化为：两个树在什么情况下互为镜像？
    如果同时满足下面的条件，两个树互为镜像：
    它们的两个根结点具有相同的值
    每个树的右子树都与另一个树的左子树镜像对称
     */
    class Solution {
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
104. 二叉树的最大深度
思路：当前节点的最大深度 左子树的最大深度与右子树最大深度取最大值加一
     */
    class Solution1 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
/*
96. 不同的二叉搜索树
动态规划
dp[0] = 1;
dp[1] = 1;
 */


    class Solution2 {
        public int numTrees(int n) {
            if (n <= 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] += dp[i - j] * dp[j - 1];
                }
            }
            return dp[n];
        }
    }

     /*
    95. 不同的二叉搜索树 II
     */
     class Solution3 {
         public List<TreeNode> generateTrees(int n) {
             if (n == 0) {
                 return new LinkedList<TreeNode>();
             }
             return generateTrees(1, n);
         }

         public List<TreeNode> generateTrees(int start, int end) {
             LinkedList<TreeNode> treeNodes = new LinkedList<>();
             if (start > end) {
                 treeNodes.add(null);
                 return treeNodes;
             }
             for (int i = start; i <= end ; i++) {
                 List<TreeNode> leftTree = generateTrees(start, i - 1);
                 List<TreeNode> rightTree = generateTrees(i + 1, end);
                 // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
                 for (TreeNode lNode : leftTree) {
                     for (TreeNode rNode : rightTree) {
                         TreeNode headNode = new TreeNode(i);
                         headNode.left = lNode;
                         headNode.right = rNode;
                         treeNodes.add(headNode);
                     }
                 }
             }
             return treeNodes;
         }
     }
}
