package com.sixthpoint.spring.boot.elasticcache.redis.service;

import com.sixthpoint.spring.boot.elasticcache.redis.service.dto.TaskDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TaskService {

    @Cacheable(value = "getLongRunningTaskResult", key="{#seconds}", cacheManager = "cacheManager1Hour")
    public Optional<TaskDTO> getLongRunningTaskResult(long seconds) {
        try {
            long randomMultiplier = new Random().nextLong();
            long calculatedResult = randomMultiplier * seconds;
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setCalculatedResult(calculatedResult);
            Thread.sleep(2000); // 2 Second Delay to Simulate Workload
            return Optional.of(taskDTO);
        } catch (InterruptedException e) {
            return Optional.of(null);
        }
    }

    @CacheEvict(value = "getLongRunningTaskResult", key = "#seconds")
    public void resetLongRunningTaskResult(long seconds) {
        // Intentionally blank
    }
}
