import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class ifTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicIf() {
        main m = new main();
        String[] t = {"./scripts/tests/if/if_test_1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nFive\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void elseIf() {
        main m = new main();
        String[] t = {"./scripts/tests/if/if_test_2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nThree\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void advanceIf() {
        main m = new main();
        String[] t = {"./scripts/tests/if/if_test_3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nYes\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}