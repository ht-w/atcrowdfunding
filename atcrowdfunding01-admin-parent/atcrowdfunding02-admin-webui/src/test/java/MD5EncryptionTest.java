import io.hongting.crowd.util.Util;
import org.junit.Test;

/**
 * @author hongting
 * @create 2021 09 27 3:36 PM
 */
public class MD5EncryptionTest {



    @Test
    public void testMD5Encryption(){
        String MD5= Util.MD5encrypt("asdzxc");
        System.out.println(MD5);
    }
}
