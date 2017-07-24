package baway.com.huyudemo.home.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 贾秀坤 on 2017/7/10.
 */

public class ConfigResponse {

    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"31","wind_direction":"南风","wind_strength":"2级","humidity":"57%","time":"20:14"},"today":{"temperature":"23℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"南风微风","week":"星期五","city":"北京","date_y":"2017年07月07日","dressing_index":"较舒适","dressing_advice":"建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。","uv_index":"中等","comfort_index":"","wash_index":"较适宜","travel_index":"适宜","exercise_index":"适宜","drying_index":""},"future":[{"temperature":"23℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"南风微风","week":"星期五","date":"20170707"},{"temperature":"25℃~34℃","weather":"多云转雷阵雨","weather_id":{"fa":"01","fb":"04"},"wind":"微风","week":"星期六","date":"20170708"},{"temperature":"25℃~34℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"北风微风","week":"星期日","date":"20170709"},{"temperature":"24℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期一","date":"20170710"},{"temperature":"26℃~35℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期二","date":"20170711"},{"temperature":"24℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期三","date":"20170712"},{"temperature":"25℃~34℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"北风微风","week":"星期四","date":"20170713"}]}
     * error_code : 0
     */

    public String resultcode;
    public String reason;
    public ResultBean result;
    public int error_code;

    public static ConfigResponse objectFromData(String str) {

        return new Gson().fromJson(str, ConfigResponse.class);
    }

    public static class ResultBean {
        /**
         * sk : {"temp":"31","wind_direction":"南风","wind_strength":"2级","humidity":"57%","time":"20:14"}
         * today : {"temperature":"23℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"南风微风","week":"星期五","city":"北京","date_y":"2017年07月07日","dressing_index":"较舒适","dressing_advice":"建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。","uv_index":"中等","comfort_index":"","wash_index":"较适宜","travel_index":"适宜","exercise_index":"适宜","drying_index":""}
         * future : [{"temperature":"23℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"南风微风","week":"星期五","date":"20170707"},{"temperature":"25℃~34℃","weather":"多云转雷阵雨","weather_id":{"fa":"01","fb":"04"},"wind":"微风","week":"星期六","date":"20170708"},{"temperature":"25℃~34℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"北风微风","week":"星期日","date":"20170709"},{"temperature":"24℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期一","date":"20170710"},{"temperature":"26℃~35℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期二","date":"20170711"},{"temperature":"24℃~34℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"北风微风","week":"星期三","date":"20170712"},{"temperature":"25℃~34℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"北风微风","week":"星期四","date":"20170713"}]
         */

        public SkBean sk;
        public TodayBean today;
        public List<FutureBean> future;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public static class SkBean {
            /**
             * temp : 31
             * wind_direction : 南风
             * wind_strength : 2级
             * humidity : 57%
             * time : 20:14
             */

            public String temp;
            public String wind_direction;
            public String wind_strength;
            public String humidity;
            public String time;

            public static SkBean objectFromData(String str) {

                return new Gson().fromJson(str, SkBean.class);
            }
        }

        public static class TodayBean {
            /**
             * temperature : 23℃~34℃
             * weather : 晴
             * weather_id : {"fa":"00","fb":"00"}
             * wind : 南风微风
             * week : 星期五
             * city : 北京
             * date_y : 2017年07月07日
             * dressing_index : 较舒适
             * dressing_advice : 建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。
             * uv_index : 中等
             * comfort_index :
             * wash_index : 较适宜
             * travel_index : 适宜
             * exercise_index : 适宜
             * drying_index :
             */

            public String temperature;
            public String weather;
            public WeatherIdBean weather_id;
            public String wind;
            public String week;
            public String city;
            public String date_y;
            public String dressing_index;
            public String dressing_advice;
            public String uv_index;
            public String comfort_index;
            public String wash_index;
            public String travel_index;
            public String exercise_index;
            public String drying_index;

            public static TodayBean objectFromData(String str) {

                return new Gson().fromJson(str, TodayBean.class);
            }

            public static class WeatherIdBean {
                /**
                 * fa : 00
                 * fb : 00
                 */

                public String fa;
                public String fb;

                public static WeatherIdBean objectFromData(String str) {

                    return new Gson().fromJson(str, WeatherIdBean.class);
                }
            }
        }

        public static class FutureBean {
            /**
             * temperature : 23℃~34℃
             * weather : 晴
             * weather_id : {"fa":"00","fb":"00"}
             * wind : 南风微风
             * week : 星期五
             * date : 20170707
             */

            public String temperature;
            public String weather;
            public WeatherIdBeanX weather_id;
            public String wind;
            public String week;
            public String date;

            public static FutureBean objectFromData(String str) {

                return new Gson().fromJson(str, FutureBean.class);
            }

            public static class WeatherIdBeanX {
                /**
                 * fa : 00
                 * fb : 00
                 */

                public String fa;
                public String fb;

                public static WeatherIdBeanX objectFromData(String str) {

                    return new Gson().fromJson(str, WeatherIdBeanX.class);
                }
            }
        }
    }
}
