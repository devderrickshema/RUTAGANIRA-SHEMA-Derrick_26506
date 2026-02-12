package auca.ac.rw.restfullApiAssignment.controller.user;

import auca.ac.rw.restfullApiAssignment.model.user.ApiResponse;
import auca.ac.rw.restfullApiAssignment.model.user.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> userProfiles = new ArrayList<>();
    private Long nextId = 1L;

    public UserProfileController() {
        userProfiles.add(new UserProfile(nextId++, "john_doe", "john@example.com", "John Doe", 28, "USA", "Software developer", true));
        userProfiles.add(new UserProfile(nextId++, "jane_smith", "jane@example.com", "Jane Smith", 32, "Canada", "Product manager", true));
        userProfiles.add(new UserProfile(nextId++, "mike_jones", "mike@example.com", "Mike Jones", 25, "UK", "Designer", true));
        userProfiles.add(new UserProfile(nextId++, "sarah_williams", "sarah@example.com", "Sarah Williams", 29, "Australia", "Marketing specialist", false));
        userProfiles.add(new UserProfile(nextId++, "david_brown", "david@example.com", "David Brown", 35, "USA", "Data analyst", true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "User profiles retrieved successfully", userProfiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        Optional<UserProfile> user = userProfiles.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
        
        if (user.isPresent()) {
            ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile found", user.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserByUsername(@PathVariable String username) {
        Optional<UserProfile> user = userProfiles.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
        
        if (user.isPresent()) {
            ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile found by username", user.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByCountry(@PathVariable String country) {
        List<UserProfile> result = userProfiles.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users from " + country + " retrieved", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByAgeRange(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> result = userProfiles.stream()
                .filter(u -> u.getAge() >= min && u.getAge() <= max)
                .collect(Collectors.toList());
        
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users in age range " + min + "-" + max + " retrieved", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getActiveUsers() {
        List<UserProfile> result = userProfiles.stream()
                .filter(UserProfile::isActive)
                .collect(Collectors.toList());
        
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Active users retrieved", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUserProfile(@RequestBody UserProfile userProfile) {
        userProfile.setUserId(nextId++);
        userProfile.setActive(true);
        userProfiles.add(userProfile);
        
        ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile created successfully", userProfile);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile updatedProfile) {
        Optional<UserProfile> existingUser = userProfiles.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
        
        if (existingUser.isPresent()) {
            UserProfile user = existingUser.get();
            user.setUsername(updatedProfile.getUsername());
            user.setEmail(updatedProfile.getEmail());
            user.setFullName(updatedProfile.getFullName());
            user.setAge(updatedProfile.getAge());
            user.setCountry(updatedProfile.getCountry());
            user.setBio(updatedProfile.getBio());
            
            ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile updated successfully", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        Optional<UserProfile> existingUser = userProfiles.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
        
        if (existingUser.isPresent()) {
            UserProfile user = existingUser.get();
            user.setActive(true);
            
            ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile activated successfully", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        Optional<UserProfile> existingUser = userProfiles.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
        
        if (existingUser.isPresent()) {
            UserProfile user = existingUser.get();
            user.setActive(false);
            
            ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile deactivated successfully", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUserProfile(@PathVariable Long userId) {
        boolean removed = userProfiles.removeIf(u -> u.getUserId().equals(userId));
        
        if (removed) {
            ApiResponse<Void> response = new ApiResponse<>(true, "User profile deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Void> response = new ApiResponse<>(false, "User profile not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
