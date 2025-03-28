
package entities;

public class EnemyFactory {
    public static Enemy createEnemy(String type, float xPos, float yPos) {
        switch (type.toLowerCase()) {
            case "normal":
                return new NormalEnemy(xPos, yPos);
            // Add more enemy types here
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
