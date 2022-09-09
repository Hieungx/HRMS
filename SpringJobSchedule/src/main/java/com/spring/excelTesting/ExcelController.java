package com.spring.excelTesting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/excel")
public class ExcelController {

    @Resource
    ExcelUtils excelUtils;

    @GetMapping
    public List<EntityFromExcel> test (@RequestParam MultipartFile file) throws IOException {
        return  excelUtils.readFile(file);
    }
}
