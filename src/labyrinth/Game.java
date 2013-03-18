package labyrinth;

public abstract class Game {

    protected Room currentRoom;

    /**
     * Construction du labyrinthe et de sa carte interne.
     */
    public Game() {
        this.createRooms();
    }

    /**
     * Construction des pièces, des liens d'adjacence entre pièces, et initialisation de la pièce où
     * est le joueur au départ du jeu. Les pièces doivent être connexes sans chevauchement.
     */
    protected abstract void createRooms();

    public Room currentRoom() { return currentRoom; }

    protected boolean enterRoom(Room room) {
        currentRoom = room;
        return currentRoom.enter(this);
    }

    /**
     * Boucle principale du jeu. Demande et exécute des commandes jusqu'à la fin du jeu.
     */
    public void play() {
        this.printWelcome();
        enterRoom(currentRoom);

        // {{{ task
        boolean finished = false;
        while (!finished) {
            Command command = Command.readCommand();
            finished = processCommand(command);
        }
        // }}}

        System.out.println("Merci d'avoir joué.  Au revoir.");
    }

    protected boolean processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("Je ne comprends pas cette commande...");
            return false;
        }

        boolean wantToQuit = false;
        String verb = command.getVerb();
        if (verb.equals("aide")) {
            printHelp(command);
        } else if (verb.equals("plan")) {
            printMap(command);
        } else if (verb.equals("quitter")) {
            wantToQuit = processQuit(command);
        } else if (verb.equals("aller")) {
            wantToQuit = processGo(command);
        }
        return wantToQuit;
    }

    protected boolean processGo(Command command) {
        Room exit = null;
        Direction dir = null;

        dir = Direction.named(command.getArgument());
        if (dir == null) { // direction absente ou mal formée
            System.out.println("Aller où ?");
            return false;
        }

        exit = currentRoom.getExit(dir);
        if (exit == null) { // pas de salle adjacente
            System.out.println("Impossible d'aller par là !");
            return false;
        }

        return enterRoom(exit);
    }

    protected boolean processQuit(Command command) {
        if (command.hasArgument()) {
            // l'utilisateur pensait peut-être pouvoir quitter un endroit...
            System.out.println("Quitter quoi ?");
            return false;
        } else {
            return true;
        }
    }

    private void printMap(Command command) { System.out.println(new Plan(currentRoom)); }

    private void printHelp(Command command) {
        System.out.println("Vous êtes perdu. Vous êtes seul.");
        System.out.println("Vous flânez à travers l'université.");
        System.out.println();
        System.out.print("Les commandes sont :");
        Command.printAll();
    }

    protected void printWelcome() {
        System.out.println("     ____         _ ");
        System.out.println("    |_  /  _ _  _| |");
        System.out.println("     / / || | || | |");
        System.out.println("    /___\\_,_|\\_,_|_|");
        System.out.println();
        System.out.println("Bienvenue. Zuul est un nouveau jeu d'aventure terriblement ennuyeux.");
        System.out.println("Trouvez la sortie le plus vite possible!");
        System.out.println();
        System.out.println("En dernier recours, tapez 'aide'.");
        System.out.println();
    }

}
