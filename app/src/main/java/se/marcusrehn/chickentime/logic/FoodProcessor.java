package se.marcusrehn.chickentime.logic;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by rehnen on 2016-10-16.
 */

public final class FoodProcessor {

    public static final String CHICKEN = "chicken";
    public static final String NO_CHICKEN = "Disappoint";
    public static final String CHICKEN_DAY = "The gods are smiling on this";
    public static final String CHICKEN_WEEK = "Chicken all week!";
    public static final String AIOLI = "Aioli!";



    public static String getFoodMessageForDay(String document, String day) {
        String message = NO_CHICKEN;

        String foodOfTheDay = "";
        if(!day.equals("Saturday") && !day.equals("Sunday")) {
            foodOfTheDay = document.substring(document.indexOf("<p><b>"+ day +"</b>"));
            if(foodOfTheDay.length() > 0) {
                foodOfTheDay = foodOfTheDay.substring(0, foodOfTheDay.indexOf("</p>"));
            }
        }
        if(foodOfTheDay.toLowerCase().contains("chicken")) {
            message = CHICKEN_DAY + " " + day;

        } else if(foodOfTheDay.toLowerCase().contains("aioli")) {
            message = AIOLI;
        }

        return message;
    }

    public static String getWeeklyFoodMessage(String document) {
        String message = NO_CHICKEN;

        int specialPosition = document.indexOf("this week's special");
        if(specialPosition > -1) {
            boolean chicken = document.substring(document.indexOf("this week's special"), document.indexOf("<p><b>Monday</b><br />"))
                    .toLowerCase()
                    .contains(CHICKEN);

            if(chicken) {
                message = CHICKEN_WEEK;
            }
        }

        return message;
    }
}
