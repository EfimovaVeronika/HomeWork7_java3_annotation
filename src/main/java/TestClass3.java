import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import org.testng.annotations.AfterSuite;


public class TestClass3 {

    @Test(priority = 3)
    public void printVeryVeryImportantMethod() {
        System.out.println("Very-Very important Method");
    }

    @AfterSuite
    public void printLondonGoodbye(){
        System.out.println("London Goodbye!");
    }
}
