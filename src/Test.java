import bean.JavaBean;
import bean.JavaBeanVo;
import bean.Variable;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.*;
import com.ximalaya.ad.common.model.material.ClickAction;
import com.ximalaya.ad.common.model.plan.ChargeMethod;
import com.ximalaya.ad.common.model.plan.MultiTimeRange;
import com.ximalaya.ad.common.model.plan.Plan;
import com.ximalaya.ad.common.model.plan.TimeRange;
import com.ximalaya.ad.common.model.position.PositionType;
import com.ximalaya.ad.common.model.unit.SoundPatchMode;
import com.ximalaya.ad.common.model.zone.ZoneInfo;
import com.ximalaya.ad.common.util.CommonDateUtils;
import com.ximalaya.ad.common.util.CommonJsonUtils;
import com.ximalaya.ad.common.util.HasId;
import com.ximalaya.ad.common.util.LogMessageBuilder;
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
import org.apache.commons.codec.digest.DigestUtils;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public static final ImmutableMap<Integer, String> SCHEDULE_SOUND_PATCH_STYLES =
            ImmutableMap.<Integer, String>builder()
                    .putAll(Arrays.stream(SoundPatchMode.values())
                            .collect(Collectors.toMap(SoundPatchMode::getCode, SoundPatchMode::getDesc)))
                    .build();

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

        Map<String, List<Variable>> collect = variables.stream().collect(Collectors.groupingBy(Variable::getVariableCode, Collectors.mapping(Function.identity(), Collectors.toList())));


        String ssss = variables.stream().findAny().map(Variable::getOpt).orElse("ssss");


        List<Integer> PRODUCT_SALESMAN = ImmutableList.of(526);

        String jsonString = CommonJsonUtils.toJsonString(PRODUCT_SALESMAN);
        print(jsonString);

        String jsonString1 = CommonJsonUtils.toJsonString(Lists.newArrayList());
        print(jsonString1);

        String jsonString11 = CommonJsonUtils.toJsonString(Collections.EMPTY_LIST);
        print(jsonString11);


    }



    private static <T extends HasId<ID>, ID> List<T> getAllSafelyList(List<T> list1, List<T> list2) {
        List<T> allLists = Lists.newArrayList();
        if (list1 != null) {
            allLists.addAll(list1);
        }
        if (list2 != null) {
            allLists.addAll(list2);
        }
        return allLists;
    }

    public static Object deepCopy(Serializable o) throws ClassNotFoundException, IOException {
//		//先序列化，写入到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(o);
        //然后反序列化，从流里读取出来，即完成复制
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();

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

//    private static void printCityDiff() {
//        List<String> source = Lists.newArrayList("石家庄市","唐山市","秦皇岛市","邯郸市","邢台市","保定市","张家口市","承德市","沧州市","廊坊市","衡水市","太原市","大同市","阳泉市","长治市","晋城市","朔州市","晋中市","运城市","忻州市","临汾市","吕梁市","沈阳市","大连市","鞍山市","抚顺市","本溪市","丹东市","锦州市","营口市","阜新市","辽阳市","盘锦市","铁岭市","朝阳市","葫芦岛市","长春市","吉林市","四平市","辽源市","通化市","白山市","松原市","白城市","延边朝鲜族自治州","哈尔滨市","齐齐哈尔市","鹤岗市","双鸭山市","鸡西市","大庆市","伊春市","牡丹江市","佳木斯市","七台河市","黑河市","绥化市","大兴安岭地区","南京市","无锡市","徐州市","常州市","苏州市","南通市","连云港市","淮安市","盐城市","扬州市","镇江市","泰州市","宿迁市","杭州市","宁波市","温州市","嘉兴市","湖州市","绍兴市","金华市","衢州市","舟山市","台州市","丽水市","合肥市","芜湖市","蚌埠市","淮南市","马鞍山市","淮北市","铜陵市","安庆市","黄山市","滁州市","阜阳市","宿州市","六安市","亳州市","池州市","宣城市","福州市","厦门市","莆田市","三明市","泉州市","漳州市","南平市","龙岩市","宁德市","南昌市","景德镇市","萍乡市","九江市","新余市","鹰潭市","赣州市","吉安市","宜春市","抚州市","上饶市","济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","莱芜市","临沂市","德州市","聊城市","滨州市","菏泽市","郑州市","开封市","洛阳市","平顶山市","安阳市","鹤壁市","新乡市","焦作市","濮阳市","许昌市","漯河市","三门峡市","南阳市","商丘市","信阳市","周口市","驻马店市","济源市","武汉市","黄石市","十堰市","荆州市","宜昌市","襄樊市","鄂州市","荆门市","孝感市","黄冈市","咸宁市","随州市","仙桃市","天门市","潜江市","神农架林区","恩施土家族苗族自治州","长沙市","株洲市","湘潭市","衡阳市","邵阳市","岳阳市","常德市","张家界市","益阳市","郴州市","永州市","怀化市","娄底市","湘西土家族苗族自治州","广州市","深圳市","珠海市","汕头市","韶关市","佛山市","江门市","湛江市","茂名市","肇庆市","惠州市","梅州市","汕尾市","河源市","阳江市","清远市","东莞市","中山市","潮州市","揭阳市","云浮市","兰州市","金昌市","白银市","天水市","嘉峪关市","武威市","张掖市","平凉市","酒泉市","庆阳市","定西市","陇南市","临夏回族自治州","甘南藏族自治州","成都市","自贡市","攀枝花市","泸州市","德阳市","绵阳市","广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市","广安市","达州市","雅安市","巴中市","资阳市","阿坝藏族羌族自治州","甘孜藏族自治州","凉山彝族自治州","贵阳市","六盘水市","遵义市","安顺市","铜仁地区","毕节地区","黔西南布依族苗族自治州","黔东南苗族侗族自治州","黔南布依族苗族自治州","海口市","三亚市","三沙市","五指山市","琼海市","儋州市","文昌市","万宁市","东方市","澄迈县","定安县","临高县","白沙黎族自治县","昌江黎族自治县","乐东黎族自治县","陵水黎族自治县","保亭黎族苗族自治县","琼中黎族苗族自治县","昆明市","曲靖市","玉溪市","保山市","昭通市","丽江市","普洱市","临沧市","文山壮族苗族自治州","红河哈尼族彝族自治州","西双版纳傣族自治州","楚雄彝族自治州","大理白族自治州","德宏傣族景颇族自治州","怒江傈僳族自治州","迪庆藏族自治州","西宁市","海东地区","海北藏族自治州","黄南藏族自治州","海南藏族自治州","果洛藏族自治州","玉树藏族自治州","海西蒙古族藏族自治州","西安市","铜川市","宝鸡市","咸阳市","渭南市","延安市","汉中市","榆林市","安康市","商洛市","南宁市","柳州市","桂林市","梧州市","北海市","防城港市","钦州市","贵港市","玉林市","百色市","贺州市","河池市","来宾市","崇左市","拉萨市","那曲地区","昌都地区","山南地区","日喀则地区","阿里地区","林芝地区","银川市","石嘴山市","吴忠市","固原市","中卫市","乌鲁木齐市","克拉玛依市","石河子市","吐鲁番地区","阿克苏地区","喀什地区","哈密地区","和田地区","克孜勒苏柯尔克孜自治州","巴音郭楞蒙古自治州","昌吉回族自治州","博尔塔拉蒙古自治州","伊犁哈萨克自治州","塔城地区","阿勒泰地区","呼和浩特市","包头市","乌海市","赤峰市","通辽市","鄂尔多斯市","呼伦贝尔市","巴彦淖尔市","乌兰察布市","锡林郭勒盟","兴安盟","阿拉善盟","澳门","香港","台湾","北京","天津","上海","重庆","阿拉尔","图木舒克","五家渠","北屯","铁门关","双河","可克达拉");
//        List<String> target = Lists.newArrayList("延安市","揭阳市","海西蒙古族藏族自治州","玉树藏族自治州","南宁市","图木舒克市","潍坊市","七台河市","鹤壁市","博尔塔拉蒙古自治州","聊城市","长春市","伊春市","大连市","拉萨市","酒泉市","阳江市","北京市","长治市","临高县","定安县","朝阳市","马鞍山市","日照市","铁岭市","琼海市","佛山市","南平市","平顶山市","唐山市","可克达拉市","吐鲁番市","安庆市","巴彦淖尔市","攀枝花市","滁州市","遵义市","百色市","防城港市","自贡市","苏州市","安顺市","徐州市","新乡市","赤峰市","云浮市","韶关市","吉林市","咸阳市","宜春市","定西市","五指山市","克拉玛依市","湖州市","铜川市","临沂市","怀化市","邢台市","银川市","黔西南布依族苗族自治州","克孜勒苏柯尔克孜自治州","四平市","宿迁市","杭州市","澄迈县","葫芦岛市","台州市","喀什地区","阜新市","南阳市","玉林市","哈密地区","漯河市","亳州市","太原市","荆门市","内江市","惠州市","衡水市","佳木斯市","塔城地区","屯昌县","牡丹江市","琼中黎族苗族自治县","齐齐哈尔市","临汾市","丽江市","和田地区","嘉峪关市","大庆市","宿州市","平凉市","昌吉回族自治州","河源市","遂宁市","洛阳市","九江市","乐山市","扬州市","运城市","昌江黎族自治县","益阳市","石河子市","天水市","抚顺市","黄冈市","陵水黎族自治县","淮北市","镇江市","阿勒泰地区","泰州市","金昌市","锦州市","中卫市","昆明市","石家庄市","双鸭山市","河池市","荆州市","湘潭市","赣州市","枣庄市","濮阳市","襄阳市","保亭黎族苗族自治县","武威市","肇庆市","双河市","无锡市","温州市","崇左市","柳州市","许昌市","三门峡市","潜江市","合肥市","池州市","郴州市","商丘市","果洛藏族自治州","张掖市","抚州市","重庆市","晋中市","萍乡市","龙岩市","万宁市","商洛市","吕梁市","固原市","淮南市","白沙黎族自治县","鞍山市","仙桃市","德宏傣族景颇族自治州","莱芜市","铜陵市","成都市","莆田市","包头市","南昌市","白山市","巴中市","保定市","阿拉尔市","株洲市","甘南藏族自治州","本溪市","香港市","承德市","清远市","神农架林区","松原市","海东市","北屯市","芜湖市","嘉兴市","济宁市","黄石市","德州市","东方市","常德市","威海市","达州市","呼伦贝尔市","昭通市","珠海市","阿拉善盟市","东莞市","六盘水市","乌兰察布市","廊坊市","景德镇市","岳阳市","甘孜藏族自治州","红河哈尼族彝族自治州","辽阳市","雅安市","海南藏族自治州","山南地区","张家界市","鄂尔多斯市","秦皇岛市","鸡西市","东营市","丹东市","深圳市","济南市","白银市","眉山市","沧州市","朔州市","文昌市","阿坝藏族羌族自治州","哈尔滨市","泸州市","盐城市","辽源市","漳州市","普洱市","铁门关市","玉溪市","孝感市","阳泉市","绥化市","郑州市","阿克苏地区","开封市","汕头市","湛江市","蚌埠市","黔东南苗族侗族自治州","乌海市","南京市","天门市","邯郸市","乐东黎族自治县","N/A","吉安市","延边朝鲜族自治州","娄底市","潮州市","兴安盟市","鄂州市","五家渠市","忻州市","衡阳市","保山市","厦门市","渭南市","白城市","西双版纳傣族自治州","迪庆藏族自治州","金华市","宜宾市","来宾市","天津市","上饶市","鹤岗市","黄山市","中山市","吴忠市","周口市","大兴安岭地区","江门市","黔南布依族苗族自治州","泰安市","邵阳市","北海市","阿里地区","广州市","楚雄彝族自治州","呼和浩特市","淮安市","烟台市","焦作市","十堰市","茂名市","淄博市","长沙市","桂林市","绍兴市","三明市","上海市","南通市","舟山市","西宁市","锡林郭勒盟市","黑河市","庆阳市","德阳市","文山壮族苗族自治州","绵阳市","陇南市","大同市","菏泽市","临沧市","台湾市","通辽市","宁波市","石嘴山市","安康市","宜昌市","市","大理白族自治州","黄南藏族自治州","咸宁市","梅州市","西安市","连云港市","昌都市","榆林市","钦州市","六安市","铜仁市","滨州市","永州市","贵港市","青岛市","宁德市","巴音郭楞蒙古自治州","海口市","临夏回族自治州","福州市","三亚市","林芝市","海北藏族自治州","营口市","安阳市","广安市","济源市","驻马店市","儋州市","曲靖市","阜阳市","宝鸡市","恩施土家族苗族自治州","湘西土家族苗族自治州","盘锦市","贺州市","伊犁哈萨克自治州","怒江傈僳族自治州","凉山彝族自治州","张家口市","新余市","泉州市","衢州市","通化市","晋城市","兰州市","广元市","日喀则市","宣城市","汕尾市","鹰潭市","乌鲁木齐市","梧州市","贵阳市","资阳市","随州市","毕节市","汉中市","澳门市","常州市","那曲地区","丽水市","南充市","武汉市","信阳市","沈阳市","三沙市");
//        source.removeIf(target::contains);
//        print(source);
//    }

//    private static void printProvinceDiff() {
//        List<String> source = Lists.newArrayList("北京","天津","上海","重庆","河北","山西","台湾","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","甘肃","四川","贵州","海南","云南","青海","陕西","广西","西藏","宁夏","新疆","内蒙古","澳门","香港");
//        List<String> target = Lists.newArrayList("广东","云南","内蒙古","湖北","新疆","海南","西藏","陕西","香港","天津","广西","河南","贵州","江苏","宁夏","青海","福建","黑龙江","辽宁","重庆","安徽","山东","澳门","湖南","台湾","上海","山西","甘肃","河北","北京","浙江","江西","四川","吉林");
//        source.removeIf(target::contains);
//        print(source);
//    }


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
