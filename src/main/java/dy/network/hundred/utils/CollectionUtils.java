package dy.network.hundred.utils;

import java.util.Collection;

public class CollectionUtils {

    /**
     * 是否是空
     *
     * @param coll
     * @return
     */
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.size() == 0;
    }

}
