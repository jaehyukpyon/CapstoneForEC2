package com.example.demo.openapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OpenController {

    private final OpenService openService;
    private final ConvertService convertService;

    @Autowired
    public OpenController(OpenService openService, ConvertService convertService) {
        this.openService = openService;
        this.convertService = convertService;
    }

    @ResponseBody
    @PostMapping("/noauth/openapi/hospital")
    public ResponseEntity<?> loadHospital() throws IOException {
        openService.deleteHospitalOpenData();
        int start = 1;
        int size = 0;
        do {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" +  URLEncoder.encode("735164496663686a3734485679554d","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("LOCALDATA_020301","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start+999),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            // System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            rd = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            StringBuffer result = new StringBuffer();
            result.append(rd.readLine());

            try {
                JsonObject jsonObject = (JsonObject) JsonParser.parseString(result.toString());
                // System.out.println(jsonObject);
                JsonArray jsonArray = jsonObject.get("LOCALDATA_020301").getAsJsonObject()
                        .get("row").getAsJsonArray();
                // System.out.println(jsonArray);
                start += 1000;
                size = jsonArray.size();

                for (int i = 0; i < size; i++) {
                    JsonObject object = (JsonObject) jsonArray.get(i);
                    String loadId = object.get("MGTNO").toString();
                    loadId = loadId.substring(1, loadId.length() - 1);
                    String isOpen = object.get("TRDSTATEGBN").toString();
                    isOpen = isOpen.substring(1, isOpen.length() - 1);
                    String phoneNum = object.get("SITETEL").toString();
                    phoneNum = phoneNum.substring(1, phoneNum.length() - 1);
                    String address = object.get("RDNWHLADDR").toString();
                    address = address.substring(1, address.length() - 1);
                    String postNum = object.get("RDNPOSTNO").toString();
                    postNum = postNum.substring(1, postNum.length() - 1);
                    String name = object.get("BPLCNM").toString();
                    name = name.substring(1, name.length() - 1);
                    String x = object.get("X").toString();
                    x = x.substring(1, x.length() - 1).replaceAll(" ", "");
                    String y = object.get("Y").toString();
                    y = y.substring(1, y.length() - 1).replaceAll(" ", "");

                    Map<String, String> convert = new HashMap<>();
                    if (x != null && x.length() > 0) {
                        convert = convertService.convert(x, y);
                    } else {
                        convert.put("latitude", "");
                        convert.put("longitude", "");
                    }

                    LoadVO loadVO = new LoadVO(loadId, isOpen, phoneNum, address, postNum, name, x, y, convert.get("latitude"), convert.get("longitude"));
                    openService.saveHospitalOpenData(loadVO);
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
            rd.close();
            conn.disconnect();
        } while (size == 1000);

        return ResponseEntity.ok("success");
    }

    // 전체 병원 데이터 조회
    @ResponseBody
    @GetMapping("/noauth/openapi/hospital")
    public ResponseEntity<List<LoadVO>> getHospital() {
        return ResponseEntity.ok(openService.findOpenHospital());
    }

    // 병원 별 평점 및 댓글 작성
    @ResponseBody
    @PostMapping("/api/openapi/hospital/rating")
    public ResponseEntity<HospitalVO> saveHospitalRating(@RequestBody HospitalVO hospitalVO) {
        int result = openService.saveHospitalRating(hospitalVO);
        return ResponseEntity.ok(openService.findByIdHospital(result));
    }

    // 병원 별 평점 및 댓글 조회
    @ResponseBody
    @GetMapping("/noauth/openapi/hospital/{hid}/rating")
    public ResponseEntity<List<HospitalVO>> getHospitalRating(@PathVariable String hid) {
        return ResponseEntity.ok(openService.findByHid(hid));
    }

    //
    @ResponseBody
    @PostMapping("/noauth/openapi/pharmacy")
    public ResponseEntity<?> loadPharmacy() throws IOException {
        openService.deletePharmacyOpenData();
        int start = 1;
        int size = 0;
        do {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" +  URLEncoder.encode("735164496663686a3734485679554d","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("LOCALDATA_020302","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start+999),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            // System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            rd = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            StringBuffer result = new StringBuffer();
            result.append(rd.readLine());

            try {
                JsonObject jsonObject = (JsonObject) JsonParser.parseString(result.toString());
                // System.out.println(jsonObject);
                JsonArray jsonArray = jsonObject.get("LOCALDATA_020302").getAsJsonObject()
                        .get("row").getAsJsonArray();
                // System.out.println(jsonArray);
                start += 1000;
                size = jsonArray.size();

                for (int i = 0; i < size; i++) {
                    JsonObject object = (JsonObject) jsonArray.get(i);
                    String loadId = object.get("MGTNO").toString();
                    loadId = loadId.substring(1, loadId.length() - 1);
                    String isOpen = object.get("TRDSTATEGBN").toString();
                    isOpen = isOpen.substring(1, isOpen.length() - 1);
                    String phoneNum = object.get("SITETEL").toString();
                    phoneNum = phoneNum.substring(1, phoneNum.length() - 1);
                    String address = object.get("RDNWHLADDR").toString();
                    address = address.substring(1, address.length() - 1);
                    String postNum = object.get("RDNPOSTNO").toString();
                    postNum = postNum.substring(1, postNum.length() - 1);
                    String name = object.get("BPLCNM").toString();
                    name = name.substring(1, name.length() - 1);
                    String x = object.get("X").toString();
                    x = x.substring(1, x.length() - 1).replaceAll(" ", "");
                    String y = object.get("Y").toString();
                    y = y.substring(1, y.length() - 1).replaceAll(" ", "");

                    Map<String, String> convert = new HashMap<>();
                    if (x != null && x.length() > 0) {
                        convert = convertService.convert(x, y);
                    } else {
                        convert.put("latitude", "");
                        convert.put("longitude", "");
                    }

                    LoadVO loadVO = new LoadVO(loadId, isOpen, phoneNum, address, postNum, name, x, y, convert.get("latitude"), convert.get("longitude"));
                    openService.savePharmacyOpenData(loadVO);
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
            rd.close();
            conn.disconnect();
        } while (size == 1000);

        return ResponseEntity.ok("ok");
    }

    // 전체 약국 데이터 조회
    @ResponseBody
    @GetMapping("/noauth/openapi/pharmacy")
    public ResponseEntity<List<LoadVO>> getPharmacy() {
        return ResponseEntity.ok(openService.findOpenPharmacy());
    }

    // 약국 별 평점 및 댓글 작성
    @ResponseBody
    @PostMapping("/api/openapi/pharmacy/rating")
    public ResponseEntity<PharmacyVO> savePharmacyRating(@RequestBody PharmacyVO pharmacyVO) {
        int result = openService.savePharmacyRating(pharmacyVO);
        return ResponseEntity.ok(openService.findByIdPharmacy(result));
    }

    // 약국 별 평점 및 댓글 조회
    @ResponseBody
    @GetMapping("/noauth/openapi/pharmacy/{pid}/rating")
    public ResponseEntity<List<PharmacyVO>> getPharmacyRating(@PathVariable String pid) {
        return ResponseEntity.ok(openService.findByPid(pid));
    }
}
