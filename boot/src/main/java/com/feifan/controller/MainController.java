package com.feifan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feifan.activemq.Produce;
import com.feifan.vo.User;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisCluster;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Donald
 * @create 2019-01-26 0:02
 */
@SuppressWarnings("MagicConstant")
@Controller
public class MainController {

    @Autowired
    private JedisCluster jedisCluster;


    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    @RequestMapping("load")
    public String load() {
        return "load";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody // 参数名字和表格里面文件的名字一样
    public String uploadFile(MultipartFile file) {
        System.err.println("文件上传了");
        try {
            // 创建文件在服务器的存放地址
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            File file2 = new File("e:/fileLoad/");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            // 生成服务端的名称
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + fileSuffix;
            File file3 = new File(file2 + "/" + fileName);

            // 上传文件
            file.transferTo(file3);


        } catch (Exception e) {
            System.err.println(e);
            return "上传文件失败";
        }
        return "OK";
    }

    @RequestMapping(value = "updateMany",method = RequestMethod.POST)
    @ResponseBody
    public String loadMany(MultipartFile[] file) {
        try {
            // 创建文件在服务器的存放地址
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            File file2 = new File("e:/fileLoad/");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            // 生成服务端的名称
            String filename = null;
            for (MultipartFile file1 :file) {
                String suttix = file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf("."));
                filename = UUID.randomUUID().toString()+suttix;
                File file3 = new File(file2+"/"+filename);
                file1.transferTo(file3);
                System.out.println("shuchuchenggng le ");
            }

        } catch (Exception e) {
            System.err.println(e);
            return "上传文件失败 异常";
        }
        return "OK";
    }

    @RequestMapping("get")
    @ResponseBody
    //@Cacheable(value = "name")//开启缓存，键值为map 自定义
    public Map<String, User> getSomeThing(){
        System.err.println("第一次进来了呀");
        Map<String,User> map = new HashMap<>();
        User user =  new User();
        user.setAge(18);
        user.setBrithday(new Date());
        user.setCreateTime(new Date());
        user.setGender("男");
        map.put("one",user);
        map.put("two",user);

        jedisCluster.set("你看","你是哪个");
        return map;
    }
    //springboot 已经整合了mongodb 直接注入即可
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("mongo")
    @ResponseBody
    public String mongoTest(){
        User user = new User();
        user.setId(9);
        user.setBrithday(new Date());
        user.setGender("man");
        user.setCreateTime(new Date());
        user.setAge(18);
        user.setNickname("司马达");
        Map<Integer,User> map = new HashMap<>();
        map.put(1,user  );
        map.put(2,user);

        mongoTemplate.save(user);
        mongoTemplate.save(map);
        return  "saver mongodb is OK";
    }

    @RequestMapping("mongofind")
    @ResponseBody
    public List<User> findList(){
        List<User> all = mongoTemplate.findAll(User.class);
        User byId = mongoTemplate.findById(9, User.class);
        List<Map> all1 = mongoTemplate.findAll(Map.class);
        LOGGER.info("查询mongodb 数据库得到{}:{}",byId,all1);
        return  all;
    }

    @Autowired
    private Produce produce;

    @ResponseBody
    @RequestMapping("activeMQ")
    public String activeMQ(){
        //点对点消息 1:1
        Destination des =new ActiveMQQueue("myqueue");//指定存放消息队列的名称
        for(int i=0;i<50;i++){
            produce.sendMessage(des,"你是哪个呦"+i);
        }
        return "OK produce message";
    }

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @RequestMapping("activeMQP")
    public String activeMQP(){
        //复杂对象一对一消息机制
        Destination destination =  new ActiveMQQueue("POJO");//指定消息对列的名称
        User user = null;
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            user = new User();
            user.setId(i);
            user.setUsername(String.valueOf(i));
            user.setBrithday(new Date());
            user.setAge(i);
            user.setEmail(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            try {
                map.put(String.valueOf(i),objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            produce.sendPOJO(destination,map);
        }
        return "ActiveMQ 发送 消息对列正确";
    }
}
