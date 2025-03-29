package entities;

import manager.EntityManager;

public class EntityFactory {
	
    public static Player createPlayer(float xPos, float yPos) {
        Player player = new Player(xPos, yPos);
        EntityManager.getInstance().addEntity(player);
        return player;
    }

	public static NormalEnemy createNormalEnemy(float xPos, float yPos) {
		NormalEnemy normalEnemy = new NormalEnemy(xPos, yPos);
		EntityManager.getInstance().addEntity(normalEnemy);
		return normalEnemy;
	}
}