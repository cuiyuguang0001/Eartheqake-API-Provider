package turui.earthquake.test;

import turui.eartheqake.util.CommonUtil;

import java.util.Date;

public class Test1 {

    public static void main(String[] args) {

        System.out.println(CommonUtil.timestampToStr(Long.valueOf(CommonUtil.getTineLine())));

//        String s = "821568758";
//        System.out.println(MD5Util.md5Encode(s));
//        String s2 = MD5Util.md5Encode(s);
//        System.out.println(MD5Util.md5Convert(s2)));
//        String s1 = MD5Util.md5Encode(s);
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        char[] chars = s1.toCharArray();
//        byte[] b = new byte[chars.length];
//        for(int i = 0; i < chars.length; i++)
//            b[i] = (byte) chars[i];
//        StringBuffer stringBuffer = new StringBuffer();
//        b = md5.digest(b);
//        for(int i = 0; i <b.length; i++)
//        {
//            int val = ((int)b[i]) & 0xff;
//            if(val < 16)
//            {
//                stringBuffer.append("0");
//            }
//            stringBuffer.append(Integer.toHexString(val));
//        }
//        System.out.println(stringBuffer.toString());

    }

}
