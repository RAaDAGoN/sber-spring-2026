package org.radagon.day4;


import lombok.Data;

@Data
public class FileInfo {
    private String name;
    private String absolutePath;
    private boolean isDirectory;
    private Long size;
    private String lastModified;
}
