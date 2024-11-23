package com.demo.configservice.service;

import com.demo.configservice.constant.ApiMessage;
import com.demo.configservice.dto.request.ConfigRequest;
import com.demo.configservice.dto.response.ConfigResponse;
import com.demo.configservice.entity.Config;
import com.demo.configservice.entity.Status;
import com.demo.configservice.exception.ResourceNotFoundException;
import com.demo.configservice.exception.ValidationException;
import com.demo.configservice.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public ConfigResponse addConfig(ConfigRequest request) {
        validateRequest(request);

        Optional<Config> existing = configRepository.findByGroupAndTypeAndKey(request.getGroup(), request.getType(), request.getConfigKey());
        if(existing.isPresent()) {
            Config existingConfig = existing.get();
            existingConfig.setStatus(Status.INACTIVE);
            configRepository.save(existingConfig);
        }
        Config config = Config.builder()
                .group(request.getGroup())
                .type(request.getType())
                .key(request.getConfigKey())
                .value(request.getConfigValue())
                .status(Status.ACTIVE)
                .create_at(LocalDateTime.now())
                .created_by("ADMIN")
                .update_at(LocalDateTime.now())
                .updated_by("ADMIN")
                .build();
        Config saved = configRepository.save(config);
        return mapToResponse(saved);
    }

    @Override
    public ConfigResponse updateConfig(UUID configId, ConfigRequest request) {
        validateRequest(request);

        Config config = configRepository.findById(configId)
                .orElseThrow(() -> new ValidationException(
                        ApiMessage.ERROR_CONFIG_ID_NOT_FOUND.getMessage(),
                        ApiMessage.ERROR_CONFIG_ID_NOT_FOUND.getMessage(),
                        ApiMessage.ERROR_CONFIG_ID_NOT_FOUND.getStatusCode()));
        config.setStatus(Status.INACTIVE);
        configRepository.save(config);
        Config updatedConfig = Config.builder()
                .group(request.getGroup())
                .type(request.getType())
                .key(request.getConfigKey())
                .value(request.getConfigValue())
                .status(Status.ACTIVE)
                .create_at(config.getCreate_at())
                .created_by(config.getCreated_by())
                .update_at(LocalDateTime.now())
                .updated_by("ADMIN")
                .build();
        configRepository.save(updatedConfig);
        return mapToResponse(updatedConfig);
    }

    @Override
    public Page<ConfigResponse> getActiveConfigs(String group, String type, String configKey, Status status, Pageable pageable) {
        Page<Config> configs = configRepository.findByGroupAndTypeAndKeyAndStatus(group, type, configKey, status, pageable);
        return configs.map(this::mapToResponse);
    }

    @Override
    public ConfigResponse deleteConfig(UUID configId) {
        Config config = configRepository.findById(configId)
                .orElseThrow(() -> new ValidationException(
                        ApiMessage.ERROR_CONFIG_ID_NOT_FOUND.getMessage(),
                        ApiMessage.ERROR_CONFIG_ID_NOT_FOUND.getMessage(),
                        ApiMessage.ERROR_CONFIG_ID_NOT_FOUND.getStatusCode()));

        config.setStatus(Status.INACTIVE);
        configRepository.save(config);
        return mapToResponse(config);
    }

    private ConfigResponse mapToResponse(Config config) {
        return ConfigResponse.builder()
                .configID(config.getConfigId())
                .group(config.getGroup())
                .type(config.getType())
                .configKey(config.getKey())
                .configValue(config.getValue())
                .status(config.getStatus())
                .create_at(config.getCreate_at())
                .create_by(config.getCreated_by())
                .update_at(config.getUpdate_at())
                .update_by(config.getUpdated_by())
                .build();
    }

    private void validateRequest(ConfigRequest request) {
        if (request.getGroup() == null || request.getGroup().isBlank()) {
            throw new ValidationException(ApiMessage.ERROR_GROUP_REQUIRED.getMessage(),
                    ApiMessage.ERROR_GROUP_REQUIRED.getMessage(),
                    ApiMessage.ERROR_GROUP_REQUIRED.getStatusCode());
        }
        if (request.getType() == null || request.getType().isBlank()) {
            throw new ValidationException(ApiMessage.ERROR_TYPE_REQUIRED.getMessage(),
                    ApiMessage.ERROR_TYPE_REQUIRED.getMessage(),
                    ApiMessage.ERROR_TYPE_REQUIRED.getStatusCode());
        }
        if (request.getConfigKey() == null || request.getConfigKey().isBlank()) {
            throw new ValidationException(ApiMessage.ERROR_KEY_REQUIRED.getMessage(),
                    ApiMessage.ERROR_KEY_REQUIRED.getMessage(),
                    ApiMessage.ERROR_KEY_REQUIRED.getStatusCode());
        }
        if (request.getConfigValue() == null || request.getConfigValue().isBlank()) {
            throw new ValidationException(ApiMessage.ERROR_VALUE_REQUIRED.getMessage(),
                    ApiMessage.ERROR_VALUE_REQUIRED.getMessage(),
                    ApiMessage.ERROR_VALUE_REQUIRED.getStatusCode());
        }
    }

}
