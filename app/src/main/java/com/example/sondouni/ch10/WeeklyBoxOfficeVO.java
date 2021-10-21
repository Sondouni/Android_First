package com.example.sondouni.ch10;

public class WeeklyBoxOfficeVO {
    private String movieNm;
    private String openDt;
    private String rank;
    private String audiCnt;

    public String getMovieNm() { return movieNm; }

    public void setMovieNm(String movieNm) { this.movieNm = movieNm;  }

    public String getOpenDt() { return openDt;}

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAudiCnt() {
        return audiCnt;
    }

    public void setAudiCnt(String audiCnt) {
        this.audiCnt = audiCnt;
    }
}
