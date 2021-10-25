package com.example.sondouni.ch10;

import com.example.sondouni.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieListResultBodyVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KobisService {
    //일별 박스오피스를 검색시 사용하는 URL, retrofit에서 주소를 다 만들어준다

    String KEY = "f5eef3421c602c6cb7ea224104795888";
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxofficeSerchDailyBoxOfficeList(@Query("key") String key
                , @Query("targetDt") String targetDt);
    //weekly
    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxofficeSerchWeeklyBoxOfficeList(@Query("key") String key
            ,@Query("targetDt") String targetDt,@Query("weekGb") String weekGb);
    @GET("boxoffice/searchWeeklyBoxOfficeList.json?weekGb=1")
    Call<BoxOfficeResultBodyVO> boxofficeSerchWeeklyBoxOfficeList1(@Query("key") String key
            ,@Query("targetDt") String targetDt);
    //영화목록
    @GET("movie/searchMovieList.json?key="+KEY)
    Call<MovieListResultBodyVO> movieSearchList(@Query("itmePerPage") String itemPerPage,
                                                @Query("curPage") String curPage);
    //영화 디테일
    //@GET("movie/searchMovieInfo.json?key="+KEY)
    //Call<>
    @GET("movie/searchMovieInfo.json?key="+KEY)
    Call<MovieInfoResultBodyVO> searchMovieInfo(@Query("movieCd") String movieCd);
}