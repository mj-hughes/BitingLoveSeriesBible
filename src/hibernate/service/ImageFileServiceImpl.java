package hibernate.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ImageFileServiceImpl implements ImageFileService {

    // file is the actual file; applicationPath is the full path of where the application is running on Tomcat
    @Override
    public String saveFile(MultipartFile file,
                           String applicationPath,
                           String imageDirectory) {
        String fileName=null;
        try {
            if (!file.isEmpty()) {
                String artifactPath = applicationPath +
                        "WEB-INF\\resources\\img\\persona";
                String sourcePath = applicationPath +
                        "..\\..\\..\\web\\WEB-INF\\resources\\img\\persona";

                fileName = file.getOriginalFilename();
                File imageFile = new File(artifactPath, fileName);
                file.transferTo(imageFile);

                File copyOfImage = new File(sourcePath, fileName);
                FileUtils.copyFile(imageFile, copyOfImage);

            }
        } catch (Exception e) {
        }
        return fileName;
    }
}
