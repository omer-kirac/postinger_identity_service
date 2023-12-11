package com.kafein.intern.identity.repository;

import com.kafein.intern.identity.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
