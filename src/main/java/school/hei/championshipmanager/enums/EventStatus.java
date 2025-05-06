package school.hei.championshipmanager.enums;

public enum EventStatus {
    NOT_STARTED,
    STARTED,
    FINISHED;

    public Boolean isAfter(EventStatus otherStatus) {
        return this.ordinal() > otherStatus.ordinal();
    }
}
