package com.example.ReportPlayer.enumerated;

public enum ReportType {

    NEGATIVE_ATTITUDE,VERBAL_ABUSE,AFK,INTENTIONAL_FEEDING,HATE_SPEECH,CHEATING;

    public String toString() {
        switch (this) {
            case NEGATIVE_ATTITUDE:
                return "Negative Attitude";
            case VERBAL_ABUSE:
                return "Verbal Abuse";
            case AFK:
                return "Afk";
            case CHEATING:
                return "Cheating";
            case HATE_SPEECH:
                return "Hate Speech";
            case INTENTIONAL_FEEDING:
                return "Intentional Feeding";
            default:
                return "None";
        }
    }
}
