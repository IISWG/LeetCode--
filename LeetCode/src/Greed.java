import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Greed {
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int manzu = 0;
            for (int i = 0, j = 0; i < s.length && j < g.length; ) {
                if (s[i] >= g[j]) {
                    manzu++;
                    j++;
                }
                i++;
            }
            return manzu;
        }
    }

    class Solution1 {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[1] - interval2[1];
                }
            });

            int num = intervals.length;
            int right = intervals[0][1];
            int liuNum = 1;
            for (int i = 1; i < num; i++) {
                if (intervals[i][0] >= right) {
                    ++liuNum;
                    right = intervals[i][1];
                }
            }

            return num - liuNum;
        }
    }

    class Solution2 {
        public int findMinArrowShots(int[][] points) {
            if (points.length == 0) {
                return 0;
            }
            if (points.length == 1) {
                return 1;
            }

            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[0] - interval2[0];
                }
            });

            int num = 1;
            int lift = points[0][0];
            int right = points[0][1];
            for (int i = 1; i < points.length; i++) {
                if (points[i][0] >= lift && points[i][0] <= right) {
                    if (points[i][1] < right) {
                        right = points[i][1];
                    }
                    lift = points[i][0];
                    continue;
                }
                lift = points[i][0];
                right = points[i][1];
                num++;
            }
            return num;
        }
    }

    /*
    根据身高和序号重组队列
406. Queue Reconstruction by Height(Medium)
     */


}