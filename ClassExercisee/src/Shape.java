public abstract class Shape {
    int x;

    /**
     * @return x
     * @author zhou.chuh
     **/
    public int getX() {
        return x;
    }

    ;

    /**
     * @param x the coodrinate of shape
     */
    public void SetX(int x) {
        this.x = x;
    }

    public void main() {
        SetX(1);
        getX();
    }
}
