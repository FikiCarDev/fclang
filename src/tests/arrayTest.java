import com.fikicar.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class arrayTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void arrayDeclaration() {
        String[] t = {"./scripts/tests/array/arrayTest1.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void arraySize() {
        String[] t = {"./scripts/tests/array/arrayTest2.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n10\n10\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void arraySort() {
        String[] t = {"./scripts/tests/array/arrayTest3.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setIntArray() {
        String[] t = {"./scripts/tests/array/arrayTest4.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setDecimalArray() {
        String[] t = {"./scripts/tests/array/arrayTest5.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setStringArray() {
        String[] t = {"./scripts/tests/array/arrayTest6.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void setBoolArray() {
        String[] t = {"./scripts/tests/array/arrayTest7.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getIntArray() {
        String[] t = {"./scripts/tests/array/arrayTest8.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n4\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getDecimalArray() {
        String[] t = {"./scripts/tests/array/arrayTest9.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n4.0\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getStringArray() {
        String[] t = {"./scripts/tests/array/arrayTest11.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\nTest\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getBoolArray() {
        String[] t = {"./scripts/tests/array/arrayTest10.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\ntrue\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void getUndefinedArrayPrint() {
        String[] t = {"./scripts/tests/array/arrayTest12.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n3\nDONE\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}
