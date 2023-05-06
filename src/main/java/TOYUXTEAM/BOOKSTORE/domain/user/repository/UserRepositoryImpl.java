package TOYUXTEAM.BOOKSTORE.domain.user.repository;

import TOYUXTEAM.BOOKSTORE.domain.user.dto.request.UserSearchCond;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.response.QUserCountResponse;
import TOYUXTEAM.BOOKSTORE.domain.user.dto.response.UserCountResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.Objects;

import static TOYUXTEAM.BOOKSTORE.domain.user.model.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserCountResponse findByDate(UserSearchCond cond) {
        Tuple counts = queryFactory
                .select(user.bookReviews.size(), user.diaries.size())
                .from(user)
                .where(
                        userIdEq(cond.getUserId()))
                .fetchOne();

        if (counts == null) {
            return new UserCountResponse(cond.getUserId(), 0);
        } else {
            Integer reviewCount = counts.get(user.bookReviews.size());
            Integer diaryCount = counts.get(user.diaries.size());

            Integer result = (reviewCount != null ? reviewCount : 0) + (diaryCount != null ? diaryCount : 0);

            return new UserCountResponse(cond.getUserId(), result);
        }
    }

    private BooleanExpression userIdEq(Long userId) {
        return Objects.isNull(userId) ? null : user.user_id.eq(userId);
    }
}
