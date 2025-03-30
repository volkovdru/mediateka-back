package ru.volkovd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
@EnableFilesystemStores(basePackages = "ru.volkovd.music_player.repository")
public class StorageConfig {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Bean
    FileSystemResourceLoader fileSystemResourceLoader() throws IOException {
        return new FileSystemResourceLoader(Paths.get(uploadDirectory).toAbsolutePath().toString());
    }
}