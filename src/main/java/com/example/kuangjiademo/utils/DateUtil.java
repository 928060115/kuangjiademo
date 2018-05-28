package com.example.kuangjiademo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author liuyang
 */
public class DateUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String HOUR_FORMAT = "HH:mm:ss";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";

    private DateUtil() {
    }

    /**
     * 时间格式化成字符串(pattern为空时,返回yyyy-MM-dd型字符串)
     *
     * @param date
     *            Date
     * @param pattern
     *            StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN，
     *            如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) throws ParseException {
        if (pattern == null || pattern == "") {
            pattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString
     *            String
     * @param pattern
     *            StrUtils.DATE_TIME_PATTERN ||
     *            StrUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
        if (pattern == null || pattern == "") {
            pattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeString);
    }

    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化） // 即
     * "yyyy-MM-dd HH:mm:ss" --->>> "yyyy-MM-dd"
     *
     * @param dateTime
     *            Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateString(Date dateTime) throws ParseException {
        String dateTimeString = DateUtil.dateFormat(dateTime, DateUtil.DATE_TIME_PATTERN);
        return dateTimeString.substring(0, 10);
    }

    /**
     * 当时、分、秒为00:00:00时，将日期时间格式成只有日期(yyyy-MM-dd)的字符串， //整理"yyyy-MM-dd HH:mm:ss"
     * 和"yyyy-MM-dd"两种格式的混和数据，以yyyy-MM-dd格式字符串输出 当时、分、秒不为00:00:00时，直接返回
     *
     * @param dateTime
     *            Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) throws ParseException {
        String dateTimeString = DateUtil.dateFormat(dateTime, DateUtil.DATE_TIME_PATTERN);
        if (dateTimeString.endsWith("00:00:00")) {
            return dateTimeString.substring(0, 10);
        } else {
            return dateTimeString;
        }
    }

    /**
     * 用途：获得 Date的时、分、秒 2017年8月5日 下午12:21:34 张进 return String
     */
    public static String getHHmmssTime(Date date) {
        SimpleDateFormat sdf_hour_format = new SimpleDateFormat(DateUtil.HOUR_FORMAT);
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        String temp = "";
        try {
            temp += sdf_hour_format.format(cale.getTime());
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 将日期时间格式成日期对象，和dateParse互用 //时、分、秒、归零
     *
     * @param dateTime
     *            Date
     * @return Date
     * @throws ParseException
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 时间加减小时
     *
     * @param startDate
     *            要处理的时间，Null则为当前时间
     * @param hours
     *            加减的小时
     * @return Date
     */
    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    /**
     * 时间加减分钟
     *
     * @param startDate
     *            要处理的时间，Null则为当前时间
     * @param minutes
     *            加减的分钟
     * @return
     */
    public static Date dateAddMinutes(Date startDate, int minutes) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    /**
     * 时间加减秒数
     *
     * @param startDate
     *            要处理的时间，Null则为当前时间
     * @return
     */
    public static Date dateAddSeconds(Date startDate, int seconds) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return c.getTime();
    }

    /**
     * 日期相加减天数
     *
     * @param date
     *            如果为Null，则为当前时间
     * @param days
     *            加减天数
     * @param ---->>
     *            includeTime 是否包括时分秒,true表示包含
     * @return
     */
    public static Date dateAddDays(Date date, int days) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 时间加减月数
     *
     * @param startDate
     *            要处理的时间，Null则为当前时间
     * @param months
     *            加减的月数
     * @return Date
     */
    public static Date dateAddMonths(Date startDate, int months) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
        return c.getTime();
    }

    /**
     * 时间加减年数
     *
     * @param startDate
     *            要处理的时间，Null则为当前时间
     * @param years
     *            加减的年数
     * @return Date
     */
    public static Date dateAddYears(Date startDate, int years) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
        return c.getTime();
    }

    /**
     * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）
     *
     * @param myDate
     *            时间
     * @param compareDate
     *            要比较的时间
     * @return int
     */
    public static int dateCompare(Date myDate, Date compareDate) {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }

    /**
     * 用途：比较两个日期相差的天数
     */
    public static int getDayMargin(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 比较两个日期相差的月数
     *
     * @return int
     */
    public static int getMonthMargin(String date1, String date2) {
        int margin = 0;
        try {
            margin = (Integer.parseInt(date2.substring(0, 4))
                    - Integer.parseInt(date1.substring(0, 4))) * 12;
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0", "-"))
                    - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));
            return margin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return margin;
    }

    /**
     * 获取两个时间中最小的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMin(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return compareDate;
        } else if (-1 == dateCompare(date, compareDate)) {
            return date;
        }
        return date;
    }

    /**
     * 获取两个时间中最大的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMax(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return date;
        } else if (-1 == dateCompare(date, compareDate)) {
            return compareDate;
        }
        return date;
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetween(Date startDate, Date endDate) throws ParseException {
        Date dateStart = dateParse(dateFormat(startDate, DATE_PATTERN), DATE_PATTERN);
        Date dateEnd = dateParse(dateFormat(endDate, DATE_PATTERN), DATE_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，包含今天
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException {
        return dateBetween(startDate, endDate) + 1;
    }

    /**
     * 获取日期时间的年份，如2017-02-13，返回2017
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期时间的月份，如2017年2月13日，返回2
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13
     *
     * @param date
     * @return
     */
    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 根据时间获取当月最大的日期
     * <li>2017-02-13，返回2017-02-28</li>
     * <li>2016-02-13，返回2016-02-29</li>
     * <li>2016-01-11，返回2016-01-31</li>
     *
     * @param date
     *            Date
     * @return
     * @throws Exception
     */
    public static Date maxDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     *
     * @param date
     *            Date
     * @return
     * @throws Exception
     */
    public static Date minDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 是否符合日期格式yyyy-MM-dd
     *
     * @return boolean
     */
    public static boolean isFormatDay(String day) {
        return day.matches(
                "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)");
    }

    /**
     * 是否符合时间格式HH:mm:ss
     *
     * @param time
     *            字符串
     * @return boolean
     */
    public static boolean isFormatTime(String time) {
        return time.matches(
                "(0[1-9]|1[0-9]|2[0-4]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])(\\.000000)?");
    }

    /**
     * 是否符合时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return boolean
     */
    public static boolean isFormat(String time) {
        String[] temp = time.split(" ");
        return isFormatDay(temp[0]) && isFormatTime(temp[1]);
    }

    /**
     * 获得当前日期是星期几
     *
     * @return int
     */
    public static int getCurNumOFWeek(String dat, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(dat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 得到输入日期所在的周一
     *
     * @return String
     */
    public static String getMondayOfThisWeek(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 1);
        return format.format(c.getTime());
    }

    /**
     * 得到输入日期所在的周日
     *
     * @return String
     */
    public static String getSundayOfThisWeek(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 7);
        return format.format(c.getTime());
    }

    /**
     * 获得当前日期的年份
     *
     * @return int
     */
    public static int getCurrentYearNum(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获得当前的月份那一年的第几月
     *
     * @return
     */
    public static int getCurrentMonthNum(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前的日期那一年的第几周
     *
     * @return
     */
    public static int getweekNumOfTheYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得当前日期是那一年的第几天
     *
     * @return
     */
    public static int getNumOfTheYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得当前日期是本月的第几天
     *
     * @return 当前日
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    /**
     * 获得当前日期是本月的第几周
     *
     * @return int
     */
    public static int getCurWeekNumOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getFirstOfYear(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        int currentYear = currCal.get(Calendar.YEAR);
        return getFirstOfYear(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getFirstOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getLastOfYear(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        int currentYear = currCal.get(Calendar.YEAR);
        return getLastOfYear(currentYear);
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getLastOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取当月第一天
     *
     * @return String
     */
    public static String getFirstOfMonth(Date date, String pattern) {
        Calendar cal = Calendar.getInstance();// 获取当前日期
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        date = cal.getTime();
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 获取当月最后一天
     *
     * @return String
     */
    public static String getLastOfMonth(Date date, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        date = cal.getTime();
        ;
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 通过给定的年、月、周获得该周内的每一天日期
     *
     * @param year
     *            int 年
     * @param month
     *            int 月
     * @param week
     *            int 周
     * @return List<Date> 七天的日期
     */
    public static List<Date> getDayListByWeek(int year, int month, int week) {
        List<Date> list = new ArrayList<Date>();
        // 先滚动到该年.
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        // 滚动到月:
        c.set(Calendar.MONTH, month - 1);
        // 滚动到周:
        c.set(Calendar.WEEK_OF_MONTH, week);
        // 得到该周第一天:
        for (int i = 0; i < 6; i++) {
            c.set(Calendar.DAY_OF_WEEK, i + 2);
            list.add(c.getTime());
        }
        // 最后一天:
        c.set(Calendar.WEEK_OF_MONTH, week + 1);
        c.set(Calendar.DAY_OF_WEEK, 1);
        list.add(c.getTime());
        return list;
    }

    /**
     * 用途：获得起始时间和结束时间范围内，所有的星期一和星期天
     */
    public static List<String> getMonAndSundayList(Date date1, Date date2) throws ParseException {
        List<String> list = new ArrayList<String>();
        String startMonday = getMondayOfThisWeek(date1, "yyyy-MM-dd");
        String endSunday = getSundayOfThisWeek(date2, "yyyy-MM-dd");
        Date dateOne = dateParse(startMonday, "yyyy-MM-dd");
        Date dateTwo = dateParse(endSunday, "yyyy-MM-dd");
        String result = "";
        Date da = dateOne;
        list.add(startMonday);
        for (int i = 0; i >= 0; i++) {
            if (DateUtil.dateCompare(da, dateTwo) == 0) {
                break;
            } else {
                if (i % 2 == 0) {
                    da = dateAddDays(da, 6);
                } else {
                    da = dateAddDays(da, 1);
                }
                result = DateUtil.dateFormat(da, "yyyy-MM-dd");
                list.add(result);
            }
        }
        return list;
    }

    /**
     * 用途：获得起始时间和结束时间范围内，所有的月初和月末 2017年8月5日 下午3:28:49 张进 return List<String>
     */
    public static List<String> getfirstAndLastListOfMonth(Date date1, Date date2)
            throws ParseException {
        List<String> list = new ArrayList<String>();
        String startFirstOfMonth = getFirstOfMonth(date1, "yyyy-MM-dd");
        String endLastOfMonth = getLastOfMonth(date2, "yyyy-MM-dd");
        Date dateOne = dateParse(startFirstOfMonth, "yyyy-MM-dd");
        Date dateTwo = dateParse(endLastOfMonth, "yyyy-MM-dd");
        Date da = dateOne;
        String result = "";
        Date comPareFlag;
        for (int i = 0; i >= 0; i++) {
            comPareFlag = dateParse(getLastOfMonth(da, "yyyy-MM-dd"), "yyyy-MM-dd");
            if (DateUtil.dateCompare(comPareFlag, dateTwo) == 0) {
                break;
            } else {
                result = getFirstOfMonth(da, "yyyy-MM-dd");
                list.add(result);
                result = getLastOfMonth(da, "yyyy-MM-dd");
                list.add(result);
                da = dateAddMonths(da, 1);
            }
        }
        String LastFirstOfMonth = getFirstOfMonth(date2, "yyyy-MM-dd");
        list.add(LastFirstOfMonth);
        list.add(endLastOfMonth);
        return list;
    }

    /**
     * 用途：获得起始时间和结束时间范围内，所有的年初和年末 2017年8月5日 下午4:00:09 张进 return List<String>
     */
    public static List<String> getfirstAndLastListOfYear(Date date1, Date date2)
            throws ParseException {
        List<String> list = new ArrayList<String>();
        String startFirstOfYear = dateFormat(getFirstOfYear(date1), "yyyy-MM-dd");
        String endLastOfYear = dateFormat(getLastOfYear(date2), "yyyy-MM-dd");
        Date dateOne = dateParse(startFirstOfYear, "yyyy-MM-dd");
        Date dateTwo = dateParse(endLastOfYear, "yyyy-MM-dd");
        Date da = dateOne;
        String result = "";
        Date comPareFlag;
        for (int i = 0; i >= 0; i++) {
            comPareFlag = getLastOfYear(da);
            if (DateUtil.dateCompare(comPareFlag, dateTwo) == 0) {
                break;
            } else {
                result = dateFormat(getFirstOfYear(da), "yyyy-MM-dd");
                list.add(result);
                result = dateFormat(getLastOfYear(da), "yyyy-MM-dd");
                list.add(result);
                da = dateAddYears(da, 1);
            }
        }
        String LastFirstOfYear = dateFormat(getFirstOfYear(date2), "yyyy-MM-dd");
        list.add(LastFirstOfYear);
        list.add(endLastOfYear);
        return list;
    }

    public static void main(String[] args) throws Exception {

        List<String> list = getMonAndSundayList(dateParse("2017-06-26", "yyyy-MM-dd"),
                dateParse("2017-08-15", "yyyy-MM-dd"));
        for (String string : list) {
            System.out.println(string);
        }
    }

}
