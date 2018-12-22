package service.Constant;

public class SqlStatement {
    public static String REPEAT_ID_ADVERTISEMENT="select id from advertisement.advertisingdata a \n" +
            "where \n" +
            "(a.date,a.app_name,a.channel,a.advertising_type,a.platform,a.note) in (select date,app_name,channel,advertising_type,platform,note from advertisement.advertisingdata group by date,app_name,channel,advertising_type,platform,note having count(*) > 1) \n" +
            "and\n" +
            " id not in (select max(id) from advertisement.advertisingdata group by date,app_name,channel,advertising_type,platform,note having count(*) > 1);" ;


    public static String REPEAT_ID_USER="select * from advertisement.userdata a \n" +
            "where \n" +
            "(a.date,a.app_name,a.channel,a.version) in (select date,app_name,channel,version from advertisement.userdata group by date,app_name,channel,version having count(*) > 1) \n" +
            "and\n" +
            " id not in (select max(id) from advertisement.userdata group by date,app_name,channel,version having count(*) > 1);";

    public static String DEL_ID_USER="delete from advertisement.userdata where id in ";

    public static String DEL_ID_ADVERTISEMENT="delete from advertisement.advertisingdata where id in ";

    public static String INSERT_ADVERTISEMENT="insert into advertisement.advertisingdata (date,app_name,channel,advertising_type,earned,click_rate,ecpm,impression,click,fill_rate,platform,note,sdk_name) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String INSERT_USER="insert into advertisement.userdata (date,app_name,channel,dnu,dau,startup_time,single_use_time,retention,version) values (?,?,?,?,?,?,?,?,?)";

    public static String SELECT_USERDATA = "select * from advertisement.userdata where date >= ? and date<= ? and app_name like  ? ";

    public static String SELECT_ADDATA = "select * from advertisement.advertisingdata where date >= ? and date<= ? and  app_name like ? ";

    public static String SELECT_CHANNEL = "SELECT * FROM advertisement.channel;";

    public static String INSERT_CHANNEL = "insert into advertisement.channel values(null, ? , ? , ? );";

    public static String UPDATE_CHANNEL = "UPDATE `advertisement`.`channel`\n" +
            "SET\n" +
            "`name` = ?,\n" +
            "`program_mark` = ?,\n" +
            "`note` = ?\n" +
            "WHERE `id` = ?;";

    public static String DELETE_CHANNEL = "DELETE FROM `advertisement`.`channel`\n" +
            "WHERE `id` = ?;";

    public static String SELECT_APP = "SELECT * FROM advertisement.appname;\n";

    public static String INSERT_APP = "INSERT INTO `advertisement`.`appname`\n" +
            "(`name`,\n" +
            "`system`,\n" +
            "`icon`,\n" +
            "`introduce`)\n" +
            "VALUES( ?,? ,?,?);";

    public static String UPDATE_APP = "UPDATE `advertisement`.`appname`\n" +
            "SET\n" +
            "`name` = ?,\n" +
            "`system` = ?,\n" +
            "`icon` = ?,\n" +
            "`introduce` = ? \n" +
            "WHERE `id` = ?;";

    public static String SELEECT_ADTYPE="SELECT * FROM advertisement.advertisingtype;\n";

    public static String UPDATE_ADTYPE="UPDATE \n" +
            "`advertisement`.`advertisingtype`\n" +
            "SET\n" +
            "`name` = ? ,`program_mark` =  ? ,`note` = ? ,`introduce` = ? \n" +
            "WHERE `id` = ? ;";

    public static String INSERT_ADTYPE="INSERT INTO \n" +
            "`advertisement`.`advertisingtype`\n" +
            "(`name`,`program_mark`,`note`,`introduce`)\n" +
            "VALUES\n" +
            "(? ,? ,? ,?);";

    public static String DELETE_ADTYPE="DELETE FROM `advertisement`.`advertisingtype`\n" +
            "WHERE id = ? ;";

    public static String DELETE_APP = "DELETE FROM `advertisement`.`appname` WHERE `id` = ?;\n";

    public static String SELECT_CHANNEL_NAME="SELECT name,program_mark FROM advertisement.channel;\n";

    public static String SELECT_GAME_NAME=" SELECT name FROM advertisement.appname;";

    public static String SELECT_ADTYPE_NAME="SELECT name FROM advertisement.advertisingtype;\n";

    public static String SELECT_USERDATA_BY_TIME=" SELECT `userdata`.`id`,`userdata`.`date`,`userdata`.`app_name`,`userdata`.`channel`,`userdata`.`dnu`,`userdata`.`dau`,`userdata`.`startup_time`,`userdata`.`single_use_time`,\n" +
            " `userdata`.`retention`,`userdata`.`version` FROM  `advertisement`.`userdata` where date >= ? and date <= ?;";
}
