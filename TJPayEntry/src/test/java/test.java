import io.vertx.core.Vertx;
import org.junit.Test;
import service.DataOperation;
import service.ExcelRead.ExcelRead;
import service.entity.PayObject;
import java.util.List;

public class test {
    @Test
    public void test1(){
        DataOperation dataOperation=new DataOperation(Vertx.vertx());
        ExcelRead excelRead=new ExcelRead();
        String path="file-uploads/2019-01-11/1.xlsx";
//        List list = excelRead.readExcel(path);
//        System.out.println(list);
        dataOperation.huaweiOperation(path,"huawei").setHandler(rs->{
           if (rs.succeeded()){
               List<PayObject> list=rs.result();
               System.out.println(list);
           }else {
               rs.cause().printStackTrace();
           }
        });
    }
}