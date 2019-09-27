package encry;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;
import java.util.List;

public class Main {
	
	private static Character[] charArray =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static Character[] intArray = {'0','1','2','3','4','5','6','7','8','9'};
	private static List<Character> intList = Arrays.asList(intArray);
	private static List<Character> charList = Arrays.asList(charArray);
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
	
	public static void shuffler() {	
		Collections.shuffle(intList);
		intList.toArray(intArray);
		System.out.println(intList);	
		
		Collections.shuffle(charList);
		charList.toArray(charArray);
		System.out.println(charList);
	}

	
	public static <T> ArrayList<T[]> chunks(){
	    ArrayList<T[]> chunks = new ArrayList<T[]>();
	    int n = charList.size()/2;
	    for (int i = 0; i < charList.size(); i += n) {
	        T[] chunk = (T[])charList.subList(i, Math.min(charList.size(), i + n)).toArray();         
	        chunks.add(chunk);
	    }
	    return chunks;
	}

	public static <T> void main(String[] args) {
		shuffler();
		ArrayList<T[]> chunks = chunks();
		
		
		/*
		for (int i = 0; i < 26; i++) {
			for(int j = 0; j < 10; j++) {
				char ch = (char) ('a' + i);
				char nu = (char) ('0' + j);
				String num = Character.toString(nu);
		    	System.out.printf("29dh120" + ch + "dk1" + nu + "3%n");
		    	System.out.println(decrypt(message, "29dh120" + ch + "dk1" + nu + "3"));
		    	contador++;
			}
		}*/
		
		System.out.println(chunks.get(0)[3]);
	}
}
