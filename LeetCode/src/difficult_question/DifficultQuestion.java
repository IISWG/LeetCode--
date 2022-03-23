package difficult_question;

import javax.swing.tree.TreeNode;

/**
 * @author ASUS
 */

public class DifficultQuestion {
    class TreeNode {
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

    class Solution {
        int resultMaxPath = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            getMaxPath(root);
            return resultMaxPath;
        }

        public int getMaxPath(TreeNode root){
            if (root == null) {
                return 0;
            }
            int lift = Math.max(getMaxPath(root.left), 0);
            int right = Math.max(getMaxPath(root.right), 0);
            int maxPath = lift + right + root.val;
            if (maxPath > resultMaxPath) {
                resultMaxPath = maxPath;
            }
            return root.val + Math.max(lift, right);
        }
    }


}
