package com.example.carmanager.user.repository;

import com.example.carmanager.user.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Query("SELECT u.userId FROM User u WHERE u.email = :email AND u.provider = :provider")
    Long findUserIdByEmailAndProvider(@Param("email") String email, @Param("provider") String provider);

    @Query("SELECT r.refreshToken FROM RefreshToken r WHERE r.userId =:userId")
    String findRefreshTokenByUserId(@Param("userId")Long userId);

    Long findUserIdByRefreshToken(String refreshToken);

    /* userid에 해당하는 refresh_token 맟 access 토큰 삭제 */
    @Modifying
    @Query(value = "DELETE FROM RefreshToken r WHERE r.userId = :userId", nativeQuery = true)
    void deleteById(Long userId);
}
