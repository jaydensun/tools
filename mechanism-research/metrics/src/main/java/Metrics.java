import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import com.codahale.metrics.Timer;

import java.util.concurrent.TimeUnit;

/**
 * Created by 089245 on 2017/4/20.
 */
public class Metrics {
    public static void main(String[] args) throws InterruptedException {
//        Timer timer1 = new Timer(new SlidingTimeWindowReservoir(10, TimeUnit.MINUTES));
        Timer timer1 = new Timer(new ExponentiallyDecayingReservoir(1000, 0.015));


        Timer.Context time = timer1.time();
        Thread.sleep(2000);
        time.stop();
    }
}
