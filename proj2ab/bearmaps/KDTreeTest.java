package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testKDTree() {
        ArrayList<Point> inputList = new ArrayList<>();
        Random randomDouble = new Random(666);

        for (int i = 0; i < 10000; i++) {
            double x = randomDouble.nextDouble() * 100;
            double y = randomDouble.nextDouble() * 100;
            inputList.add(new Point(x, y));
        }

        NaivePointSet NPS = new NaivePointSet(inputList);
        KDTree KDT = new KDTree(inputList);

        for (int j = 0; j < 10000; j++) {
            double testX = randomDouble.nextDouble() * 100;
            double testY = randomDouble.nextDouble() * 100;

            Point resKDT = KDT.nearest(testX, testY);
            Point resNPS = NPS.nearest(testX, testY);

            assertEquals(resKDT.getX(), resNPS.getX(), 0.01);
            assertEquals(resKDT.getX(), resNPS.getX(), 0.01);
        }
    }
}
