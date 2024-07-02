

let navRouteli = document.querySelectorAll('.navList li');
navRouteli = Array.from(navRouteli);

let imHere = window.location.href.split("/");

//도서관 이용. 바로가기 리스트
navRouteli[0].onclick = () => window.location.href = "/placeUsetime";
navRouteli[1].onclick = () => window.location.href = "/dataRule";
navRouteli[2].onclick = () => window.location.href = "/user/hopeBookApply";

if(imHere[3]=="placeUsetime"){
	navRouteli[0].classList.add("curPlace");
}
if(imHere[3]=="dataRule"){
	navRouteli[1].classList.add("curPlace");
}
if(imHere[4]=="hopeBookApply"){
	navRouteli[2].classList.add("curPlace");
}


