package com.hawk.utility.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DESTools {
    //算法名称  
    public static final String KEY_ALGORITHM = "DES";  
    //算法名称/加密模式/填充方式  
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";  
    
    public static final String CHART_SET="UTF-8";
        
//    // 还原密钥 
//    private static Key toKey(String key) throws Exception {  
//        DESKeySpec des = new DESKeySpec(key.getBytes(CHART_SET));  
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);  
//        return keyFactory.generateSecret(des);  
//    }  

    
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    /**
     * des加密
     * @param encryptString 要加密的字符串
     * @param encryptKey 秘钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String encryptString ,String encryptKey) throws Exception{
    	IvParameterSpec zeroIv = new IvParameterSpec(iv);
    	SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("utf-8"), "DES");
    	Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    	cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
    	byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
    	return toHexString(encryptedData);
    }
    
    /**
     * des解密
     * @param decryptString 要解密的字符串
     * @param decryptKey 秘钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String decryptString, String decryptKey) throws Exception{
    	 byte[] byteMi = convertHexString(decryptString);
    	 IvParameterSpec zeroIv = new IvParameterSpec(iv);
    	 SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("utf-8"), "DES");
    	 Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    	 cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
    	 byte decryptedData[] = cipher.doFinal(byteMi);
    	 
    	 return new String(decryptedData,"utf-8");
    }
    
    private static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}
    
    private static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}

		return hexString.toString();
	}
    


}
