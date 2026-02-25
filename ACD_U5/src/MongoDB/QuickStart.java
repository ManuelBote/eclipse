package MongoDB;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

public class QuickStart {
	public static void main(String[] args) {
		
		// Cambiar la cadena de conexión a tu base de datos MongoDB
		//mongodb+srv:// <user>:<db_password>@cluster0.plsvy.mongodb.net/?appName=Cluster0
		String uri = "mongodb+srv://mbotez01:mgWx3bbpWR17yYti@cluster1.gfqs9.mongodb.net/?appName=Cluster1";
		
		try (MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase("sample_mflix");
			MongoCollection<Document> collection = database.getCollection("movies");
			Document doc = collection.find(eq("title", "Back to the Future")).first();
			
			if (doc != null) {
				//Todo
				//System.out.println(doc.toJson());
				// o mostrando campos
				System.out.println("Título: " + doc.getString("title"));
				System.out.println("Año: " + doc.getInteger("year"));
				System.out.println("Géneros: " + doc.get("genres"));
				
			} else {
				System.out.println("No matching documents found.");
			}
		}
	}
}