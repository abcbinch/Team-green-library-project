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

function updateLocalStorage(isClosed) {
    let navArrayed = Array.from(navBarList.classList);
    let btnArrayed = Array.from(putInBtn.classList);
    localStorage.setItem("navClass", JSON.stringify(navArrayed));
    localStorage.setItem("btnClass", JSON.stringify(btnArrayed));
    localStorage.setItem("isClosed", isClosed);
}

putInBtn.onclick = () => {
    if (!navBarList.classList.contains('putinLeft')) {
        Openup();
    } else {
        Close();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    let storedNavClass = localStorage.getItem("navClass");
    let storedBtnClass = localStorage.getItem("btnClass");
    let isClosed = localStorage.getItem("isClosed") === 'true';

    if (storedNavClass && storedBtnClass) {
        let navArrayed = JSON.parse(storedNavClass);
        let btnArrayed = JSON.parse(storedBtnClass);

        navArrayed.forEach(cls => navBarList.classList.add(cls));

        btnArrayed.forEach(cls => putInBtn.classList.add(cls));

        navBarList.classList.add('show');
        putInBtn.classList.add('show');
    }

    if (isClosed) {
        Close();
    } else {
        Openup();
    }
});
