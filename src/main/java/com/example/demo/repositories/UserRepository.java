package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GET
    public List<User> findAll() {
        String sql = "select * from userTable";
        RowMapper<User> userRowMapper = (r, i) -> {
            User user = new User();
            user.setId(r.getObject("id", UUID.class));
            user.setName(r.getString("name"));
            user.setAge(r.getInt("age"));
            return user;
        };
        return jdbcTemplate.query(sql, userRowMapper);
    }

    //POST
    public User save(User user) {
        UUID id = UUID.randomUUID();
        String sql = "insert into userTable (id, name, age) values (?, ?, ?)";

        // Вставка сгенерированного ID
        jdbcTemplate.update(sql, id, user.getName(), user.getAge());

        // Устанавливаем сгенерированный ID в объект User
        user.setId(id);
        return user; // Возвращаем объект User с установленным ID
    }

    public void deleteById(UUID id) {
        String sql = "delete from userTable WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public void update(User user) {
        String sql = "UPDATE userTable SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getId());
    }

    public User findById(UUID id) {
        String sql = "select * from userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User user = new User();
            user.setId(r.getObject("id", UUID.class));
            user.setName(r.getString("name"));
            user.setAge(r.getInt("age"));
            return user;
        };
        List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
        return users.isEmpty() ? null : users.get(0);
    }


}
