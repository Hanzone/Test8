import bean.JavaBean;
import bean.JavaBeanVo;
import bean.Variable;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.*;
import com.ximalaya.ad.common.util.CommonDateUtils;
import com.ximalaya.ad.common.util.CommonJsonUtils;
import com.ximalaya.ad.common.util.HasId;
import com.ximalaya.ad.common.util.LogMessageBuilder;
import com.ximalaya.ad.web.api.RtbPlanInfo;
import com.ximalaya.ad.web.api.RtbSourceTypeInfo;
import com.ximalaya.ad.web.api.schedule.ResourceOrderInfo;
import com.ximalaya.mainstay.common.Option;
import enums.Test_Enum;
import funInterfaces.BufferedReaderProcessor;
import interfaces.Test_IF;
import interfaces.impl.Test_Impl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import util.DTOUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ximalaya.ad.common.util.CommonDateUtils.dayStartTime;
import static com.ximalaya.ad.common.util.CommonDateUtils.parseDateTime;
import static java.lang.System.out;

/**
 * Created by Hanzone on 2018/4/12.
 */
public class Test<T> {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    int we = 122;
    static String s = "123";

    private static final long MILLS_SECOND_OF_DAY = 24 * 3600 * 1000L;
    private static final int SHARD_COUNT = 2;
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final ExecutorService sendExecutor = Executors.newFixedThreadPool(50);
    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, ClassNotFoundException {


        List<Variable> variables = Arrays.asList(
                new Variable("#AA1#", "啊啊啊"),
                new Variable("#AA#", "啊啊啊"),
                new Variable("#AA#", "啊啊啊1"),
                new Variable("#AA#", "啊啊啊1"),
                new Variable("#BB#", "欢欢欢")
        );

        String s = LocalDateTime.now().plusMinutes(1).toString();
        String s1 = CommonDateUtils.formatDateTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime = CommonDateUtils.parseDateTime(s, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime1 = CommonDateUtils.parseDateTime(s1, "yyyy-MM-dd HH:mm:ss");


        String s2 = CommonDateUtils.formatDateTime(LocalDateTime.now().plusMinutes(1), "yyyy-MM-dd HH:mm:ss");
        String s3 = CommonDateUtils.formatDateTime(LocalDateTime.now().plusMinutes(1).plusDays(3), "yyyy-MM-dd HH:mm:ss");

        print("");
    }


    public static String generateTableName(LocalDateTime date) {
        return generateTableName(CommonDateUtils.toEpochMillis(date));
    }

    /**
     * Generate table name by epoc milliseconds.
     */
    private static  String generateTableName(long epocMillis) {
        return "custom_stat_new" + epocMillis / MILLS_SECOND_OF_DAY / SHARD_COUNT % 200;
    }

    public enum SortDirection {
        ASC("asc"),
        DESC("desc");
        private String direction;

        private SortDirection(String direction) {
            this.direction = direction;
        }

        public static SortDirection fromCode(String value) {
            for (SortDirection sortDirection : values()) {
                if (sortDirection.getDirection().equals(value)) {
                    return sortDirection;
                }
            }
            return null;
        }

        public String getDirection() {
            return this.direction;
        }
    }


    T readLines(BufferedReaderProcessor<T> brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/Hanzone/test.sql"))) {
            return brp.process(br);
        }
    }

    interface PersonFactory<P extends Test_IF> {
        P create();
    }

    @FunctionalInterface
    interface Converter<F, F2, T> {
        T convert(F from1, F2 from2);
    }

    static void print(Object o) {
        out.println(o);
    }

}
