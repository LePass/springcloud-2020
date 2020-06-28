package com.replace;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wangx
 * @create 2020/5/26
 * @since 1.0.0
 */
public class replaceTest {

    //F_CLEAN_NAME
    @Test
    public void replace() {
        String str = " （张  三   *@#），ＱＷＥＲＴＹＵＩＯＰＡＳＤＦＧＨＪＫＬＺＸＣＶＢＮＭ１２３４５６７８９ ";

        System.out.println("替换前：" + str.length());
        System.out.println(str);
        //含有全角的数值英文字母，则需转换成半角的数值及英文字母
        str = replaceTest.toDBC(str);
        //中文小括号转英文小括号,中文逗号转英文逗号
        str = str.replaceAll("（", "(");
        str = str.replaceAll("）", ")");
        str = str.replaceAll("，", ",");
        //把除了汉字、字母、数字、空格、'.'、英文小括号,逗号、'&'外的字符去除
        str = str.replaceAll("[^啊-齄丂-狛狜-a-zA-Z0-9·.&\\(\\) \\,\\'']", "");
        //删除中文之后的空格
        str = str.replaceAll("([^a-zA-Z])", "");
        //去掉前后空格
        str = str.trim();
        //把长度小于2个字符的名字清洗置空处理
        if (str.length() < 2) {
            str = "";
        }
        System.out.println("替换后：" + str.length());
        System.out.println(str);
    }


    public static String toDBC(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = charToDBC(chars[i]);
        }
        return new String(chars);
    }
    protected static char charToDBC(char ch) {
        if (ch == '\u3000') {
            return '\u0020';// 半角空格:
        } else if (ch > '\uFF00' && ch < '\uFF5F') {
            return (char) (ch - 65248);
        }
        return ch;
    }
}
