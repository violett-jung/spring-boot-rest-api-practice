package com.study.restapipractice.data;

import com.study.restapipractice.entity.MemberEntity;
import com.study.restapipractice.repository.MemberRepository;
import com.study.restapipractice.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final MemberRepository memberRepository;

//    @Bean
//    public CommandLineRunner loadData() {
//        return args -> {
//            try {
//                String jsonFilePath = "classpath:account.json";
//                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);
//                if (inputStream == null) {
//                    throw new FileNotFoundException("Cannot find file: " + jsonFilePath);
//                }
//                List<MemberEntity> members = JsonUtil.readJsonFromStream(inputStream, MemberEntity.class);
//                memberRepository.saveAll(members);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };
//    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            String jsonFilePath = "./account.json"; // JSON 파일 경로
            List<MemberEntity> members = JsonUtil.readJsonFromFile(jsonFilePath, MemberEntity.class);
            memberRepository.saveAll(members);
        };
    }
}
