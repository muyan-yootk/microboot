package com.yootk.rsocket.type;

public enum UploadStatus { // 上传状态
    CHUNK_COMPLETED, // 文件上传处理之中
    COMPLETED, // 文件上传完毕
    FAILED; // 文件上传失败
}
