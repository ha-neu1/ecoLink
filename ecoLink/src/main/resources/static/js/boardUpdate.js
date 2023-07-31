$(document).ready(function() {
    $("#updateForm").on("submit", function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        updateBoard(formData);
    });
});

function updateBoard(formData) {
    $.ajax({
        url: "/updateBoard",
        type: "POST",
        data: formData,
        dataType: "json",
        success: function(response) {
            if (response.success) {
                alert(response.message);
                window.location.href = "/boardRead?boardId=" + response.boardId;
            } else {
                alert(response.message);
            }
        },
        error: function(xhr, status, error) {
            alert("오류가 발생했습니다. 다시 시도해주세요.");
            console.log(xhr.responseText);
        }
    });
}
