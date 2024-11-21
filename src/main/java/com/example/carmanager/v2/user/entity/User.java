package com.example.carmanager.v2.user.entity;

import com.example.carmanager.v2.user.dto.JoinRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "provider")
    private String provider;

    @UpdateTimestamp    // 현재시간 디폴트값
    @Column(name="createAt", updatable = false) // insert시 최초 시간만 넣고 시간 수정 안되게
    private LocalDateTime createdAt;

    @UpdateTimestamp    // 현재시간 디폴트값
    @Column(name="modifiedAt", updatable = false) // insert시 최초 시간만 넣고 시간 수정 안되게
    private LocalDateTime modifiedAt;

    @Transient
    private String role;
}
