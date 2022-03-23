package primary_algorithms;



import sort.ListNode;

import java.util.List;

public class DoublePointer {
    public static void main(String[] args) throws Exception{
        try {
            int x = 1;
            int y = 0;
            int z = x / y;
        } finally {
            System.out.println("finally");

        }
        System.out.println("继续执行！");
        Integer integer = 1;
        int[] ints = new int[2];

    }
/*
汉罗塔实现
 */
    public static void hlt(int num,String a,String b,String c) {
        if (num == 1) {
            System.out.println(a + "->" + c);
        } else {
            hlt(num - 1, a, c, b);
            System.out.println(a + "->" + c);
            hlt(num - 1, b, a, c);
        }
    }
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int length = numbers.length;
            int i = 0;
            int j = length - 1;
            int[] ints = new int[2];
            int sum;
            while (i != j) {
                sum = numbers[i] + numbers[j];
                if (sum > target) {
                    j--;
                } else if (sum < target) {
                    i++;
                } else {
                    ints[0] = i;
                    ints[1] = j;
                    return ints;
                }
            }
            return ints;
        }
    }

    class Solution2 {
        public boolean judgeSquareSum(int c) {
            long max = (int) Math.sqrt(c);
            long i = 0;
            long sum = 0;
            while (i <= max) {
                sum = i * i + max * max;
                if (sum == c) {
                    return true;
                } else if (sum < c) {
                    i++;
                } else {
                    max--;
                }
            }
            return false;
        }
    }

    class Solution3 {
        public String reverseVowels(String s) {
            char[] chars = s.toCharArray();
            int length = s.length();
            int j = length-1;
            int i = 0;
            while (i < j) {
                while (i < j && !is(chars[i])) {
                    i++;
                }
                while (i < j && !is(chars[j])) {
                    j--;
                }
                if (i < j) {
                    swap(chars, i, j);
                    i++;
                    j--;
                }
            }

            return String.valueOf(chars);
        }

        public boolean is(char s) {
            return "aeiouAEIOU".indexOf(s)>=0;
        }

        public void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    class Solution4 {
        public boolean validPalindrome(String s) {
            char[] chars = s.toCharArray();
            int length = chars.length;
            int j = length - 1;
            int i = 0;
            while (i < j) {
                if (chars[i] == chars[j]) {
                    i++;
                    j--;
                } else {
                    return validPalindrome(s, i, j - 1) || validPalindrome(s, i + 1, j);
                }
            }
            return true;

        }
        public boolean validPalindrome(String s, int low, int high) {
            for (int i = low, j = high; i < j; ++i, --j) {
                char c1 = s.charAt(i), c2 = s.charAt(j);
                if (c1 != c2) {
                    return false;
                }
            }
            return true;
        }

    }
    class Solution5 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int length = n + m - 1;
            m--;
            n--;
            while (m >= 0 && n >= 0) {
                if (nums1[m] > nums2[n]) {
                    nums1[length--] = nums1[m--];

                } else {
                    nums1[length--] = nums2[n--];
                }
            }
            if (m < 0) {
                while (n >= 0) {
                    nums1[length--] = nums2[n--];
                }
            } else {
                while (m >= 0) {
                    nums1[length--] = nums1[m--];
                }
            }
        }
    }
/*
判断是否有环链表
 */
    public class Solution6 {
        public boolean hasCycle(ListNode head) {
            if(head == null || head.next == null){
                return false;
            }
            ListNode kuai = head.next;
            ListNode man = head;
            while(man != kuai){
                if(kuai == null || kuai.next == null){
                    return false;
                }
                kuai = kuai.next.next;
                man = man.next;
            }
            return true;
        }
    }

    class Solution7 {
        public String findLongestWord(String s, List<String> dictionary) {
            String result = "";
            for (String s1 : dictionary) {
                int i = 0;
                int j = 0;
                while (i < s1.length() && j < s.length()) {
                    if (s1.charAt(i) == s.charAt(j)) {
                        i++;
                    }
                    j++;
                }
                if (i == s1.length()) {
                    if ((s1.length() > result.length()) || ((s1.length() == result.length()) && (s1.compareTo(result) < 0))) {
                        result = s1;
                    }
                }
            }
            return result;
        }
    }
}
