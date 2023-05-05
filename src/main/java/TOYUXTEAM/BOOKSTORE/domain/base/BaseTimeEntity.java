package TOYUXTEAM.BOOKSTORE.domain.base;

import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 생성일, 수정일
public class BaseTimeEntity {

    @CreatedDate
    @Column(name = "create_date")
    private LocalDate createdDate;

    /* - 기획상 수정시간일자가 사용되지 않을거라 판단하여 보류
       - 추후 스냅샷 기능과 엮어서 리팩토링 계획
    @LastModifiedBy
    @Column(name = "update_date")
    private LocalDate updateDate;
     */

}