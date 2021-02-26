import com.fikicar.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class matrixTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void matrixDeclaration() {
        String[] t = {"./scripts/tests/matrix/matrixTest1.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixSet() {
        String[] t = {"./scripts/tests/matrix/matrixTest2.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixRowSize() {
        String[] t = {"./scripts/tests/matrix/matrixTest3.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n2\n2\n2\n2\n2\n2\n2\n2\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixColumnSize() {
        String[] t = {"./scripts/tests/matrix/matrixTest4.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5\n5\n5\n5\n5\n5\n5\n5\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void matrixGet() {
        String[] t = {"./scripts/tests/matrix/matrixTest5.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10\n11\n11.0\nHello\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
