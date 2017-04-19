package com.example.controller;

import com.example.model.ParamVo;
import com.example.model.Test1;
import com.example.service.ParamService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sjf on 17-2-26.
 */
@RequestMapping("/")
@RestController
public class TestController {


    @Autowired
    private ParamService paramService;

    @RequestMapping("test4")
    public String index(){

        ParamVo vo = new ParamVo();
        vo.setP1("p1p1p1p1");

        return paramService.findParam2(vo);
    }

    @RequestMapping("test")
    public @ResponseBody String test(){
        List<Test1> list = Lists.newArrayList(new Test1(1, "test1"), new Test1(2, "test2"), new Test1(3, "test3"));
        return list.stream()
                .filter(t -> t.getId() > 3).findFirst()
                .orElseThrow(RuntimeException::new)
                .getName();
    }

    @RequestMapping("test1")
    public @ResponseBody String test1(){
        HashMap<String, Integer> list = new HashMap<String, Integer>(){{
            put("B121", 124);
            put("A345", 235);
            put("A123", 123);
            put("B312", 312);
        }};
        return list.entrySet()
                .stream()
                .sorted(Comparator.comparing(t -> -1 * Integer.valueOf(t.getKey().substring(1)) + t.getKey().charAt(0)))
                .findFirst()
                .get()
                .toString();

    }

    @RequestMapping("test2")
    protected Object castValue(){

        Class clazz;
        Object obj = null;

        String type = "com.example.model.Test1";

        try {
            clazz = Class.forName(type);
            obj = clazz.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @RequestMapping("test3")
    protected Object castValue3(){
        List<Map> list = new ArrayList<>();
        list.add(new HashMap<String, Object>(){{
            put("a1k", "a1-v");
            put("a2k", "a2-v");
            put("a3k", "a3-v");
            put("a4k", "list2");
        }});

        List<String> list4 = new ArrayList<String>(){{
            add("a1");
            add("b1");
            add("a2");
            add("b2");
        }};

        List<Object> list3 = new ArrayList<Object>() {{
            add("c1");
            add("c2");
        }};
        List<Object> list2 = new ArrayList<Object>() {{
            add("b1");
            add("b2");
            add("l3");
        }};
        List<Object> list1 = new ArrayList<Object>() {{
            add("a1");
            add("a2");
            add("l2");
        }};

        List<String> a1 = new ArrayList<String>(){{
            add("a1-1");
            add("a1-2");
        }};
        List<String> a2 = new ArrayList<String>(){{
            add("a2-1");
            add("a2-2");
        }};
        List<String> c1 = new ArrayList<String>(){{
            add("c1-1");
            add("c1-2");
        }};
        List<String> c2 = new ArrayList<String>(){{
            add("c2-1");
            add("c2-2");
        }};
        List<String> b1 = new ArrayList<String>(){{
            add("b1-1");
            add("b1-2");
        }};
        List<String> b2 = new ArrayList<String>(){{
            add("b2-1");
            add("b2-2");
        }};
        Map map = new HashMap<String, Object>() {{
            put("a1", a1);
            put("a2", a2);
            put("b1", b1);
            put("b2", b2);
        }};

        List<Object> r1 = (List)(list4.stream()
                .flatMap(l -> ((List)map.get(l)).stream())
                .collect(Collectors.toList()));

        Object r3 = list4.stream()
                .flatMap(l -> ((List)map.get(l)).stream())
                .collect(Collectors.toList());

        Object r2 = list4.stream()
                .map(l -> (List)map.get(l))
                .collect(Collectors.toList());


        return map.values().stream();

        /*StringBuilder sb = new StringBuilder();
        sb.append(getGivenYearStartTime(new Date()))
                .append("<br/>")
                .append(getGivenYearEndTime(new Date()))
                .append("<br/>")
                .append(getGivenHalfYearStartTime(new Date()))
                .append("<br/>")
                .append(getGivenHalfYearEndTime(new Date()))
                .append("<br/>")
                .append(getGivenQuarterStartTime(new Date()))
                .append("<br/>")
                .append(getGivenQuarterEndTime(new Date()))
                .append("<br/>")
                .append(getGivenMonthStartTime(new Date()))
                .append("<br/>")
                .append(getGivenMonthEndTime(new Date()));
        return sb.toString();*/
        //return getGivenYearEndTime(new Date()).toLocaleString();

    }

    public long dateToMillis(){
        return System.currentTimeMillis();
    }

    public <D extends java.util.Date> long dateToMillis(D date){
        if (date == null)
            return dateToMillis();
        return date.getTime();
    }


    public Date getGivenMonthStartTime(Date date){

        Instant instant = Instant.ofEpochMilli(dateToMillis(date));

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        instant = localDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

    public Date getGivenMonthEndTime(Date date){

        Instant instant = Instant.ofEpochMilli(dateToMillis(date));

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        instant = localDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1L).atStartOfDay().atZone(ZoneId.systemDefault()).minusNanos(1L).toInstant();

        return Date.from(instant);
    }


    public Date getGivenQuarterStartTime(Date date){

        Instant instant = Instant.ofEpochMilli(dateToMillis(date));

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        int month = localDate.getMonthValue();

        month = month < 4 ? 1 : month < 7 ? 4 : month < 10 ? 7 : 10;

        instant = localDate.withMonth(month).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

    public Date getGivenQuarterEndTime(Date date){

        Instant instant = Instant.ofEpochMilli(dateToMillis(date));

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        int month = localDate.getMonthValue();

        month = month < 4 ? 3 : month < 7 ? 6 : month < 10 ? 9 : 12;

        instant = localDate.withMonth(month).with(TemporalAdjusters.lastDayOfMonth()).plusDays(1L).atStartOfDay().atZone(ZoneId.systemDefault()).minusNanos(1L).toInstant();

        return Date.from(instant);
    }

    public Date getGivenHalfYearStartTime(Date date){

        Instant instant = Instant.ofEpochMilli(dateToMillis(date));

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        int month  = localDate.getMonthValue() > 6 ? 6 : 1;

        instant = localDate.withMonth(month).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }



    public Date getGivenHalfYearEndTime(Date date){

        Instant instant = Instant.ofEpochMilli(dateToMillis(date));

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        int month  = localDate.getMonthValue() > 6 ? 12 : 6;

        instant = localDate.withMonth(month).with(TemporalAdjusters.lastDayOfMonth()).plusDays(1L).atStartOfDay().atZone(ZoneId.systemDefault()).minusNanos(1L).toInstant();

        return Date.from(instant);
    }


    public Date getGivenYearStartTime(Date date){

        Instant instant = Instant.ofEpochMilli(date.getTime());

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        instant = localDate.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

    public Date getGivenYearEndTime(Date date){

        Instant instant = Instant.ofEpochMilli(date.getTime());

        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

        instant = localDate.with(TemporalAdjusters.lastDayOfYear()).plusDays(1L).atStartOfDay().atZone(ZoneId.systemDefault()).minusNanos(1L).toInstant();

        return Date.from(instant);
    }
}
