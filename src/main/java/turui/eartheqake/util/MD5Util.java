package turui.eartheqake.util;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//md5加密解密
public class MD5Util {

    public static String md5Encode(String instr)
    {
        MessageDigest md5 = null;
        byte[] byteArray = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byteArray = instr.getBytes("UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i <md5Bytes.length; i++)
        {
            int val = (int)md5Bytes[i] & 0xff;
            if(val < 16)
            {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(val));
        }
        return stringBuffer.toString();
    }

   public static String fileMd5(Part file){
       MessageDigest digest = null;
       byte buffer[] = new byte[8192];
       int len;
       try {
           digest =MessageDigest.getInstance("MD5");
           InputStream is = file.getInputStream();
           while ((len = is.read(buffer)) != -1) {
               digest.update(buffer, 0, len);
           }
           BigInteger bigInt = new BigInteger(1, digest.digest());
           return bigInt.toString(16);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       } finally {
       }
   }

}
