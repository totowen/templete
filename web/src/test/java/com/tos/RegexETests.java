package com.tos;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qq136 on 2017/7/20.
 */

public class RegexETests {

    public static void main(String[] args) {
        //简单认识正则表达式的概念
        /*p("abc".matches("..."));//一个点代表一个字符
        p("a234a".replaceAll("\\d","-"));//“\d”代表一个数字  ，java中两个反斜杠代表一个反斜杠（转译字符）
        Pattern p = Pattern.compile("[a-z]{3}");//匹配3位字符串的每个字符是否在a-z之间
        Matcher m = p.matcher("adb");
        p(m.matches());
        p("adbc".matches("[a-z]{3}"));*/

        //初步认识 . * + ?
       /* p("a".matches("."));
        p("aa".matches("aa"));
        p("aaaa".matches("a*"));
        p("aaaa".matches("a+"));
        p("".matches("a*"));
        p("aaaa".matches("a?"));
        p("".matches("a?"));
        p("a".matches("a?"));
        p("2146846516879846".matches("\\d{3,100}")); //花括号表示数字出现次数
        p("192.168.0.aaa".matches("\\d(1,3)\\.\\d(1,3)\\.\\d(1,3)\\.\\d(1,3)"));
        p("192".matches("[0-2][0-9][0-9]"));*/

       //范围

       /* p("a".matches("[abc]"));
        p("a".matches("[^abc]")); //非
        p("A".matches("[a-zA-Z]"));
        p("A".matches("[a-z]|[A-Z]"));
        p("A".matches("[a-z[A-Z]]"));
        p("R".matches("[A-Z&&[RFG]]"));*/

        //认识\s(空字符) \w   \d \
       /* p(" \n\r\t".matches("\\s{4}"));
        p(" ".matches("\\S"));
        p("a_8".matches("\\w{3}"));
        p("abc888&^%".matches("[a-z]{1,3}\\d+[&^#%]+"));
        p("\\".matches("\\\\"));*/

        //POSIX Style
//        p("a".matches("\\p{Lower}"));

        //boundary 边界匹配

        /*p("hello sir".matches("^h.*"));
        p("hello sir".matches(".*ir$"));
        p("hello sir".matches("^h[a-z]{1,3}o\\b.*"));
        p(" \n".matches("^[\\s&&[^\\n]]*\\n$"));*/
        //whilte lines

       /* p("aaa 8888c".matches(".*\\d{4}."));
        p("aaa 8888c".matches(".*\\b\\d{4}."));
        p("aaa8888c".matches(".*\\d{4}."));
        p("aaa8888c".matches(".*\\b\\d{4}."));*/

        /*p("136248471@qq.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));
        p("123456".matches("[0-9]*"));*/


        //matches(找所有的字符串)  find(找子串) lookingAt(从字符串头部开始找)
        //matches与find方法相互影响
/*
        Pattern p = Pattern.compile("\\d{3,5}");
        String s = "123-4565-234-00";
        Matcher m = p.matcher(s);
        p(m.matches());//吃掉前四个字符串
        m.reset();//重置将所有字符串恢复

        p(m.find());//吃掉前几个符合匹配的字符串
        p(m.start()+"-"+m.end());
        p(m.find());//同上
        p(m.start()+"-"+m.end());
        p(m.find());//同上
        p(m.start()+"-"+m.end());
        p(m.find());//同上

        p(m.lookingAt());//每次都从头上开始找匹配字符
        p(m.lookingAt());
        p(m.lookingAt());
        p(m.lookingAt());*/

        //replace
       /* Pattern p = Pattern.compile("java",Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher("java Java JavA jAVa sdfsfad");
        StringBuffer b = new StringBuffer();
        int i = 0;
        while(matcher.find()){
            i++;
            if(i%2==0){
                matcher.appendReplacement(b,"java");
            }else{
                matcher.appendReplacement(b,"JAVA");
            }
        }
        matcher.appendTail(b);
        p(b);*/

       //group
        /*Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
        Matcher m = p.matcher("123aa 34567bb 1342cc 00");
        while(m.find()){
            p(m.group(2));
        }*/

        /*String s = "123@qq.comssdad  sdfsadfasgsdfsadf abcd@163.com";
        Pattern p = Pattern.compile("([\\w[.-]]+)(@[\\w[.-]]+\\.[\\w]+)");
        Matcher m = p.matcher(s);
        while(m.find()){
            p(m.group(2));
        }*/

    }

    public static void p(Object o){
        System.out.println(o);
    }

}
