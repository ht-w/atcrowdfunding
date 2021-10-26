import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.AdminExample;
import io.hongting.crowd.mapper.AdminMapper;
import io.hongting.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hongting
 * @create 2021 09 23 4:43 PM
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-tx.xml", "classpath:spring-persist-mybatis.xml","classpath:spring-web-mvc.xml"})
public class TestConnection {
    @Autowired
    DataSource dataSource;

    @Autowired
    AdminService adminService;

    @Test
    public void test01() throws SQLException {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-persist-mybatis.xml");
        //DataSource dataSource = context.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testInsertAdmin(){

     Admin   admin = new Admin(null,"kangting","kangting","kangting","kangting@gmail.com",null);
        adminService.saveAdmin(admin);

    }

    @Test
    public void  test3(){
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());
        System.out.println(admins);
    }


    @Test
    public void testInsertAdmin2(){
        for (int i = 0; i < 222; i++) {
           Admin admin = new Admin(null,"asdzxc"+i,"cffbad68bb97a6c3f943538f119c992c","asdzxc"+i,"asdzxc"+i+"@gmail.com",null);
           adminMapper.insert(admin);
        }


    }

    @Test
    public void test03(){
        Logger logger = LoggerFactory.getLogger(TestConnection.class);
        logger.debug("I am DEBUG!!!");

        logger.info("I am INFO!!!");

        logger.warn("I am WARN!!!");

        logger.error("I am ERROR!!!");
    }
}
