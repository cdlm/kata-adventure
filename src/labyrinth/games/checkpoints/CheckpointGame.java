package labyrinth.games.checkpoints;

import labyrinth.Direction;
import labyrinth.Room;
import labyrinth.games.campus.CampusGame;

public class CheckpointGame extends CampusGame {

    public static void main(String[] args) { // {{{ task
        new CheckpointGame().play();
    } // }}}

    protected int checkpointsToVisit;

    public CheckpointGame() {
        super();
        checkpointsToVisit = 4; // hall, amphi, lab*2
    }

    @Override
    protected Room createRooms() {
        // {{{ task
        Room outside, hall, amphi, cafet, lab, office;

        // création des pièces
        outside = new Exit("à l'extérieur de l'entrée principale de l'Université",
                "Bravo, une journée bien remplie!",
                "C'était quoi l'emploi du temps déjà ?");
        hall = new Checkpoint("dans le hall",
                "Vous vérifiez le programme de la journée: un cours et deux TP.");
        amphi = new Checkpoint("dans un amphithéâtre",
                "Juste à l'heure pour votre cours!");
        cafet = new Room("à la cafétéria");
        lab = new Checkpoint("dans la salle informatique",
                "Votre projet avance.", 2);
        office = new Checkpoint("au bureau des techniciens",
                "Votre mot de passe est renouvelé.");

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
        // }}}
    }

    public void visitCheckpoint() {
        // {{{ task
        if (checkpointsToVisit > 0) {
            System.out.println("Une bonne chose de faite aujourd'hui.");
            checkpointsToVisit--;
        }
        // }}}
    }

    @Override
    public boolean isOkToFinish() {
        return checkpointsToVisit == 0; // {{{ task }}}
    }
}
