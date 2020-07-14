import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class print_test {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nHello world!\nI love fclang\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void intPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n12\n6\n7\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void decimalPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n12.0\n6.5\n3.14\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void stringPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_4.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nFC Lang\n-FikiCar\n/\\/\\/\\/\\/\\\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void boolPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_5.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\nfalse\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void intExpressionPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_6.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void decimalExpressionPrint() {
        main m = new main();
        String[] t = {"./scripts/tests/print/print_test_7.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5.75\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
