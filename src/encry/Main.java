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
	
	/*
	 * Funcion encarga de la desencriptacion de datos
	 */
	public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            return null;
        }
    }
	
	/*
	 * Funcion encargada de la conversion de la llave a la valida
	 */
	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			byte[] key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Encargada de la seleccion de llaves, encuentra la valida en caso de lograr con exito la desencriptacion
	 */
	public static <T> void tester(ArrayList<T[]> Chunks, int chunk) {
		for(int cantidad = 0; cantidad != Chunks.get(chunk).length; cantidad ++) {
			for(int numero = 0; numero != intList.size(); numero ++) {				
		    	String mensaje = decrypt(message, "29dh120" + Chunks.get(chunk)[cantidad] + "dk1" + intList.get(numero) + "3");
		    	if(mensaje != null) {
		    		System.out.println("La llave encontrada!!  29dh120" + Chunks.get(chunk)[cantidad] + "dk1" + intList.get(numero) + "3");
		    		System.out.println(mensaje);
		    		System.out.println("En la iteracion "+ contador);
		    	}
		    	contador++;
			}
		}
	}
	
	/*
	 * Funcion shuffler aplica el shuffle a los arrays de posibilidades
	 */
	public static void shuffler() {	
		Collections.shuffle(intList);
		intList.toArray(intArray);
		System.out.println(intList);	
		
		Collections.shuffle(charList);
		charList.toArray(charArray);
		System.out.println(charList);
	}

	/*
	 * Da la division del array base a chunks de datos 
	 */
	public static <T> ArrayList<T[]> chunks(){
	    ArrayList<T[]> chunks = new ArrayList<T[]>();
	    int division = charList.size()/4;
	    for (int contador = 0; contador < charList.size(); contador += division) {
	        T[] chunk = (T[])charList.subList(contador, Math.min(charList.size(), contador + division)).toArray();         
	        chunks.add(chunk);
	    }
	    return chunks;
	}

	/*
	 * Funcion Main
	 */
	public static <T> void main(String[] args) {
		shuffler();
		ArrayList<T[]> Chunks = chunks();
		
		for(int chunk = 0; chunk != Chunks.size()/2; chunk++ ) {
			tester(Chunks, chunk);
		}
		System.out.println("Si el mensaje no logro decodificarse el porcentaje de exito fue de " + 50 + " %");
		System.out.println("Debido a la forma de aplicacion, la division de datos se da por la mitad");
	}
}
