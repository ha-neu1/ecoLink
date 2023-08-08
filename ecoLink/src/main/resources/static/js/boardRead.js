document.addEventListener('DOMContentLoaded', () => {
    const commentForm = document.getElementById('boardComment');
    commentForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        
        const formData = new FormData(commentForm);
        const boardId = formData.get('boardId');
        const newComment = formData.get('comment');
        
        if (newComment.trim() === '') {
            alert('댓글 내용을 입력해주세요.');
            return;
        }
        
        // 서버로 댓글 등록 요청 및 처리
        const response = await fetch(`/createComment/${boardId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ bcContents: newComment }),
        });
        
        if (response.ok) {
            // 댓글 등록 성공 시 화면 갱신
            location.reload();
        } else {
            alert('댓글 등록에 실패하였습니다.');
        }
    });

    const replyForms = document.querySelectorAll('#replyComment');
    replyForms.forEach((form) => {
        form.addEventListener('submit', async (event) => {
            event.preventDefault();
            
            const formData = new FormData(form);
            const boardId = formData.get('boardId');
            const bcRef = formData.get('bcRef');
            const newReply = formData.get('reply');
            
            if (newReply.trim() === '') {
                alert('답글 내용을 입력해주세요.');
                return;
            }
            
            // 서버로 답글 등록 요청 및 처리
            const response = await fetch(`/createReply/${boardId}/${bcRef}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ bcContents: newReply }),
            });
            
            if (response.ok) {
                // 답글 등록 성공 시 화면 갱신
                location.reload();
            } else {
                alert('답글 등록에 실패하였습니다.');
            }
        });
    });

    // 답글 더 보기 및 접기 로직 추가
    function onDisplay(bcRef) {
        const replyWraps = document.querySelectorAll(`.replyWrap[data-bcRef="${bcRef}"]`);
        replyWraps.forEach((replyWrap) => {
            replyWrap.style.display = 'block';
        });

        const showButton = document.querySelector(`button[data-show="${bcRef}"]`);
        const hideButton = document.querySelector(`button[data-hide="${bcRef}"]`);
        showButton.style.display = 'none';
        hideButton.style.display = 'block';
    }

    function offDisplay(bcRef) {
        const replyWraps = document.querySelectorAll(`.replyWrap[data-bcRef="${bcRef}"]`);
        replyWraps.forEach((replyWrap) => {
            replyWrap.style.display = 'none';
        });

        const showButton = document.querySelector(`button[data-show="${bcRef}"]`);
        const hideButton = document.querySelector(`button[data-hide="${bcRef}"]`);
        showButton.style.display = 'block';
        hideButton.style.display = 'none';
    }

    const showButtons = document.querySelectorAll('[data-show]');
    showButtons.forEach((showButton) => {
        showButton.addEventListener('click', () => {
            const bcRef = showButton.getAttribute('data-show');
            onDisplay(bcRef);
        });
    });

    const hideButtons = document.querySelectorAll('[data-hide]');
    hideButtons.forEach((hideButton) => {
        hideButton.addEventListener('click', () => {
            const bcRef = hideButton.getAttribute('data-hide');
            offDisplay(bcRef);
        });
    });
});
