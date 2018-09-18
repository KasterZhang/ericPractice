package tools;

// import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class SmineDESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DESede";
    /**
     * 加密算法/解密算法/工作模式/填充方式
     */
    private static final String TRANSFORMATION = "DESede/ECB/PKCS5Padding";

    /**
     * 生成随机密钥
     * 
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(new SecureRandom());
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * 生成固定密钥
     * 
     * @param seed
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey(byte[] seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(new SecureRandom(seed));
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * 生成固定密钥
     * 
     * @param password
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey(String password) throws Exception {
        return generateKey(password.getBytes());
    }

    /**
     * 执行加密 
     * 
     * @param content
     * @param key长度必须为8位,即64bit
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String str, byte[] key) throws Exception {
        byte[] content = str.getBytes();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, ALGORITHM));
        byte[] output = cipher.doFinal(content);
        return output;
    }
    /**
     * 执行加密 
     * 
     * @param content
     * @param key长度必须为8位,即64bit
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] content, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, ALGORITHM));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    /**
     * 执行加密
     * 
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] content, String password) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(password));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    /**
     * 执行解密
     * 
     * @param content
     * @param key长度必须为8位,即64bit
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, ALGORITHM));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    /**
     * 执行解密
     * 
     * @param content
     * @param key长度必须为8位,即64bit
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(String str, byte[] key) throws Exception {
        byte[] content = str.getBytes();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, ALGORITHM));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    /**
     * 执行解密
     * 
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, String password) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
        byte[] output = cipher.doFinal(content);
        return output;
    }
    /**
     * 执行解密
     * 
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(String str, String password) throws Exception {
        byte[] content = str.getBytes();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    

    public static void main(String[] args) throws Exception { 
        String str = "3306";

        SecretKey key = SmineDESUtil.generateKey("zksj2018");
        System.out.println("原文:" + str);
        // Base64.Encoder
        System.out.println("密钥:"+ Base64.encodeBase64String(key.getEncoded()));
        byte[] data=SmineDESUtil.encrypt(str, key.getEncoded());
        System.out.println("加密后:"+Base64.encodeBase64String(data));
        data=SmineDESUtil.decrypt(data, key.getEncoded());  
        System.out.println("解密后:"+new String(data));
        // pwdEncode = Arrays.toString(encrypt("ZCMF577%IIK9PFGF".getBytes(), "zksj18"));
        // System.out.println("加密结果:" + pwdEncode);
        // System.out.println(pwdEncode.getBytes());
        // System.out.println("解密结果:" + decrypt(pwdEncode.getBytes(), "zksj18"));
        // System.out.println(Arrays.toString(encrypt("使用3条56位的密钥对 数据进行三次加密。".getBytes(), "012345")));
        // System.out.println(new String(decrypt(encrypt("使用3条56位的密钥对 数据进行三次加密。".getBytes(), "012345"), "012345")));
        // System.out.println(
        //         Arrays.toString(encrypt("使用3条56位的密钥对 数据进行三次加密。".getBytes(), "012345670123456701234567".getBytes())));
        // System.out.println(
        //         new String(decrypt(encrypt("使用3条56位的密钥对 数据进行三次加密。".getBytes(), "012345670123456701234567".getBytes()),
        //                 "012345670123456701234567".getBytes())));
    }
}