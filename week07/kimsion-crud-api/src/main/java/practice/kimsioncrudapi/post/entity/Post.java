package practice.kimsioncrudapi.post.entity;

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
import practice.kimsioncrudapi.member.entity.Member;
import practice.kimsioncrudapi.post.controller.dto.request.CreatePostRequest;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  @Id
  @Column(name = "post_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn (name = "member_id")
  private Member member;

  @Builder
  private Post(String title, String content, Member member) {
    this.title = title;
    this.content = content;
    this.member = member;
  }

  public void update(CreatePostRequest createPostRequest) {
    this.title = createPostRequest.getTitle();
    this.content = createPostRequest.getContent();
  }

}
