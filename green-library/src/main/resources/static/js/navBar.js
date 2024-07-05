
let putInBtn = document.querySelector('.putinBtn');
let navBarList = document.querySelector('.navBar');
let navArrow = document.querySelector('.putinBtn img');

let rightArrow = "/images/circleArrowGreenRight.png";
let leftArrow = "/images/circleArrowGreenLeft.png";

let urlPast = document.referrer.split("/");//4번째를 가져다 쓴다. [3]
let urlPresent = window.location.href.split("/");

let navArrayed = Array.from(navBarList.classList);
let btnArrayed = Array.from(putInBtn.classList);

localStorage.setItem("navClass", JSON.stringify(navArrayed));
localStorage.setItem("btnClass", JSON.stringify(btnArrayed));

let navClass = JSON.parse(localStorage.getItem("navClass"));
let btnClass = JSON.parse(localStorage.getItem("btnClass"));

//로컬 스토리지에 저장하고 꺼내오는 것 자체는 어디서든 가능한 


//새로 만든 함수

//버튼 여는 함수
function Openup(){
	navBarList.classList.add('putinLeft');
	putInBtn.classList.add('btnInLeft');
	navArrow.src = rightArrow;
	
	let navArrayed = Array.from(navBarList.classList);//로컬스토리지가 한 번 들어가고 마는 게 아니라 계속 업데이트 되어서
	let btnArrayed = Array.from(putInBtn.classList);//putinLeft가 다음 페이지에서 덮어쓰고 없어져버린다.

	localStorage.setItem("navClass", JSON.stringify(navArrayed));
	localStorage.setItem("btnClass", JSON.stringify(btnArrayed));
	
	console.log(localStorage);
}
//버튼 닫는 함수
function Close(){
	navBarList.classList.remove('putinLeft');
	navBarList.style.transition = "left 300ms ease-in-out";
	putInBtn.classList.remove('btnInLeft');
	putInBtn.style.transition = "left 300ms ease-in-out";
	navArrow.src = leftArrow;
	
	console.log(localStorage);
}

	

//온클릭으로 함수 넣음
putInBtn.onclick = () => {
	if(!navBarList.classList.contains('putinLeft')){

Openup();
	}else if(navBarList.classList.contains('putinLeft')){

Close();
	}
}
//챗봇의 도움. 페이지가 로드되었을 때 클래스를 적용하는 함수. 
document.addEventListener("DOMContentLoaded", () => {
	if (storedNavClass && storedBtnClass) {
	        let navArrayed = JSON.parse(storedNavClass);
	        let btnArrayed = JSON.parse(storedBtnClass);

	        // navBarList에 클래스 적용
	        navArrayed.forEach(cls => navBarList.classList.add(cls));

	        // putInBtn에 클래스 적용
	        btnArrayed.forEach(cls => putInBtn.classList.add(cls));

	        // 초기화 후 로컬 스토리지에서 클래스 정보 제거 (선택적)
	        localStorage.removeItem("navClass");
	        localStorage.removeItem("btnClass");
			}
});



//putInBtn.addEventListener('click', function() {
//    navBarList.classList.toggle('putinLeft');
//	putInBtn.classList.toggle('btnInLeft');
//
//});
//
//var isEffectOn = navBarList.classList.contains('putinLeft');
//	var isEffectOn2 = putInBtn.classList.contains('btnInLeft')
//    localStorage.setItem('effectState', isEffectOn ? 'on' : 'off');
//	localStorage.setItem('effectState2', isEffectOn2 ? 'on2' : 'off2');
//document.addEventListener('DOMContentLoaded', function() {
//    var effectState = localStorage.getItem('effectState');
//	var effectState2 = localStorage.getItem('effectState2');
//    
//    if (effectState === 'on'&&effectState2 === 'on') {
//        navBarList.classList.add('putinLeft');
//		putInBtn.classList.add('btnInLeft');
//    } else {
//        navBarList.classList.remove('putinLeft');
//		putInBtn.classList.remove('btnInLeft');
//    }
//});

 
//버튼 클릭 시 열고 닫힌다. 기존의 열고 닫는 함수
//putInBtn.onclick = () => {
//	if(!navBarList.classList.contains('putinLeft')){
//		navBarList.classList.add('putinLeft');
//		putInBtn.classList.add('btnInLeft');
//		navArrow.src = rightArrow;
//		
//	}else if(navBarList.classList.contains('putinLeft')){
//		navBarList.classList.remove('putinLeft');
//		navBarList.style.transition = "left 300ms ease-in-out";
//		putInBtn.classList.remove('btnInLeft');
//		putInBtn.style.transition = "left 300ms ease-in-out";
//		navArrow.src = leftArrow;
//	}
//}


	
				
//addLoadEvent(stillClose);

		

