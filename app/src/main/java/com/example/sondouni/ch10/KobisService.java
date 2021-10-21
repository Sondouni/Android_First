package com.example.sondouni.ch10;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KobisService {
    //일별 박스오피스를 검색시 사용하는 URL, retrofit에서 주소를 다 만들어준다
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxofficeSerchDailyBoxOfficeList(@Query("key") String key
                ,@Query("targetDt") String targetDt);

    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxofficeSerchWeeklyBoxOfficeList(@Query("key") String key
            ,@Query("targetDt") String targetDt,@Query("weekGb") String weekGb);
}
