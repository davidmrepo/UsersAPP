package org.users.enquires.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.users.enquires.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
