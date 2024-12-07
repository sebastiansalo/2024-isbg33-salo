package movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
	private final GUI gui;
    private final Communications comms;

    public Controller(GUI gui, Communications comms) {
        this.gui = gui;
        this.comms = comms;
        
        // lyssnare för sök-knappen
        this.gui.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	handleSearch();
            }
        });
    }

    // funktion för att hantera genre-sökinngen och visa outputten
    private void handleSearch() {
        String genre = gui.getGenreInput();
        
        // se till att input finns
        if (genre.isEmpty()) {
        	gui.setResultOutput("Ingen genre angiven.");
            return;
        }
        
        // säkerställ att vi är asnlutna genom att pinga mongodb
        if (!comms.isConnected()) {
        	gui.setResultOutput("Anslutningen till databasen misslyckades.");
        	return;
        }

        List<String> movies = comms.getMovies(genre);

        if (movies.isEmpty()) { // inga resultat
            gui.setResultOutput("Ingen film matchade genren");
        } else { // om resultat finns så bygger vi strängar
            StringBuilder results = new StringBuilder(); 	// läste att StringBuilder är mer minneseffektiv då String
            for (String movie : movies) {					// och operatorn += skapar nya objekt i minnet istället
            	results.append(movie).append("\n");			// för att modifiera det existerande objektet.
            }
            gui.setResultOutput(results.toString());
        }
    }
}
