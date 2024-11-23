package com.demo.configservice.repository;

import com.demo.configservice.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConfigRepository extends JpaRepository<Config, UUID> , ConfigRepositoryCustom{
    Optional<Config> findByGroupAndTypeAndKey(String group, String type, String configKey);

//    Page<Config> findByGroupAndTypeAndKeyAndStatus(String group, String type, String configKey, Status status, Pageable pageable);

//    @Query(value = "SELECT * FROM configservice.config c WHERE c.\"group\" = :group AND c.\"type\" = :type AND c.\"key\" = :configKey AND c.status = :status",
//            countQuery = "SELECT count(*) FROM configservice.config c WHERE c.\"group\" = :group AND c.\"type\" = :type AND c.\"key\" = :configKey AND c.status = :status",
//            nativeQuery = true)
//    Page<Config> findByGroupAndTypeAndKeyAndStatus(@Param("group") String group, @Param("type") String type, @Param("configKey") String configKey, @Param("status") Status status, Pageable pageable);
}
