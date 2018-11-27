//package service.pubmethod;
//
//import io.vertx.core.logging.Log4jLogDelegateFactory;
//import org.apache.log4j.*;
//import org.apache.log4j.net.SyslogAppender;
//import org.apache.log4j.spi.LoggerFactory;
//
//import java.io.File;
//import java.util.Enumeration;
//
///**
// * 自定义日志
// */
//public class CustomLog extends Log4jLogDelegateFactory {
//    private static CustomLog log = new CustomLog();
//    private CustomLog(){}
//    public static CustomLog getInstance() {
//        return log;
//    }
//    /**
//     * 继承Level自定义级别
//     */
//    private static class CustomLogLevel extends Level{
//        private static final long serialVersionUID = 1L;
//        protected CustomLogLevel(int level, String levelStr, int syslogEquivalent) {
//            super(level, levelStr, syslogEquivalent);
//        }
//    }
//    /**
//     * 自定义级别名称，以及级别范围
//     */
//    private static final Level CustomerLevel = new CustomLogLevel(20050, "CUSTOMER", SyslogAppender.LOG_LOCAL0);
//
//    /**
//     * 生成日志对象
//     */
//    public Logger createLogger(){
//        // 生成新的Logger
//        // 如果已经有了一个Logger实例则返回
//        Logger logger = Logger.getLogger("TJDataEntryLogger.log");
//        // 清空Appender。特別是不想使用现存实例时一定要初期化
//        logger.removeAllAppenders();
//        // 设计定Logger级别。
//        logger.setLevel(Level.DEBUG);
//        // 设定是否继承父Logger。
//        // 默认为true。继承root输出。
//        // 设定false后将不输出root。
//        logger.setAdditivity(true);
//        // 生成新的Appender
//        FileAppender appender = new RollingFileAppender();
//        // log的输出形式
//        PatternLayout layout = new PatternLayout();
//        layout.setConversionPattern("%d{ISO8601}"+" "+"%l"+"    "+" %L  "+"%m%n");
//        appender.setLayout(layout);
//        // log输出路径
//        appender.setFile("TJDataEntryLogger.log");
////      appender.setFile(filePath+ fileName + ".log");                //自定义logger路径
//        // log的文字码
//        appender.setEncoding("UTF-8");
//        // true:在已存在log文件后面追加 false:新log覆盖以前的log
//        appender.setAppend(true);
//        // 适用当前配置
//        appender.activateOptions();
//        // 将新的Appender加到Logger中
//        logger.addAppender(appender);
//        return logger;
//    }
//
//    /**
//     * 使用自定义日志打印logger中的log方法
//     * @param logger 日志对象
//     * @param objLogInfo：日志内容
//     */
//    public void customLog(Logger logger, Object objLogInfo){
//        logger.log(CustomerLevel, objLogInfo);
//    }
//
//    /**
//     * 关闭自定义log
//     * @param logger 日志对象
//     */
//    @SuppressWarnings("unchecked")
//    public void closeCustomLog(Logger logger){
//        for (Enumeration<Appender> appenders=logger.getAllAppenders(); appenders.hasMoreElements();) {
//            Appender appender=appenders.nextElement();
//            appender.close();
//        }
//    }
//}
