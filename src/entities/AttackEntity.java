package entities;

public abstract class AttackEntity extends Entities{
	
	protected int damage;

	public AttackEntity(Entities entity) {
		super(entity.getX(), entity.getY());
	}

    public boolean isOffScreen() {
        return (getX() < 0 || getX() > 800 || getY() < 0 || getY() > 600);
    }
    
    public int getDamage() {
    	return damage;
    }
    
    public void setDamage(int damage) {
    	this.damage = Math.max(0,damage);
    }
}
