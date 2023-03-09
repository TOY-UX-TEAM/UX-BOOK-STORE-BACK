package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.QDiaryDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static TOYUXTEAM.BOOKSTORE.domain.diary.model.QDiary.*;
import static TOYUXTEAM.BOOKSTORE.domain.user.model.QUser.*;

public class DiaryRepositoryImpl implements DiaryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DiaryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public DiaryDto findByDiaryId(Long diaryId) {
        return queryFactory
                .select(new QDiaryDto(
                        diary.id,
                        diary.title,
                        diary.content,
                        diary.user.user_id,
                        diary.createdDate))
                .from(diary)
                .where(diary.id.eq(diaryId))
                .fetchOne();
    }

    @Override
    public Page<DiaryDto> findByIdDiaries(DiarySearchCond cond, Pageable pageable) {
        List<DiaryDto> result = queryFactory
                .select(new QDiaryDto(
                        diary.id,
                        diary.title,
                        diary.content,
                        diary.user.user_id.as("userId"),
                        diary.createdDate.as("createdDate")))
                .from(diary)
                .leftJoin(diary.user, user)
                .where(
                        userIdEq(cond.getUserId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(diary.count())
                .from(diary)
                .leftJoin(diary.user, user)
                .where(
                        userIdEq(cond.getUserId()),
                        createDateEq(cond.getLocalDate()));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<DiaryDto> findByIdDiaryForDate(DiarySearchCond cond, Pageable pageable) {
        List<DiaryDto> result = queryFactory
                .select(new QDiaryDto(
                        diary.id,
                        diary.title,
                        diary.content,
                        diary.user.user_id.as("userId"),
                        diary.createdDate.as("createdDate")))
                .from(diary)
                .leftJoin(diary.user, user)
                .where(
                        userIdEq(cond.getUserId()),
                        createDateEq(cond.getLocalDate()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(diary.count())
                .from(diary)
                .leftJoin(diary.user, user)
                .where(
                        userIdEq(cond.getUserId()),
                        createDateEq(cond.getLocalDate()));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    private BooleanExpression userIdEq(Long userId) {
        return Objects.isNull(userId) ? null : diary.user.user_id.eq(userId);
    }

    private BooleanExpression createDateEq(LocalDate localDate) {
        return Objects.isNull(localDate) ? null : diary.createdDate.eq(localDate);
    }
}
