import com.ppikarin.Starter;
import com.ppikarin.UserAccountService;
import org.junit.jupiter.api.Test;

import static com.ppikarin.UserAccountService.INCORRECT_INPUT;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    UserAccountService starter = new UserAccountService(null, null, null, null);

    @Test
    public void test1() throws Exception {
        assertEquals("z", starter.getSubstring(25));
    }

    @Test()
    public void test2() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            starter.getSubstring(26);
        });

        assertNotNull(exception);
        assertEquals(INCORRECT_INPUT, exception.getMessage());
    }

}