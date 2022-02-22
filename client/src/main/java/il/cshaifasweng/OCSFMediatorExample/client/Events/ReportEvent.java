package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ReportBus;

public class ReportEvent {
    ReportBus reportBus;

    public ReportEvent() {
    }

    public ReportEvent(ReportBus reportBus) {
        this.reportBus = reportBus;
    }

    public ReportBus getReportBus() {
        return reportBus;
    }
}
