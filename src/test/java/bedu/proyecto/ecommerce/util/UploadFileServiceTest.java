package bedu.proyecto.ecommerce.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UploadFileServiceTest {

    @Mock
    private Path mockPath;

    @Mock
    private File mockFile;

    @InjectMocks
    private UploadFileService uploadFileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveImage() throws IOException {
        // Arrange
        String folder = "images//";
        String originalFilename = "test.jpg";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", originalFilename, "image/jpeg", "test image content".getBytes());

        when(mockPath.toString()).thenReturn(folder + originalFilename);
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.getAbsolutePath()).thenReturn(folder + originalFilename);
        when(mockFile.getPath()).thenReturn(folder + originalFilename);

        // Act
        String result = uploadFileService.saveImage(mockMultipartFile);

        // Assert
        assertEquals(originalFilename, result);
    }

    @Test
    public void testSaveImageEmptyFile() throws IOException {
        // Arrange
        MockMultipartFile emptyFile = new MockMultipartFile("emptyfile", new byte[0]);

        // Act
        String result = uploadFileService.saveImage(emptyFile);

        // Assert
        assertEquals("default.jpg", result);
    }

    @Test
    public void testDeleteImage() {
        // Arrange
        String imageName = "test.jpg";
        String folder = "images/";

        // Act
        uploadFileService.deleteImage(imageName);

        // Assert
        assertEquals(null, mockFile.getPath());
    }
}