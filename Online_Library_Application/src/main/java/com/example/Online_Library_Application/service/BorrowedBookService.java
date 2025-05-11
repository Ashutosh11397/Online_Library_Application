package com.example.Online_Library_Application.service;

import com.example.Online_Library_Application.DTO.BookDTO.UserBooksResponseDTO;
import com.example.Online_Library_Application.DTO.BorrowedDTO.CategoryReportDTO;
import com.example.Online_Library_Application.Repository.BookRepository;
import com.example.Online_Library_Application.Repository.BorrowedBookRepository;
import com.example.Online_Library_Application.Repository.UserRepository;
import com.example.Online_Library_Application.model.Book;
import com.example.Online_Library_Application.model.BookStatus;
import com.example.Online_Library_Application.model.BorrowedBook;
import com.example.Online_Library_Application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowedBookService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public String borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        LocalDate endDate = user.getMembershipStartDate().plusMonths(user.getMembershipMonths());
        if (LocalDate.now().isAfter(endDate)) {
            return "Membership expired. Please renew.";
        }

        if (book.getStatus() != BookStatus.AVAILABLE) {
            return "Book is not available.";
        }

        book.setStatus(BookStatus.TAKEN);
        bookRepository.save(book);

        BorrowedBook borrowed = new BorrowedBook();
        borrowed.setUser(user);
        borrowed.setBook(book);
        borrowed.setBorrowDate(LocalDate.now());
        borrowedBookRepository.save(borrowed);

        return "Book borrowed successfully.";
    }

    public String returnBook(Long userId, Long bookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId)
                .orElseThrow(() -> new RuntimeException("No active borrowed record found."));

        borrowedBook.setReturnDate(LocalDate.now());
        borrowedBookRepository.save(borrowedBook);

        Book book = borrowedBook.getBook();
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);

        return "Book returned successfully.";
    }

    public UserBooksResponseDTO getUserBooks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> previousBooks = borrowedBookRepository.findPreviouslyReadBooks(userId);
        List<String> currentBooks = borrowedBookRepository.findCurrentBorrowedBooks(userId);

        UserBooksResponseDTO dto = new UserBooksResponseDTO();
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setPreviouslyReadBooks(previousBooks);
        dto.setCurrentlyBorrowedBooks(String.valueOf(currentBooks)); // change DTO field from single to list

        return dto;
    }


    public List<CategoryReportDTO> getCategoryReport() {
        long totalUsers = userRepository.count();

        List<Object[]> categoryUserCounts = borrowedBookRepository.getCategoryUserCount();

        List<CategoryReportDTO> report = new ArrayList<>();
        for (Object[] result : categoryUserCounts) {
            String categoryName = (String) result[0];
            long userCount = (long) result[1];
            double percentage = ((double) userCount / totalUsers) * 100;

            CategoryReportDTO dto = new CategoryReportDTO();
            dto.setCategoryName(categoryName);
            dto.setUserCount(userCount);
            dto.setPercentage(percentage);

            report.add(dto);
        }

        return report;
    }
}


