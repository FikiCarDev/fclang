import com.devfoFikiCar.main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class matrixTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void matrixDeclaration() {
        main m = new main();
        String[] t = {"./scripts/tests/matrix/matrixTest1.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixSet() {
        main m = new main();
        String[] t = {"./scripts/tests/matrix/matrixTest2.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixRowSize() {
        main m = new main();
        String[] t = {"./scripts/tests/matrix/matrixTest3.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n2\n2\n2\n2\n2\n2\n2\n2\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixColumnSize() {
        main m = new main();
        String[] t = {"./scripts/tests/matrix/matrixTest4.fclang"};
        main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5\n5\n5\n5\n5\n5\n5\n5\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
