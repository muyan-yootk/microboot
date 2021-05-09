package com.yootk.test;

import com.yootk.rsocket.client.StartRSocketClientApplication;
import com.yootk.rsocket.constants.UploadConstants;
import com.yootk.rsocket.type.UploadStatus;
import com.yootk.rsocket.vo.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StartRSocketClientApplication.class)
public class TestUploadRSocket { // 编写测试类
    @Autowired
    private Mono<RSocketRequester> requesterMono; // 来进行服务调用
    @Value("classpath:/images/muyan_yootk.jpg") // 资源定位表达式
    private Resource resource; // 配置资源
    @Test
    public void testUpload() { // 测试服务响应
        String fileName = "muyan-" + UUID.randomUUID(); // 随机生成上传文件名称
        String fileExt = this.resource.getFilename().substring(this.resource.getFilename().lastIndexOf(".") + 1);
        Flux<DataBuffer> resourceFlux = DataBufferUtils.read(
                this.resource, new DefaultDataBufferFactory(), 1024)
                .doOnNext(s -> System.out.println("文件上传：" + s));
        Flux<UploadStatus> uploadStatusFlux = this.requesterMono
                .map(r -> r.route("message.upload") // 配置路由地址
                .metadata(metadataSpec -> {
                    System.out.println("【上传测试】文件名称：" + fileName + "." + fileExt);
                    metadataSpec.metadata(fileName, MimeType.valueOf(UploadConstants.MIME_FILE_NAME)); // 设置文件名称
                    metadataSpec.metadata(fileExt, MimeType.valueOf(UploadConstants.MIME_FILE_EXTENSION)); // 设置文件后缀
                }).data(resourceFlux)) // 设置文件上传数据
                .flatMapMany(r -> r.retrieveFlux(UploadStatus.class))
                .doOnNext(o -> System.out.println("上传进度：" + o));
        uploadStatusFlux.blockLast(); // 进行阻塞等待
    }
}
