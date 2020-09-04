import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class printTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nHello world!\nI love fclang\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void intPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n12\n6\n7\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void decimalPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n12.0\n6.5\n3.14\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void stringPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest4.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nFC Lang\n-FikiCar\n/\\/\\/\\/\\/\\\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void boolPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest5.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\nfalse\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void intExpressionPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest6.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void decimalExpressionPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest7.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5.75\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void stringPlusInt() {
        main m = new main();
        String[] t = {"./scripts/tests/print/printTest8.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nA: \n0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
