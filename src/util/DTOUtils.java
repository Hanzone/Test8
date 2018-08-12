
package util;

import com.ximalaya.ad.common.util.LogMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DTOUtils {
    private static final Logger log = LoggerFactory.getLogger(DTOUtils.class);

    public static <S, T> T map(S source, Class<T> targetClass) {
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error(
                    new LogMessageBuilder("copy bean properties failed")
                            .addParameter("source", source)
                            .addParameter("targetClass", targetClass.getName())
                            .toString(), e);
        }
        return null;
    }

    public static <S, T> void mapTo(S source, T dist) {
        try {
            BeanUtils.copyProperties(source, dist);
        } catch (Exception e) {
            log.error(
                    new LogMessageBuilder("copy bean properties failed")
                            .addParameter("source", source)
                            .addParameter("target", dist)
                            .toString(), e);
        }
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < source.size(); i++) {
            T t = map(source.get(i), targetClass);
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }
}
