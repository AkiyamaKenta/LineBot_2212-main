package com.example.linebot.repository;

import com.example.linebot.value.ReminderItem;
import com.example.linebot.value.ReminderSlot;
import com.example.linebot.value.ReminderTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public class ReminderRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ReminderRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    public List<ReminderTuple> findPreviousItems(){
        //language=sql
        String sql = "select user_id, push_at, push_text " +
                "from reminder_item " +
                "where push_at <= ? "; //<1>

        LocalTime now = LocalTime.now(); //<2>
        List<ReminderTuple> list =
                jdbc.query(sql, //<3>
                        new DataClassRowMapper(ReminderTuple.class), //<4>
                        now); //<5>
        return list;
    }

    public void deletePreviousItems(){
        //language=sql
        String sql = "delete from reminder_item " +
                "where push_at <= ? ";

        LocalTime now = LocalTime.now();
        jdbc.update(sql, now);
    }

    public void insert(ReminderItem item){
        // language = SQL
        String sql = "insert into reminder_item "
                + "(user_id, push_at, push_text) "
                + "values (?, ?, ?)";

        String userId = item.getUserId();
        ReminderSlot slot = item.getSlot();
        jdbc.update(sql, userId, slot.getPushAt(), slot.getPushtext());
    }

}
