import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import metrics_influxdb.HttpInfluxdbProtocol;
import metrics_influxdb.InfluxdbReporter;
import metrics_influxdb.api.measurements.CategoriesMetricMeasurementTransformer;
import metrics_influxdb.measurements.HttpInlinerSender;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * Created by 089245 on 2016/11/22.
 */
public class TestInfulxdbReporter {

    public static void main(String[] args) throws Exception {
        Field field = HttpInlinerSender.class.getDeclaredField("MAX_MEASURES_IN_SINGLE_POST");
        field.setAccessible(true);
        System.out.println(field.get(null));
        field.set(null, 1);
        System.out.println(field.get(null));

        MetricRegistry registry = new MetricRegistry();
        ScheduledReporter reporter = InfluxdbReporter.forRegistry(registry)
                .protocol(new HttpInfluxdbProtocol("http", "10.202.15.201", 8086, null, null, "food_data"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
//                .transformer(new CategoriesMetricMeasurementTransformer("module", "artifact"))
                .build();
        reporter.start(3, TimeUnit.SECONDS);

        registry.register(MetricRegistry.name("orders"), (Gauge<Long>) System::currentTimeMillis);


        Thread.sleep(10000000000L);

    }
}
