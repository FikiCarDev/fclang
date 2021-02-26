import com.fikicar.Main;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class forTest {
    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void classicFor() {
        String[] t = {"./scripts/tests/for/forTest1.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n0\n1\n2\n3\n4\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void minusFor() {
        String[] t = {"./scripts/tests/for/forTest2.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n5\n4\n3\n2\n1\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }

    @Test
    public void sumFor() {
        String[] t = {"./scripts/tests/for/forTest3.fclang"};
        Main.main(t);
        Assert.assertEquals("======================================\nBeginning of FCLang execution: \n" +
                "======================================\n60\n======================================\n" +
                "Successful execution.\n======================================\n", systemOut.getLog());
    }
}