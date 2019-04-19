package database;

public class RepeatSql {
    public static final String GET_AD_REPEAT_show="select * from advertisement.advertisingdata a \n" +
            "where \n" +
            "a.earned !=0 and a.click!=0 and a.ecpm!=0 and impression > 500 and\n" +
            "(a.date,a.channel,a.earned,a.click_rate,a.ecpm,a.impression,a.click) in \n" +
            "(select date,channel,earned,click_rate,ecpm,impression,click from advertisement.advertisingdata group by date,channel,earned,click_rate,ecpm,impression,click having count(*) > 1) " +
            "order by earned,id";

    public static final String GET_AD_REPEAT="select * from advertisement.advertisingdata a \n" +
            "where a.earned !=0 and a.click!=0 and a.ecpm!=0 and \n" +
            "(a.date,a.app_name,a.channel,a.advertising_type,a.ecpm,a.earned) in \n" +
            "(select date,app_name,channel,advertising_type,ecpm,earned from advertisement.advertisingdata group by date,app_name,channel,advertising_type,ecpm,earned having count(*) > 1) \n" +
            "and\n" +
            "id not in (select max(id) from advertisement.advertisingdata group by date,app_name,channel,advertising_type,ecpm,earned having count(*) > 1);";

    public static final String DEL_ADLIST = "delete from advertisement.advertisingdata where id in ";

    public static final String DEL_ADLIST_ONE = "delete from advertisement.advertisingdata where id in ";
   // public static final String DEL_ADLIST_ONE = "delete from advertisement.advertisingdata where id = ? ";

    public static final String GET_USER_REPEAT1=" select * from advertisement.userdata a \n" +
            "where \n" +
            "dnu!=0 and dau!=0 and startup_time!=0 and\n" +
            "(a.date,a.channel,a.dnu,a.dau,a.startup_time,a.single_use_time,a.retention) in \n" +
            "(select date,channel,dnu,dau,startup_time,single_use_time,retention from advertisement.userdata \n" +
            "group by date,channel,dnu,dau,startup_time,single_use_time,retention  having count(*) > 1) \n" +
            "and\n" +
            " id not in (select max(id) from advertisement.userdata group by date,channel,dnu,dau,startup_time,single_use_time,retention  having count(*) > 1);";

    public static final String DEL_USERDATA="delete from advertisement.userdata where id in ";
}
