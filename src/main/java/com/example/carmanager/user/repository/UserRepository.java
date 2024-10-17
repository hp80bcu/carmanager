package com.example.carmanager.user.repository;

import com.example.carmanager.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    Optional<User> findUserByEmail(String email);
    List<User> findAll();
    User findAllByNickname(String nickname);
    User findByNicknameAndEmailAndProvider(String nickname, String email, String provider);
    User findIdByEmailAndProvider(String email, String provider);
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.userId = :userId", nativeQuery = true)
    void deleteById(Long userId);
}
