package com.imooc.utils.minio;

import io.minio.messages.Item;
import io.minio.messages.Owner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author Yang Tao
 * @Date 2020/7/11 17:38
 * @Version 1.0
 *
 * 文件的描述类
 *
 */
@Getter
@Setter
@ToString
public class MinioItem {
    /**对象名称**/

    private String objectName;
    /**最后操作时间**/

    private Date lastModified;
    private String etag;
    /**对象大小**/

    private String size;
    private String storageClass;
    private Owner owner;
    /**对象类型：directory（目录）或file（文件）**/
    private String type;

    public MinioItem(String objectName, Date lastModified, String etag, String size, String storageClass, Owner owner, String type) {
        this.objectName = objectName;
        this.lastModified = lastModified;
        this.etag = etag;
        this.size = size;
        this.storageClass = storageClass;
        this.owner = owner;
        this.type = type;
    }


    public MinioItem(Item item) {
        this.objectName = item.objectName();
        this.type = item.isDir() ? "directory" : "file";
        this.etag = item.etag();
        long sizeNum = item.objectSize();
        this.size = sizeNum > 0 ? convertFileSize(sizeNum):"0";
        this.storageClass = item.storageClass();
        this.owner = item.owner();
        try {
            this.lastModified = item.lastModified();
        }catch(NullPointerException e){}
    }
    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else{
            return String.format("%d B", size);
        }
    }


}
