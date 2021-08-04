package com.gez.woodware.util;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.gez.woodware.entity.basics.User;

import org.springframework.web.multipart.MultipartFile;


public class ResourceUtil {


    public static String path   = (String) ResourcesApplication.getCommonYml("resources.path");
    public static String resuri = (String) ResourcesApplication.getCommonYml("resources.resuri");


    /**
     * 获取resurl，文件资源地址
     */
    public static String getResourceUrl(String resourceName) {
        if (resourceName != null && resourceName.length() > 10) {
            String fileName = resourceName.substring(0, 36) + ".image";

            if (resourceName.contains("/202")) {
                fileName = resourceName;
            }

            System.out.println(fileName);
            //            return fileName;
            return resuri + "resources/files?file=" + fileName;

        } else {
            return "";
        }

    }


    /**
     * 获取resurl，文件资源地址
     */
    public static String getResourceRealPath(String resourceName) {
        if (resourceName != null && resourceName.length() > 5) {
            return path + "/" + resourceName;
        } else {
            return "";
        }

    }


    public static Object upload(MultipartFile tcFile) throws IllegalStateException, IOException {
        Map<String, Object> data = new HashMap();


        if (tcFile != null && tcFile.getOriginalFilename().trim() != null && tcFile.getOriginalFilename().trim().length() > 2) {


            //如配置文件中的路径不存在，则新建该文件夹
            File tcFileDir = new File(path);
            if (!tcFileDir.isDirectory()) {
                tcFileDir.mkdirs();
            }

            String suffix = tcFile.getOriginalFilename().substring(tcFile.getOriginalFilename().lastIndexOf(".") + 1);

//            String tsFileNameServer = UUID.randomUUID().toString() + "." + suffix;
            String tsFileNameServer = UUID.randomUUID().toString() + ".image";
//            tcFile.getOriginalFilename().trim();
            String todayFilePath = getTodayFile(tsFileNameServer);
            File targetFile = new File(path + todayFilePath);

            tcFile.transferTo(targetFile);

            data.put("fileName", todayFilePath);
            data.put("url", resuri + "resources/files?file=" + todayFilePath);
            data.put("name", tcFile.getOriginalFilename());
        }

        return data;
    }

    private static String getTodayFile(String fileName) {
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String temp_str = sdf.format(dt);

        String todayFileName = path + File.separator + temp_str;


        File todayfile = new File(todayFileName);
        // 如果文件夹不存在则创建
        if (!todayfile.exists() && !todayfile.isDirectory()) {

            todayfile.mkdir();
        }

        return "/" + temp_str + "/" + fileName;
    }

    /**
     * 获取多图多文件
     */
    public static List<String> getResourceImageList(String fileParams) {

        List<String> doutArrList = new ArrayList<String>();
        if (fileParams != null && fileParams.length() > 5) {
            for (String dt : fileParams.split(",")) {
                doutArrList.add(getResourceUrl(dt));
            }
        }

        return doutArrList;

    }

    /**
     * 获取路径中的文件名称.
     */
    public static String getPathFile(String path) {
        if (path != null && path.length() > 0) {

            int index = path.lastIndexOf('/');
            return path.substring(index + 1);
        }

        return path;
    }


    public static User getUser(HttpServletRequest request) {

        User u = (User) request.getSession().getAttribute("user");

        return u;
    }


//    /**
//     * 读取富文本.
//     *
//     * @return
//     */
//    public static String readRichText(String fileName) {
//        BufferedReader reader = null;
//        if (fileName !=null && fileName.length() > 10) {
//            try {
//
//
//                String tsFileFullName = fileName + ".document";
//                String tsFileContent = path + File.separator + tsFileFullName;
//
//                System.out.println("tsFileContent " + tsFileContent);
//                InputStreamReader isr = new InputStreamReader(new FileInputStream(tsFileContent), "utf-8");
//                reader = new BufferedReader(isr);
//                String text = "";
//                String tempString = null;
//                while ((tempString = reader.readLine()) != null) {
//                    text += tempString;
//                }
//                reader.close();
//                return text;
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e1) {
//                    }
//                }
//            }
//        }
//        return "";
//    }

    /**
     * 读取富文本.
     *
     * @return
     */
    public static String readRichText(String fileName) {


        String text = "";
        if (fileName != null && fileName.length() > 10) {
            BufferedReader reader = null;

            try {

                String tsFileFullName = getPathFile(fileName) + ".document";
                String tsFileContent = path + File.separator + tsFileFullName;


                File file = new File(tsFileContent);


                String code = getFileCharset(file);
                FileInputStream in = new FileInputStream(file);
                byte[] b = new byte[3];
                in.read(b);

//                if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
//                    code = "UTF-8";
//                }
////                code  = "UTF-8";


//                System.out.println(code);

                InputStreamReader isr = new InputStreamReader(new FileInputStream(tsFileContent), code);
                reader = new BufferedReader(isr);

                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    text += tempString;
                }


                reader.close();


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }

        }
        String str = text.replace("/resources/files", resuri + "resources/files");

        return str;
    }


    private static String getFileCharset(File sourceFile) {

        String charset = "GBK";

        byte[] first3Bytes = new byte[3];

        try {

            boolean checked = false;

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));

            bis.mark(0);

            int read = bis.read(first3Bytes, 0, 3);

            if (read == -1) {

                return charset; // 文件编码为 ANSI

            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {

                charset = "UTF-16LE"; // 文件编码为 Unicode

                checked = true;

            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {

                charset = "UTF-16BE"; // 文件编码为 Unicode big endian

                checked = true;

            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB

                    && first3Bytes[2] == (byte) 0xBF) {

                charset = "UTF-8"; // 文件编码为 UTF-8

                checked = true;

            }

            bis.reset();

            if (!checked) {

                int loc = 0;

                while ((read = bis.read()) != -1) {

                    loc++;

                    if (read >= 0xF0)

                        break;

                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK

                        break;

                    if (0xC0 <= read && read <= 0xDF) {

                        read = bis.read();

                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)

// (0x80

// - 0xBF),也可能在GB编码内

                            continue;

                        else

                            break;

                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小

                        read = bis.read();

                        if (0x80 <= read && read <= 0xBF) {

                            read = bis.read();

                            if (0x80 <= read && read <= 0xBF) {

                                charset = "UTF-8";

                                break;

                            } else

                                break;

                        } else

                            break;

                    }

                }

            }

            bis.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return charset;

    }

    public static String saveRichText(String content) {

        String fileName = getTodayFile(UUID.randomUUID().toString());
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            String filePath = path + fileName + ".document";


            fos = new FileOutputStream(filePath);
            osw = new OutputStreamWriter(fos, "UTF-8");

            if (null == content) {
                content = "";
            }
            osw.write(content);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;
    }

    public static String ConvertImgStrFormat(String strImg) {
        if (strImg == null) {
            return null;
        }
        strImg = strImg.replace(" ", "");
        strImg = strImg.replace("[", "");
        strImg = strImg.replace("]", "");
        strImg = strImg.replace("\"", "");
        strImg = strImg.replace(",", "/");
        return strImg;
    }

    public static void main(final String[] args) {
        String str = "[\"525d65fa-d081-401f-843d-a98c8b89fe52.image\",\"94f3c1b7-3f13-4c82-88e5-7e8a972f4822.image\"]";
        str = ConvertImgStrFormat(str);
        System.out.println(str);
    }


}
