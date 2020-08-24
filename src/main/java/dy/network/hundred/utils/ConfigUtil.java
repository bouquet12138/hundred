package dy.network.hundred.utils;


import dy.network.hundred.dao.ConfigDao;
import dy.network.hundred.java_bean.db_bean.ConfigBean;


public class ConfigUtil {


    /**
     * 得到配置的整数值
     *
     * @param configName
     * @param configDao
     * @return
     */
    public static Integer getConfigValueInteger(String configName, ConfigDao configDao) {
        ConfigBean configBean = getConfigBean(configName, configDao);

        if (configBean == null) {
            return null;
        }
        return Integer.parseInt(configBean.getConfig_value());
    }


    /**
     * 得到配置的浮点值
     *
     * @param configName
     * @param configDao
     * @return
     */
    public static Float getConfigValueFloat(String configName, ConfigDao configDao) {
        ConfigBean configBean = getConfigBean(configName, configDao);

        if (configBean == null) {
            return null;
        }
        return Float.valueOf(Float.parseFloat(configBean.getConfig_value()));
    }

    /**
     * 得到配置的字符串
     *
     * @param configName
     * @param configDao
     * @return
     */
    public static String getConfigValueString(String configName, ConfigDao configDao) {
        ConfigBean configBean = getConfigBean(configName, configDao);

        if (configBean == null) {
            return null;
        }
        return configBean.getConfig_value();
    }


    public static ConfigBean getConfigBean(String configName, ConfigDao configDao) {
        return configDao.getConfigBeanWithName(configName);
    }
}
