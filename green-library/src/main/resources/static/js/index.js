

//슬라이드 함수
const newbooks = document.querySelector('.newbook');
const popbooks = document.querySelector('.popbook');

const window1 = document.querySelector('.window');
const window2 = document.querySelector('.window2');

const backBtn = document.querySelector('.backBtn');
const goBtn = document.querySelector('.goBtn');
const backBtn2 = document.querySelector('.backBtn2');
const goBtn2 = document.querySelector('.goBtn2');

//탭 함수
const tab = document.querySelectorAll('.bookTab li');
const swipe = document.querySelector('.bookSwipe');
const swipe2 = document.querySelector('.bookSwipe2');

const bookQuick = document.querySelector('.tabCon2 img');







//슬라이드 함수

let curIdx = 0;

goBtn.onclick = () => {
	if(curIdx==0){
		curIdx++;
		newbooks.style.left = -1260 + "px";
		newbooks.style.transition = "left 300ms ease-in-out";
		popbooks.style.left = -1260 + "px";
				popbooks.style.transition = "left 300ms ease-in-out";
	}else if(curIdx==1){
		curIdx++;
		newbooks.style.left = -2520 + "px";
		newbooks.style.transition = "left 300ms ease-in-out";
		popbooks.style.left = -2520 + "px";
				popbooks.style.transition = "left 300ms ease-in-out";
	}
}
goBtn2.onclick = () => {
	if(curIdx==0){
		curIdx++;
		popbooks.style.left = -1260 + "px";
		popbooks.style.transition = "left 300ms ease-in-out";
	}else if(curIdx==1){
		curIdx++;
		popbooks.style.left = -2520 + "px";
		popbooks.style.transition = "left 300ms ease-in-out";
	}
}
backBtn.onclick = () => {
	if(curIdx==1){
		newbooks.style.left = 0 + "px";
		newbooks.style.transition = "left 300ms ease-in-out";
		popbooks.style.left = 0 + "px";
		popbooks.style.transition = "left 300ms ease-in-out";
		curIdx--;
	}else if(curIdx==2){
		newbooks.style.left = -1260 + "px";
		newbooks.style.transition = "left 300ms ease-in-out";
		popbooks.style.left = -1260 + "px";
		popbooks.style.transition = "left 300ms ease-in-out";
		curIdx--;
	}
}
backBtn2.onclick = () => {
	if(curIdx==1){
		popbooks.style.left = 0 + "px";
		popbooks.style.transition = "left 300ms ease-in-out";
		curIdx--;
	}else if(curIdx==2){
		popbooks.style.left = -1260 + "px";
		popbooks.style.transition = "left 300ms ease-in-out";
		curIdx--;
	}
}


//맨 처음 슬라이드 함수
//function slide(){
//	
//	let curIdx = 0;
//	let newWid = 1260;
//
//
//		if(curIdx===0){
//			goBtn.onclick = () => {
//				newbooks.style.left = -newWid + 'px';
//				newbooks.style.transition = '200ms ease-in-out';
//               
//                curIdx+=1;
//                
//			}
//			backBtn.onclick = () => {
//				newbooks.style.left = 0 + 'px';
//			}
//		}
//	}
//function slide2(){
//	
//	let curIdx = 0;
//	let newWid = 1260;
//
//
//		if(curIdx===0){
//			goBtn2.onclick = () => {
//				popbooks.style.left = -newWid + 'px';
//				popbooks.style.transition = '200ms ease-in-out';
//               
//                curIdx+=1;
//                
//			}
//			backBtn2.onclick = () => {
//				popbooks.style.left = 0 + 'px';
//			}
//		}
//	}
//
//
//addLoadEvent(slide);
//addLoadEvent(slide2);




// 여기서부터는 탭 함수
function booktab(){

	if(tab[0].classList.contains("here")){
				bookQuick.onclick = () => {
					window.location.href='/initiativeBook';
				}
			}
	tab[0].onclick = () => {
		tab[0].classList.add("here");
		tab[1].classList.remove("here");
		swipe.style.display = "flex";
		swipe2.style.display = "none";
		if(tab[0].classList.contains("here")){
				bookQuick.onclick = () => {
					window.location.href='/initiativeBook';
				}
			}
	}
	tab[1].onclick = () => {
		tab[0].classList.remove("here");
		tab[1].classList.add("here");
		swipe.style.display = "none";
		swipe2.style.display = "flex";
		if(tab[1].classList.contains("here")){
				bookQuick.onclick = () => {
									window.location.href='/popularBook';
								}
			}
	}
}


addLoadEvent(booktab);




