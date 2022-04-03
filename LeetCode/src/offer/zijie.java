package offer;

import java.util.ArrayList;
import java.util.HashMap;

//划分字符串
public class zijie {
        public static void main(String[] args) {
            ArrayList<Integer> list = Substring("abcafhdfgsdgskuiu");
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
        public static ArrayList<Integer> Substring(String s) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                hashMap.put(s.charAt(i), i);
            }
            ArrayList<Integer> arrayList = new ArrayList<>();
            int end = 0;
            int start = 0;
            for (int i = 0; i < s.length(); ) {
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
