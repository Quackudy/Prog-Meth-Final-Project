
package entities;

import javafx.scene.layout.Pane;

public abstract class Enemy extends Entities implements Damageable {
    protected int health = 1;
    protected float speedFactor = 10.0f;
    protected float sizeFactor = 5.0f;
    protected float xSpeed = 0;
    protected float ySpeed = 0;
    protected int frameCounter = 0;
    protected boolean facingRight = true;

    public Enemy(float xPos, float yPos) {
        super(xPos, yPos);
    }

	public abstract void attack(Player player);

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.die();
        }
    }

    protected void die() {
        // Handle enemy death (not done)
    }

    @Override
    public abstract void update(float deltaTime);

    @Override
    public abstract void render(Pane root);
    
    public float getSpeedFactor() {
		return speedFactor;
	}

	public void setSpeedFactor(float speed) {
		this.speedFactor = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
