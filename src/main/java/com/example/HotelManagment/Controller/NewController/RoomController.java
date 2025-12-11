package com.example.HotelManagment.Controller.NewController;

import com.example.HotelManagment.Dto.RoomDTO;
import com.example.HotelManagment.Service.NewService.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Use application properties or default to a safe directory
    @Value("${file.upload-dir:uploads/}")
    private String uploadDir;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(
            @RequestPart("room") RoomDTO roomDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = saveFile(file);
                roomDTO.setImage(fileName);
            }
            RoomDTO createdRoom = roomService.createRoom(roomDTO);
            return ResponseEntity.ok(createdRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(
            @PathVariable Long id,
            @RequestPart("room") RoomDTO roomDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = saveFile(file);
                roomDTO.setImage(fileName);
            }
            RoomDTO updatedRoom = roomService.updateRoom(id, roomDTO);
            return ResponseEntity.ok(updatedRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }

    // ================= Improved File Save Method =================
    private String saveFile(MultipartFile file) throws IOException {
        // Create absolute path in project directory or user home directory
        String absoluteUploadDir = getSafeUploadDirectory();

        // Create directory if it doesn't exist
        Path uploadPath = Paths.get(absoluteUploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique filename
        String originalFileName = file.getOriginalFilename();
        String extension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString() + extension;

        // Save file
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());

        // Return relative path for database storage
        return "uploads/" + fileName;
    }

    // ================= Safe Directory Selection =================
    private String getSafeUploadDirectory() {
        // Try project directory first
        String projectDir = System.getProperty("user.dir");
        Path projectUploadPath = Paths.get(projectDir, "uploads");

        // Try user home directory as fallback
        String userHome = System.getProperty("user.home");
        Path homeUploadPath = Paths.get(userHome, "hotel_uploads");

        // Use project directory if writable, otherwise use user home
        try {
            if (Files.isWritable(projectUploadPath) ||
                    Files.isWritable(projectUploadPath.getParent())) {
                return projectUploadPath.toString();
            } else {
                return homeUploadPath.toString();
            }
        } catch (Exception e) {
            return homeUploadPath.toString();
        }
    }
}