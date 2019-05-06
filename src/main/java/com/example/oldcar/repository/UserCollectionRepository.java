package com.example.oldcar.repository;

import com.example.oldcar.domain.User;
import com.example.oldcar.domain.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCollectionRepository extends JpaRepository<UserCollection,Long> {
    UserCollection findFirstByUserAndTypeOrderByIdDesc(User user, Integer type);
}
