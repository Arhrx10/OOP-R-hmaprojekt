import java.util.ArrayList;
import java.util.Arrays;

public class Joonistaja {
    Simulaator simulaator;
    int x_max, y_max;
    int x_suurus, y_suurus;
    double x_aspekt, y_aspekt;
    char[][] maatriks;


    public Joonistaja(Simulaator simulaator, int x, int y) {
        this.simulaator = simulaator;
        this.x_max = simulaator.väärtusePiirkonnaSuurus();
        this.y_max = simulaator.suurimVäärtus();
        this.x_suurus = x;
        this.y_suurus = y;
        this.x_aspekt = (double) x_suurus / x_max;
        this.y_aspekt = (double) y_suurus /y_max;
        this.maatriks = new char[x_suurus][y_suurus];

    }

    public Joonistaja(Simulaator simulaator) {
        this(simulaator, simulaator.väärtusePiirkonnaSuurus(), 24);
    }

    @Override
    public String toString() {
        return "Joonistaja{" +
                "x_pikkus=" + x_suurus +
                ", y_pikkus=" + y_suurus +
                '}';
    }

    private void genereeriMaatriks(){
        for (int i = 0; i < x_suurus ; i++) {
            for (int j = 0; j < (int)((double)simulaator.getViimaneSimulatsioon().get(i) * y_aspekt); j++) {
                maatriks[i][j] = '#';
            }
            for (int j = (int)((double)simulaator.getViimaneSimulatsioon().get(i) * y_aspekt); j < y_suurus; j++) {
                maatriks[i][j] = ' ';
                if(j == 0){
                    maatriks[i][j] = 'n';
                }
            }
        }
    }

    public void joonista(){
        genereeriMaatriks();
        for (int i = 0; i < maatriks.length; i++) {
            if(maatriks[i][0] == 'n'){
                continue;
            }
            System.out.printf("%2d|", i);
            for (int j = 0; j < maatriks[0].length; j++) {

                System.out.print(maatriks[i][j]);
            }
            System.out.println();
        }
    }

}
