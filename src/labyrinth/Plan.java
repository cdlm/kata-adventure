package labyrinth;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe auxiliaire pour le calcul et l'affichage du plan des salles connues du joueur.
 *
 * Utilisation : <code>System.out.println(new Plan(someRoom))</code>
 */
public class Plan {
    protected Room initialRoom;
    protected List<Room> mappedRooms;
    protected int minX, maxX, minY, maxY;

    /**
     * Calcule le plan des salles connues du joueur, centré sur <code>initialRoom</code>.
     *
     * @param initialRoom Salle mise en évidence sur le plan (<em>vous êtes ici</em>).
     */
    public Plan(Room initialRoom) {
        this.initialRoom = initialRoom;
        mappedRooms = new LinkedList<Room>();
        mapRoom(initialRoom, 0, 0);
    }

    /**
     * Découvre la topologie du monde par exploration récursive à partir de la salle <code>room</code>.
     *
     * Seules les salles déjà visitées par le joueur sont prises en compte. Les coordonnées sont
     * propagées de proche en proche, donc dépendent de l'étendue des salles connues du joueur et de
     * la {@link Plan#initialRoom salle de départ du calcul}, dont les coordonnées sont toujours (0,0).
     *
     * @param room Salle de départ de l'algorithme.
     * @param x Abscisse de la salle de départ.
     * @param y Ordonnée de la salle de départ.
     */
    protected void mapRoom(Room room, int x, int y) {
        mappedRooms.add(room);
        room.setXY(x, y);
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
        for (Direction d : Direction.values()) {
            Room exit = room.getExit(d);
            if (exit != null && exit.isVisited() && !mappedRooms.contains(exit)) {
                mapRoom(exit, room.getX() + d.dx, room.getY() + d.dy);
            }
        }
    }

    /**
     * Génère le plan des salles connues du joueur.
     *
     * Chaque salle est représentée par un caractère unique entre parenthèses, ou entre crochets
     * pour la salle de départ du plan, où se trouve le joueur.
     *
     * @return Représentation ascii-art du plan.
     * @see labyrinth.Room#characterDescription()
     */
    public String toString() {
        Collections.sort(mappedRooms, new Comparator<Room>() {
            public int compare(Room a, Room b) {
                if (a.getY() < b.getY()) return -1;
                if (a.getY() > b.getY()) return 1;
                if (a.getX() < b.getX()) return -1;
                if (a.getX() > b.getX()) return 1;
                return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        int x = minX, y = minY;
        for (Room r : mappedRooms) {
            if (y < r.getY()) {
                x = minX;
                y++;
                result.append("\n");
            }
            while (x < r.getX()) {
                result.append("   ");
                x++;
            }
            result.append(r == initialRoom ? "[" : "(");
            result.append(r.characterDescription());
            result.append(r == initialRoom ? "]" : ")");
            x++;
        }
        return result.toString();
    }
}
