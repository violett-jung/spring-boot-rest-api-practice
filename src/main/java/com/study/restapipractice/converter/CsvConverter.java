package com.study.restapipractice.converter;

import com.opencsv.CSVWriter;
import com.study.restapipractice.dto.MemberDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvConverter {

    public static byte[] convertToCsvBinary(List<MemberDto> members) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //OutputStream 인터페이스 구현(출력된 데이터를 바이트 배열로 변환할 수 있도록 함)
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8); //Writer 인터페이스 구현(CSVWriter가 생성한 데이터를 OutputStreamWriter를 통해 문자열로 변환)
        CSVWriter csvWriter = new CSVWriter(outputStreamWriter, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        String[] csvHeader = {"Seq", "ID", "PW", "Name", "Email", "HP", "Role", "State", "Description"};
        csvWriter.writeNext(csvHeader);

        for (MemberDto member : members) {
            String[] csvData = {
                    String.valueOf(member.getSeq()), // String.valueOf(): 주어진 인수를 문자열로 변환 -> CSV 파일을 출력할 때 모든 필드값을 문자열로 출력해야 하기 때문
                    member.getId(),
                    member.getPw(),
                    member.getName(),
                    member.getEmail(),
                    member.getHp(),
                    String.valueOf(member.getRole()),
                    String.valueOf(member.getState()),
                    member.getDescription()
            };
            csvWriter.writeNext(csvData);
        }

        csvWriter.close();
        outputStreamWriter.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
