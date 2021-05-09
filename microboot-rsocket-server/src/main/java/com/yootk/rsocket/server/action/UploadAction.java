package com.yootk.rsocket.server.action;

import com.yootk.rsocket.constants.UploadConstants;
import com.yootk.rsocket.type.UploadStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Controller
@Slf4j
public class UploadAction {
    @Value("${output.file.path:upload}")  // 项目目录/upload
    private Path outputPath; // 文件保存路径
    @MessageMapping("message.upload")
    public Flux<UploadStatus> upload(  // 文件上传处理
            @Headers Map<String, Object> metadata,
            @Payload Flux<DataBuffer> content) throws Exception {
        log.info("【上传路径】outputPath = {}", this.outputPath); // 保存路径
        var fileName = metadata.get(UploadConstants.FILE_NAME); // 获取文件名称
        var fileExt = metadata.get(UploadConstants.FILE_EXT); // 获取文件后缀
        var path = Paths.get(fileName + "." + fileExt); // 获取文件操作路径
        log.info("【文件上传】FileName = {}、FileExt = {}、Path = {}", fileName, fileExt, this.outputPath);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(
                this.outputPath.resolve(path), // 解析出输出路径
                StandardOpenOption.CREATE, // 文件创建
                StandardOpenOption.WRITE // 文件写入
        ); // 异步文件通道
        return Flux.concat(DataBufferUtils.write(content, channel)
        .map(s -> UploadStatus.CHUNK_COMPLETED), Mono.just(UploadStatus.COMPLETED))
                .doOnComplete(() -> {
                    try {
                        channel.close(); // 通道关闭
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .onErrorReturn(UploadStatus.FAILED); // 我失败了
    }
}
