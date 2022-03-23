package primary_algorithms;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pri_al {
}

class Solution {

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = "1";
        for (int i = 1; i < n; i++) {
            s = fun(s);
        }
        return s;
    }

    public static String fun(String n) {
        char biaozhun = n.charAt(0);
        int num=1;
        int size = n.length();
        String s = "";
        for (int i = 1; i < size; i++)
        {
            char b = n.charAt(i);
            if (b == biaozhun) {
                num++;
            } else {
                s=s+num+biaozhun;
                biaozhun = b;
                num = 1;
            }
        }
        s=s+num+biaozhun;
        return s;
    }
}
//以下是链表相关的算法

//  Definition for singly-linked list.
  class ListNode {
      int val;
      ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution1 {
    public ListNode reverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        ListNode temp = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            //留着下一步访问的
            temp = head.next;
            //每次访问的原链表节点都会成为新链表的头结点，
            //其实就是把新链表挂到访问的原链表节点的
            //后面就行了
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }


}
class Solution2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = new ListNode();
        ListNode temp = head;
        while(p1!=null&&p2!=null){
            if(p1.val<=p2.val){

                temp.next = p1;
                p1 = p1.next;
            }else{
                temp.next = p2;
                p2 = p2.next;
            }
            temp = temp.next;
        }
        if(p1==null){
            temp.next = p2;
        }else{
            temp.next = p1;
        }
        return head.next;
    }
}


class Solution3 {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }
        //反转后半部分链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    //反转链表
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}

//以下是关于树的算法



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

class Solution4 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left) + 1;
        int r = maxDepth(root.right) + 1;
        return Math.max(l, r);
    }
}

/**
 * 检验二叉搜索树
 */
class Solution5 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

}

/**
 * 对称二叉树
 */
class Solution6 {
    /**使用的递归，思路是判断二叉树是否是对称，需要从子节点开始比较，两个子节点的值必须相同，
     * 并且左子节点的右子节点（如果有）必须等于右子节点的左子节点，左子节点的左子节点必须等于右子节点的右子节点。
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null) {
            return true;
        }
        if(left == null || right == null || left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}

/**
 * 二叉树的层序遍历
 */
class Solution7 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //边界条件判断
        if (root == null) {
            return new ArrayList<>();
        }

        //队列
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        //根节点入队
        queue.add(root);
        //如果队列不为空就继续循环
        while (!queue.isEmpty()) {
            //BFS打印，levelNum表示的是每层的结点数
            int levelNum = queue.size();
            //subList存储的是每层的结点值
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                //出队
                TreeNode node = queue.poll();
                subList.add(node.val);
                //左右子节点如果不为空就加入到队列中
                if (node.left != null) {
                    queue.add(node.left);
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                } else {
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            //把每层的结点值存储在res中，
            res.add(subList);
        }
        return res;
    }
}




