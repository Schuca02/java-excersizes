package learn.pets.data;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@ComponentScan
public class DbTestConfig {
    @Bean
    public DataSource getDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        result.setUrl("jdbc:mysql://localhost:3306/pets_test");
        result.setUser("root");
        result.setPassword("Joker1954");
        return result;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
