const form = document.getElementById('form');
const pswd = document.getElementById('pswd');
const passCheck = document.getElementById('passCheck');
const email = document.getElementById('email');
const mailspace = document.getElementById('email2');
const eselect = document.getElementById('emailSel');

const pswdRegexp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#^*_])[A-Za-z\d!@#^*_]{8,20}$/;

form.addEventListener('submit', function(e) {
	console.log(1);
	concatInput();
	if (!modifyCheck()) {
		e.preventDefault();
	}
});

function modifyCheck() {
	console.log(2);
	if (!pswd.value) {
		alert("비밀번호를 입력해주세요.");
		pswd.focus();
		return false;
	}
	console.log(1);
	if (pswd.value.length < 8 || pswd.value.length > 20) {
		alert("비밀번호는 8자에서 20자 사이여야 합니다.");
		pswd.focus();
		return false;
	}
	if (!pswdRegexp.test(pswd.value)) {
		alert("비밀번호는 숫자, 영문 대소문자, 특수문자 !, @, #, ^, *, _ 를 모두 포함한 조합이어야 합니다.");
		pswd.focus();
		return false;
	}console.log(1);
	if (pswd.value !== passCheck.value || !passCheck.value) {
		alert("비밀번호가 틀렸습니다.");
		passCheck.focus();
		return false;
	}console.log(1);
	if (!email.value) {
		alert("이메일을 입력해주세요.");
		email.focus();
		return false;
	}console.log(1);
	if (!mailspace.value) {
		alert("이메일 주소를 선택해주세요.");
		return false;
	}console.log(1);
	alert("회원정보가 수정되었습니다.");
	return true;
}

function mailText() {
	let va = eselect.value;
	mailspace.value = va;
	mailspace.textContent = mailspace.value;
}

function concatInput() {
	let emailF = document.getElementById("email").value;
	let emailB = document.getElementById("email2").value;
	let fullEmail = document.getElementById("fullEmail");

	fullEmail.value = emailF + "@" + emailB;

	let brief = document.getElementsByClassName("brief");
	let fullPhone = document.getElementById("fullPhone");

	fullPhone.value = brief[0].value + brief[1].value + brief[2].value;
	
	let str = birthdate.value;
	if (!str.includes("-")) {
        let formattedStr = str.slice(0, 4) + "-" + str.slice(4, 6) + "-" + str.slice(6, 8);
        birthdate.value = formattedStr;
	}
}
function goBack() {
    if (confirm("회원정보 수정을 취소하시겠습니까?")) {
        window.history.back();
    }
}