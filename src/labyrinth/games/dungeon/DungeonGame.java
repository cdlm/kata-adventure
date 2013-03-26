package labyrinth.games.dungeon;

import labyrinth.Direction;
import labyrinth.Game;
import labyrinth.Room;

public class DungeonGame extends Game {

    protected final int difficulty;

    protected int healthPoints;

    public DungeonGame(int healthPoints) { this(healthPoints, 1); }

    public DungeonGame(int healthPoints, int difficulty) {
        super();
        this.healthPoints = healthPoints;
        this.difficulty = difficulty;
    }

    public static void main(String[] args) {
        new DungeonGame(12).play();
    }

    public Room createRooms() {
        Room fond, tunnel, alcove, cave, crypte, colonne, escalier, sortie;

        fond = new Room("au fond d'un puits... tombé dedans ? votre mémoire vous fait défaut...");
        tunnel = new ObstacleRoom("un tunnel humide et trop bas pour se tenir debout.", 1);
        alcove = new ObstacleRoom("une sorte d'alcôve derrière un effondrement ;" +
                " les locataires de l'endroit n'apprécient pas votre présence.", 2);
        cave = new BonusRoom("une petite cave voûtée, avec une flaque au milieu.",
                "Vous y trouvez une bouteille de gnôle qui vous redonne quelques forces.", 2);
        crypte = new Room("une crypte, peut-être un réservoir souterrain ;" +
                " une barque flotte entre les rangées de colonnes.");
        colonne = new Room("la barque, à côté d'une colonne effondrée au milieu de l'eau ;" +
                " c'est une clé qui a attiré votre regard.");
        escalier = new Room("un escalier en spirale émergé de l'eau" +
                " et s'élevant à travers le plafond.");
        sortie = new ExitRoom("un caveau, probablement ;" +
                " une grille laisse passer un courant d'air frais.",
                "La serrure grince, mais elle finit par s'ouvrir." +
                        " Vous êtes libre, mais toujours amnésique.",
                colonne);

        fond.setExit(Direction.WEST, tunnel);

        tunnel.setExit(Direction.EAST, fond);
        tunnel.setExit(Direction.SOUTH, alcove);
        tunnel.setExit(Direction.WEST, cave);

        alcove.setExit(Direction.NORTH, tunnel);

        cave.setExit(Direction.EAST, tunnel);
        cave.setExit(Direction.NORTH, crypte);

        crypte.setExit(Direction.SOUTH, cave);
        crypte.setExit(Direction.EAST, colonne);
        crypte.setExit(Direction.NORTH, escalier);

        colonne.setExit(Direction.WEST, crypte);

        escalier.setExit(Direction.SOUTH, crypte);
        escalier.setExit(Direction.NORTH, sortie);

        sortie.setExit(Direction.SOUTH, escalier);

        return fond;
    }

    @Override
    protected boolean enterRoom(Room room) {
        affectHealth(-difficulty);
        return healthPoints < 0 || super.enterRoom(room);
    }

    @Override
    protected void printGoodbye() {
        if (healthPoints == 0) {
            System.out.println("Vous êtes au bout de vos forces, mais vous survivrez.");
        } else if (healthPoints < 0) {
            System.out.println("Hélas, votre aventure s'arrête funestement.");
        } else {
            System.out.println("(" + healthPoints + "HP restants)");
        }
        super.printGoodbye();
    }

    @Override
    public void affectHealth(int delta) {
        healthPoints += delta;
    }
}
