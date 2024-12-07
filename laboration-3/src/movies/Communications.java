package movies;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class Communications {
    private MongoClient mc;
    private MongoDatabase db;

    public Communications() {
        mc = MongoClients.create("mongodb://localhost:27017");
        db = mc.getDatabase("sample_mflix");
    }
    
    // säkerställ att vi är anslutna, verkar fungera korrekt, dock med hög timout om jag ändrar till en felaktig port
    public boolean isConnected() {
    	try {
    		// säkerställ att servern är nåbar
    		db.runCommand(new Document("ping", 1)); // https://www.mongodb.com/docs/manual/reference/command/ping/
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    }

    // funktion för att iterera igenom "movies" och returnera resultat utifrån passerad input "genre"
    public List<String> getMovies(String genre) {
        List<String> results = new ArrayList<>();
        try {
            MongoCollection<Document> movies = db.getCollection("movies");

            FindIterable<Document> docs = movies.find(new Document("genres", new Document("$regex", genre).append("$options", "i"))) // regex case insensitive för att vara vänligare för användaren
                    .projection(new Document("title", 1).append("year", 1))
                    .sort(new Document("title", -1))
                    .limit(10);
            
            for (Document doc : docs) {
                String title = doc.getString("title");
                Integer year = doc.getInteger("year");
                results.add(title + ", " + year);
            }
        } catch (Exception e) {
        	results.add("Någonting gick fel: " + e);
        }
        return results;
    }

    public void close() {
    	mc.close();
    }
}
