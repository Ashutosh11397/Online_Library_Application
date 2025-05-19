package com.example.Online_Library_Application.service;

import com.example.Online_Library_Application.DTO.BookDTO.UserBooksResponseDTO;
import com.example.Online_Library_Application.DTO.UserDTO.UpdateMembershipRequest;
import com.example.Online_Library_Application.DTO.UserDTO.UserResponseDTO;
import com.example.Online_Library_Application.Repository.BorrowedBookRepository;
import com.example.Online_Library_Application.exception.InvalidCredentialsException;
import com.example.Online_Library_Application.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_Library_Application.Repository.UserRepository;
import com.example.Online_Library_Application.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public User registerUser(User user) {
        if (isUserExists(user.getEmail())) {
            throw new UserAlreadyExistsException("User Already Register with email id");
        }
        return userRepository.save(user);
    }

    public boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Optional<User> login(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(password)) {
            return existingUser;
        } else {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }


    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getUserType(),
                        user.getMembershipStartDate(),
                        user.getMembershipMonths()
                ))
                .collect(Collectors.toList());
    }

    public boolean isMembershipValid(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate endDate = user.getMembershipStartDate().plusMonths(user.getMembershipMonths());
        return LocalDate.now().isBefore(endDate);
    }

    public boolean updateMembership(Long userId, UpdateMembershipRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate endDate = user.getMembershipStartDate().plusMonths(user.getMembershipMonths());

        if (LocalDate.now().isAfter(endDate)) {
            user.setMembershipStartDate(request.getMembershipStartDate() != null ?
                    request.getMembershipStartDate() : LocalDate.now());
            user.setMembershipMonths(request.getMembershipMonths() != null ?
                    request.getMembershipMonths() : 12);
            userRepository.save(user);

            return true;
        }
        return false;
    }


}



