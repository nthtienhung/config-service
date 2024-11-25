package com.demo.configservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "config", schema = "configservice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "configid")
    private UUID configId;

    @Column(name = "\"group\"")
    private String group; // 'group' is a reserved keyword in Java

    @Column(name = "\"type\"")
    private String type;

    @Column(name = "\"key\"")
    private String key;

    @Column(name = "\"value\"")
    private String value;

    @Enumerated(EnumType.STRING)
    private Status status; // e.g., 'active', 'inactive'

    private LocalDateTime create_at;

    private String created_by;

    private LocalDateTime update_at;

    private String updated_by;
}
