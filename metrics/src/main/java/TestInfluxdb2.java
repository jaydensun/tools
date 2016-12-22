import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by 089245 on 2016/11/11.
 */
public class TestInfluxdb2 {

    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://10.202.15.201:8086", "root", "root");
        String dbName = "db1";
        influxDB.createDatabase(dbName);

        while (true) {
            BatchPoints batchPoints = BatchPoints
                    .database(dbName)
//                    .tag("async", "true")
//                    .retentionPolicy("autogen")
                    .consistency(InfluxDB.ConsistencyLevel.ALL)
                    .build();
            Point point1 = Point.measurement("cpu")
                    .addField("host", "serverA")
                    .addField("region", "us_west")
                    .addField("value", new Random().nextFloat())
                    .build();
//        Point point2 = Point.measurement("disk")
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .addField("used", 80L)
//                .addField("free", 1L)
//                .build();
            batchPoints.point(point1);
//        batchPoints.point(point2);
            influxDB.write(batchPoints);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        Query query = new Query("SELECT idle FROM cpu", dbName);
//        influxDB.query(query);
//        influxDB.deleteDatabase(dbName);
    }
}
