package fr.msrt.botgreffier.features;

import java.util.ArrayList;
import java.util.Random;

public class LabyGrille {

    private final int nx;
    private final int ny;
    private final ArrayList<ArrayList<LabyCellule>> cadrillage;

    public LabyGrille(int nx, int ny) {

        this.nx = nx;
        this.ny = ny;
        this.cadrillage = new ArrayList<>();

        for (int i = 0; i < nx; i++) {
            ArrayList<LabyCellule> grilleLigne = new ArrayList<>();
            for (int j = 0; j < ny; j++) {
                grilleLigne.add(new LabyCellule());
            }
            this.cadrillage.add(grilleLigne);
        }

    }

    public LabyCellule getCellule(int x, int y) {
        return this.cadrillage.get(x).get(y);
    }

    public String toString() {

        StringBuilder labyLignes = new StringBuilder();

        for (int i = 0; i < this.ny; i++) {
            labyLignes.append("+---");
        }

        labyLignes.append("+").append("\n");

        for (int i = 0; i < this.nx; i++) {

            StringBuilder labyL = new StringBuilder();

            if (i == 0) {
                labyL.append("→");
            } else {
                labyL.append("|");
            }

            for (int j = 0; j < this.ny; j++) {
                if (i == this.nx-1 && j == this.ny-1) {
                    labyL.append("   →");
                } else {
                    if (this.cadrillage.get(i).get(j).getMur(2)) {
                        labyL.append("   |");
                    } else {
                        labyL.append("    ");
                    }
                }
            }

            labyLignes.append(labyL);
            labyL.setLength(0);
            labyL.append("+");

            for (int j = 0; j < this.ny; j++) {
                if (this.cadrillage.get(i).get(j).getMur(1)) {
                    labyL.append("---+");
                } else {
                    labyL.append("   +");
                }
            }

            labyLignes.append("\n").append(labyL).append("\n");

        }

        return "\n" + labyLignes;

    }

    public void delMur(int x, int y, int orientation) {

        LabyCellule cell = this.getCellule(x, y);

        switch (orientation) {
            case 0:
                cell.setMur(0, false);
                if (x > 0) {
                    this.getCellule(x-1, y).setMur(1, false);
                }
                break;
            case 1:
                cell.setMur(1, false);
                if (x < this.nx-1) {
                    this.getCellule(x+1, y).setMur(0, false);
                }
                break;
            case 2:
                cell.setMur(2, false);
                if (y < this.ny-1) {
                    this.getCellule(x, y+1).setMur(3, false);
                }
                break;
            case 3:
                cell.setMur(3, false);
                if (y > 0) {
                    this.getCellule(x, y-1).setMur(2, false);
                }
                break;
            default:
                break;
        }

    }

    public void expExhaustive() {

        ArrayList<int[]> pile = new ArrayList<>();
        int[][] visite = new int[this.nx][this.ny];

        pile.add(new int[]{new Random().nextInt(this.nx), new Random().nextInt(this.ny)});
        visite[pile.get(0)[0]][pile.get(0)[1]] = 1;

        while (!pile.isEmpty()) {

            int i = pile.get(0)[0];
            int j = pile.get(0)[1];
            ArrayList<int[]> voisins = new ArrayList<>();
            ArrayList<Integer> orientations = new ArrayList<>();

            if (i-1 >= 0 && visite[i-1][j] == 0) {
                voisins.add(new int[]{i-1, j});
                orientations.add(0);
            }

            if (i+1 < this.nx && visite[i+1][j] == 0) {
                voisins.add(new int[]{i+1, j});
                orientations.add(1);
            }

            if (j+1 < this.ny && visite[i][j+1] == 0) {
                voisins.add(new int[]{i, j+1});
                orientations.add(2);
            }

            if (j-1 >= 0 && visite[i][j-1] == 0) {
                voisins.add(new int[]{i, j-1});
                orientations.add(3);
            }

            if (!voisins.isEmpty()) {
                int randVoisin = new Random().nextInt(voisins.size());
                int k = voisins.get(randVoisin)[0];
                int l = voisins.get(randVoisin)[1];
                delMur(i, j, orientations.get(randVoisin));
                visite[k][l] = 1;
                pile.add(0, new int[]{k, l});
            } else {
                pile.remove(0);
            }

        }

    }

}