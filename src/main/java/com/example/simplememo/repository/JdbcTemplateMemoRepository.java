package com.example.simplememo.repository;

import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateMemoRepository implements MemoRepository{

    //의존성 주입?...
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MemoResponseDto save(Memo memo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("memo").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", memo.getTitle());
        parameters.put("content", memo.getContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new MemoResponseDto(key.longValue(), memo.getTitle(), memo.getContent());
    }

    @Override
    public List<MemoResponseDto> getMemos() {
        return jdbcTemplate.query("select * from memo", memoRowMapper());
    }


    @Override
    public Optional<Memo> findMemoById(Long id) {
        List<Memo> result = jdbcTemplate.query("select * from memo where id = ?", memoRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public int updateMemo(Long id, String title, String content) {
        //jdbcTemplate.update => update된 행의 수를 반환
        int update = jdbcTemplate.update("UPDATE memo SET title = ?, content = ? WHERE id = ?", title, content, id);
        return update;
    }

    @Override
    public int updateTitle(Long id, String title) {
        return jdbcTemplate.update("UPDATE memo set title = ? WHERE id = ?", title, id);
    }

    @Override
    public int deleteMemoById(Long id) {
        return jdbcTemplate.update("DELETE FROM memo WHERE id = ?", id);
    }

    @Override
    public Memo findMemoByIdOrElseThrow(Long id) {
        List<Memo> result = jdbcTemplate.query("select * from memo where id = ?", memoRowMapperV2(), id);
        return result.stream().findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    //주로 JDBC(Java Database Connectivity)와 함께 사용되는 인터페이스
    //SQL 쿼리의 결과 집합(ResultSet)을 Java 객체로 매핑하는 역할을 합니다.
    private RowMapper<MemoResponseDto> memoRowMapper(){
        return new RowMapper<MemoResponseDto>() {
            @Override
            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new MemoResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
        };
    }

    private RowMapper<Memo> memoRowMapperV2(){
        return new RowMapper<Memo>() {
            @Override
            public Memo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Memo(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content")
                );
            }
        };
    }

}
