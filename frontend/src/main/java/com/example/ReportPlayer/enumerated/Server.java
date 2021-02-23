package com.example.ReportPlayer.enumerated;

public enum Server {

    EUW, NORTH_AMERICA, KOREA, JAPAN, EU_NORDIC_AND_EST, OCEANIA, BRAZIL, LAS,
    LAN, RUSSIA, SINGAPORE, INDONESIA, PHILIPPINES, TAIWAN, VIETNAM, THAILAND,
    TURKEY;

    @Override
    public String toString() {
        switch (this) {
            case EUW:
                return "euw";
            case NORTH_AMERICA:
                return "na";
            case KOREA:
                return "korea";
            case JAPAN:
                return "japan";
            case EU_NORDIC_AND_EST:
                return "eu_nordic_and_est";
            case OCEANIA:
                return "oceania";
            case BRAZIL:
                return "brazil";
            case LAN:
                return "lan";
            case LAS:
                return "las";
            case TURKEY:
                return "turkey";
            case TAIWAN:
                return "taiwan";
            case VIETNAM:
                return "vietnam";
            case THAILAND:
                return "thailand";
            case PHILIPPINES:
                return "philippine";
            case INDONESIA:
                return "indonesia";
            case SINGAPORE:
                return "singapore";
            case RUSSIA:
                return "russia";
            default:
                return "None";
        }
    }
    public class Region{
        public static final String EUW = "euw";
        public static final String NORTH_AMERICA = "na";
        public static final String KOREA ="korea";
        public static final String JAPAN = "japan";
        public static final String EU_NORDIC_AND_EST =  "eu_nordic_and_est";
        public static final String OCEANIA ="oceania";
        public static final String BRAZIL = "brazil";
        public static final String LAS =  "las";
        public static final String LAN =  "lan";
        public static final String RUSSIA =  "russia";
        public static final String TURKEY = "turkey";
        public static final String SINGAPORE = "singapore";
        public static final String INDONESIA = "indonesia";
        public static final String PHILIPPINES = "philippine";
        public static final String TAIWAN ="taiwan";
        public static final String VIETNAM = "vietnam";
        public static final String THAILAND = "thailand";
    }

}



