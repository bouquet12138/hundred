package dy.network.hundred.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.UseFeature;
import org.dom4j.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class AppInfoUtil {
    private static final Logger log = LoggerFactory.getLogger(AppInfoUtil.class);


    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String calcMD5(File file) {

        try {
            InputStream stream = Files.newInputStream(file.toPath(), new OpenOption[]{StandardOpenOption.READ});
            try {
                String str = calcMD5(stream);
                if (stream != null) stream.close();
                return str;
            } catch (Throwable throwable) {
                if (stream != null) try {
                    stream.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }


    public static String calcMD5(InputStream stream) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buf = new byte[8192];
            int len;
            while ((len = stream.read(buf)) > 0) {
                digest.update(buf, 0, len);
            }
            return toHexString(digest.digest());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toHexString(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[b >> 4 & 0xF]);
            r.append(hexCode[b & 0xF]);
        }
        return r.toString();
    }


    private static final Namespace NS = Namespace.get("http://schemas.android.com/apk/res/android");

    public static Long getVersionCode(File apkUrl) {

        try {
            ApkFile apkFile = new ApkFile(apkUrl);
            try {
                ApkMeta apkMeta = apkFile.getApkMeta();

                for (UseFeature feature : apkMeta.getUsesFeatures()) {
                    log.info(feature.getName());
                }
                Long long_ = apkMeta.getVersionCode();
                apkFile.close();
                return long_;
            } catch (Throwable throwable) {
                try {
                    apkFile.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException e) {
            e.printStackTrace();

            return new Long(1L);
        }

    }

    public static String getVersionName(File apkUrl) {

        try {
            ApkFile apkFile = new ApkFile(apkUrl);
            try {
                ApkMeta apkMeta = apkFile.getApkMeta();

                for (UseFeature feature : apkMeta.getUsesFeatures()) {
                    log.info(feature.getName());
                }

                String str = apkMeta.getVersionName();
                apkFile.close();
                return str;
            } catch (Throwable throwable) {
                try {
                    apkFile.close();
                } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                }
                throw throwable;
            }
        } catch (IOException e) {
            e.printStackTrace();

            return "";
        }

    }

    public static String getVersionName(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();

        String suffixName = FileUtil.getSuffixName(originalFilename);
        File _file = null;
        try {
            _file = File.createTempFile(String.valueOf(System.currentTimeMillis()), suffixName);
            FileUtil.copyInputStreamToFile(multipartFile.getInputStream(), _file);
            log.info(_file.getName());

            ApkFile apkFile = new ApkFile(_file);


            ApkMeta apkMeta = apkFile.getApkMeta();

            for (UseFeature feature : apkMeta.getUsesFeatures()) {
                log.info(feature.getName());
            }

            return apkMeta.getVersionName();
        } catch (IOException e) {
            e.printStackTrace();

            return "";
        }
    }
}
