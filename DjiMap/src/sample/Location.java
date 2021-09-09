package sample;

public class Location {
    public String name;
    int posX;
    int posY;

    public Location(String name, int posX, int posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public String getName() {
        return name;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
