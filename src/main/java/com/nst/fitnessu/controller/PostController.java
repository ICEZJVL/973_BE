package com.nst.fitnessu.controller;

import com.nst.fitnessu.domain.Post;
import com.nst.fitnessu.domain.Type;
import com.nst.fitnessu.dto.ResultResponse;
import com.nst.fitnessu.dto.post.*;
import com.nst.fitnessu.dto.user.LoginResponseDto;
import com.nst.fitnessu.service.PostService;
import com.nst.fitnessu.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/coach")
    @ApiOperation(value = "코치 글쓰기")
    public ResponseEntity<ResultResponse> createCoachPost(@RequestBody @ApiParam CreatePostRequestDto requestDto) {
        ViewPostResponseDto responseDto=postService.createPost(requestDto,Type.coach);
        ResultResponse<ViewPostResponseDto> resultResponse=new ResultResponse<>();
        resultResponse.successResponse("게시글 조회",responseDto);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @PostMapping("/player")
    @ApiOperation(value = "플레이어 글쓰기")
    public ResponseEntity<ResultResponse> createPlayerPost(@RequestBody @ApiParam CreatePostRequestDto requestDto) {
        ViewPostResponseDto responseDto=postService.createPost(requestDto,Type.player);
        ResultResponse<ViewPostResponseDto> resultResponse=new ResultResponse<>();
        resultResponse.successResponse("게시글 조회",responseDto);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @GetMapping("/coach/{page}")
    @ApiOperation(value = "코치 게시글 목록")
    public ResponseEntity<ResultResponse> viewCoachPostList(@PathVariable @ApiParam Integer page) {
        List<PostListResponseDto> pageList=postService.viewList(Type.coach,page,10);
        ResultResponse<List<PostListResponseDto>> resultResponse=new ResultResponse<>();
        resultResponse.successResponse("코치 글목록 조회",pageList);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @GetMapping("/player/{page}")
    @ApiOperation(value = "플레이어 게시글 목록")
    public ResponseEntity<ResultResponse> viewPlayerPostList(@PathVariable @ApiParam Integer page) {
        List<PostListResponseDto> pageList=postService.viewList(Type.player,page,10);
        ResultResponse<List<PostListResponseDto>> resultResponse=new ResultResponse<>();
        resultResponse.successResponse("플레이어 글목록 조회",pageList);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "게시글 조회")
    public ResponseEntity<ResultResponse> viewPostList(@PathVariable @ApiParam Long id) {
        //임시
        ViewPostRequestDto requestDto = new ViewPostRequestDto(id);
        ViewPostResponseDto responseDto=postService.findPost(requestDto);
        ResultResponse<ViewPostResponseDto> resultResponse=new ResultResponse<>();
        resultResponse.successResponse("게시글 조회",responseDto);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @PutMapping()
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<ResultResponse> updatePostList(@RequestBody @ApiParam UpdatePostRequestDto requestDto) {
        //임시
        UpdatePostResponseDto responseDto=postService.updatePost(requestDto);
        ResultResponse<UpdatePostResponseDto> resultResponse=new ResultResponse<>();
        resultResponse.successResponse("게시글 수정",responseDto);
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<ResultResponse> deletePostList(@PathVariable @ApiParam Long id) {
        postService.deletePost(id);
        ResultResponse resultResponse=new ResultResponse(200,"게시글 삭제");
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }
//    @GetMapping("/view")
//    @ApiOperation(value="게시물 조회")

}
