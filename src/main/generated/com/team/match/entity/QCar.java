package com.team.match.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCar is a Querydsl query type for Car
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCar extends EntityPathBase<Car> {

    private static final long serialVersionUID = -1844339274L;

    public static final QCar car = new QCar("car");

    public final StringPath brand = createString("brand");

    public final NumberPath<Long> cno = createNumber("cno", Long.class);

    public final StringPath img = createString("img");

    public final StringPath km = createString("km");

    public final NumberPath<Integer> kmr = createNumber("kmr", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath oil = createString("oil");

    public final StringPath price = createString("price");

    public final NumberPath<Integer> pricer = createNumber("pricer", Integer.class);

    public final StringPath tag = createString("tag");

    public final StringPath type = createString("type");

    public final NumberPath<Short> year = createNumber("year", Short.class);

    public QCar(String variable) {
        super(Car.class, forVariable(variable));
    }

    public QCar(Path<? extends Car> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCar(PathMetadata metadata) {
        super(Car.class, metadata);
    }

}

