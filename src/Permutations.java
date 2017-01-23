package scatchpad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Decode a string of numbers usinf a mapping 0=A, 1=B, 2=C ... Z=25
 * Assume numeric strings are entered in std input
 * 
 * The String 12 could be decoded as BC(1 2) or M(12) therefore has 2 permutations
 * The String 123 could be decoded as BCD(1 2 3) or BX(1 23) or MD(12 3) therefore has 3 permutations
 * 
 * @author Neil
 */

public class Permutations {

	private static HashMap<String, Character> key;
	
	public static void main(String[] args) throws IOException {
		decode();
	}

	public static HashMap<String, Character> initKey() {
		Character c = 'A';
		HashMap<String, Character> key = new HashMap<String, Character>();
		
		for (int i = 0; i < 26; i++) {
			key.put(Integer.toString(i) , c);
			c++;
		}
		
		return key;
	}

	public static int countPermutations(String s) {
		int perms = 0;
		
		List<String> matchList = new ArrayList<String>();
		for (Iterator iterator = key.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if ( s.startsWith(key) ) {
				matchList.add(key);
			}
		}
		
		perms += matchList.size();
		for (Iterator iterator = matchList.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int subPerms = countPermutations(s.substring(key.length()) );
			if ( subPerms > 1) {
				perms += subPerms - 1;
			}
		}
		
		return perms;
	}
	
	public static void decode() throws IOException {
		int permutations = 0;
		key = initKey();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			if (s.length() > 0)  {
				
				permutations = countPermutations(s);

				System.out.println(permutations);

			} else {
				in.close();
				break;
			}
		}
	}

}
