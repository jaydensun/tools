import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
//import org.elasticsearch.metrics.ElasticsearchReporter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by 089245 on 2016/11/11.
 */
public class TestElasticReporter {
    public static void main(String[] args) throws Exception {
//        final MetricRegistry registry = new MetricRegistry();
//        ElasticsearchReporter reporter = ElasticsearchReporter.forRegistry(registry)
//                .hosts("10.202.15.201:9200")
//                .build();
//        reporter.start(5, TimeUnit.SECONDS);
//
////        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
////                .convertRatesTo(TimeUnit.SECONDS)
////                .convertDurationsTo(TimeUnit.MILLISECONDS)
////                .build();
////        reporter.start(1, TimeUnit.SECONDS);
//
//        registry.register(MetricRegistry.name("test", "a"), (Gauge<Long>) System::currentTimeMillis);

        Thread.sleep(10000000000L);
    }
}
