function deleteComment(button) {
    // 선택한 댓글의 ID 가져오기
    var commentId = button.getAttribute("data-commentId");

    // AJAX 요청을 사용하여 댓글 삭제
    // 여기에서는 jQuery를 사용한 예시
    $.ajax({
        type: "POST",  // 또는 "DELETE" 메서드를 사용할 수 있습니다.
        url: "/comments/delete/" + commentId,
        success: function (data) {
            // 삭제 성공한 경우 댓글 목록 업데이트 또는 리다이렉트 처리
        },
        error: function () {
            // 삭제 실패한 경우 오류 처리
        }
    });
}
