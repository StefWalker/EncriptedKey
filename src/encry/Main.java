package encry;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Main {
	
	private static int contador = 0;
	private static SecretKeySpec secretKey;
	private static String message = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
	
	public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            System.out.println(contador);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
	
	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			byte[] key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
			//return secretKey;
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			//return null;
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			//return null;
		}
	}

	public static void main(String[] args) {
		
		for (int i = 0; i < 26; i++) {
			for(int j = 0; j < 10; j++) {
				char ch = (char) ('a' + i);
				char nu = (char) ('0' + j);
				String num = Character.toString(nu);
		    	System.out.printf("29dh120" + ch + "dk1" + nu + "3%n");
		    	System.out.println(decrypt(message, "29dh120" + ch + "dk1" + nu + "3"));
		    	contador++;
			}
		}
	}
}
