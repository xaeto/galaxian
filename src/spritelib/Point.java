package spritelib;

public class Point
{
    private float x;
    private float y;

    public Point()
    {
        x = y = 0;
    }

    public Point( float x, float y )
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public void setX( float x )
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY( float y )
    {
        this.y = y;
    }

    public Point sub(Point other){
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }
    public Point add(Point other){
        this.x += other.x;
        this.y += other.y;
        return this;
    }
    public Point mult(Point other){
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }
    public Point mult(double value){
        this.x *= value;
        this.y *= value;
        return this;
    }
    public Point mult(float value){
        this.x *= value;
        this.y *= value;
        return this;
    }
}
