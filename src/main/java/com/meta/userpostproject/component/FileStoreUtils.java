package com.meta.userpostproject.component;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:50
*/

import com.meta.userpostproject.dto.PostDto;
import org.apache.tika.Tika;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FileStoreUtils {

    private static final String UPLOAD_DIR_PATH = System.getProperty("user.home") + "/Desktop/META_FILE_STORE";
    /**
     * This function will store the multipart file in server
     * and then it will return file path
     *
     * @param multipartFile
     * @return
     */

    public String saveMultipartFile(MultipartFile multipartFile, PostDto postDto) throws IOException {

        File directoryFile = new File(UPLOAD_DIR_PATH);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
//        String originalFileName = multipartFile.getOriginalFilename();
//        String filePath = UPLOAD_DIR_PATH + "/" + originalFileName;
//        File myFile = new File(filePath);

        String generatedFileName = generateValidFileName(multipartFile, postDto);
        String filePath = UPLOAD_DIR_PATH + File.separator + generatedFileName;

        File newFile = new File(filePath);

        if(multipartFile instanceof PostMultipartFile){
          PostMultipartFile tempPostMultipartFile = new PostMultipartFile(multipartFile.getOriginalFilename());
          tempPostMultipartFile.transferTo(newFile);
        }

        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;

    }

    /**
     * @param multipartFile
     * @return boolean : whether imageExtension is valid or not
     */
    public Boolean imageExtensionValidator(MultipartFile multipartFile) {

        List<String> validImageTypes = List.of("image/jpeg", "image/png", "image/jpg", "application/octet-stream");
        String uploadedFileType = multipartFile.getContentType();

        Boolean isValidFileType = false;
        // check if the file type is valid
        for (String validImageType : validImageTypes) {
            if (validImageType.equals(uploadedFileType)) {
                isValidFileType = true;
                break;
            }
        }

        return isValidFileType;
    }

    public String generateValidFileName(MultipartFile multipartFile, PostDto postDto){
        // postId-date-authorName.imageExtension
        String imageNameFormat = "%s_%s_%s";
//        Short postId = postDto.getId() != null? postDto.getId():100;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fileUploadedTime = LocalDateTime.now().format(formatter);
        String fileFormat = multipartFile.getContentType().substring(multipartFile.getContentType().indexOf("/") + 1);

        String imageFileName =  imageNameFormat.formatted(fileUploadedTime, "authorName") + "." + fileFormat;

        return imageFileName;

    }



}
