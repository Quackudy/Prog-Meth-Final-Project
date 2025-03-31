package entities;

import input.Direction;
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
	
	public static Bow createBow(Player player, boolean playerFacingRight, Direction arrowDirection) {
		Bow bow = new Bow(player, playerFacingRight, arrowDirection);
		EntityManager.getInstance().addEntity(bow);
		return bow;
	}
	
	public static Arrow createArrow(Bow bow) {
		Arrow arrow = new Arrow(bow);
		EntityManager.getInstance().addEntity(arrow);
		return arrow;
	}
	
	
	public static void removeEntity(Entities entity) {
		EntityManager.getInstance().removeEntity(entity);
	}
}