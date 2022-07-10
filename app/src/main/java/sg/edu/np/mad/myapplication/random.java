package sg.edu.np.mad.myapplication;

import java.util.Random;

public class random {
    static int getRandom() {
        Random random = new Random();
        int value = random.nextInt(1000000000);
        return value;
    }
}
