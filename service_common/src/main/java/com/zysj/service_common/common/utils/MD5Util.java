package com.zysj.service_common.common.utils;


import java.security.MessageDigest;

public class MD5Util {
    private final static char[] HEXD = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};//'a','b','c','d','e','f',

    public final static String encode(String s) {
        return encode(s, null);
    }

    public final static String encode(String s, final String encoding) {
        final char[] hexDigits = HEXD;
        try {
            byte[] strTemp = (encoding == null ? s.getBytes() : s.getBytes(encoding));
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(byte0 & 0xF)];
            }
            return new String(str);
        } catch (Exception e) {
        }
        return null;
    }
//  public static String getMD5(String msg,String salt){
//    return new Md5Hash(msg,salt,4).toString();
//  }
//   public  static  void main(String[] arg){
//     Date date = new Date();
//     long time = date.getTime();
//     String uuid = "6EE33D159EE365B779FFFA7D7CF97447";
//     System.out.println(time);
//     String token = MD5Util.encode(time + uuid).toUpperCase();
//     System.out.println(token);
////
////     String[] strings = "true|".split("\\|");
////     System.out.println(strings.length);
////     String secret = "unicorn";
////     String uid = MD5Util.encode(secret + UUID.randomUUID()).toUpperCase();
////     System.out.println(uid);
//   }
}
