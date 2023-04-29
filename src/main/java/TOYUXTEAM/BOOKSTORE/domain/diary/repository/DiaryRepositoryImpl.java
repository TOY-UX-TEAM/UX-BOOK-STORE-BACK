package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.QDiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.QDiaryWithFileResponse;
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

import static TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.QDiary.*;
import static TOYUXTEAM.BOOKSTORE.domain.user.model.QUser.*;

public class DiaryRepositoryImpl implements DiaryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DiaryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<DiaryWithFileResponse> findByIdDiaries(DiarySearchCond cond, Pageable pageable) {
        List<DiaryWithFileResponse> result = queryFactory
                .select(new QDiaryWithFileResponse(
                        diary.id,
                        diary.title,
                        diary.content,
                        diary.user.user_id.as("userId"),
                        diary.createdDate.as("createdDate"),
                        diary.diaryContent.name))
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
    public Page<DiaryWithFileResponse> findByIdDiaryForDate(DiarySearchCond cond, Pageable pageable) {
        List<DiaryWithFileResponse> result = queryFactory
                .select(new QDiaryWithFileResponse(
                        diary.id,
                        diary.title,
                        diary.content,
                        diary.user.user_id.as("userId"),
                        diary.createdDate.as("createdDate"),
                        diary.diaryContent.name))
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
