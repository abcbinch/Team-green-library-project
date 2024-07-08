let putInBtn = document.querySelector('.putinBtn');
let navBarList = document.querySelector('.navBar');
let navArrow = document.querySelector('.putinBtn img');

let rightArrow = "/images/circleArrowGreenRight.png";
let leftArrow = "/images/circleArrowGreenLeft.png";

// 버튼 여는 함수
function Openup() {
    navBarList.classList.add('putinLeft');
    putInBtn.classList.add('btnInLeft');
    navArrow.src = rightArrow;

    updateLocalStorage(false);
}

// 버튼 닫는 함수
function Close() {
    navBarList.classList.remove('putinLeft');
    navBarList.style.transition = "left 300ms ease-in-out";
    putInBtn.classList.remove('btnInLeft');
    putInBtn.style.transition = "left 300ms ease-in-out";
    navArrow.src = leftArrow;

    updateLocalStorage(true);
}

// 로컬 스토리지를 업데이트하는 함수
function updateLocalStorage(isClosed) {
    let navArrayed = Array.from(navBarList.classList);
    let btnArrayed = Array.from(putInBtn.classList);
    localStorage.setItem("navClass", JSON.stringify(navArrayed));
    localStorage.setItem("btnClass", JSON.stringify(btnArrayed));
    localStorage.setItem("isClosed", isClosed);
}

// 버튼 클릭 이벤트 핸들러
putInBtn.onclick = () => {
    if (!navBarList.classList.contains('putinLeft')) {
        Openup();
    } else {
        Close();
    }
}

// 페이지 로드 시 로컬 스토리지에서 상태를 복원하는 함수
document.addEventListener("DOMContentLoaded", () => {
    let storedNavClass = localStorage.getItem("navClass");
    let storedBtnClass = localStorage.getItem("btnClass");
    let isClosed = localStorage.getItem("isClosed") === 'true';

    if (storedNavClass && storedBtnClass) {
        let navArrayed = JSON.parse(storedNavClass);
        let btnArrayed = JSON.parse(storedBtnClass);

        // navBarList에 클래스 적용
        navArrayed.forEach(cls => navBarList.classList.add(cls));

        // putInBtn에 클래스 적용
        btnArrayed.forEach(cls => putInBtn.classList.add(cls));

        // 초기 상태를 보여줌
        navBarList.classList.add('show');
        putInBtn.classList.add('show');
    }

    if (isClosed) {
        Close();
    } else {
        Openup();
    }
});
