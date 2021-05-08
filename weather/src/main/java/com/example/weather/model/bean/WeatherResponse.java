package com.example.weather.model.bean;

import com.example.network.beans.VJHBaseResponse;

import java.util.List;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/05/08
 * desc   :
 * version: 1.0
 */


public class WeatherResponse  extends VJHBaseResponse {


    /**
     * reason : 查询成功
     * result : {"city":"佳木斯","realtime":{"temperature":"8","humidity":"83","info":"阴","wid":"02","direct":"北风","power":"3级","aqi":"18"},"future":[{"date":"2021-05-08","temperature":"4/10℃","weather":"小雨转中雨","wid":{"day":"07","night":"08"},"direct":"西南风转西北风"},{"date":"2021-05-09","temperature":"4/8℃","weather":"小雨","wid":{"day":"07","night":"07"},"direct":"北风转西北风"},{"date":"2021-05-10","temperature":"3/11℃","weather":"小雨转多云","wid":{"day":"07","night":"01"},"direct":"西风"},{"date":"2021-05-11","temperature":"8/16℃","weather":"多云转小雨","wid":{"day":"01","night":"07"},"direct":"南风转东南风"},{"date":"2021-05-12","temperature":"8/19℃","weather":"阵雨转多云","wid":{"day":"03","night":"01"},"direct":"东南风转东风"}]}
     * error_code : 0
     */

    public ResultBean result;

    public static class ResultBean {
        /**
         * city : 佳木斯
         * realtime : {"temperature":"8","humidity":"83","info":"阴","wid":"02","direct":"北风","power":"3级","aqi":"18"}
         * future : [{"date":"2021-05-08","temperature":"4/10℃","weather":"小雨转中雨","wid":{"day":"07","night":"08"},"direct":"西南风转西北风"},{"date":"2021-05-09","temperature":"4/8℃","weather":"小雨","wid":{"day":"07","night":"07"},"direct":"北风转西北风"},{"date":"2021-05-10","temperature":"3/11℃","weather":"小雨转多云","wid":{"day":"07","night":"01"},"direct":"西风"},{"date":"2021-05-11","temperature":"8/16℃","weather":"多云转小雨","wid":{"day":"01","night":"07"},"direct":"南风转东南风"},{"date":"2021-05-12","temperature":"8/19℃","weather":"阵雨转多云","wid":{"day":"03","night":"01"},"direct":"东南风转东风"}]
         */

        public String city;
        public WeatherBean realtime;
        public List<FutureBean> future;

        public static class WeatherBean {
            /**
             * temperature : 8
             * humidity : 83
             * info : 阴
             * wid : 02
             * direct : 北风
             * power : 3级
             * aqi : 18
             */

            public String temperature;
            public String humidity;
            public String info;
            public String wid;
            public String direct;
            public String power;
            public String aqi;
        }

        public static class FutureBean {
            /**
             * date : 2021-05-08
             * temperature : 4/10℃
             * weather : 小雨转中雨
             * wid : {"day":"07","night":"08"}
             * direct : 西南风转西北风
             */

            public String date;
            public String temperature;
            public String weather;
            public WidBean wid;
            public String direct;

            public static class WidBean {
                /**
                 * day : 07
                 * night : 08
                 */

                public String day;
                public String night;
            }
        }
    }
}
