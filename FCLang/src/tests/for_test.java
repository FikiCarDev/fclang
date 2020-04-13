import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class for_test {
    @Test
    public void myFirstTest(){
        Assert.assertEquals("One plus one should be two",2, 1 + 1);
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void overrideProperty() {
        System.out.println("hello world");
        Assert.assertEquals("hello world\n", systemOutRule.getLog());
    }
}