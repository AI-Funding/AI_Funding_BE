package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;

@Entity // JPA가 사용하도록 Entity라는 어노테이션을 붙입니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만듭니다.
@AllArgsConstructor // 파라미터가 모두 있는 생성자를 만듭니다.
@Builder // 빌더 패턴을 사용합니다.
@ToString // ToString을 오버라이딩합니다.
@Getter // Getter를 사용합니다.
@Table(name = "COMMENT")  // comment 테이블에 매핑을 합니다.
public class CommentEntity extends BaseCreateModifiedEntity{

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String content;

    @Column(name = "comment_like")
    private Integer commentLike;

    @Column(name = "parent_id")
    private Long parent;

    @ManyToOne
    @JoinColumn(name = "customer_info_id")
    private CustomerInformationEntity customer;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
