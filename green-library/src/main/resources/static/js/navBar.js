
let putInBtn = document.querySelector('.putinBtn');
let navBarList = document.querySelector('.navBar');
let navArrow = document.querySelector('.putinBtn img');

let rightArrow = "/images/circleArrowGreenRight.png";
let leftArrow = "/images/circleArrowGreenLeft.png";

putInBtn.onclick = () => {
	if(!navBarList.classList.contains('putinLeft')){
		navBarList.classList.add('putinLeft');
		putInBtn.classList.add('btnInLeft');
		navArrow.src = rightArrow;
	}else if(navBarList.classList.contains('putinLeft')){
		navBarList.classList.remove('putinLeft');
		navBarList.style.transition = "left 300ms ease-in-out";
		putInBtn.classList.remove('btnInLeft');
		putInBtn.style.transition = "left 300ms ease-in-out";
		navArrow.src = leftArrow;
	}
}



/**
 * 
 */