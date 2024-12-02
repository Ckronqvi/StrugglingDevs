package com.ckronqvi.website.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.ckronqvi.website.enums.InterestStatus;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_interests", uniqueConstraints = @UniqueConstraint(columnNames = { "post_id", "developer_id" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private ProjectPost projectPost;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private User developer;

    @Enumerated(EnumType.STRING)
    private InterestStatus status = InterestStatus.PENDING;

    @CreationTimestamp
    private LocalDateTime createdAt;

}