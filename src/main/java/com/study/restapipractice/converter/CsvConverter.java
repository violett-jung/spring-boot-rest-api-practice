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
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8);
        CSVWriter csvWriter = new CSVWriter(outputStreamWriter, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        String[] csvHeader = {"Seq", "ID", "PW", "Name", "Email", "HP", "Role", "State", "Description"};
        csvWriter.writeNext(csvHeader);

        for (MemberDto member : members) {
            String[] csvData = {
                    String.valueOf(member.getSeq()),
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
        return byteArrayOutputStream.toByteArray();
    }
}
