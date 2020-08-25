package dy.network.hundred.service.impl;

import dy.network.hundred.dao.OptionDao;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.OptionBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.service.OptionService;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.utils.DateUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("optionService")
public class OptionServiceImpl
        implements OptionService {
    @Autowired
    private OptionDao optionDao;

    public BaseBean<Integer> userDeclareOneself(OptionBean optionBean) {
        BaseBean<Integer> baseBean = new BaseBean();
        optionBean.setPublic_time(DateUtil.getDate());
        Integer oneself = this.optionDao.userDeclareOneself(optionBean);
        if (oneself > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("意见发表成功");
        } else {

            baseBean.setMsg("意见发表失败");
        }
        return baseBean;
    }


    public BaseBean<List<OptionBean>> findUserDeclareOneself() {
        BaseBean<List<OptionBean>> baseBean = new BaseBean();
        List<OptionBean> optionBeanList = this.optionDao.findUserDeclareOneself();

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("意见获取成功");
        baseBean.setData(optionBeanList);

        return baseBean;
    }

    @Override
    public BaseBean<List<OptionBean>> getOptionList(PageBean pageBean) {

        BaseBean<List<OptionBean>> baseBean = new BaseBean();
        List<OptionBean> optionBeanList = optionDao.getOptionList(pageBean);

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("意见获取成功");
        baseBean.setData(optionBeanList);

        return baseBean;

    }
}

