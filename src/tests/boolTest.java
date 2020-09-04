import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class boolTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void compareIntBool() {
        main m = new main();
        String[] t = {"./scripts/tests/bool/boolTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void compareIntSimpleBool() {
        main m = new main();
        String[] t = {"./scripts/tests/bool/boolTest2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nfalse\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void compareIntAdvancedBool() {
        main m = new main();
        String[] t = {"./scripts/tests/bool/boolTest3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
