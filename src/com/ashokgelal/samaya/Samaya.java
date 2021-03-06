package com.ashokgelal.samaya;

import java.util.Calendar;

public class Samaya extends DateTime{
	private static final long serialVersionUID = 2098914112990897968L;
	public static final Samaya MIN_VALUE = new Samaya(1, 1, 1, 0, 0, 0, 0);
    public static final Samaya MAX_VALUE = new Samaya(9999, 12, 31, 23, 59, 59, 9999999);
    public static final Samaya EPOCH = new Samaya(1970, 1, 1);

    public Samaya(String aDateTime) {
        super(aDateTime);
    }

    public Samaya(int aYear, int aMonth, int aDay, int aHour, int aMinute, int aSecond, int aNanoseconds) {
        super(aYear, aMonth, aDay, aHour, aMinute, aSecond, aNanoseconds);
    }

    public Samaya(int aYear, int aMonth, int aDay, int aHour, int aMinute, int aSecond) {
        super(aYear, aMonth, aDay, aHour, aMinute, aSecond, 0);
    }

   public Samaya(int year, int month, int day) {
       super(year, month, day, 0, 0, 0, 0);
   }

   public Samaya(int year, int month, int day, int hour, int minute) {
       super(year, month, day, hour, minute, 0, 0);
   }

    public Samaya Subtract(int year, int month, int day, int hour, int minute, int second)
    {
        DateTime time = minus(year, month, day, hour, minute, second, DayOverflow.LastDay);
        return new Samaya(time);
    }

    public TimeSpan Subtract(Samaya time)
    {
        // AG: feel backasswards so I'm reverting the way minus works
        return TimeSpan.FromSeconds(time.numSecondsFrom(this));
    }
    
    public Samaya Subtract(TimeSpan timeSpan) {
        return new Samaya(minus(0, 0, timeSpan.Days(), timeSpan.Hours(), timeSpan.Minutes(), timeSpan.Seconds(), DayOverflow.LastDay));
    }

    public Samaya Add(TimeSpan timeSpan){
    	if(timeSpan.Days()>9999)
    	{
    		Calendar ca = Calendar.getInstance();
    		ca.setTimeInMillis((long)timeSpan.TotalMilliseconds());
    		DateTime time = plus(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), ca.get(Calendar.HOUR), ca.get(Calendar.MINUTE), ca.get(Calendar.SECOND), DayOverflow.LastDay);
    		return new Samaya(time);
    	}
        DateTime time = plus(0, 0, timeSpan.Days(), timeSpan.Hours(), timeSpan.Minutes(), timeSpan.Seconds(), DayOverflow.LastDay);
        return new Samaya(time);
    }
    
    public Samaya Add(DateTime time)
    {
    	return new Samaya(plus(time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute(), time.getSecond(), DayOverflow.LastDay));
    }

    public Samaya AddDays(double days)
    {
        return Add(TimeSpan.FromDays(days));
    }

    public Samaya AddHours(double hours)
    {
       return Add(TimeSpan.FromHours(hours));
    }

    public Samaya AddMilliseconds(double milliseconds)
    {
        return Add(TimeSpan.FromMilliseconds(milliseconds));
    }

    public Samaya AddMinutes(double minutes)
    {
        return Add(TimeSpan.FromMinutes(minutes));
    }

    public Samaya AddSeconds(double seconds)
    {
        return Add(TimeSpan.FromSeconds(seconds));
    }

    public Samaya AddTicks(long ticks )
    {
        return Add(TimeSpan.FromTicks(ticks));
    }

    public Samaya AddMonths(int months)
    {
        DateTime time = plus(0, months, 0, 0, 0, 0, DayOverflow.LastDay);
        return new Samaya(time);
    }

    public Samaya AddYears(int years)
    {
        DateTime time = plus(years, 0, 0, 0, 0, 0, DayOverflow.LastDay);
        return new Samaya(time);
    }
    public Samaya(DateTime time){
        super(time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute(), time.getSecond(), time.getNanoseconds());
    }

    public static Samaya Now() {
        DateTime time = Samaya.now(Calendar.getInstance().getTimeZone());
        return new Samaya(time);
    }

    public long Milliseconds() {
        return getMilliseconds(Calendar.getInstance().getTimeZone());
    }

    public Integer Hour() {
        return getHour();
    }
    
    public static TimeSpan CurrentTimestamp(){
    	long unixTime = System.currentTimeMillis()/1000l;
    	return TimeSpan.FromSeconds(unixTime);
    }
    
    public TimeSpan TimeSpanSinceEpoch(){
    	return TimeSpan.FromMilliseconds(TimestampInMilliseconds());
    }
    
    public long Timestamp(){
    	return TimestampInMilliseconds()/1000;
    }
    
    public long TimestampInSeconds(){
    	return Timestamp();
    }
    
    public long TimestampInMilliseconds(){
    	return getMilliseconds(Calendar.getInstance().getTimeZone());
    }
    
    public static Samaya FromTimestamp(long milliseconds)
    {
    	return new Samaya(DateTime.forInstant(milliseconds, Calendar.getInstance().getTimeZone()));
    }
}
