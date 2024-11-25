package com.demo.configservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiMessage {
    // Success Messages
    SUCCESS_ADD_CONFIG("success", "C0001: You have successfully added a new configuration", HttpStatus.OK),
    SUCCESS_UPDATE_CONFIG("success", "C0008: You have successfully updated a configuration", HttpStatus.OK),
    SUCCESS_GET_CONFIG("success", "C0009: Get configurations succeeded", HttpStatus.OK),
    SUCCESS_SOFT_DELETE_CONFIG("success", "C0012: You have successfully deleted a configuration", HttpStatus.OK),

    // Error Messages
    ERROR_GROUP_REQUIRED("error", "C0002: Group is required", HttpStatus.BAD_REQUEST),
    ERROR_TYPE_REQUIRED("error", "C0003: Type is required", HttpStatus.BAD_REQUEST),
    ERROR_KEY_REQUIRED("error", "C0004: Key is required", HttpStatus.BAD_REQUEST),
    ERROR_VALUE_REQUIRED("error", "C0005: Value is required", HttpStatus.BAD_REQUEST),
    ERROR_UPDATE_DB("error", "C0006: Fail to update data in Database", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_INSERT_DB("error", "C0007: Fail to insert data in Database", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_CONFIG_NOT_FOUND("error", "C0010: Configuration not found", HttpStatus.NOT_FOUND),
    ERROR_QUERY_DB("error", "C0011: Fail to query configurations in database", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_CONFIG_ID_NOT_FOUND("error", "C0013: Configuration ID not found", HttpStatus.NOT_FOUND),
    ERROR_DELETE_DB("error", "C0014: Fail to delete configurations in database", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String status;
    private final String message;
    private final HttpStatus statusCode;

}
