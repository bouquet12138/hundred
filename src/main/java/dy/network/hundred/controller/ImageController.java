package dy.network.hundred.controller;

import dy.network.hundred.contract.FileContract;
import dy.network.hundred.java_bean.db_bean.ImageBean;
import dy.network.hundred.java_bean.Image_Bean;
import dy.network.hundred.service.ImageService;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.utils.FileUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;

import dy.network.hundred.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static dy.network.hundred.contract.FileContract.IMG_DIR;


@RestController
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;


    /**
     * 上传图片
     *
     * @param multipartFile
     * @param image_bean
     * @return
     * @throws IOException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping({"/upload_img"})
    public BaseBean<ImageBean> upload(@RequestParam("image_url") MultipartFile multipartFile, Image_Bean image_bean) throws IOException {

        FileContract.makeDir();//创建一下文件夹


        String originalFilename = multipartFile.getOriginalFilename();

        String suffixName = FileUtil.getSuffixName(originalFilename);

        String fileName = "HD" + UUID.randomUUID() + suffixName;


        File _file = File.createTempFile(String.valueOf(System.currentTimeMillis()), suffixName);
        log.info(_file.getName());
        FileUtil.copyInputStreamToFile(multipartFile.getInputStream(), _file);
        BufferedImage bufferedImage = ImageIO.read(_file);

        if (bufferedImage == null) {
            BaseBean<ImageBean> baseBean = new BaseBean();
            baseBean.setCode(0);
            baseBean.setMsg("上传失败");
            return baseBean;
        }

        Integer width = Integer.valueOf(bufferedImage.getWidth());
        Integer height = Integer.valueOf(bufferedImage.getHeight());


        ImageBean imageBean = new ImageBean();
        imageBean.setImage_describe(image_bean.getImage_describe());
        imageBean.setImage_name(originalFilename);
        imageBean.setImage_type(image_bean.getImage_type());
        imageBean.setWidth(width.intValue());
        imageBean.setHeight(height.intValue());
        imageBean.setImage_url(fileName);
        imageBean.setInsert_time(DateUtil.getDate());


        File dest = new File(IMG_DIR, fileName);

        multipartFile.transferTo(dest);
        return imageService.uploadImage(imageBean);
    }

}
