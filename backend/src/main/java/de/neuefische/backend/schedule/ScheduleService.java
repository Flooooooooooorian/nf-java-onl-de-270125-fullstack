package de.neuefische.backend.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ScheduleService {

//    @Scheduled(fixedDelay = 1_000) // 1 sekunde
//    public void scheduleTask() {
//        System.out.println("Scheduled task executed: " + Instant.now());
//    }

//    @Scheduled(fixedRate = 1_000) // 1 sekunde
//    public void scheduleTask() {
//        System.out.println("Scheduled task executed: " + Instant.now());
//    }

    @Scheduled(cron = "${app.schedule.cron}")
    public void scheduleTask() {
        System.out.println("Scheduled task executed: " + Instant.now());
    }
}
