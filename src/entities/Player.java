package entities;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import manager.SceneManager;

public class Player extends Entities {

	public Player(int xPos, int yPos) {
		super(xPos, yPos);
		
		this.loadSprite("walk_1", "images/Blue_player/blue_walk_1.png");
		this.loadSprite("walk_2", "images/Blue_player/blue_walk_2.png");
		this.loadSprite("walk_3", "images/Blue_player/blue_walk_3.png");
		this.loadSprite("walk_4", "images/Blue_player/blue_walk_4.png");
		this.loadSprite("walk_5", "images/Blue_player/blue_walk_5.png");
		this.loadSprite("walk_6", "images/Blue_player/blue_walk_6.png");
		this.loadSprite("axe_1", "images/Blue_player/blue_axe_1.png");
		this.loadSprite("axe_2", "images/Blue_player/blue_axe_2.png");
		this.loadSprite("axe_3", "images/Blue_player/blue_axe_3.png");
		this.loadSprite("axe_4", "images/Blue_player/blue_axe_4.png");
		this.loadSprite("axe_5", "images/Blue_player/blue_axe_5.png");
		this.loadSprite("axe_6", "images/Blue_player/blue_axe_6.png");
		this.loadSprite("crouch_1", "images/Blue_player/blue_crouch_1.png");
		this.loadSprite("crouch_2", "images/Blue_player/blue_crouch_2.png");
		this.loadSprite("crouch_3", "images/Blue_player/blue_crouch_3.png");
		this.loadSprite("crouch_4", "images/Blue_player/blue_crouch_4.png");
		this.loadSprite("crouch_5", "images/Blue_player/blue_crouch_5.png");
		this.loadSprite("crouch_6", "images/Blue_player/blue_crouch_6.png");
		this.loadSprite("hammer_1", "images/Blue_player/blue_hammer_1.png");
		this.loadSprite("hammer_2", "images/Blue_player/blue_hammer_2.png");
		this.loadSprite("hammer_3", "images/Blue_player/blue_hammer_3.png");
		this.loadSprite("hammer_4", "images/Blue_player/blue_hammer_4.png");
		this.loadSprite("hammer_5", "images/Blue_player/blue_hammer_5.png");
		this.loadSprite("hammer_6", "images/Blue_player/blue_hammer_6.png");
		this.loadSprite("jump_1", "images/Blue_player/blue_jump_1.png");
		this.loadSprite("jump_2", "images/Blue_player/blue_jump_2.png");
		this.loadSprite("jump_3", "images/Blue_player/blue_jump_3.png");
		this.loadSprite("jump_4", "images/Blue_player/blue_jump_4.png");
		this.loadSprite("jump_5", "images/Blue_player/blue_jump_5.png");
		this.loadSprite("jump_6", "images/Blue_player/blue_jump_6.png");
		this.loadSprite("run_1", "images/Blue_player/blue_run_1.png");
		this.loadSprite("run_2", "images/Blue_player/blue_run_2.png");
		this.loadSprite("run_3", "images/Blue_player/blue_run_3.png");
		this.loadSprite("run_4", "images/Blue_player/blue_run_4.png");
		this.loadSprite("run_5", "images/Blue_player/blue_run_5.png");
		this.loadSprite("run_6", "images/Blue_player/blue_run_6.png");

		this.setSprite("walk_1");
	}
	
	


	@Override
	public void update(long deltaTime) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void render(StackPane root) {
        ImageView sprite = this.getSprite(); // Get the ImageView
        if (!root.getChildren().contains(sprite)) {
            root.getChildren().add(sprite);
        }
    }



	
}
