import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class bool_test {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void compareIntBool() {
        main m = new main();
        String[] t = {"./scripts/tests/bool/bool_test_1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void compareIntSimpleBool() {
        main m = new main();
        String[] t = {"./scripts/tests/bool/bool_test_2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nfalse\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void compareIntAdvancedBool() {
        main m = new main();
        String[] t = {"./scripts/tests/bool/bool_test_3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
