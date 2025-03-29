package entities;

import java.util.List;

import javafx.scene.layout.Pane;
import manager.EntityManager;

public class NormalEnemy extends Enemy {
    private float speedFactor = 8.0f;
    private float sizeFactor = 5.0f;
    private int walkSpriteCount = 0;

    public NormalEnemy(float xPos, float yPos) {
        super(xPos, yPos);
        super.setSizeFactor(sizeFactor);
        
        
        this.loadSprite("normal_enemy_still_7", "images/normal_enemy/normal_enemy_still_7.png");
        

        for (int i = 1; i <= 6; i++) {
            this.loadSprite("normal_enemy_walk_" + i, "images/normal_enemy/normal_enemy_walk_" + i + ".png");
            this.loadSprite("flip_normal_enemy_walk_" + i, "images/normal_enemy/flip_normal_enemy_walk_" + i + ".png");
            this.loadSprite("normal_enemy_hit_down_" + i, "images/normal_enemy/normal_enemy_hit_down_" + i + ".png");
            this.loadSprite("normal_enemy_hit_horizontal_" + i, "images/normal_enemy/normal_enemy_hit_horizontal_" + i + ".png");
            this.loadSprite("normal_enemy_hit_up_" + i, "images/normal_enemy/normal_enemy_hit_up_" + i + ".png");
            this.loadSprite("flip_normal_enemy_hit_horizontal_" + i, "images/normal_enemy/flip_normal_enemy_hit_horizontal_" + i + ".png");
            this.loadSprite("normal_enemy_still_" + i, "images/normal_enemy/normal_enemy_still_" + i + ".png");
            this.loadSprite("flip_normal_enemy_still_" + i, "images/normal_enemy/flip_normal_enemy_still_" + i + ".png");
        }
        this.setSprite("normal_enemy_still_1");
    }
    
    
    public void calculateSpeedToPlayer() {
    	
    	List<Player> playerList = EntityManager.getInstance().getPlayers();
    	
    	float minDistance = 10000;
    	Player player = null;
    	for (Player playerLoop : playerList) {
    		
			if (calculateDistantToPlayer(playerLoop) < minDistance) {
				minDistance = calculateDistantToPlayer(playerLoop);
				player = playerLoop;
			}
    	}
    	
		float dx = player.getX() - xPos;
		float dy = player.getY() - yPos;
		float distance = (float) Math.sqrt(dx * dx + dy * dy);
		xSpeed = speedFactor * dx / distance;
		ySpeed = speedFactor * dy / distance;
    }
    
    public float calculateDistantToPlayer(Player player) {
        return (float) Math.sqrt(Math.pow(player.getX() - xPos, 2) + Math.pow(player.getY() - yPos, 2));
    }

    @Override
    public void attack(Player player) {
        // Implement attack logic (e.g., reduce player health)
    }

    @Override
    public void update(float deltaTime) {
        calculateSpeedToPlayer();
        
		if (xSpeed > 0) {
			facingRight = true;
		} else if (xSpeed < 0) {
			facingRight = false;
		}
    	
        xPos += xSpeed * deltaTime;
        yPos += ySpeed * deltaTime;
        sprite.setX(xPos);
        sprite.setY(yPos);
        
        if (frameCounter++ % 10 == 0) {
        	//System.out.println("frameCounter: " + frameCounter);
            walkSpriteCount = (++walkSpriteCount) % 6;
            this.setSprite((facingRight ? "normal_enemy_walk_" : "flip_normal_enemy_walk_") + (walkSpriteCount + 1));
        }
    }

    @Override
    public void render(Pane root) {
    	
    	
        if (!root.getChildren().contains(sprite)) {
            root.getChildren().add(sprite);
        }
    }


}
