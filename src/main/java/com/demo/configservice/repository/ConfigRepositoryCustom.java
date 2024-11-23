package com.demo.configservice.repository;

import com.demo.configservice.entity.Config;
import com.demo.configservice.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConfigRepositoryCustom {
    Page<Config> findByGroupAndTypeAndKeyAndStatus(String group, String type, String configKey, Status status, Pageable pageable);
}
