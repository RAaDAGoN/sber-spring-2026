package org.radagon.day4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("")
    public String index(
            @RequestParam(value = "path", required = false) String path,
            Model model
    ) {
        if (path == null) {
            path = System.getProperty("user.home");
        }

        File dir = new File(path);

        if (!dir.exists() || !dir.isDirectory() || !dir.canRead()) {
            model.addAttribute("dirNotExists", true);

            path = System.getProperty("user.home");
            dir = new File(path);
        }

        String parentPath = dir.getParent();
        List<FileInfo> fileList = new ArrayList<>();

        File[] filesInDir = dir.listFiles();

        if (filesInDir != null) {
            for (File file : filesInDir) {
                FileInfo fileInfo = new FileInfo();

                String name = file.getName();
                String absolutePath = file.getAbsolutePath();
                String formatedData = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(file.lastModified()));
                Long size = file.length() / 1024;

                if (file.isDirectory()) {
                    fileInfo.setDirectory(file.isDirectory());
                }

                fileInfo.setName(name);
                fileInfo.setAbsolutePath(absolutePath);
                fileInfo.setLastModified(formatedData);
                fileInfo.setSize(size);

                fileList.add(fileInfo);
            }

        }

        fileList.sort((a,b)-> Boolean.compare(b.isDirectory(), a.isDirectory()));

        model.addAttribute("fileList", fileList);
        model.addAttribute("path", path);
        model.addAttribute("parentPath", parentPath);

        return "index";
    }
}
