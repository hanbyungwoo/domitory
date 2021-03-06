package com.tory.domi.domitory.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.tory.domi.domitory.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;

public class ReceiveMenu extends AsyncTask<URL, Integer, Long> {
    Context context;
    TextView textView_temperature;
    TextView textView_humidity;
    TextView textView_rainfallProbability;
    TextView textView_weatherText;
    ImageView imageView_weatherIcon;

    public ReceiveMenu(Context context, TextView textView_temperature,
                       TextView textView_humidity, TextView textView_rainfallProbability,
                       TextView textView_weatherText, ImageView imageView_weatherIcon) {

        this.context = context;
        this.textView_temperature = textView_temperature;
        this.textView_humidity = textView_humidity;
        this.textView_rainfallProbability = textView_rainfallProbability;
        this.textView_weatherText = textView_weatherText;
        this.imageView_weatherIcon = imageView_weatherIcon;
    }

    protected Long doInBackground(URL... urls) {

        InfoManager.setData(context);
        String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=" + InfoManager.cityCode;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            parseXML(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(Long result) {

        textView_temperature.setText(todayWeatherInfo.getTemp() + "º");
        textView_humidity.setText(todayWeatherInfo.getReh() + "%");
        textView_rainfallProbability.setText(todayWeatherInfo.getPop() + "%");
        textView_weatherText.setText(todayWeatherInfo.getWfKor());
        imageView_weatherIcon.setBackgroundResource(setWeatherIcon(todayWeatherInfo.getWfKor()));

    }

    void parseXML(String xml) {
        try {
            String tagName = "";
            boolean onHour = false;
            boolean onTem = false;
            boolean onWfKor = false;
            boolean onReh = false;
            boolean onPop = false;
            boolean onEnd = false;
            boolean isItemTag1 = false;
            int i = 0;

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("data")) {
                        onEnd = false;
                        isItemTag1 = true;
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                    if (tagName.equals("hour") && !onHour) {
                        todayWeatherInfo.setHour(parser.getText());
                        onHour = true;
                    }
                    if (tagName.equals("temp") && !onTem) {
                        todayWeatherInfo.setTemp(parser.getText());
                        onTem = true;
                    }
                    if (tagName.equals("wfKor") && !onWfKor) {
                        todayWeatherInfo.setWfKor(parser.getText());
                        onWfKor = true;
                    }
                    if(tagName.equals("pop") && !onPop) {
                        todayWeatherInfo.setPop(parser.getText());
                        onPop = true;
                    }
                    if (tagName.equals("reh") && !onReh) {
                        todayWeatherInfo.setReh(parser.getText());
                        onReh = true;
                        break;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (tagName.equals("s06") && onEnd == false) {
                        i++;
                        onHour = false;
                        onTem = false;
                        onWfKor = false;
                        onReh = false;
                        onPop = false;
                        isItemTag1 = false;
                        onEnd = true;
                    }
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int setWeatherIcon(String weather) {

        if(weather.equals("맑음")) {
            return R.drawable.sun;
        } else if(weather.equals("구름 조금")) {
            return R.drawable.cloud;
        } else if(weather.equals("구름 많음")) {
            return R.drawable.clouds;
        } else if(weather.equals("흐림")) {
            return R.drawable.partly_cloudy;
        } else if(weather.equals("비")) {
            return R.drawable.rain;
        } else if(weather.equals("눈/비")) {
            return R.drawable.rain_and_snow;
        } else if(weather.equals("눈")) {
            return R.drawable.snow;
        } else {
            return R.drawable.sun;
        }
    }
}