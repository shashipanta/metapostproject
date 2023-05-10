package com.meta.userpostproject.component;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:50
*/

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileStoreUtils {

    /**
     * This function will store the multipart file in server
     * and then it will return file path
     *
     * @param multipartFile
     * @return
     */

    public String saveMultipartFile(MultipartFile multipartFile) throws IOException, TikaException {


        String dirPath = System.getProperty("user.home") + "/Desktop/META_FILE_STORE";
        File directoryFile = new File(dirPath);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String filePath = dirPath + "/" + originalFileName;
        File myFile = new File(filePath);


            try {
                multipartFile.transferTo(myFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return filePath;



    }
    public String extensionvalidation(MultipartFile multipartFile) throws IOException {

        String dirPaths= System.getProperty("user.home") + "/Desktop/META_FILE_STORE";
        File directoryFile = new File(dirPaths);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String filePath = dirPaths + "/" + originalFileName;
        File myFile = new File(filePath);

        Tika tika = new Tika();
        String type = tika.detect(myFile);

        return type;

    }


}
