package sample.bank.data;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import sample.bank.bean.CustomerBean;

public class CustomerMongoDB {

	public String addRecord(String userId, CustomerBean cust) {

		try {

			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			//MongoClient mongo = new MongoClient("583aedf889f5cff7ad000083-prasdev.rhcloud.com", 37811);
			MongoClient mongo = new MongoClient(System.getenv("OPENSHIFT_MONGODB_DB_HOST") , Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT")));

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("royalsuperbank");
			
			boolean auth = db.authenticate("admin", "MSbB19lPSQsU".toCharArray());

			/**** Get collection / table from 'royalsuperbank' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("user");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("userId", cust.getUserId());
			document.put("password", cust.getPassword());
			document.put("address", cust.getAddress());
			document.put("openingBalance", cust.getOpeningBalance());
			document.put("createdDate", new Date());
			table.insert(document);

			/**** Find and display ****/
			
		    DBCursor cursor = table.find();
		    while(cursor.hasNext()) {
		        System.out.println(cursor.next());
		    }
		    
		    
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("userId", "pras");

			DBCursor cursor1 = table.find(searchQuery);

			while (cursor1.hasNext()) {
				System.out.println(cursor1.next());
			}

			/**** Update ****//*
			// search document where name="mkyong" and update it with new values
			BasicDBObject query = new BasicDBObject();
			query.put("name", "mkyong");

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", "mkyong-updated");

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);

			*//**** Find and display ****//*
			BasicDBObject searchQuery2 = new BasicDBObject().append("name", "mkyong-updated");

			DBCursor cursor2 = table.find(searchQuery2);

			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
			}

			*//**** Done ****//*
			System.out.println("Done");*/

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

}
