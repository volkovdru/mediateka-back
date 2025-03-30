package ru.volkovd;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class ProjectCombiner {

    private static final List<String> DEFAULT_EXTENSIONS = Arrays.asList(
            ".java", ".kt", ".js", ".py", ".html", ".css", ".xml", ".json"
    );

    private static final List<String> EXCLUDE_DIRS = Arrays.asList(
            ".git", "build", "node_modules", "target", "__pycache__", "venv"
    );

    public static void main(String[] args) throws IOException {
        String projectPath = "E:/projects/java/mediateka-back/";
        Path outputPath = Paths.get("combined_code.txt");
        combineProjectFiles(projectPath, outputPath);
        System.out.println("✅ Все файлы объединены в: " + outputPath.toAbsolutePath());
    }

    public static void combineProjectFiles(String projectDir, Path outputFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            Files.walk(Paths.get(projectDir))
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        String fileName = path.getFileName() != null ? path.getFileName().toString() : "";
                        return DEFAULT_EXTENSIONS.stream().anyMatch(fileName::endsWith);
                    })
                    .filter(path -> {
                        // Проверяем, что ни одна из родительских папок не в EXCLUDE_DIRS
                        for (Path parent = path.getParent(); parent != null; parent = parent.getParent()) {
                            String dirName = parent.getFileName() != null ? parent.getFileName().toString() : "";
                            if (EXCLUDE_DIRS.contains(dirName)) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .forEach(path -> {
                        try {
                            writer.write("\n\n=== " + path + " ===\n\n");
                            writer.write(Files.readString(path) + "\n");
                        } catch (IOException e) {
                            System.err.println("⚠ Ошибка при чтении файла: " + path);
                        }
                    });
        }
    }
}