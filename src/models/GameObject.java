package models;

import processing.core.PApplet;
import processing.core.PVector;
import spritelib.Sprite;

public abstract class GameObject {
  /** The `canShoot` variable is a boolean flag that indicates whether or not the GameObject can shoot a
  * projectile. It is set to `true` by default, but can be changed to `false` if the GameObject is not
  * able to shoot at a certain time.
  */
  public boolean canShoot = true;
  /**
   * Position of the GameObject
  */
  protected PVector position = new PVector();
  /** 
   * Velocity of the GameObject
  */
  protected PVector velocity = new PVector();
  /** 
   * Width of the Sprite
  */
  protected int width;
  /** 
   * Height of the Sprite
  */
  protected int height;
  /** 
   * Rotation Angle of the GameObject
  */
  protected double angle;
  /** 
   * Sprite of the specific GameObject
  */
  protected Sprite sprite;
  /** 
   * The specific PApplet the GameObject is supposed to be rendered on
  */
  protected PApplet _applet;

  /**
   * This is a constructor for the GameObject class that initializes its
   * properties such as
   * position, width, height, and angle. It takes in parameters such as the
   * PApplet instance used
   * for drawing, the x and y coordinates of the object, and its width and height.
   * It sets the
   * position, width, height, and angle properties of the object to the values
   * passed in as
   * parameters.
   */
  public GameObject(PApplet applet, float x, float y, int width, int height) {
    this._applet = applet;

    this.position.x = x;
    this.position.y = y;

    this.width = width;
    this.height = height;
    this.angle = Math.PI / 2; // 90°
  }

  /**
   * The `update()` method is updating the position of the GameObject by adding
   * its velocity vector
   * to its current position vector. It then resets the velocity vector to zero.
   * This method is
   * typically called once per frame to update the position of the GameObject
   * based on its current
   * velocity.
   */
  public void update() {
    this.position.add(this.velocity);
    this.velocity = new PVector();
  }

  public PVector getPosition() {
    return this.position;
  }

  public PVector getVelocity() {
    return this.velocity;
  }

  public boolean isVisible() {
    if (this.sprite == null)
      return false;
    return this.sprite.isVisible()
        && (this.position.x >= 0 && this.position.y <= this._applet.height);
  }

  /**
   * Toggles the visibility of the sprite associated with this object. If the
   * sprite is currently
   * visible, it will be hidden. If it is hidden, it will be shown.
   */
  public void toggleVisibility() {
    if (this.sprite == null)
      return;
    if (this.sprite.isVisible()
        || this.position.x < 0
        || this.position.x > this._applet.width
        || this.position.y > this._applet.height
        || this.position.y < 0) {
      this.sprite.hide();
      return;
    }
    this.sprite.show();
  }

  public void die() {
  }

  /**
   * Checks if this GameObject intersects with the specified GameObject.
   *
   * @param other the GameObject to check for intersection
   * @return true if the two GameObjects intersect, false otherwise
   */
  public boolean intersect(GameObject other) {
    boolean checkX = this.position.x < other.getX() + other.width && this.getX() + this.width > other.getX();
    boolean checkY = this.position.y < other.getY() + other.height && this.getY() + this.height > other.getY();

    return checkX && checkY;
  }

  /**
   * Draws the projectiles on the screen and updates their positions.
   */
  public void draw() {
  }

  /**
   * Sets up the object's sprite and sequences for animation.
   */
  public abstract void setup();

  public float getX() {
    return this.position.x;
  }

  public void setX(float x) {
    this.position.x = x;
  }

  public float getY() {
    return this.position.y;
  }

  public void setY(float y) {
    this.position.y = y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public Projectile shoot() {
    setup();
    return new Projectile(this._applet, this.getX(), this.getY(), 1, ProjectileSource.Enemy);
  }
}
