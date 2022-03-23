package primary_algorithms;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        int[] nums = {2, 4, 1, 45, 3, 4, 7, 45, 6};
        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int first = nums[left];
        int l = left;
        int r = right;
        while (left < right) {
            while (left < right && (nums[right] >= first)) {
                right--;
            }
            while (left < right && (nums[left] <= first)) {
                left++;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        nums[l] = nums[left];
        nums[left] = first;
        quickSort(nums, l, left - 1);
        quickSort(nums, left + 1, r);

    }
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            quickSort(nums, 0, nums.length - 1);
            return nums[nums.length - k];
        }

        public void quickSort(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }
            int first = nums[left];
            int l = left;
            int r = right;
            while (left < right) {
                while (left < right && (nums[right] >= first)) {
                    right--;
                }
                while (left < right && (nums[left] <= first)) {
                    left++;
                }
                if (left < right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
            }
            nums[l] = nums[left];
            nums[left] = first;
            quickSort(nums, l, left - 1);
            quickSort(nums, left + 1, r);

        }
    }

    class Solution2 {
        public String frequencySort(String s) {
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                int frequency = map.getOrDefault(c, 0) + 1;
                map.put(c, frequency);
            }
            List<Character> list = new ArrayList<Character>(map.keySet());
            Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
            StringBuffer sb = new StringBuffer();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                char c = list.get(i);
                int frequency = map.get(c);
                for (int j = 0; j < frequency; j++) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

}
