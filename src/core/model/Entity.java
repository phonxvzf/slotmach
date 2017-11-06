package core.model;

public abstract class Entity implements IMovable {
	
	protected boolean isFrozen = false;
	protected double posX, posY;
	protected double velX = 0, velY = 0; // in pixels per second
	protected double accelX = 0, accelY = 0; // in pixels per second^2
	
	public Entity(double x, double y) {
		this.posX = x;
		this.posY = y;
	}
	
	@Override
	public void move(long dt) {
		if (isFrozen) return;
		final double dt_sec = dt / 1000000000.0f;
		this.posX += this.velX * dt_sec;
		this.posY += this.velY * dt_sec;
		this.velX += this.accelX * dt_sec;
		this.velY += this.accelY * dt_sec;
	}
	
	public void setFreeze(boolean frz) {
		isFrozen = frz;
	}
	
	public void setPos(double x, double y) {
		this.posX = x;
		this.posY = y;
	}
	
	public void setVelocity(double vx, double vy) {
		this.velX = vx;
		this.velY = vy;
	}
	
	public void setAcceleration(double ax, double ay) {
		this.accelX = ax;
		this.accelY = ay;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public double getAccelX() {
		return accelX;
	}

	public void setAccelX(double accelX) {
		this.accelX = accelX;
	}

	public double getAccelY() {
		return accelY;
	}

	public void setAccelY(double accelY) {
		this.accelY = accelY;
	}
	
}
