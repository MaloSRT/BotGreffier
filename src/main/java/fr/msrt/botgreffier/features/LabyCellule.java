package fr.msrt.botgreffier.features;

public class LabyCellule {

    private final int x;
    private final int y;
    private final boolean[] murs = {true, true, true, true};

    public LabyCellule(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean getMur(int orientation) {
        /*
         * 0: Nord
         * 1: Sud
         * 2: Est
         * 3: Ouest
         */
        return murs[orientation];
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setMur(int orientation, boolean mur) {
        this.murs[orientation] = mur;
    }

}