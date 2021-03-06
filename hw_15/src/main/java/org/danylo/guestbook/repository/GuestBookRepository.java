package org.danylo.guestbook.repository;

import org.danylo.guestbook.models.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
}
