package com.example.carmanager.user.entity;

import com.example.carmanager.user.dto.JoinRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
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

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "provider")
    private static String provider;

    @UpdateTimestamp    // 현재시간 디폴트값
    @Column(name="createAt", updatable = false) // insert시 최초 시간만 넣고 시간 수정 안되게
    private LocalDateTime createdAt;

    @UpdateTimestamp    // 현재시간 디폴트값
    @Column(name="modifiedAt", updatable = false) // insert시 최초 시간만 넣고 시간 수정 안되게
    private LocalDateTime modifiedAt;

    public User(Long userId) {
        this.userId = userId;
    }

    public User(String nickname, String email, Date birth, String adress, String phone) {
        this.nickname = nickname;
        this.email = email;
        this.birth = birth;
        this.adress = adress;
        this.phone = phone;
    }

    public static User of(JoinRequest request) {
        return new User(request.getPassword(), request.getNickname(), request.getBirth(), request.getAdress(), request.getPhone());
    }

    public void update(String email, String name) {
        this.email = email;
        this.nickname = name;
    }
}
