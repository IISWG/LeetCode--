import java.util.*;


import java.util.Scanner;


/**
 * @author 木心
 */
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int caoz = scanner.nextInt();
//        String kong = scanner.nextLine();
//        String s = scanner.nextLine();
//        char[] chars = s.toCharArray();
//        ArrayList<Character> characters = new ArrayList<>(100000);
//
//        char[] charcopy = new char[s.length()];
//        if (caoz == 1) {
////            int zhong = chars.length / 2;
//            for (char aChar : chars) {
//                characters.add(aChar);
//            }
//            for (int i = 0; i < chars.length; i++) {
//                charcopy[i] = characters.get(characters.size() / 2 + characters.size() % 2 -1);
//                characters.remove( characters.size() / 2 + characters.size() % 2 -1);
//            }
//        } else {
//            for (int i = chars.length-1; i >=0 ; i--) {
//                int size = characters.size()+1;
//                characters.add(size / 2 + size % 2 - 1, chars[i]);
//            }
//        }
//        if (caoz == 1) {
//            for (char c : charcopy) {
//                System.out.print(c);
//            }
//        } else {
//            for (int i = 0; i < characters.size(); i++) {
//                System.out.print(characters.get(i));
//            }
//        }
//    }
//}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =scanner.nextInt();
        int[][] zk = new int[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                zk[i][j] = scanner.nextInt();
            }
        }
        int m = scanner.nextInt();
        int[][] mj = new int[2][m];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                mj[i][j] = scanner.nextInt();
            }
        }
        int[][] bj = new int[n][2];
        bj[0][0] = zk[1][0];
        if (zk[0][0] < mj[0][0]) {
            bj[0][1] = zk[0][0];
        } else {
            int i = 0;
            for ( i = 0; i < m; i++) {
                if (zk[0][0] >= mj[0][0]) {
                    i++;
                }
            }
            bj[0][1] = zk[0][0] - mj[1][i-1];
        }

        int sum = zk[0][0];
        for (int i = 1; i < n; i++) {
            bj[i][0] = bj[i - 1][0] + zk[1][i];
            sum += zk[0][i];
            if (sum < mj[0][0]) {
                bj[i][0] = sum;
            } else {
                int j = 0;
                for (; j < m; j++) {
                    if (sum >= mj[0][j]) {
                        continue;
                    } else {
                        break;
                    }
                }
                bj[i][1] = sum - mj[1][j - 1];
            }
        }
        for (int[] ints : bj) {
            if (ints[0] == ints[1]) {
                System.out.print("B");
            } else if (ints[0] < ints[1]) {
                System.out.print("Z");
            } else {
                System.out.print("M");
            }
        }
    }
}























































