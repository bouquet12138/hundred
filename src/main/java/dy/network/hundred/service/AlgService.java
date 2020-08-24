package dy.network.hundred.service;

import dy.network.hundred.java_bean.ALGBean;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.PageBean;

import java.util.List;

public interface AlgService {

    BaseBean<List<ALGBean>> getAlgList(PageBean pageBean);

    BaseBean modifyAlgInfo(ALGBean algBean);
}
