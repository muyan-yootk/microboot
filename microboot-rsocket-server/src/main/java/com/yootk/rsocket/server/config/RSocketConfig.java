package com.yootk.rsocket.server.config;

import com.yootk.rsocket.constants.UploadConstants;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;

@Configuration
public class RSocketConfig { // RSocket配置类
    @Bean // 配置策略
    public RSocketStrategies getRSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder())) // 编码器
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder())) // 解码器
                .metadataExtractorRegistry(metadataExtractorRegistry -> {   // 元数据注册处理
                    metadataExtractorRegistry.metadataToExtract(
                            MimeType.valueOf(UploadConstants.MIME_FILE_NAME), String.class, UploadConstants.FILE_NAME);
                    metadataExtractorRegistry.metadataToExtract(
                            MimeType.valueOf(UploadConstants.MIME_FILE_EXTENSION), String.class, UploadConstants.FILE_EXT);
                }).build();
    }
}
