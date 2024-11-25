package com.demo.configservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse<T>{

    private String message;
    private short status;
    private String localDateTime;
//    private ConfigResponse data;
    private T data;
}
