package check.streak;

import check.streak.data.Goal;
import check.streak.data.GoalMap;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.*;
import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

import static com.jayway.jsonpath.Criteria.where;


public class testFilter {
    private static final ObjectMapper JSON = new ObjectMapper();

/*    public static void main(String [] args) throws IOException {
        GoalMap goalMap = null;
        File goalFile = new File("/Users/Nada/Desktop/Check-Streak/src/main/resources/static/pages/events.json");

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String s = dateFormat.format(date);

        System.out.println(s);
        //List<String> events = JsonPath.read(goalFile, "$.Monthly[?(@.startdate<=s)]");

        Filter cheapFictionFilter = filter(
                where("startdate").lte(s)
        );

        goalMap = parse(goalFile).read("$.monthly[?]", cheapFictionFilter);

        ArrayList<Goal> nada = new ArrayList<Goal>(goalMap.values());

        for (Goal ss : nada) {
            System.out.println(ss.toString());
        }

    }*/
/*        System.out.println(goals.size());

        for(Map<String, Object> ss : goals){
            System.out.println(ss.toString());
        }*//*
        //System.out.println(dateFormat.format(date));
    }*/
}
