package school.hei.championshipmanager.repository;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Repository
public class DataSource {
    private final String username;
    private final String url;
    private final String password;

    @Autowired
    public DataSource(@Value("${spring.datasource.username}") String username,
                      @Value("${spring.datasource.password}") String password,
                      @Value("${spring.datasource.url}") String url) {
        this.username = username;
        this.password = password;
        this.url = url;

    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

