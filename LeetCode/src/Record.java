public class Record {

//public class Main {
//
//    public static List<Integer> partitionLabels(String s) {
//        List<Integer> res = new LinkedList<>();
//        // get all character's last postion
//        int[] lastCharPos = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            lastCharPos[s.charAt(i) - 'A'] = i;
//        }
//
//        // two pointers
//        int start = 0, end = 0;
//        for (int i = 0; i < s.length(); i++) {
//            end = Math.max(end, lastCharPos[s.charAt(i) - 'A']);
//            if (end == i) {
//                res.add(end - start + 1);
//                start = end + 1;
//            }
//        }
//        return res;
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        List<Integer> arrayList = partitionLabels(s);
//        for (int i = 0; i < arrayList.size(); i++) {
//            System.out.print(arrayList.get(i)+" ");
//        }
//
//
//
//    }
//}
}
