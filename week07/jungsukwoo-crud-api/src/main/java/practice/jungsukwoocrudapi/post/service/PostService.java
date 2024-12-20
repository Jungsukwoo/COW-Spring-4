package practice.jungsukwoocrudapi.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.jungsukwoocrudapi.member.entity.Member;
import practice.jungsukwoocrudapi.member.repository.MemberRepository;
import practice.jungsukwoocrudapi.post.controller.dto.request.CreatePostRequest;
import practice.jungsukwoocrudapi.post.controller.dto.request.UpdatePostRequest;
import practice.jungsukwoocrudapi.post.controller.dto.response.PostResponse;
import practice.jungsukwoocrudapi.post.entity.Post;
import practice.jungsukwoocrudapi.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public void postUp(CreatePostRequest createPostRequest) {
        Member member = memberRepository.findById(createPostRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Member를 찾을 수 없습니다."));
        Post post = createPostRequest.toEntity(member);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(final Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post를 찾을 수 없습니다."));
        return PostResponse.from(post);
    }

    public void updatePostById(final Long id, UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post를 찾을 수 없습니다."));
        post.updatePost(updatePostRequest.getTitle(), updatePostRequest.getContent());
        postRepository.save(post);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
