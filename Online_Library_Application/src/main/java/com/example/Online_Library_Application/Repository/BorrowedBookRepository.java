package com.example.Online_Library_Application.Repository;

import com.example.Online_Library_Application.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    Optional<BorrowedBook> findByUserIdAndBookIdAndReturnDateIsNull(Long userId, Long bookId);

    // Previously read books
    @Query("SELECT bb.book.bookName FROM BorrowedBook bb WHERE bb.user.id = :userId AND bb.returnDate IS NOT NULL")
    List<String> findPreviouslyReadBooks(@Param("userId") Long userId);

    @Query("SELECT bb.book.bookName FROM BorrowedBook bb WHERE bb.user.id = :userId AND bb.returnDate IS NULL")
    List<String> findCurrentBorrowedBooks(@Param("userId") Long userId);

    // keep Optional<String> if only one active book allowed
    @Query("SELECT b.category, COUNT(DISTINCT bb.user) " +
            "FROM BorrowedBook bb " +
            "JOIN bb.book b " +
            "WHERE bb.returnDate IS NOT NULL " +
            "GROUP BY b.category")
    List<Object[]> getCategoryUserCount();


}