package com.example.demo.gonggongdata.abandoned_animal.service;

import com.example.demo.gonggongdata.abandoned_animal.dto.TestDto1_j;
import com.example.demo.gonggongdata.abandoned_animal.mapper.AbandonedAnimalMapper_j;
import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import com.example.demo.gonggongdata.abandoned_animal.response.ApiResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.response.Item_j;
import com.example.demo.gonggongdata.abandoned_animal.response.Items_j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class AbandonedAnimalService_j {

    private final AbandonedAnimalMapper_j abandonedAnimalMapper;

    @Value("${gonggongdatakey}")
    private String gonggongDataKey;

    private final String GONGGONG_DATA_BASE_URL = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";

    private WebClient getBaseWebClient() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(GONGGONG_DATA_BASE_URL);
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(consumer -> consumer.defaultCodecs().maxInMemorySize(-1))
                .build();

        return WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .uriBuilderFactory(defaultUriBuilderFactory)
                .baseUrl(GONGGONG_DATA_BASE_URL)
                .build();
    }

    private int getTotalAbandonedAnimalCount(String dogOrCat, String state) {
        WebClient webClient = getBaseWebClient();

        ApiResponse_j response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", gonggongDataKey)
                        .queryParam("_type", "json")
                        .queryParam("numOfRows", 1)
                        .queryParam("upkind", dogOrCat)
                        .queryParam("state", state)
                        .build())
                .retrieve()
                .bodyToMono(ApiResponse_j.class)
                .block();
        Integer totalCount = response.getResponse().getBody().getTotalCount();
        log.info("DogOrCat: " + dogOrCat + ", State: " + state + ", count: " + totalCount);
        return totalCount;
    }

    private List<Item_j> retrieveAbandonedAnimalFromGonggongData(int pageNo, int count, String dogOrCat, String state) {
        WebClient webClient = getBaseWebClient();

        ApiResponse_j response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", gonggongDataKey)
                        .queryParam("_type", "json")
                        .queryParam("numOfRows", count)
                        .queryParam("pageNo", pageNo)
                        .queryParam("upkind", dogOrCat)
                        .queryParam("state", state)
                        .build())
                .retrieve()
                .bodyToMono(ApiResponse_j.class)
                .block();
        log.info("********** check pageNo: " + response.getResponse().getBody().getPageNo() + " **********");
        Items_j items = response.getResponse().getBody().getItems();
        List<Item_j> itemList = items.getItem();
        return itemList;
    }

    // 강아지 저장하기
    private List<List<Item_j>> saveDogListInList() {
        List<List<Item_j>> dogList = new ArrayList<>();
        int subCountByNotice = getTotalAbandonedAnimalCount("417000", "notice");
        int subCountByProtect = getTotalAbandonedAnimalCount("417000", "protect");

        int i = 1;
        for (i = 1; i <= subCountByNotice / 1_000; i++) {
            List<Item_j> noticeResult = retrieveAbandonedAnimalFromGonggongData(i, 1_000, "417000", "notice");
            dogList.add(noticeResult);
        }
        List<Item_j> noticeResult = retrieveAbandonedAnimalFromGonggongData(i, subCountByNotice % 1_000, "417000", "notice");
        dogList.add(noticeResult);


        i = 1;
        for (i = 1; i <= subCountByProtect / 1_000; i++) {
            List<Item_j> protectResult = retrieveAbandonedAnimalFromGonggongData(i, 1_000, "417000", "protect");
            dogList.add(protectResult);
        }
        List<Item_j> protectResult = retrieveAbandonedAnimalFromGonggongData(i, subCountByNotice % 1_000, "417000", "protect");
        dogList.add(protectResult);

        return dogList;
    }

    // 고양이 저장하기
    private List<List<Item_j>> saveCatListInList() {
        List<List<Item_j>> catList = new ArrayList<>();
        int subCountByNotice = getTotalAbandonedAnimalCount("422400", "notice");
        int subCountByProtect = getTotalAbandonedAnimalCount("422400", "protect");

        int i = 1;
        for (i = 1; i <= subCountByNotice / 1_000; i++) {
            List<Item_j> noticeResult = retrieveAbandonedAnimalFromGonggongData(i, 1_000, "422400", "notice");
            catList.add(noticeResult);
        }
        List<Item_j> noticeResult = retrieveAbandonedAnimalFromGonggongData(i, subCountByNotice % 1_000, "422400", "notice");
        catList.add(noticeResult);


        i = 1;
        for (i = 1; i <= subCountByProtect / 1_000; i++) {
            List<Item_j> protectResult = retrieveAbandonedAnimalFromGonggongData(i, 1_000, "422400", "protect");
            catList.add(protectResult);
        }
        List<Item_j> protectResult = retrieveAbandonedAnimalFromGonggongData(i, subCountByNotice % 1_000, "422400", "protect");
        catList.add(protectResult);

        return catList;
    }

    private void saveAbandonedAnimalListIntoDatabase() {
        List<List<Item_j>> dogList = saveDogListInList();
        List<List<Item_j>> catList = saveCatListInList();

        abandonedAnimalMapper.deleteAllAnimalFromTable();

        for (List<Item_j> item : dogList) {
            abandonedAnimalMapper.insertAnimalList(item);
        }

        for (List<Item_j> item : catList) {
            abandonedAnimalMapper.insertAnimalList(item);
        }
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        log.info("Formatted Date: " + formattedDate);
        return formattedDate;
    }

    private List<Item_j> getExpiredAnimalList() {
        String formattedDate = getCurrentDate();

        List<Item_j> expiredAnimalList = abandonedAnimalMapper.getExpiredAnimalList(formattedDate);
        return expiredAnimalList;
    }

    private void insertExpiredAnimalListIntoExpiredTable() {
        List<Item_j> expiredAnimalList = getExpiredAnimalList();

        if (expiredAnimalList.size() > 0) {
            abandonedAnimalMapper.insertExpiredAnimalList(expiredAnimalList);
        } else {
            log.info("공고 기간(noticeEdt)이 지난 유기동물이 없습니다.");
        }
    }

    private void deleteExpiredAnimalFromAbandonedAnimalListTbl() {
        String currentDate = getCurrentDate();
        abandonedAnimalMapper.deleteExpiredAnimalFromAbandonedAnimalListTbl(currentDate);
    }

    public void doJob() {
        insertExpiredAnimalListIntoExpiredTable();
        saveAbandonedAnimalListIntoDatabase();
        deleteExpiredAnimalFromAbandonedAnimalListTbl();
    }

    public void test1() {
        List<TestDto1_j> list = new ArrayList<>();
        list.add(new TestDto1_j(1, "jae1", 27, "jae1@gmail.com"));
        list.add(new TestDto1_j(4, "jae4", 44, "jae4@gmail.com"));

        abandonedAnimalMapper.test(list);
    }

    public List<AnimalListDto_j> getAllAnimalWithPagination(int startRowNum) {
        return abandonedAnimalMapper.getAllAnimalWithPagination(startRowNum);
    }

    public int getTotalCount() {
        return abandonedAnimalMapper.getTotalCount();
    }


    /* 유기동물을 sido, sigungu로 필터링해서 갖고 오기 */
    public int getCountFilteredBySidoSigungu(Map<String, Object> map) {
        return abandonedAnimalMapper.getCountFilteredBySidoSigungu(map);
    }

    public List<AnimalListDto_j> getAnimalFilteredBySidoSigungu(Map<String, Object> map) {
        return abandonedAnimalMapper.getAnimalFilteredBySidoSigungu(map);
    }

    public List<AnimalListDto_j> getAllListFilteredBySidoSigungu(Map<String, Object> map) {
        return abandonedAnimalMapper.getAllListFilteredBySidoSigungu(map);
    }
}
