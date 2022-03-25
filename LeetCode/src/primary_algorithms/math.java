package primary_algorithms;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class math {
    public static void main(String[] args) {
        String s;

    }
    /*
    计数质数
    给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     */
    class Solution {
        public int countPrimes(int n) {
            boolean[] booleans = new boolean[n];
            int sum = 0;
            for (int i = 2; i < n; i++) {
                if (booleans[i]) {
                    continue;
                }
                sum++;
                for (int j = i; j < n; j=j+i) {
                    booleans[j] = true;
                }
            }
            return sum;
        }
    }
/*
给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。

整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 */
    class Solution1 {
        public boolean isPowerOfThree(int n) {
            if (n > 1) {
                while (n % 3 == 0) {
                    n /= 3;
                }
            }
            return n == 1;

            /*
            *递归方式
             if(n>1 && n%3==0){
              return isPowerOfThree(n/3);
          }
          else{
              return n==1;
          }
             */
        }
    }
/*
罗马数字转整数
 */
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        //所有可能的都列出来
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int res = 0;
        for (int i = 0; i < s.length(); ) {
            //先截取两个字符，如果这两个字符存在于map中，就表示他们是一个整体。否则就截取一个
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                res += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                res += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        return res;
    }
}
class Father{
    private String name = "father";
    private int age = 45;
    class test{

    }
    public Father() {

    }
     int get(){
         System.out.println("father:"+age);
        return 0;
    }
}
interface In{
      void test();
}

class Children extends Father {
    private int age = 23;
    @Override
    int get(){
        super.get();
        return 0;
    }

}
class test2{
    public void test() {
        Father father = new Father();
        Father.test test = father.new test();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder1 = new StringBuilder();
    }
}

class Outer {
    void outMethod() {
        int a = 10;
        class Inner {
            void innerMethod() {
                System.out.println(a);
            }
        }
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
    }
}

class t2 extends Object implements Cloneable {

    public void tet() throws FileNotFoundException {
        ArrayList<Integer> objects = new ArrayList<>();
        CopyOnWriteArrayList<Integer> objects5 = new CopyOnWriteArrayList<>();
        LinkedList<Integer> objects3 = new LinkedList<>();
        Vector<Integer> objects4 = new Vector<>();

        Map<String, Integer> HashMap = new HashMap<>();
        TreeMap<Object, Object> objectObjectTreeMap1 = new TreeMap<>();
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        HashSet<Object> objects1 = new HashSet<>();
        TreeMap<String, Object> objectObjectTreeMap = new TreeMap<>();
        PriorityQueue<Object> objects2 = new PriorityQueue<>();
        Thread thread = new Thread();
        CharArrayReader charArrayReader = new CharArrayReader(new char[5]);
        StringReader stringReader = new StringReader("");
        BufferedReader bufferedReader = new BufferedReader(charArrayReader);
        try {
            bufferedReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class thread1 extends Thread {

}
