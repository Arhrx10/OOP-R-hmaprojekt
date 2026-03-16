import java.util.Random;

public class Täring {
    private int min, max;
    public Täring(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public Täring() {
        this.min = 1;
        this.max = 6;
    }

    int viska(){
        return (int) (Math.random()*(max-min+1)+min);
    };

    @Override
    public String toString() {
        return String.valueOf(viska());
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
