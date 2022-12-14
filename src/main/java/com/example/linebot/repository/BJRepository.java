package com.example.linebot.repository;

import com.example.linebot.value.BJItem;
import com.example.linebot.value.BJSlot;
import com.example.linebot.value.BJTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public class BJRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public BJRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    public List<BJTuple> findPreviousItems(){
        //language=sql
        String sql = "select card_label, card_suit, card_number " +
                "from trumps " +
                "where push_at <= ? "; //<1>

        LocalTime now = LocalTime.now(); //<2>
        List<BJTuple> list =
                jdbc.query(sql, //<3>
                        new DataClassRowMapper(BJTuple.class), //<4>
                        now); //<5>
        return list;
    }

    public void deletePreviousItems(){
        //language=sql
        String sql = "delete from trumps " +
                "where card_suit <= ? ";

        LocalTime now = LocalTime.now();
        jdbc.update(sql, now);
    }

    public void insert(BJItem item){
        // language = SQL
        String sql = "insert into reminder_item "
                + "(user_id, push_at, push_text) "
                + "values (?, ?, ?)";

        String userId = item.getUserId();
        BJSlot slot = item.getSlot();
        jdbc.update(sql, userId, slot.getPushAt(), slot.getPushtext());
    }

}
