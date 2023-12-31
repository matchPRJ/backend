package com.team.match.repository.search;

import com.team.match.entity.QReply;
import com.team.match.entity.QBoard;
import com.team.match.entity.QUser;
import com.team.match.entity.Board;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1.............");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QUser user = QUser.user;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(user).on(board.buno.eq(user));
        jpqlQuery.leftJoin(reply).on(reply.rbno.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, user.uno, reply.count());

        tuple.groupBy(board);

        log.info("-----------------------------------");
        log.info(tuple);
        log.info("-----------------------------------");

        List<Tuple> result = tuple.fetch();
        log.info(result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, Pageable pageable) {
        log.info("searchPage.........................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QUser user = QUser.user;


        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(user).on(board.buno.eq(user));
        jpqlQuery.leftJoin(reply).on(reply.rbno.eq(board));


        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, user, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        // 글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(expression)
                .and(board.bdelete.ne(false));

        // 댓글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(
                reply.rdelete.ne(false).or(reply.isNull())
        );

        booleanBuilder.and(expression);

        if (type != null) {
            String[] conditions = type.split(":");
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            String field = conditions[0];
            String value = conditions[1];
                switch (field) {
                        case "n":
                            conditionBuilder.or(user.nickname.containsIgnoreCase(value));
                            break;
                        case "t":
                            conditionBuilder.or(board.btitle.containsIgnoreCase(value));
                            break;
                }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier<>(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(board, user);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();
        log.info("COUNT: " + count);

        return new PageImpl<>(
                result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, count
        );
    }


}
