package com.qmms.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by liutie on 17-11-8.
 */
public class UploadUtil {
    public static Map<String,String> uploadImg(MultipartFile file,String basePath,String privatePath){
        Map<String,String> data = new HashMap<>();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = StringUtils.substringAfter(fileName, ".");
                    if(!extensionName.equalsIgnoreCase("jpg") && !extensionName.equalsIgnoreCase("jpeg") && !extensionName.equalsIgnoreCase("gif") && !extensionName.equalsIgnoreCase("png")){
                        data.put("msg","格式不正确,支持的图片格式为：JPEG、GIF、PNG！");
                        return data;
                    }
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String dataPath = privatePath.concat(newFileName);
                    // 文件路径
                    String filePath = basePath.concat(privatePath);
                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    file.transferTo(dest);
                    data.put("success","1");
                    data.put("imgPath",dataPath);
                }catch (Exception e){
                    data.put("msg","上传失败:"+e.getMessage());
                }
            }
        }
        return data;
    }

    public static Map<String,String> uploadFile(MultipartFile file, String basePath, String privatePath, Set<String> extNameSet){
        Map<String,String> data = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取扩展名
                String extensionName = StringUtils.substringAfter(fileName, ".");
                if(extNameSet != null && extNameSet.size() > 0 && !extNameSet.contains(extensionName)){
                    data.put("msg","格式不正确,支持的文件格式为："+ extNameSet.toString());
                    return data;
                }
                // 新文件名 = 获取时间戳+"."扩展名
                String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                // 数据库保存的目录
                String dataPath = privatePath.concat(newFileName);
                // 文件路径
                String filePath = basePath.concat(privatePath);
                File dest = new File(filePath, newFileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                // 上传到指定目录
                file.transferTo(dest);
                data.put("success","1");
                data.put("filePath",dataPath);
            }catch (Exception e){
                data.put("msg","上传失败:"+e.getMessage());
            }
        }

        return data;
    }
}
