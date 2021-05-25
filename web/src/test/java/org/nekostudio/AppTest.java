package org.nekostudio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nekostudio.es.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertTrue;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
    @Autowired
    private RecordRepository appUsrRepository;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }



    @Test
    public void testAop() {
        LocalDateTime localDateTime = Instant.ofEpochMilli(1621923118114L).atZone(ZoneId.of("UTC+8")).toLocalDateTime();
        System.out.println(localDateTime);
    }
}
