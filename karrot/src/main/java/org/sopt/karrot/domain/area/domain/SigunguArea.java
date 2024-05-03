package org.sopt.karrot.domain.area.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
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
public class SigunguArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "adm_code")
    private Long code;

    @Column(nullable = false)
    private String name;

//    @OneToMany(mappedBy = "sigunguArea")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<EmdArea> emdAreas;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sido_area_id", nullable = false)
//    private SidoArea sidoArea;

    @Builder
    private SigunguArea(final Long code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static SigunguArea from(final BeopjeongdongDto beopjeongdongDto) {
        return SigunguArea.builder()
                .code(beopjeongdongDto.code())
                .name(beopjeongdongDto.sigunguName())
                .build();
    }
}
