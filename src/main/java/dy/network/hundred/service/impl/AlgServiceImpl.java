package dy.network.hundred.service.impl;

import dy.network.hundred.dao.ALGDao;
import dy.network.hundred.java_bean.ALGBean;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.service.AlgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("algService")
public class AlgServiceImpl implements AlgService {

    @Autowired
    private ALGDao algdao;

    @Override
    public BaseBean<List<ALGBean>> getAlgList(PageBean pageBean) {

        BaseBean<List<ALGBean>> baseBean = new BaseBean<>();
        List<ALGBean> algBeanList = algdao.getAlgList(pageBean);

        baseBean.setMsg("加载成功");
        baseBean.setCode(BaseBean.SUCCESS);//成功
        baseBean.setData(algBeanList);

        return baseBean;
    }

    @Override
    public BaseBean modifyAlgInfo(ALGBean algBean) {

        BaseBean baseBean = new BaseBean<>();

        algdao.modifyAlgInfo(algBean);

        baseBean.setCode(BaseBean.SUCCESS);//成功
        baseBean.setMsg("修改成功");

        return baseBean;
    }
}
