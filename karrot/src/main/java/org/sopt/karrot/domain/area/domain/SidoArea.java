package org.sopt.karrot.domain.area.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class SidoArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "adm_code")
    private Long code;

    @Column(nullable = false)
    private String name;

//    @OneToOne(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private SigunguArea sigunguArea;

    @Builder
    private SidoArea(final Long code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static SidoArea from(final BeopjeongdongDto beopjeongdongDto) {
        return SidoArea.builder()
                .code(beopjeongdongDto.code())
                .name(beopjeongdongDto.sidoName())
                .build();
    }
}
