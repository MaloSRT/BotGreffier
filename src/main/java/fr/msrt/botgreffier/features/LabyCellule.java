package fr.msrt.botgreffier.features;

public class LabyCellule {

    private final boolean[] murs = {true, true, true, true};

    public LabyCellule() {}

    public boolean getMur(int orientation) {
        /*
         * 0: Nord
         * 1: Sud
         * 2: Est
         * 3: Ouest
         */
        return murs[orientation];
    }

    public void setMur(int orientation, boolean mur) {
        this.murs[orientation] = mur;
    }

}