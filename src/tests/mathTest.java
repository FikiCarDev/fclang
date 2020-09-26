import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class mathTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void maxInt() {
        main m = new main();
        String[] t = {"./scripts/tests/math/maxIntTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n30\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void maxDecimal() {
        main m = new main();
        String[] t = {"./scripts/tests/math/maxDecimalTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n30.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void minInt() {
        main m = new main();
        String[] t = {"./scripts/tests/math/minIntTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void minDecimal() {
        main m = new main();
        String[] t = {"./scripts/tests/math/minDecimalTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void powInt() {
        main m = new main();
        String[] t = {"./scripts/tests/math/powIntTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n100\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void powDecimal() {
        main m = new main();
        String[] t = {"./scripts/tests/math/powDecimalTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n100.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void sqrtInt() {
        main m = new main();
        String[] t = {"./scripts/tests/math/sqrtIntTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n4\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void sqrtDecimal() {
        main m = new main();
        String[] t = {"./scripts/tests/math/sqrtDecimalTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n4.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void absInt() {
        main m = new main();
        String[] t = {"./scripts/tests/math/absIntTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void absDecimal() {
        main m = new main();
        String[] t = {"./scripts/tests/math/absDecimalTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
