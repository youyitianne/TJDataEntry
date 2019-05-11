package http.apk;

import java.io.*;

/**
 * Created by Jay on 2017/5/18.
 */
public final class ApkUtil {
    public static final String VERSION_CODE = "versionCode";
    public static final String VERSION_NAME = "versionName";
    public static final String SDK_VERSION = "sdkVersion";
    public static final String TARGET_SDK_VERSION = "targetSdkVersion";
    public static final String USES_PERMISSION = "uses-permission";
    public static final String APPLICATION_LABEL = "application-label";
    public static final String APPLICATION_ICON = "application-icon";
    public static final String USES_FEATURE = "uses-feature";
    public static final String USES_IMPLIED_FEATURE = "uses-implied-feature";
    public static final String SUPPORTS_SCREENS = "supports-screens";
    public static final String SUPPORTS_ANY_DENSITY = "supports-any-density";
    public static final String DENSITIES = "densities";
    public static final String PACKAGE = "package";
    public static final String APPLICATION = "application:";
    public static final String LAUNCHABLE_ACTIVITY = "launchable-activity";
    private ProcessBuilder mBuilder;
    private static final String SPLIT_REGEX = "(: )|(=')|(' )|'";
    private static final String FEATURE_SPLIT_REGEX = "(:')|(',')|'";

    /**
     * aapt所在的目录。
     */
    //windows环境下直接指向appt.exe
    //比如你可以放在lib下
    //private String mAaptPath = "lib/aapt";  TJMission/bin/windows/aapt.exe
    //下面是linux下
    private String linux_mAaptPath = "bin"+File.separator+"ubuntu"+File.separator+"aapt";
    private String windows_mAaptPath = "TJMission"+File.separator+"bin"+File.separator+"windows"+File.separator+"aapt.exe";


    public ApkUtil() {
        mBuilder = new ProcessBuilder();
        mBuilder.redirectErrorStream(true);
    }

    /**
     * 返回一个apk程序的信息。
     *
     * @param apkPath
     *            apk的路径。
     * @return apkInfo 一个Apk的信息。
     */
    public ApkInfo getApkInfo(String apkPath) {
        ApkInfo apkInfo = null;
        try {
            String mAaptPath="";
            String osName = System.getProperty("os.name");
            System.out.println(osName);
            String [] cmds=null;
            if (osName.startsWith("Mac OS")) {
                // 苹果
            } else if (osName.startsWith("Windows")) {
                // windows
                mAaptPath=windows_mAaptPath;
            } else {
                // unix or linux
                mAaptPath=linux_mAaptPath;
            }
            //通过命令调用aapt工具解析apk文件
            File file=new File(mAaptPath);
            if (file.exists()){
                System.out.println("aapt位置已找到++++"+file.getPath());
            }
            Process process = mBuilder.command(mAaptPath, "d", "badging", apkPath)
                    .start();
            InputStream is = null;
            is = process.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "utf8"));
            String tmp = br.readLine();
            if (tmp == null || !tmp.startsWith("package")) {
                throw new Exception("参数不正确，无法正常解析APK包。输出结果为:\n" + tmp + "...");
            }
            apkInfo = new ApkInfo();
            System.out.println("++++++++++++++");
            do {
                System.out.println(tmp);
                setApkInfoProperty(apkInfo, tmp);
            } while ((tmp = br.readLine()) != null);
            System.out.println("++++++++++++++++++++");
            process.destroy();
            closeIO(is);
            closeIO(br);
        }catch (Exception e){
            e.printStackTrace();
        }
        return apkInfo;
    }

    /**
     * 设置APK的属性信息。
     *
     * @param apkInfo
     * @param source
     */
    private void setApkInfoProperty(ApkInfo apkInfo, String source) {
        if (source.startsWith(PACKAGE)) {
            splitPackageInfo(apkInfo, source);
        } else if(source.startsWith(LAUNCHABLE_ACTIVITY)){
            apkInfo.setLaunchableActivity(getPropertyInQuote(source));
        } else if (source.startsWith(SDK_VERSION)) {
            apkInfo.setSdkVersion(getPropertyInQuote(source));
        } else if (source.startsWith(TARGET_SDK_VERSION)) {
            apkInfo.setTargetSdkVersion(getPropertyInQuote(source));
        } else if (source.startsWith(USES_PERMISSION)) {
            apkInfo.addToUsesPermissions(getPropertyInQuote(source));
        } else if (source.startsWith(APPLICATION_LABEL)) {
            //window下获取应用名称
            apkInfo.setApplicationLable(getPropertyInQuote(source));
        } else if (source.startsWith(APPLICATION_ICON)) {
            apkInfo.addToApplicationIcons(getKeyBeforeColon(source),
                    getPropertyInQuote(source));
        } else if (source.startsWith(APPLICATION)) {
            String[] rs = source.split("( icon=')|'");
            apkInfo.setApplicationIcon(rs[rs.length - 1]);
            //linux下获取应用名称
            apkInfo.setApplicationLable(rs[1]);
        } else if (source.startsWith(USES_FEATURE)) {
            apkInfo.addToFeatures(getPropertyInQuote(source));
        } else if (source.startsWith(USES_IMPLIED_FEATURE)) {
            apkInfo.addToImpliedFeatures(getFeature(source));
        } else {
//          System.out.println(source);
        }
    }

    private ImpliedFeature getFeature(String source) {
        String[] result = source.split(FEATURE_SPLIT_REGEX);
        ImpliedFeature impliedFeature = new ImpliedFeature(result[1], result[2]);
        return impliedFeature;
    }

    /**
     * 返回出格式为name: 'value'中的value内容。
     *
     * @param source
     * @return
     */
    private String getPropertyInQuote(String source) {
        int index = source.indexOf("'") + 1;
        return source.substring(index, source.indexOf('\'', index));
    }

    /**
     * 返回冒号前的属性名称
     *
     * @param source
     * @return
     */
    private String getKeyBeforeColon(String source) {
        return source.substring(0, source.indexOf(':'));
    }

    /**
     * 分离出包名、版本等信息。
     *
     * @param apkInfo
     * @param packageSource
     */
    private void splitPackageInfo(ApkInfo apkInfo, String packageSource) {
        String[] packageInfo = packageSource.split(SPLIT_REGEX);
        apkInfo.setPackageName(packageInfo[2]);
        apkInfo.setVersionCode(packageInfo[4]);
        apkInfo.setVersionName(packageInfo[6]);
    }

    /**
     * 释放资源。
     *
     * @param c
     *            将关闭的资源
     */
    private final void closeIO(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String runtime = System.getProperty("user.dir");
            String demo = runtime +File.separator+"TJResourceStoreEntry"+File.separator+"apk"+File.separator+"球球跳一跳.apk";
            ApkInfo apkInfo = new ApkUtil().getApkInfo(demo);
            System.out.println(apkInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getmAaptPath() {
        String mAaptPath="";
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        String [] cmds=null;
        if (osName.startsWith("Mac OS")) {
            // 苹果
        } else if (osName.startsWith("Windows")) {
            // windows
            mAaptPath=windows_mAaptPath;
        } else {
            // unix or linux
            mAaptPath=linux_mAaptPath;
        }
        return mAaptPath;
    }

}
