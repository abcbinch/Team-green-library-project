
let putInBtn = document.querySelector('.putinBtn');
let navBarList = document.querySelector('.navBar');

putInBtn.onclick = () => {
	if(!navBarList.classList.contains('putinLeft')){
		navBarList.classList.add('putinLeft');
		putInBtn.classList.add('btnInLeft');
	}else if(navBarList.classList.contains('putinLeft')){
		navBarList.classList.remove('putinLeft');
		navBarList.style.transition = "left 300ms ease-in-out";
		putInBtn.classList.remove('btnInLeft');
		putInBtn.style.transition = "left 300ms ease-in-out";
	}
}



/**
 * 
 */