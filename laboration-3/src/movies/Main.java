package movies;

public class Main {
	// entry point av programmet, initierar alla klasser
    public static void main(String[] args) {
        GUI gui = new GUI();
        Communications comms = new Communications();
        new Controller(gui, comms);

        gui.setVisible(true);
    }
}