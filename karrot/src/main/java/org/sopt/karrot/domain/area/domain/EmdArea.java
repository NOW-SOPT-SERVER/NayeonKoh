package org.sopt.karrot.domain.area.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.karrot.domain.area.dto.BeopjeongdongDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmdArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "adm_code")
    private Long code;

    @Column
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sigungu_area_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private SigunguArea sigunguArea;

    @Builder
    private EmdArea(final Long code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static EmdArea from(final BeopjeongdongDto beopjeongdongDto) {
        return EmdArea.builder()
                .code(beopjeongdongDto.code())
                .name(beopjeongdongDto.emdName())
                .build();
    }
}
