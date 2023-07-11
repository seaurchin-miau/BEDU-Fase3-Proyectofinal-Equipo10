package bedu.proyecto.ecommerce.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;

public class UploadFileServiceTest {

    private UploadFileService uploadFileService;

    @BeforeEach
    void setUp() {
        uploadFileService = new UploadFileService();
    }

    @Test
    void testSaveImage(@TempDir Path tempDir) throws IOException {
        uploadFileService.setFolder(tempDir.toString());

        MockMultipartFile file = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", "Test Image".getBytes());

        String result = uploadFileService.saveImage(file);

        assertEquals("test.jpg", result);

        Path imagePath = tempDir.resolve("test.jpg");
        assertTrue(Files.exists(imagePath));
        byte[] imageBytes = Files.readAllBytes(imagePath);
        assertArrayEquals("Test Image".getBytes(), imageBytes);
    }

    @Test
    void testSaveImageWithEmptyFile(@TempDir Path tempDir) throws IOException {
        uploadFileService.setFolder(tempDir.toString());

        MockMultipartFile emptyFile = new MockMultipartFile("empty.jpg", new byte[0]);

        String result = uploadFileService.saveImage(emptyFile);

        assertEquals("default.jpg", result);
    }

    @Test
    void testDeleteImage(@TempDir Path tempDir) throws IOException {
        Path imagePath = tempDir.resolve("test.jpg");
        Files.createFile(imagePath);

        assertTrue(Files.exists(imagePath));

        uploadFileService.setFolder(tempDir.toString());
        uploadFileService.deleteImage("test.jpg");

        assertFalse(Files.exists(imagePath));
    }
}