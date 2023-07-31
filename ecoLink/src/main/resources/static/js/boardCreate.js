document.addEventListener("DOMContentLoaded", function () {
    const createBoardForm = document.getElementById("createBoardForm");
    const previewImageElement = document.getElementById("preview");
    const imageInput = document.getElementById("boardImage");

    createBoardForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(createBoardForm);

        fetch("/createBoard", {
            method: "POST",
            body: formData,
        })
        .then(response => response.json())
        .then(data => {
            alert(data.message);

            if (data.success) {
                window.location.href = "board";
            }
        })
        .catch(error => console.error("Error:", error));
    });

    function displayPreviewImage() {
        const file = imageInput.files[0];
        const reader = new FileReader();

        reader.onloadend = function () {
            previewImageElement.src = reader.result;
        };

        if (file) {
            reader.readAsDataURL(file);
        } else {
            // 이미지를 선택하지 않았을 경우 기본 이미지 표시
            previewImageElement.src = "/images/logo2.png";
        }
    }

    // 이미지 첨부 시 미리보기 기능 적용
    imageInput.addEventListener("change", displayPreviewImage);
});
