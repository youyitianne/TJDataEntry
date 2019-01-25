package service;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ExcelRead.ExcelRead;
import service.entity.PayObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataOperation {
    Logger logger= LoggerFactory.getLogger(DataOperation.class);
    private Vertx vertx;
    private ExcelRead excelRead=new ExcelRead();

    /**
     * 初始化
     * @param vertx
     */
    public DataOperation(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * oppo数据转换成addata
     *
     * @param path
     * @return
     */
    public Future<List<PayObject>> huaweiOperation(String path, String channel) {
        Future<List<PayObject>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<PayObject> payObjectList = new ArrayList<>();
            List list = excelRead.readExcel(path);
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    PayObject payObject=huaweiToPayObject(array,channel);
                    payObjectList.add(payObject);
                }
            }
            future1.complete(payObjectList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<PayObject> list = (List<PayObject>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    public static PayObject huaweiToPayObject(List list, String channel) {
        PayObject payObject = new PayObject();
        payObject.setOrder_number(list.get(1).toString());
        payObject.setRequest_number((list.get(2).toString()));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            payObject.setOrder_time(sdf.parse(String.valueOf(list.get(3))).getTime());
        }catch (Exception e){
            payObject.setOrder_time(0L);
        }
        try {
            payObject.setPayment_time(sdf.parse(String.valueOf(list.get(4))).getTime());
        }catch (Exception e){
            payObject.setPayment_time(0L);
        }
        payObject.setDuration(Integer.valueOf(String.valueOf(list.get(5))));
        payObject.setApp_name(String.valueOf(list.get(8)));
        payObject.setProduct_name(String.valueOf(list.get(9)));
        payObject.setPayment(String.valueOf(list.get(10)));
        payObject.setCurrency_type(String.valueOf(list.get(11)));
        payObject.setPay_type(String.valueOf(list.get(12)));
        payObject.setMarketint_channel(String.valueOf(list.get(13)));
        payObject.setOrder_status(String.valueOf(list.get(14)));
        payObject.setOrder_result(String.valueOf(list.get(15)));
        payObject.setBussiness_type(String.valueOf(list.get(16)));
        String original_order=String.valueOf(list.get(17));
        if (original_order.length()==0){
            original_order="无";
        }
        payObject.setOriginal_order(original_order);
        try {
            payObject.setRefund_time(sdf.parse(String.valueOf(list.get(18))).getTime());
        }catch (Exception e){
            payObject.setRefund_time(0L);
        }
        payObject.setRefund_amount(Double.valueOf(String.valueOf(list.get(19))).intValue());
        payObject.setCountry(String.valueOf(list.get(20)));
        payObject.setChannel(channel);
        return payObject;
    }

}
