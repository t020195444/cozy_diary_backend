package com.example.demo.controller;

import com.example.demo.dto.FileResponse;
import com.example.demo.dto.PostRequest;
import com.example.demo.entity.Post;
import com.example.demo.model.FileInfo;
import com.example.demo.service.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class FilesController {

    @Autowired
    FileStorageService storageService;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam(required=true, value="file") MultipartFile[] file,@RequestParam(required=true, value="jsondata") String jsonData) {
        String message = "";
        try {
            PostRequest postRequest = new PostRequest();
            postRequest = objectMapper.readValue(jsonData, PostRequest.class);
            System.out.println(postRequest.getPost().getTitle());
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            System.out.println(objectMapper.readValue(postRequest,PostRequest.class));
//            storageService.save(file);
//            message = "Uploaded the file successfully: ";
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file:!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponse(e.toString()));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
