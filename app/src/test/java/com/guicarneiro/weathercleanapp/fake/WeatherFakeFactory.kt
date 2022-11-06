package com.guicarneiro.weathercleanapp.fake

import com.github.javafaker.Faker
import com.guicarneiro.weathercleanapp.domain.entities.Parent
import com.guicarneiro.weathercleanapp.domain.entities.Weather

class WeatherFakeFactory {
    companion object {
        fun createFakeWeather() : Weather {
            return Weather(consolidatedWeather = listOf(), time = "", sunRise = "", sunSet = "",
                timezone = "", timezoneName = "", parent = createFakeParent(), sources = listOf(),
                lattLong = "", woeid = 0, locationType = "", title = "",)
        }

        fun provideFakeWeatherJson(): String {
            return "{\n" +
                    "  \"consolidated_weather\":[\n" +
                    "    {\n" +
                    "      \"id\":4805883302248448,\n" +
                    "      \"weather_state_name\":\"Light Rain\",\n" +
                    "      \"weather_state_abbr\":\"lr\",\n" +
                    "      \"wind_direction_compass\":\"WNW\",\n" +
                    "      \"created\":\"2021-09-13T22:16:21.961506Z\",\n" +
                    "      \"applicable_date\":\"2021-09-13\",\n" +
                    "      \"min_temp\":13.895,\n" +
                    "      \"max_temp\":22.240000000000002,\n" +
                    "      \"the_temp\":22.33,\n" +
                    "      \"wind_speed\":5.6738177100135205,\n" +
                    "      \"wind_direction\":287.33359156579274,\n" +
                    "      \"air_pressure\":1018.5,\n" +
                    "      \"humidity\":56,\n" +
                    "      \"visibility\":14.006328044221744,\n" +
                    "      \"predictability\":75\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":5028922455490560,\n" +
                    "      \"weather_state_name\":\"Light Rain\",\n" +
                    "      \"weather_state_abbr\":\"lr\",\n" +
                    "      \"wind_direction_compass\":\"S\",\n" +
                    "      \"created\":\"2021-09-13T22:16:24.964788Z\",\n" +
                    "      \"applicable_date\":\"2021-09-14\",\n" +
                    "      \"min_temp\":16.95,\n" +
                    "      \"max_temp\":25.235,\n" +
                    "      \"the_temp\":23.775,\n" +
                    "      \"wind_speed\":8.71114908481402,\n" +
                    "      \"wind_direction\":182.4690622725618,\n" +
                    "      \"air_pressure\":1012.5,\n" +
                    "      \"humidity\":74,\n" +
                    "      \"visibility\":12.12854430128052,\n" +
                    "      \"predictability\":75\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":6038117740969984,\n" +
                    "      \"weather_state_name\":\"Light Rain\",\n" +
                    "      \"weather_state_abbr\":\"lr\",\n" +
                    "      \"wind_direction_compass\":\"NW\",\n" +
                    "      \"created\":\"2021-09-13T22:16:28.753685Z\",\n" +
                    "      \"applicable_date\":\"2021-09-15\",\n" +
                    "      \"min_temp\":17.0,\n" +
                    "      \"max_temp\":21.935000000000002,\n" +
                    "      \"the_temp\":21.54,\n" +
                    "      \"wind_speed\":6.2956524474747475,\n" +
                    "      \"wind_direction\":316.263931494886,\n" +
                    "      \"air_pressure\":1015.0,\n" +
                    "      \"humidity\":65,\n" +
                    "      \"visibility\":13.869626381929532,\n" +
                    "      \"predictability\":75\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":6193240316313600,\n" +
                    "      \"weather_state_name\":\"Light Cloud\",\n" +
                    "      \"weather_state_abbr\":\"lc\",\n" +
                    "      \"wind_direction_compass\":\"E\",\n" +
                    "      \"created\":\"2021-09-13T22:16:31.362832Z\",\n" +
                    "      \"applicable_date\":\"2021-09-16\",\n" +
                    "      \"min_temp\":15.685,\n" +
                    "      \"max_temp\":21.355,\n" +
                    "      \"the_temp\":21.95,\n" +
                    "      \"wind_speed\":6.689968536938944,\n" +
                    "      \"wind_direction\":94.18194205759067,\n" +
                    "      \"air_pressure\":1021.0,\n" +
                    "      \"humidity\":65,\n" +
                    "      \"visibility\":14.140233536148891,\n" +
                    "      \"predictability\":70\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":6732261765414912,\n" +
                    "      \"weather_state_name\":\"Light Cloud\",\n" +
                    "      \"weather_state_abbr\":\"lc\",\n" +
                    "      \"wind_direction_compass\":\"S\",\n" +
                    "      \"created\":\"2021-09-13T22:16:33.881626Z\",\n" +
                    "      \"applicable_date\":\"2021-09-17\",\n" +
                    "      \"min_temp\":17.884999999999998,\n" +
                    "      \"max_temp\":24.585,\n" +
                    "      \"the_temp\":26.475,\n" +
                    "      \"wind_speed\":5.497245860197778,\n" +
                    "      \"wind_direction\":189.76390938564325,\n" +
                    "      \"air_pressure\":1019.0,\n" +
                    "      \"humidity\":71,\n" +
                    "      \"visibility\":13.283362662053607,\n" +
                    "      \"predictability\":70\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":5865000829714432,\n" +
                    "      \"weather_state_name\":\"Showers\",\n" +
                    "      \"weather_state_abbr\":\"s\",\n" +
                    "      \"wind_direction_compass\":\"SW\",\n" +
                    "      \"created\":\"2021-09-13T22:16:36.949738Z\",\n" +
                    "      \"applicable_date\":\"2021-09-18\",\n" +
                    "      \"min_temp\":19.07,\n" +
                    "      \"max_temp\":26.770000000000003,\n" +
                    "      \"the_temp\":27.72,\n" +
                    "      \"wind_speed\":3.628177841406188,\n" +
                    "      \"wind_direction\":224.0,\n" +
                    "      \"air_pressure\":1017.0,\n" +
                    "      \"humidity\":68,\n" +
                    "      \"visibility\":9.999726596675416,\n" +
                    "      \"predictability\":73\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"time\":\"2021-09-13T20:09:14.050321-04:00\",\n" +
                    "  \"sun_rise\":\"2021-09-13T06:54:56.500274-04:00\",\n" +
                    "  \"sun_set\":\"2021-09-13T19:30:52.466512-04:00\",\n" +
                    "  \"timezone_name\":\"LMT\",\n" +
                    "  \"parent\":{\n" +
                    "    \"title\":\"Canada\",\n" +
                    "    \"location_type\":\"Country\",\n" +
                    "    \"woeid\":23424775,\n" +
                    "    \"latt_long\":\"56.954681,-98.308968\"\n" +
                    "  },\n" +
                    "  \"sources\":[\n" +
                    "    {\n" +
                    "      \"title\":\"BBC\",\n" +
                    "      \"slug\":\"bbc\",\n" +
                    "      \"url\":\"http://www.bbc.co.uk/weather/\",\n" +
                    "      \"crawl_rate\":360\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"title\":\"Forecast.io\",\n" +
                    "      \"slug\":\"forecast-io\",\n" +
                    "      \"url\":\"http://forecast.io/\",\n" +
                    "      \"crawl_rate\":480\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"title\":\"HAMweather\",\n" +
                    "      \"slug\":\"hamweather\",\n" +
                    "      \"url\":\"http://www.hamweather.com/\",\n" +
                    "      \"crawl_rate\":360\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"title\":\"Met Office\",\n" +
                    "      \"slug\":\"met-office\",\n" +
                    "      \"url\":\"http://www.metoffice.gov.uk/\",\n" +
                    "      \"crawl_rate\":180\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"title\":\"OpenWeatherMap\",\n" +
                    "      \"slug\":\"openweathermap\",\n" +
                    "      \"url\":\"http://openweathermap.org/\",\n" +
                    "      \"crawl_rate\":360\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"title\":\"Weather Underground\",\n" +
                    "      \"slug\":\"wunderground\",\n" +
                    "      \"url\":\"https://www.wunderground.com/?apiref=fc30dc3cd224e19b\",\n" +
                    "      \"crawl_rate\":720\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"title\":\"World Weather Online\",\n" +
                    "      \"slug\":\"world-weather-online\",\n" +
                    "      \"url\":\"http://www.worldweatheronline.com/\",\n" +
                    "      \"crawl_rate\":360\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"title\":\"Toronto\",\n" +
                    "  \"location_type\":\"City\",\n" +
                    "  \"woeid\":4118,\n" +
                    "  \"latt_long\":\"43.648560,-79.385368\",\n" +
                    "  \"timezone\":\"America/Toronto\"\n" +
                    "}"
        }

        fun createFakeParent() : Parent {
            return Parent(title = getWord(), locationType = getWord(), woeid = getNumber().toInt(), lattLong = getWord())
        }

        fun getWord(): String {
            return Faker.instance().lorem().word()
        }

        fun getNumber(): Number {
            return Faker.instance().number().randomDigit();
        }
    }
}