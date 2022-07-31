package com.example.restful_demo.repository;

import com.example.restful_demo.entity.User;
import com.example.restful_demo.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository  extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    @Query("select u from User u join fetch u.vouchers join fetch u.roles where u.email=:email")
    User findUserHasVoucher(@Param("email") String email);
    Optional<User> findUserById(int id);
    Boolean existsByEmail(String email);

}
