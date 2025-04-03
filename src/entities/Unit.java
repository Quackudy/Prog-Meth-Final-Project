
package entities;

import javafx.scene.layout.Pane;

public abstract class Unit extends Entities implements Damageable {
    protected int health = 1;
    protected float speedFactor = 10.0f;
    protected float sizeFactor = 5.0f;
    protected float xSpeed = 0;
    protected float ySpeed = 0;
    protected int frameCounter = 0;
    protected boolean facingRight = true;

    public Unit(float xPos, float yPos) {
        super(xPos, yPos);
        setFaction(Faction.ENEMY);
    }


    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth()-damage);
        //TODO-Add sound hit damage
        
    }

    public boolean isDead() {
    	return (getHealth() <= 0);
    }

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
