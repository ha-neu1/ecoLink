document.addEventListener('DOMContentLoaded', function() {
  var addButton = document.getElementById('addButton');
  addButton.addEventListener('click', function() {
    var boardMain = document.querySelector('.boardMain');

    // 새로운 게시물을 생성하는 요소들을 생성
    var newBoardList = document.createElement('div');
    newBoardList.classList.add('boardList');

    var newMemId = document.createElement('h3');
    newMemId.classList.add('memId');
    newMemId.textContent = '새로운 작성자 이름';

    var newBoardTitle = document.createElement('h4');
    newBoardTitle.classList.add('boardTitle');
    newBoardTitle.textContent = '새로운 게시물 제목';

    var newLikeButton = document.createElement('span');
    newLikeButton.classList.add('likeButton');
    newLikeButton.addEventListener('click', function() {
      if (newLikeButton.classList.contains('liked')) {
        unlikePost(postId, function() {
          newLikeButton.classList.remove('liked');
        });
      } else {
        likePost(postId, function() {
          newLikeButton.classList.add('liked');
        });
      }
    });

    var newBoardImage = document.createElement('img');
    newBoardImage.classList.add('boardImage');
    newBoardImage.setAttribute('src', '/images/logo2.png');
    newBoardImage.setAttribute('alt', '게시물 이미지');

    var newBoardCont = document.createElement('p');
    newBoardCont.classList.add('boardCont');
    newBoardCont.textContent = '새로운 게시물 내용입니다. 새로운 게시물 내용입니다.';

    // 생성한 요소들을 새로운 게시물 요소에 추가
    newBoardList.appendChild(newMemId);
    newBoardList.appendChild(newBoardTitle);
    newBoardList.appendChild(newLikeButton);
    newBoardList.appendChild(newBoardImage);
    newBoardList.appendChild(newBoardCont);

    // 새로운 게시물 요소를 게시판 메인에 추가
    boardMain.appendChild(newBoardList);
  });
});

document.addEventListener('DOMContentLoaded', function() {
  const prevLink = document.querySelector('.prev');
  const nextLink = document.querySelector('.next');
  const pageLinks = document.querySelectorAll('.page');

  function goToPage(e) {
    e.preventDefault();
    const selectedPage = this.innerText;
    // TODO: 선택된 페이지로 이동하는 로직
    console.log('Go to page:', selectedPage);
  }

  prevLink.addEventListener('click', goToPage);
  nextLink.addEventListener('click', goToPage);
  pageLinks.forEach(function(link) {
    link.addEventListener('click', goToPage);
  });
});