package com.example.carmanager.v2.user.repository;

import com.example.carmanager.v2.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    Optional<User> findUserByEmail(String email);
    List<User> findAll();

    User findByUserId(Long userId);
    User findUserByEmailAndProvider(String Email, String provider);
    @Query("SELECT u.userId FROM User u WHERE u.email = :email AND u.provider = :provider")
    Long findUserIdByEmailAndProvider(@Param("email") String email, @Param("provider") String provider);
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.userId = :userId", nativeQuery = true)
    void deleteById(Long userId);
}
