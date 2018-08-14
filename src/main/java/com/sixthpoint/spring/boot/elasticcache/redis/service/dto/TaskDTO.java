package com.sixthpoint.spring.boot.elasticcache.redis.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskDTO implements Serializable {

    public long calculatedResult;
}
