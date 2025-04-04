package entities;

import java.util.List;

import javafx.scene.layout.Pane;
import manager.EntityManager;

public class NormalEnemy extends Unit {
	private float speedFactor = 8.0f;
	private float sizeFactor = 5.0f;
	private int walkSpriteCount = 0;
	private int stillSpriteCount = 0;
	private int dmg = 5; // ADD dmg to NormalEnemy
	// Add cooldown to attack
	private long lastAttackTime = 0;
    private static final long ATTACK_COOLDOWN_MS = 1000;

	public NormalEnemy(float xPos, float yPos) {
		super(xPos, yPos);
		super.setSizeFactor(sizeFactor);
		super.setSize(60, 60);
		super.setFaction(Faction.ENEMY);
		setHealth(20);

		this.loadSprite("normal_enemy_still_7", "images/normal_enemy/normal_enemy_still_7.png");
		this.loadSprite("flip_normal_enemy_still_7", "images/normal_enemy/flip_normal_enemy_still_7.png");

		for (int i = 1; i <= 6; i++) {
			this.loadSprite("normal_enemy_walk_" + i, "images/normal_enemy/normal_enemy_walk_" + i + ".png");
			this.loadSprite("flip_normal_enemy_walk_" + i, "images/normal_enemy/flip_normal_enemy_walk_" + i + ".png");
			this.loadSprite("normal_enemy_hit_down_" + i, "images/normal_enemy/normal_enemy_hit_down_" + i + ".png");
			this.loadSprite("normal_enemy_hit_horizontal_" + i,
					"images/normal_enemy/normal_enemy_hit_horizontal_" + i + ".png");
			this.loadSprite("normal_enemy_hit_up_" + i, "images/normal_enemy/normal_enemy_hit_up_" + i + ".png");
			this.loadSprite("flip_normal_enemy_hit_horizontal_" + i,
					"images/normal_enemy/flip_normal_enemy_hit_horizontal_" + i + ".png");
			this.loadSprite("normal_enemy_still_" + i, "images/normal_enemy/normal_enemy_still_" + i + ".png");
			this.loadSprite("flip_normal_enemy_still_" + i,
					"images/normal_enemy/flip_normal_enemy_still_" + i + ".png");
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

				if (minDistance < 40) {
					xSpeed = 0;
					ySpeed = 0;
					return;
				}
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

	public boolean isWalking() {
		return (xSpeed != 0 || ySpeed != 0);
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
		updateSpritePosition(); 
		updateHitbox();

		if (isWalking() && frameCounter % 10 == 0) {
			// System.out.println("frameCounter: " + frameCounter);
			frameCounter = 0; // Avoiding overflow
			walkSpriteCount = (++walkSpriteCount) % 6;
			this.setSprite((facingRight ? "normal_enemy_walk_" : "flip_normal_enemy_walk_") + (walkSpriteCount + 1));
		}
		else if (!isWalking() && frameCounter % 10 == 0) {
			frameCounter = 0; // Avoiding overflow
			stillSpriteCount = (++stillSpriteCount) % 7;
			this.setSprite((facingRight ? "normal_enemy_still_" : "flip_normal_enemy_still_") + (stillSpriteCount + 1));
		}
		frameCounter += 1;
//		System.out.println(frameCounter);
	}
	
	public boolean canAttack() {
        return System.currentTimeMillis() - lastAttackTime >= ATTACK_COOLDOWN_MS;
    }

    public void registerAttack() {
        lastAttackTime = System.currentTimeMillis();
    }
	
	public int getDmg() {
		return this.dmg;
	}

	@Override
	public boolean isDead() {
		return (getHealth() <= 0);
	}


}
