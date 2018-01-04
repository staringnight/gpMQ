package org.edl.gpmq.socket.recorder;

import org.apache.mina.core.session.IoSession;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yks on 2017/8/18.
 */
public class McuRecordHolder  {
//todo : 定时清除未登录校验socket
    public void afterPropertiesSet() throws Exception {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (RECORDS != null && RECORDS.size() > 0) {
                    for (McuRecord mcuRecord : RECORDS) {
                        if (mcuRecord.getId() == null && (System.currentTimeMillis() - mcuRecord.getStartLinkTime()) > 300000) {
                            RECORDS.remove(mcuRecord);
                            mcuRecord.getSession().close(true);
                        }
                    }
                }
            }
        }, 1000, 10000);

    }

    private static List<McuRecord> RECORDS;

    static {
        RECORDS = new LinkedList<>();
    }

    public static void add(McuRecord mcuRecord) {
        RECORDS.add(mcuRecord);
    }

    public static void remove(McuRecord mcuRecord) {
        RECORDS.remove(mcuRecord);
    }

    public static McuRecord get(String mcuId) {
        if (mcuId == null || "".equals(mcuId)) {
            return null;
        }
        for (McuRecord mcuRecord : RECORDS) {
            if (mcuId.equals(mcuRecord.getId())) {
                return mcuRecord;
            }
        }
        return null;
    }

    public static McuRecord get(IoSession ioSession) {
        if (ioSession == null) {
            return null;
        }
        for (McuRecord mcuRecord : RECORDS) {
            if (ioSession.equals(mcuRecord.getSession())) {
                return mcuRecord;
            }
        }
        return null;
    }
}
