package com.jayden.tx;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by 089245 on 2017/7/14.
 */
public class IdGenerator {

    private static final Logger logger = Logger.getLogger(IdGenerator.class.getName());
    // 13位的任意一个历史时间
    private final static long twepoch = 1500023980462L;
    // 机器标识位数
    private final static long instanceIdBits = 6L;
    private final static int instanceIdMask = ~(-1 << instanceIdBits);
    private static int instanceId;

    // 毫秒内自增位
    private final static long sequenceBits = 8L;
    private final static long sequenceMask = ~(-1L << sequenceBits);

    private final static long instanceIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + instanceIdBits;

    private static long lastTimestamp = -1L;

    private static long sequence = 0L;

    private IdGenerator() {}

    static {
        instanceId = new Random().nextInt(instanceIdMask);
        logger.info("current instance id is " + instanceId);
    }

    private static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindowsOS = true;
        }
        return isWindowsOS;

    }

    public synchronized static long getId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (instanceId << instanceIdShift)
                | sequence;
    }


    private static long tilNextMillis(final long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }
}
