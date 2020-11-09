package com.imooc.utils.minio;

import com.alibaba.fastjson.JSONObject;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yang Tao
 * @Date 2020/7/12 9:47
 * @Version 1.0
 */

@Slf4j
@Component
public class MinioUtils {
    @Autowired
    private MinioClient client;
    @Autowired
    private MinioProp minioProp;

    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储桶
     * @return
     */
    public JSONObject uploadFile(MultipartFile file, String bucketName,String filename) throws Exception {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        // 判断上传文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        // 判断存储桶是否存在
        createBucket(bucketName);
        // 文件名
        //String originalFilename = file.getOriginalFilename();
        // 新的文件名 = 存储桶名称_时间戳.后缀名
        //String fileName = bucketName + "_" + System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 开始上传
        client.putObject(bucketName, filename, file.getInputStream(), file.getContentType());
        res.put("code", 1);
        res.put("msg", minioProp.getEndpoint() + "/" + bucketName + "/" + filename);
        return res;
    }

    /**
     * 根据文件前缀查询文件
     *
     * @param bucketName bucket名称
     * @param prefix     前缀
     * @param recursive  是否递归查询
     * @return MinioItem 列表
     */
    @SneakyThrows
    public List<MinioItem> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        List<MinioItem> objectList = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = client.listObjects(bucketName, prefix, recursive);
        for (Result<Item> result : objectsIterator) {
            objectList.add(new MinioItem(result.get()));
        }
        return objectList;
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return client.getObject(bucketName, objectName);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName) {
        return client.presignedGetObject(bucketName, objectName);
    }
}
