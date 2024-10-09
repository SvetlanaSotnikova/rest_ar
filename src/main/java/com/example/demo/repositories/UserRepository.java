package com.example.demo.repositories;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    // GET
    public List<User> findAll() {
        String sql = "select * from userTable";
        log.info("find all users");
        return jdbcTemplate.query(sql, getUserRowMapper());
    }

    //POST
    public User save(User user) {
        UUID id = UUID.randomUUID();
        String sql = "insert into userTable (id, name, age) values (?, ?, ?)";

        jdbcTemplate.update(sql, id, user.getName(), user.getAge());

        user.setId(id);
        return user;
    }

    //DELETE
    public void deleteById(UUID id) {
        String sql = "delete from userTable WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    //PUT
    public void update(User user) {
        String sql = "UPDATE userTable SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getId());
    }

    //GET
    public User findById(UUID id) {
        String sql = "select * from userTable WHERE id = ?";

        log.info("find user with ID: {}", id);

        List<User> users = jdbcTemplate.query(sql, getUserRowMapper(), id);
        return users.isEmpty() ? null : users.getFirst();
    }

    private RowMapper<User> getUserRowMapper() {
        return (r, i) -> {
            User user = new User();
            user.setId(r.getObject("id", UUID.class));
            user.setName(r.getString("name"));
            user.setAge(r.getInt("age"));
            return user;
        };
    }

}
