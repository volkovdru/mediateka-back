package ru.volkovd.baza_adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AudioStream {
    private String lang;
    private Long bitrate;
    @JsonProperty("sample_rate")
    private String sampleRate;
    private String channels;
    @JsonProperty("channelmode")
    private String channelMode;
    private String dataformat;
    @JsonProperty("wformattag")
    private String wFormatTag;
    private String lossless;
    @JsonProperty("bitrate_mode")
    private String bitrateMode;
    @JsonProperty("compression_ratio")
    private String compressionRatio;
    private String codec;
}