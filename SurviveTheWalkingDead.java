import java.util.Scanner;

interface GameMode {
    void startMode();
}

interface PlayerActions {
    void attack();
    void defend();
    void searchForSupplies();
}

interface GameInterface {
    void startGame();
    void endGame();
}

public class SurviveTheWalkingDead implements GameInterface {
    private String playerName;
    private GameMode currentMode;
    
    public static void main(String[] args) {
        SurviveTheWalkingDead game = new SurviveTheWalkingDead();
        game.startGame();
    }
    
    @Override
    public void startGame() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        playerName = sc.nextLine();
        
        System.out.println("Press 1 or 2 to select your game mode.");
        System.out.println("1 - Story");
        System.out.println("2 - Survival");
        System.out.println("Press P to start playing, " + playerName + ".");
        String startKey = sc.nextLine().toUpperCase();
        int choice = sc.nextInt();
        sc.nextLine();
        
        if (choice == 1) {
            currentMode = new StoryMode(playerName);
        } else if (choice == 2) {
            currentMode = new SurvivalMode(playerName);
        } else {
            System.out.println("Invalid choice. Exiting game.");
            return;
        }
        
        if (startKey.equals("P")) {
            currentMode.startMode();
            endGame();
        } else {
            System.out.println("Invalid key. Exiting...");
        }
    }
    
    @Override
    public void endGame() {
        System.out.println("Thanks for playing, " + playerName + "!");
    }
    
    // Story Mode
    static class StoryMode implements GameMode {
        private String playerName;
        
        public StoryMode(String playerName) {
            this.playerName = playerName;
        }
        
        @Override
        public void startMode() {
            System.out.println("Welcome to the Story Mode, " + playerName + ".");
            System.out.println("You woke up inside your home... alone, and went outside...");
            System.out.println("...just to see the whole town surrounded by the infected.");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Choose your action: ");
            System.out.println("1 - Get a weapon nearby.");
            System.out.println("2 - Hide around the house and try not to be seen.");
            
            int action = sc.nextInt();
            
            if (action == 1) {
                System.out.println("You got a rifle. Zombies are coming. You survived.");
            } else if (action == 2) {
                System.out.println("You hid inside the house, but the zombies found you. Game over.");
            } else {
                System.out.println("Invalid choice. Zombies have overwhelmed you. Game over.");
            }
        }
    }
    
    // Survival Mode
    static class SurvivalMode implements GameMode, PlayerActions {
        private String playerName;
        private int health = 100;
        private int supplies = 0;
        
        public SurvivalMode(String playerName) {
            this.playerName = playerName;
        }
        
        @Override
        public void startMode() {
            System.out.println("Welcome to Survival Mode, " + playerName + ".");
            Scanner sc = new Scanner(System.in);
            
            while (health > 0) {
                System.out.println("Choose your action: ");
                System.out.println("1 - Attack the zombies.");
                System.out.println("2 - Defend yourself");
                System.out.println("3 - search for supplies");
                System.out.println("4 - Quit");
                
                int action = sc.nextInt();
                
                switch (action) {
                    case '1': 
                        attack();
                        break;
                    case '2':
                        defend();
                        break;
                    case '3':
                        searchForSupplies();
                        break;
                    case '4':
                        System.out.println("Exiting Survival Mode...");
                        return;
                        
                    default:
                    System.out.println("Invalid choice. Try again.");
                }
            }
            
            System.out.println("You died. Game over.");
        }
        
        @Override
        public void attack() {
            System.out.println("You attacked the zombies and survived the wave!");
            health -= 10;
            System.out.println("Health: " + health);
        }
        
        @Override
        public void defend() {
            System.out.println("You defended yourself successfully and survived the wave.");
            health -= 5;
            System.out.println("Health: " + health);
        }
        
        @Override
        public void searchForSupplies() {
            System.out.println("You searched for supplies and found some rations.");
            supplies += 10;
            System.out.println("Supplies: " + supplies);
        }
    }
}