package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VideoStream {
    private String codec;
    @JsonProperty("resolution_x")
    private String resolutionX;
    @JsonProperty("resolution_y")
    private String resolutionY;
    @JsonProperty("frame_rate")
    private String frameRate;
    @JsonProperty("bitrate_mode")
    private String bitrateMode;
    @JsonProperty("total_frames")
    private String totalFrames;
    private String fourcc;
    @JsonProperty("pixel_aspect_ratio")
    private String pixelAspectRatio;
    private String lossless;
    @JsonProperty("bits_per_sample")
    private String bitsPerSample;
    private long bitrate;
    @JsonProperty("compression_ratio")
    private String compressionRatio;
    private String dataformat;
    private String name;
    private String lang;
}