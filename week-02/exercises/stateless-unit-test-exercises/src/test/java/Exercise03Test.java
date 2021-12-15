import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise03Test {
    @Test
    void hasAllVowels(){
        assertEquals(true, Exercise03.hasAllVowels("Fish have goofy utensils"));
        assertEquals(true, Exercise03.hasAllVowels("PlAnktOn feels gunky and ill"));
        assertEquals(false, Exercise03.hasAllVowels(null));
        assertEquals(false, Exercise03.hasAllVowels("Ply wood is the finest wood"));
    }
}
