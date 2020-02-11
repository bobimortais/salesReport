package com.test.sales.parser;

import com.test.sales.entity.FileInfo;
import com.test.sales.watcher.EnvironmentConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class InputFileParser
{
    public static FileInfo getFileInfo(Path file) throws IOException
    {
        Stream<String> lines = Files.lines(file);
        FileInfo fileInfo = new FileInfo();

        lines.forEach(element -> {
            String[] columns = element.split(EnvironmentConstants.getInstance().getINPUT_FILE_COLUMN_SEPARATOR());
            System.out.println(columns.length);
        }
        );

        lines.close();
        return fileInfo;
    }
}
