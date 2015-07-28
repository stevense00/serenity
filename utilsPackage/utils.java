package utilsPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class utils {
	
	/**
	 * stringPattern
	 * 
	 * Given a pattern, e.g. "aba" return true if String matches e.g. "xyabxy"
	 * 
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static boolean stringPattern(String string, String pattern) {
		int patternSize = string.length()/pattern.length();
		List<String> stringList = new ArrayList<String>();
		Hashtable<Character, String> table = new Hashtable<Character, String>();
		int i = 0;
		while (i < string.length()) {
			stringList.add(string.substring(i, i+patternSize)); 
			i+=patternSize;
		}
		table.put(pattern.charAt(0), stringList.get(0));
		for (i=1; i<stringList.size(); i++) {
			if (table.containsKey(pattern.charAt(i))) {
				if ((stringList.get(i).equals(table.get(pattern.charAt(i)))) == false) {
					return false;
				}
			}
			else {
				if (table.containsValue(stringList.get(i))) {
					return false;
				}
				table.put(pattern.charAt(i), stringList.get(i));
			}
		}
		System.out.println("Pattern = " + pattern);
		System.out.println("String List = " + stringList);
		return true;
	}
	/* end stringPattern() */
	
	
	
	public static void connectMongoDB() {
		MongoClient mongoClient = new MongoClient( "localhost", 27017 );
		MongoDatabase db = mongoClient.getDatabase("edb");
		MongoCollection<Document> coll = db.getCollection("mycol");
		Document doc = new Document("type", "Book").append("title", "Book 1").
				append("likes", 200).append("by", "Eric");
		coll.insertOne(doc);
		mongoClient.close();
	}
	
	
	/**
	 * ransomeNote
	 * 
	 * @param magazine
	 * @param note
	 * @return
	 */
	public static boolean ransomNote( String magazine, String note ) {
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
		
		for (int i=0; i < magazine.length(); i++) {
			if (magazine.charAt(i) == ' ') {
				//Ignore Spaces
			}
			else if (table.containsKey(magazine.charAt(i))) {
				table.put(magazine.charAt(i), (table.get(magazine.charAt(i))+1));
			}
			else {
				table.put(magazine.charAt(i), 1);
			}
		}
		for (int i=0; i < note.length(); i++) {
			if (note.charAt(i) == ' ') {
				//Ignore Spaces
			}
			else if (table.containsKey(note.charAt(i))) {
				table.put(note.charAt(i), (table.get(note.charAt(i))-1));
			}
			else {
				table.put(note.charAt(i), -1);
			}
		}
		
		for (Integer value : table.values()) {
			if (value < 0)
				return false;
		}
		
		return true;
	}
	

}
