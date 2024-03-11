package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.entity.BoardContent;
import com.example.iknowboardserver.domain.board.service.BoardService;
import com.example.iknowboardserver.util.responseBody.DTOResponseBody;
import com.example.iknowboardserver.util.responseBody.MessageResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Tag(name = "게시글", description = "게시글 API")
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글 작성 요청", required = true, content = @Content(schema = @Schema(ref = "CreateBoardRequest")))
    @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content = @Content(schema = @Schema(ref = "CreateBoardResponse"), mediaType = "application/json"))
    @PostMapping
    public ResponseEntity<DTOResponseBody<BoardDTO>> createBoard(@RequestBody BoardDTO request) {
        Board board = boardService.createBoard(request);

        return ResponseEntity.ok(new DTOResponseBody<>(BoardDTO.from(board), "success"));
    }
    @GetMapping("/{id}")
    @Operation(summary = "게시글 조회", description = "게시글을 조회합니다.")
    @Parameter(name = "id", description = "게시글 id", required = true, explode = Explode.TRUE, in = ParameterIn.PATH, schema = @Schema(type = "long"))
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(ref = "GetBoardResponse"), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "게시글 조회 실패", content = @Content(schema = @Schema(ref = "INVALID_BOARD_RESPONSE"), mediaType = "application/json"))
    public ResponseEntity<DTOResponseBody<BoardDTO>> getBoard(@PathVariable Long id) {
        Board board = boardService.getBoard(id);
        BoardContent boardContent = boardService.getBoardContent(board.getContentId());
        BoardDTO response = BoardDTO.from(board);
        response.setBoardContent(BoardContentDTO.from(boardContent));
        return ResponseEntity.ok(new DTOResponseBody<>(response, "success"));
    }
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @Parameter(name = "id", description = "게시글 id", required = true, explode = Explode.TRUE, in = ParameterIn.PATH, schema = @Schema(type = "long"))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글 수정 요청", required = true, content = @Content(schema = @Schema(ref = "CreateBoardRequest")))
    @ApiResponse(responseCode = "200", description = "게시글 수정 성공", content = @Content(schema = @Schema(ref = "CreateBoardResponse"), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "게시글 수정 실패", content = @Content(schema = @Schema(ref = "INVALID_BOARD_RESPONSE"), mediaType = "application/json"))
    @PutMapping("/{id}")
    public ResponseEntity<DTOResponseBody<BoardDTO>> updateBoard(@PathVariable Long id, @RequestBody BoardDTO request) {
        Board board = boardService.updateBoard(id, request);
        return ResponseEntity.ok(new DTOResponseBody<>(BoardDTO.from(board), "success"));
    }
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @Parameter(name = "id", description = "게시글 id", required = true, explode = Explode.TRUE, in = ParameterIn.PATH, schema = @Schema(type = "long"))
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", content = @Content(schema = @Schema(ref = "MessageResponseBody"), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "게시글 수정 실패", content = @Content(schema = @Schema(ref = "INVALID_BOARD_RESPONSE"), mediaType = "application/json"))
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseBody> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(new MessageResponseBody("게시글이 삭제되었습니다.", "success"));
    }
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공", content = @Content(schema = @Schema(ref = "GetBoardListResponse"), mediaType = "application/json"))
    @GetMapping("/list")
    public ResponseEntity<DTOResponseBody<List<BoardDTO>>> getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        return ResponseEntity.ok(new DTOResponseBody<>(BoardDTO.from(boardList), "success"));
    }
}
