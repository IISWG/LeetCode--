package primary_algorithms;

import org.omg.CORBA.ARG_OUT;

public class test {
    public static void main(String[] args) {
        String s = new String("1");
        String intern = s.intern();
        String s2 = "1" ;
        System.out.println(s == s2);

//
//        String s5 = new String("2");
//        String s6 = new String("1");
//        String s3 = new String("2") + new String("1");
//        String s3 = s6 + s5;
//        String s = new String("1");
//        s.intern();
//        String s2 = "1";
//        System.out.println(s == s2);

        String s3 = new String("ja") + new String("va");
//        String s3 = "1" + "1";
        s3.intern();
//        String s4 = "11";
        String s4 = "java";
        System.out.println(s3 == s4);
//        System.out.println(s3.equals(s4));
//
//        String intern1 = s3.intern();

//        String s4 = "21" ;
//        System.out.println(intern1 == s3);
//        System.out.println(intern1 == s4);
//        System.out.println(s3 == s4);
    }
}
