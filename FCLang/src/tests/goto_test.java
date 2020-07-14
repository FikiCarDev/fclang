import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class goto_test {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicGoto() {
        main m = new main();
        String[] t = {"./scripts/tests/goto/goto_test_1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nYes\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void ifGoto() {
        main m = new main();
        String[] t = {"./scripts/tests/goto/goto_test_2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nSize doesn't matter\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void forGoto() {
        main m = new main();
        String[] t = {"./scripts/tests/goto/goto_test_3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n50\nStopped for-loop on 50\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}