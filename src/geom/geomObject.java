package geom;

import java.awt.*;

public abstract class geomObject {
    protected Color edgeColor;
    protected Color faceColor;
    protected int line_weight = 1;

    public geomObject() {
        this.edgeColor = new Color(20,200,20);
        faceColor= Color.red;
    }

    public abstract void draw(Graphics g, SpaceMapping mapper);
}
