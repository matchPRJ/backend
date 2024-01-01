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
        String brandType = requestDTO.getBrandType();
        String oilType = requestDTO.getOilType();
        String pType = requestDTO.getPType();
        String kType = requestDTO.getKType();
        String yType = requestDTO.getYType();

        BooleanExpression expression1 = qCar.cno.gt(0);
        booleanBuilder.and(expression1);

        // 검색 조건 타입
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(nType != null) {
            conditionBuilder.and(qCar.name.contains(nType));
        }
        if(tType != null) {
            conditionBuilder.and(qCar.tag.contains(tType));
        }
        if(brandType != null) {
            conditionBuilder.and(qCar.brand.contains(brandType));
        }
        if(oilType != null) {
            conditionBuilder.and(qCar.oil.contains(oilType));
        }

        // Ex. pType: 최소, 20만원
        if(pType != null) {
            String[] price = pType.split(",");
            if("최소".equals(price[0])) {
                int keyword1_1=0;
                int keyword2_1 = Integer.parseInt(price[1].replace("만원", "0000"));
                conditionBuilder.and(qCar.pricer.between(keyword1_1, keyword2_1));
            }
            else if ("최대".equals(price[1])) {
                int keyword1_1 = Integer.parseInt(price[0].replace("만원", "0000"));
                int keyword2_1 = 100000000;
                conditionBuilder.and(qCar.pricer.between(keyword1_1, keyword2_1));
            }
            else {
                int keyword1_1 = Integer.parseInt(price[0].replace("만원", "0000"));
                int keyword2_1 = Integer.parseInt(price[1].replace("만원", "0000"));
                conditionBuilder.and(qCar.pricer.between(keyword1_1, keyword2_1));
            }
        }

        if(kType != null) {
            String[] km = kType.split(",");
            if("최소".equals(km[0])) {
                int keyword1_1=0;
                int keyword2_1 = Integer.parseInt(km[1].replace("km", ""));
                conditionBuilder.and(qCar.kmr.between(keyword1_1, keyword2_1));
            }
            else if ("최대".equals(km[1])) {
                int keyword1_1 = Integer.parseInt(km[0].replace("km", ""));
                int keyword2_1 = 2000000;
                conditionBuilder.and(qCar.kmr.between(keyword1_1, keyword2_1));
            }
            else {
                int keyword1_1 = Integer.parseInt(km[0].replace("km", ""));
                int keyword2_1 = Integer.parseInt(km[1].replace("km", ""));
                conditionBuilder.and(qCar.kmr.between(keyword1_1, keyword2_1));
            }
        }

        if(yType != null) {
            String[] year = yType.split(",");
            if("최소".equals(year[0])) {
                int keyword1_1=1989;
                int keyword2_1 = Integer.parseInt(year[1].replace("년", ""));
                conditionBuilder.and(qCar.year.between(keyword1_1, keyword2_1));
            }
            else if ("최대".equals(year[1])) {
                int keyword1_1 = Integer.parseInt(year[0].replace("년", ""));
                int keyword2_1 = 2024;
                conditionBuilder.and(qCar.year.between(keyword1_1, keyword2_1));
            }
            else {
                int keyword1_1 = Integer.parseInt(year[0].replace("년", ""));
                int keyword2_1 = Integer.parseInt(year[1].replace("년", ""));
                conditionBuilder.and(qCar.year.between(keyword1_1, keyword2_1));
            }
        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    @Override
    public PageResultDTO<CarDTO, Car> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("cno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);  // 검색 조건 처리
        Page<Car> result = repository.findAll(booleanBuilder, pageable);  // Querydsl 사용
        Function<Car, CarDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }
}
