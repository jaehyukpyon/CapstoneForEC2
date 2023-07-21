package com.example.demo.openapi;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.locationtech.proj4j.ProjCoordinate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class ConvertService {

    public Map<String, String> convert(String x, String y) {
        double x_ = Double.parseDouble(x);
        double y_ = Double.parseDouble(y);

        log.info("x_" + x_);
        log.info("y_" + y_);

        org.locationtech.proj4j.CRSFactory crsFactory = new org.locationtech.proj4j.CRSFactory();

        org.locationtech.proj4j.CoordinateReferenceSystem WGS84 = crsFactory.createFromParameters("WGS84",
                "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs ");
        org.locationtech.proj4j.CoordinateReferenceSystem KOR = crsFactory.createFromParameters("BESSEL-1841",
                "+proj=tmerc +lat_0=38 +lon_0=127.0028902777778 +k=1 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs +towgs84=-115.80,474.99,674.11,1.16,-2.31,-1.63,6.43");

        org.locationtech.proj4j.CoordinateTransformFactory ctFactory = new org.locationtech.proj4j.CoordinateTransformFactory();
        org.locationtech.proj4j.CoordinateTransform korToWgs = ctFactory.createTransform(KOR, WGS84);

        org.locationtech.proj4j.ProjCoordinate result = new org.locationtech.proj4j.ProjCoordinate();
        org.locationtech.proj4j.ProjCoordinate transform = korToWgs.transform(new ProjCoordinate(x_, y_), result);

        log.info("위도: " + transform.y);
        log.info("경도: " + transform.x);
        log.info("---------- 위도 경도 변환 완료! ----------");

        Map<String, String> map = new HashMap<>();
        map.put("latitude", transform.y + "");
        map.put("longitude", transform.x + "");

        return map;
    }
}
