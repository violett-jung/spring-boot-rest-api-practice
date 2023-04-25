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

    /* CommandLineRunner 인터페이스
    * 스프링 부트 애플리케이션이 시작된 후에 자동으로 실행될 코드를 작성할 수 있도록 해주는 인터페이스
    * @Bean 어노테이션을 사용하여 CommandLineRunner 타입의 빈을 등록 후 run 메소드에 실행하고 싶은 코드를 작성
    * */
    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            String jsonFilePath = "./account.json"; // JSON 파일 경로
            List<MemberEntity> members = JsonUtil.readJsonFromFile(jsonFilePath, MemberEntity.class);
            memberRepository.saveAll(members);
        };
    }

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
}
