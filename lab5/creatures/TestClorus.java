package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus offspring = c.replicate();
        assertEquals(1, offspring.energy(), 0.01);
        assertEquals(1, c.energy(), 0.01);
        assertFalse(offspring == c);
    }
}
