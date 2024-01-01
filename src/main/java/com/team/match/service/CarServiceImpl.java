package com.team.match.service;

import com.team.match.repository.CarRepository;
import com.team.match.dto.PageRequestDTO;
import com.team.match.dto.PageResultDTO;
import com.team.match.dto.CarDTO;
import com.team.match.entity.Car;
import com.team.match.entity.QCar;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository repository;

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QCar qCar = QCar.car;

        String nType = requestDTO.getNType();
        String tType = requestDTO.getTType();
        String bType = requestDTO.getBType();
        String oilType = requestDTO.getOilType();
        String pType = requestDTO.getPType();
        String kType = requestDTO.getKType();
        String yType = requestDTO.getYType();

        BooleanExpression expression1 = qCar.cno.gt(0);
        booleanBuilder.and(expression1);

        // 검색 조건 타입
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(nType != null) {
            String[] conditions = nType.split(":");
            String value = conditions[1];
            conditionBuilder.and(qCar.name.contains(value));
        }
        if(tType != null) {
            String[] conditions = tType.split(":");
            String value = conditions[1];
            conditionBuilder.and(qCar.tag.contains(value));
        }
        if(bType != null) {
            String[] conditions = bType.split(":");
            String value = conditions[1];
            conditionBuilder.and(qCar.brand.contains(value));
        }
        if(oilType != null) {
            String[] conditions = oilType.split(":");
            String value = conditions[1];
            conditionBuilder.and(qCar.oil.contains(value));
        }

        // Ex. pType: 최소, 20만원
        if(pType != null) {
            String[] price1 = pType.split(":");
            String[] price2 = price1[1].split(",");
            if("최소".equals(price2[0])) {
                int keyword1_1=0;
                int keyword2_1 = Integer.parseInt(price2[1].replace("만원", "0000"));
                conditionBuilder.and(qCar.pricer.between(keyword1_1, keyword2_1));
            }
            else if ("최대".equals(price2[1])) {
                int keyword1_1 = Integer.parseInt(price2[0].replace("만원", "0000"));
                int keyword2_1 = 100000000;
                conditionBuilder.and(qCar.pricer.between(keyword1_1, keyword2_1));
            }
            else {
                int keyword1_1 = Integer.parseInt(price2[0].replace("만원", "0000"));
                int keyword2_1 = Integer.parseInt(price2[1].replace("만원", "0000"));
                conditionBuilder.and(qCar.pricer.between(keyword1_1, keyword2_1));
            }
        }

        if(kType != null) {
            String[] km1 = kType.split(":");
            String[] km2 = km1[1].split(",");
            if("최소".equals(km2[0])) {
                int keyword1_1=0;
                int keyword2_1 = Integer.parseInt(km2[1].replace("km", ""));
                conditionBuilder.and(qCar.kmr.between(keyword1_1, keyword2_1));
            }
            else if ("최대".equals(km2[1])) {
                int keyword1_1 = Integer.parseInt(km2[0].replace("km", ""));
                int keyword2_1 = 2000000;
                conditionBuilder.and(qCar.kmr.between(keyword1_1, keyword2_1));
            }
            else {
                int keyword1_1 = Integer.parseInt(km2[0].replace("km", ""));
                int keyword2_1 = Integer.parseInt(km2[1].replace("km", ""));
                conditionBuilder.and(qCar.kmr.between(keyword1_1, keyword2_1));
            }
        }

        if(yType != null) {
            String[] year1 = yType.split(":");
            String[] year2 = year1[1].split(",");
            if("최소".equals(year2[0])) {
                int keyword1_1=1989;
                int keyword2_1 = Integer.parseInt(year2[1].replace("년", ""));
                conditionBuilder.and(qCar.year.between(keyword1_1, keyword2_1));
            }
            else if ("최대".equals(year2[1])) {
                int keyword1_1 = Integer.parseInt(year2[0].replace("년", ""));
                int keyword2_1 = 2024;
                conditionBuilder.and(qCar.year.between(keyword1_1, keyword2_1));
            }
            else {
                int keyword1_1 = Integer.parseInt(year2[0].replace("년", ""));
                int keyword2_1 = Integer.parseInt(year2[1].replace("년", ""));
                conditionBuilder.and(qCar.year.between(keyword1_1, keyword2_1));
            }
        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    // 랜덤으로 나오게끔 패치 해야함
    @Override
    public PageResultDTO<CarDTO, Car> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("cno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);  // 검색 조건 처리
        Page<Car> result = repository.findAll(booleanBuilder, pageable);  // Querydsl 사용
        Function<Car, CarDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }
}
