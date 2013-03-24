package labyrinth.games.campus;

import labyrinth.Direction;
import labyrinth.Game;
import labyrinth.Room;

/**
 * Un exemple de jeu, avec un univers familier.
 */
public class CampusGame extends Game {

    public static void main(String[] args) {
        new CampusGame().play();
    }

    protected Room createRooms() {
        Room outside, hall, amphi, cafet, lab, office;

        // création des pièces
        outside = new Room("à l'extérieur de l'entrée principale de l'Université");
        hall = new Room("dans le hall");
        amphi = new Room("dans un amphithéâtre");
        cafet = new Room("à la cafétéria");
        lab = new Room("dans la salle informatique");
        office = new Room("au bureau des techniciens");

        // initialisation des sorties des pièces
        outside.setExit(Direction.SOUTH, hall);

        hall.setExit(Direction.NORTH, outside);
        hall.setExit(Direction.EAST, amphi);
        hall.setExit(Direction.SOUTH, lab);
        hall.setExit(Direction.WEST, cafet);

        amphi.setExit(Direction.WEST, hall);

        cafet.setExit(Direction.EAST, hall);

        lab.setExit(Direction.NORTH, hall);
        lab.setExit(Direction.EAST, office);

        office.setExit(Direction.WEST, lab);

        return outside;
    }
}
