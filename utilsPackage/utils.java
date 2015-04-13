package utilsPackage;

import java.util.Collection;
import java.util.Hashtable;

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
	
	public static void connectDB() {
		MongoClient mongoClient = new MongoClient( "localhost", 27017 );
		MongoDatabase db = mongoClient.getDatabase("edb");
		MongoCollection<Document> coll = db.getCollection("mycol");
		Document doc = new Document("title", "MongoDB").append("description", "database").
				append("likes", 100).append("by", "Eric");
		coll.insertOne(doc);
		
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