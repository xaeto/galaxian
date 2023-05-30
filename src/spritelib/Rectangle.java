package spritelib;

public class Rectangle
{
    private Point topleft;
    private Size size;

    public Rectangle()
    {
        this.topleft = new Point();
        this.size = new Size();
    }

    public Rectangle( Point topleft, Size size )
    {
        this.topleft = topleft;
        this.size = size;
    }

    public void setPos(Point point) {
        this.topleft = point;
    }

    public float top()
    {
        return topleft.getY();
    }

    public float bottom()
    {
        return topleft.getY() + size.getHeight();
    }

    public float left()
    {
        return topleft.getX();
    }

    public float right()
    {
        return topleft.getX() + size.getWidth();
    }

    public Point getTopleft()
    {
        return topleft;
    }

    public Size getSize()
    {
        return size;
    }
}
