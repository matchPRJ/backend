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


}
