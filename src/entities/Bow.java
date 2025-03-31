
package entities;

import input.Direction;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bow extends Entities {
	private Direction direction;
	private int frameCounter = 0;
	private int bowSpriteCount = 0;
	private boolean finished = false;
	private boolean playerFacingRight;
	private Player player;

	public Bow(Player player, boolean playerFacingRight, Direction arrowDirection) {
		super(player.getX(), player.getY());
		setSizeFactor(player.getSizeFactor());
		setSize(30, 30);
		
		this.player = player;
		this.playerFacingRight = playerFacingRight;
		this.direction = arrowDirection;

		this.loadSprite("bow_1", "images/bow/bow_1.png");
		this.loadSprite("bow_2", "images/bow/bow_2.png");
		this.loadSprite("bow_3", "images/bow/bow_3.png");
		this.loadSprite("bow_4", "images/bow/bow_4.png");
		this.loadSprite("bow_5", "images/bow/bow_5.png");
		this.loadSprite("bow_6", "images/bow/bow_6.png");
		this.loadSprite("bow_7", "images/bow/bow_7.png");
		this.loadSprite("bow_8", "images/bow/bow_8.png");
		this.loadSprite("flip_bow_1", "images/bow/flip_bow_1.png");
		this.loadSprite("flip_bow_2", "images/bow/flip_bow_2.png");
		this.loadSprite("flip_bow_3", "images/bow/flip_bow_3.png");
		this.loadSprite("flip_bow_4", "images/bow/flip_bow_4.png");
		this.loadSprite("flip_bow_5", "images/bow/flip_bow_5.png");
		this.loadSprite("flip_bow_6", "images/bow/flip_bow_6.png");
		this.loadSprite("flip_bow_7", "images/bow/flip_bow_7.png");
		this.loadSprite("flip_bow_8", "images/bow/flip_bow_8.png");

		this.setSprite(playerFacingRight ? "bow_1" : "flip_bow_1");
	}

	@Override
	public void update(float deltaTime) {
		frameCounter++;

		this.xPos = player.getX();
		this.yPos = player.getY();
		updateSpritePosition(); 

		if (frameCounter % 8 == 0) {
			bowSpriteCount = (++bowSpriteCount) % 8;

			if (direction == Direction.LEFT) {
				this.setSprite("flip_bow_" + (bowSpriteCount + 1));
			} else if (direction == Direction.RIGHT) {
				this.setSprite("bow_" + (bowSpriteCount + 1));
			} else if (playerFacingRight) {
				this.setSprite("bow_" + (bowSpriteCount + 1));
			} else if (!playerFacingRight) {
				this.setSprite("flip_bow_" + (bowSpriteCount + 1));
			}
			
            if (bowSpriteCount == 7) {
                finished = true;
                EntityFactory.createArrow(this);
                updateHitbox();
            }

		}

	}

	public boolean isFinished() {
		return finished;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
