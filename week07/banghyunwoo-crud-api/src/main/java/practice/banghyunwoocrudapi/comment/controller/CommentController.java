package practice.banghyunwoocrudapi.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.banghyunwoocrudapi.comment.controller.dto.request.CreateCommentRequest;
import practice.banghyunwoocrudapi.comment.controller.dto.request.UpdateCommentRequest;
import practice.banghyunwoocrudapi.comment.controller.dto.response.CommentResponse;
import practice.banghyunwoocrudapi.comment.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public void create(@PathVariable Long postId,
                       @RequestBody CreateCommentRequest createCommentRequest){
        commentService.create(postId,createCommentRequest);
    }

    @GetMapping("/comments/{postId}")
    public List<CommentResponse> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }

    @GetMapping("/users/{userId}/comments")
    public List<CommentResponse> getByUserId(@PathVariable Long userId){
        return commentService.getByUserId(userId);
    }

    @PutMapping("/comments/{id}")
    public void getComment(@PathVariable Long id,
                           @RequestBody UpdateCommentRequest updateCommentRequest){
        commentService.update(id, updateCommentRequest);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.delete(id);
    }
}
