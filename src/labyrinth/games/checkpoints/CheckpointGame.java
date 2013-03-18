package labyrinth.games.checkpoints;

import labyrinth.Direction;
import labyrinth.Room;
import labyrinth.games.campus.CampusGame;

public class CheckpointGame extends CampusGame {

    public static void main(String[] args) {
        new CheckpointGame().play(); // {{{ task }}}
    }

    protected int checkpointsToVisit;

    public CheckpointGame() {
        super();
        checkpointsToVisit = 3; // amphi, lab
    }

    protected void createRooms() {
        // {{{ task
        Room outside, hall, theatre, pub, lab, office;

        // création des pièces
        outside = new Room("à l'extérieur de l'entrée principale de l'Université");
        hall = new Room("dans le hall");
        theatre = new Checkpoint("dans un amphithéâtre", "Juste à l'heure pour votre cours!");
        pub = new Room("à la cafétéria");
        lab = new Checkpoint("dans la salle informatique", "Votre projet avance.", 2);
        office = new Checkpoint("au bureau des techniciens", "Votre mot de passe est renouvelé.");

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

    public int getCheckpointsToVisit() { return checkpointsToVisit; }

    public void visitCheckpoint() {
        if (checkpointsToVisit > 0) {
            System.out.println("Une bonne chose de faite aujourd'hui.");
            checkpointsToVisit--;
        }
    }
}
