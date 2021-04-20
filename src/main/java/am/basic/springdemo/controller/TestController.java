package am.basic.springdemo.controller;

import am.basic.springdemo.model.Role;
import am.basic.springdemo.model.excpetion.NotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {


    private String rootPath = "C:/Users/ruben.manukyan/Desktop/files/";

    @RequestMapping(value = "/test1")
    public ResponseEntity test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("resultText", "samo text");
        map.put("resultInt", 15);
        map.put("resultBoolean", false);
        map.put("resultObject", new Role().withName("someRole").withId(1500));
        return ResponseEntity.status(404).body(map);
    }


    @RequestMapping(value = "/test2")
    public ResponseEntity test2() {
        return ResponseEntity.status(404).body(new Role().withName("someRole").withId(1500));
    }


    @RequestMapping(value = "/test3")
    public ResponseEntity test3(@RequestParam String param) {
        System.out.println(param);
        return ResponseEntity.status(404).body(new Role().withName(param).withId(1500));
    }

    @RequestMapping(value = "/test4")
    public ResponseEntity test4(@RequestBody Role role) {

        System.out.println(role);
        return ResponseEntity.status(404).body(role);
    }


    @RequestMapping(value = "/test5")
    public ResponseEntity test5(@RequestBody String text) {
        System.out.println(text);
        return ResponseEntity.ok(convert(text));
    }


    @RequestMapping(value = "/test6")
    public ResponseEntity test6(@RequestParam MultipartFile image) throws NotFoundException, IOException {
        image.transferTo(new File(rootPath + image.getOriginalFilename()));
        return ResponseEntity.ok("Success");
    }


    @GetMapping(value = "/test7")
    public ResponseEntity test7(@RequestParam String fileName) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(rootPath + fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + fileName) // inline browser try to represent file  , attachment browser dowload th file
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileSystemResource);
    }


    @GetMapping(value = "/test8")
    public ResponseEntity test8(@RequestParam String fileName) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(rootPath + fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName) // inline browser try to represent file  , attachment browser dowload th file
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileSystemResource);
    }

    @GetMapping(value = "/test9")
    public ResponseEntity test9(@RequestParam String fileName) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(rootPath + fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + fileName) // inline browser try to represent file  , attachment browser dowload th file
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileSystemResource);
    }

    @GetMapping(value = "/test10/{fileName}")
    public ResponseEntity test10(@PathVariable String fileName) {
        FileSystemResource fileSystemResource = new FileSystemResource(rootPath + fileName);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + fileName) // inline browser try to represent file  , attachment browser dowload th file
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileSystemResource);
    }


    @GetMapping(value = "/test/login")
    public ResponseEntity test11() {
        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .header("location", "https://www.google.com")
                .build();
    }

    private Map<String, String> convert(String text) {
        Map<String, String> result = new HashMap<>();
        String[] params = text.trim().split(" ");
        for (String keyValue : params) {
            String[] splitted = keyValue.split("=");
            result.put(splitted[0], splitted[1]);
        }
        return result;
    }
}
