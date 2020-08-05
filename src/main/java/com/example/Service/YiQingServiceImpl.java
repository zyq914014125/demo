package com.example.Service;

import com.example.mapper.YiQingMapper;
import com.example.pojo.YiQing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.X
 **/
@Service
public class YiQingServiceImpl {
    @Autowired
    YiQingMapper yiQingMapper;
    //各省总人数
    public int selectConfirmCount() {
        return yiQingMapper.getCount();
    }

    public List<Map<String, String>> yiqingfenxi() {
          int ConfirmCount=yiQingMapper.getCount();
          //获得死亡
          int DeadCount=yiQingMapper.getDeadCount();
          //获得治愈
          int curedCount = yiQingMapper.getCuredCount();
          List<Map<String,String>> list=new ArrayList<>();
          Map<String,String> map=new HashMap<>();
          map.put("value", String.valueOf(ConfirmCount));
          map.put("name","总感染人数");
          list.add(map);
          Map<String,String> map1=new HashMap<>();
          map.put("value", String.valueOf(DeadCount));
          map.put("name","总死亡人数");
          list.add(map1);
          Map<String,String> map2=new HashMap<>();
          map.put("value", String.valueOf(curedCount));
          map.put("name","总治愈人数");
          list.add(map2);
          return list;
    }
    public List<Map<String, String>> yiqingfenxiByName(String name) {
        System.out.println(name);
        YiQing yiQing=yiQingMapper.getCountOne(name);
        int CountOne=yiQing.getConfirmcount();
        int CurredOne=yiQing.getCuredcount();
        int DeadOne=yiQing.getDeadcount();
        List<Map<String,String>> list=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("value", String.valueOf(CountOne));
        map.put("name",name+"的感染人数");
        list.add(map);
        Map<String,String> map1=new HashMap<>();
        map1.put("value", String.valueOf(CurredOne));
        map1.put("name",name+"的治愈人数");
        list.add(map1);
        Map<String,String> map2=new HashMap<>();
        map2.put("value", String.valueOf(DeadOne));
        map2.put("name",name+"的死亡人数");
        list.add(map2);
        return list;
    }
    //各省感染人
    public List<Map<String, String>> selectInfoAllName() {
        List<YiQing> list=yiQingMapper.findList();
        List<Map<String,String>> mapList=new ArrayList<>();
        for(YiQing y:list){
            Map<String,String> map=new HashMap<>();
            System.out.println(y);
            map.put("name",y.getProvincename());
            map.put("value", String.valueOf(y.getConfirmcount()));
            mapList.add(map);
        }
        return mapList;
    }
    //获取单省各城市感染
    public Map<String, Object> selectConfirmCountByName(String name) {
        List<YiQing> list=yiQingMapper.findCity(name);
        Map<String,Object> map=new HashMap<>();
        //城市名
        List<String> city=new ArrayList<>();
        //对应感染人数
        List<Integer> count=new ArrayList<>();
        for(YiQing list1:list){
            city.add(list1.getAreaname());
            count.add(list1.getConfirmcount());
        }
        map.put("x",city);
        map.put("y",count);
        return map;
    }
    public List<Map<String, String>> selectAllConfrimCountByName(String name) {
            List<Map<String,String>> list=new ArrayList<>();
            List<YiQing> yiQingList=yiQingMapper.findCity(name);
            for(YiQing u:yiQingList){
                Map<String,String> map=new HashMap<>();
                map.put("name",u.getAreaname());
                map.put("value", String.valueOf(u.getConfirmcount()));
                list.add(map);
        }
            return list;
    }
}
