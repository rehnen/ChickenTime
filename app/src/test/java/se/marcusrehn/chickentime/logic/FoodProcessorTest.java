package se.marcusrehn.chickentime.logic;

import static junit.framework.Assert.*;

import org.junit.Test;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rehnen on 2016-10-16.
 */

public final class FoodProcessorTest {

    ClassLoader classLoader = this.getClass().getClassLoader();


    @Test
    public void testForChickenWeekPositive() throws Exception {
        String fileContent = readFile("utkikenChickenWeek.html");
        String weeklyFood = FoodProcessor.getWeeklyFoodMessage(fileContent);

        assertEquals(weeklyFood, FoodProcessor.CHICKEN_WEEK);
    }
    @Test
    public void testForChickenWeekNegative() throws Exception {
        String fileContent = readFile("utkiken.html");
        String weeklyFood = FoodProcessor.getWeeklyFoodMessage(fileContent);

        assertNotSame(weeklyFood, FoodProcessor.CHICKEN_WEEK);
    }

    @Test
    public void testForChickenDayPossitive() throws Exception {
        String fileContent = readFile("utkiken.html");
        String foodOfTheDay = FoodProcessor.getFoodMessageForDay(fileContent, "Thursday");

        assertTrue(foodOfTheDay.contains(FoodProcessor.CHICKEN_DAY));
    }

    @Test
    public void testForChickenDayNegative() throws Exception {
        String fileContent = readFile("utkiken.html");
        String foodOfTheDay = FoodProcessor.getFoodMessageForDay(fileContent, "Monday");

        assertTrue(!foodOfTheDay.contains(FoodProcessor.CHICKEN_DAY));
    }

    @Test
    public void testForAioiDayPositive() throws Exception {
        String fileContent = readFile("utkiken.html");
        String foodOfTheDay = FoodProcessor.getFoodMessageForDay(fileContent, "Friday");

        assertEquals(foodOfTheDay, FoodProcessor.AIOLI);
    }

    @Test
    public void testForChickenDayWeekend() throws Exception {
        String fileContent = readFile("utkiken.html");
        String foodOfTheDay = FoodProcessor.getWeeklyFoodMessage(fileContent);

        assertNotSame(foodOfTheDay, FoodProcessor.CHICKEN_WEEK);
    }

    private String readFile(String fileName) throws Exception{
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        StringBuilder sb = new StringBuilder();

        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
