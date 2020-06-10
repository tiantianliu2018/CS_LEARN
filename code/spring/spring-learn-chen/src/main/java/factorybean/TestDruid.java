package factorybean;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

/**
 * @author Kelly
 * @create 2020-06-09 22:48
 */
public class TestDruid {
    public static void main(String[] args) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/db1");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        System.out.println(druidDataSource.getConnection());
    }
}
