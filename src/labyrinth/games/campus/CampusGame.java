package labyrinth.games.campus;

import labyrinth.Direction;
import labyrinth.Game;
import labyrinth.Room;

public class CampusGame extends Game {

    public static void main(String[] args) {
        new CampusGame().play(); // {{{ task }}}
    }

    protected void createRooms() {
        // {{{ task
        Room outside, hall, theatre, pub, lab, office;

        // création des pièces
        outside = new Room("à l'extérieur de l'entrée principale de l'Université");
        hall = new Room("dans le hall");
        theatre = new Room("dans un amphithéâtre");
        pub = new Room("à la cafétéria");
        lab = new Room("dans la salle informatique");
        office = new Room("au bureau des techniciens");

        // initialisation des sorties des pièces
        outside.setExit(Direction.SOUTH, hall);

        hall.setExit(Direction.NORTH, outside);
        hall.setExit(Direction.EAST, theatre);
        hall.setExit(Direction.SOUTH, lab);
        hall.setExit(Direction.WEST, pub);

        theatre.setExit(Direction.WEST, hall);

        pub.setExit(Direction.EAST, hall);

        lab.setExit(Direction.NORTH, hall);
        lab.setExit(Direction.EAST, office);

        office.setExit(Direction.WEST, lab);

        currentRoom = outside;
        // }}}
    }
}
