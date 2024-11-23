package com.demo.configservice.service;

import com.demo.configservice.dto.request.ConfigRequest;
import com.demo.configservice.dto.response.ConfigResponse;
import com.demo.configservice.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ConfigService {
    ConfigResponse addConfig(ConfigRequest request);
    ConfigResponse updateConfig(UUID configId, ConfigRequest request);
    Page<ConfigResponse> getActiveConfigs(String group, String type, String configKey, Status status, Pageable pageable);
    ConfigResponse deleteConfig(UUID configId);
}
