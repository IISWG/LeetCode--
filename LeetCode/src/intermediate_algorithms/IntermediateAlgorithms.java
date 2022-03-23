package intermediate_algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntermediateAlgorithms {

    /*
    矩阵置零
     */
    class Solution4 {
        public void setZeroes(int[][] matrix) {
            int row = matrix.length;
            int cow = matrix[0].length;
            //标记第一行是否有数字0
            boolean row1 = false;
            //标记第一列是否有数字0
            boolean col = false;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        //如果第一行或者第一列本来就有0，就把他标记一
                        //下，最后再把第一行或者第一列全部置为0
                        if (i == 0) {
                            row1 = true;
                        }
                        if (j == 0) {
                            col = true;
                        }
                        //把最上面一行和最左边一列对应的位置标注为0
                        matrix[0][j] = matrix[i][0] = 0;
                    }
                }
            }

            for (int i = 1; i < cow; i++) {
                if (matrix[0][i] == 0) {
                    for (int j = 1; j < row; j++) {
                        matrix[j][i] = 0;
                    }
                }
            }
            for (int i = 1; i < row; i++) {
                if (matrix[i][0] == 0) {
                    for (int j = 1; j < cow; j++) {
                        matrix[i][j] = 0;
                    }
                }
            }
            if(row1){
                for(int i=0;i<cow;i++){
                    matrix[0][i]=0;
                }
            }
            if(col){
                for(int i=0;i<row;i++){
                    matrix[i][0]=0;
                }
            }
        }
    }
    /*
    三数之和
     */
    class Solution3 {
        public List<List<Integer>> threeSum(int[] nums) {
            //先对数组进行排序
            Arrays.sort(nums);

            ArrayList<List<Integer>> result = new ArrayList<>();

            // ArrayList<Integer> tempArray = new ArrayList<>(3);
            // tempArray.add(0);
            // tempArray.add(0);
            // tempArray.add(0);
            for (int i = 0; i < nums.length - 2; i++) {
                //过滤掉重复的
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                if (nums[i] > 0) {
                    break;
                }
                int biaozhun = -nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] < biaozhun) {
                        left++;
                    } else if (nums[left] + nums[right] == biaozhun) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        //过滤掉重复的
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else {
                        right--;
                    }
                }
            }
            return result;
        }
    }

}
