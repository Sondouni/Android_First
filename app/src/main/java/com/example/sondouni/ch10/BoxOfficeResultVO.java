package com.example.sondouni.ch10;

import java.util.List;

public class BoxOfficeResultVO {
    private String boxofficeType;
    private String showRange;
    private String yearWeekTime;
    private List<WeeklyBoxOfficeVO> weeklyBoxOfficeList;
    private List<DailyBoxOfficeVO> dailyBoxOfficeList;

    public String getYearWeekTime() {
        return yearWeekTime;
    }

    public void setYearWeekTime(String yearWeekTime) {
        this.yearWeekTime = yearWeekTime;
    }

    public List<WeeklyBoxOfficeVO> getWeeklyBoxOfficelist() { return weeklyBoxOfficeList;}

    public List<DailyBoxOfficeVO> getDailyBoxOfficeVOList() { return dailyBoxOfficeList;    }
    public void setWeeklyBoxOfficelist(List<WeeklyBoxOfficeVO> weeklyBoxOfficelist) {
        this.weeklyBoxOfficeList = weeklyBoxOfficelist;
    }

    public List<DailyBoxOfficeVO> getDailyBoxOfficeList() {
        return dailyBoxOfficeList;
    }

    public void setDailyBoxOfficeList(List<DailyBoxOfficeVO> dailyBoxOfficeList) {
        this.dailyBoxOfficeList = dailyBoxOfficeList;
    }


    public String getBoxofficeType() {
        return boxofficeType;
    }

    public void setBoxofficeType(String boxofficeType) {
        this.boxofficeType = boxofficeType;
    }

    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }



    public void setDailyBoxOfficeVOList(List<DailyBoxOfficeVO> dailyBoxOfficeVOList) {
        this.dailyBoxOfficeList = dailyBoxOfficeVOList;
    }
}
