package com.example.demo.service.impl;


import com.example.demo.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    String rootPath = "/Users/yangzhelun/Desktop/development/uploadFile/";
//    private final Path root = Paths.get("/Users/yangzhelun/Desktop/development/uploadFile");

//    String rootPath = "/root/uploadFile/";
    private final Path root = Paths.get(rootPath);

    @Override
    public void init() {
        try {
            File uploadFolder = root.toFile();
            if(!uploadFolder.exists()){
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void savePost(MultipartFile[] files,String pid , String uid) {
        Arrays.asList(files).forEach(file ->{
            try {
                Path userPath = Paths.get(rootPath + uid + "/postFile/" + pid);
                File userFile = userPath.toFile();
                System.out.println(rootPath);
                if(! userFile.exists()){
                    userFile.mkdir();
                }
                Files.copy(file.getInputStream(),userPath.resolve(file.getOriginalFilename()));
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        });
    }

    @Override
    public void saveActivity(MultipartFile[] files, String aid, String uid) {
        Arrays.asList(files).forEach(file ->{
            try {
                Path userPath = Paths.get(rootPath + uid + "/activityFile/" + aid);
                File userFile = userPath.toFile();
                System.out.println(rootPath);
                if(! userFile.exists()){
                    userFile.mkdir();
                }
                Files.copy(file.getInputStream(),userPath.resolve(file.getOriginalFilename()));
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        });
    }

    @Override
    public void saveProfile(MultipartFile file, String uid) {
        try {
            Path userPath = Paths.get(rootPath + uid + "/userProfile/");
            File userFile = userPath.toFile();
            if(! userFile.exists()){
                userFile.mkdir();
            }
            Files.copy(file.getInputStream(),userPath.resolve(file.getOriginalFilename()));
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }



    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
