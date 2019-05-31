function showInterests() {
    var x = document.getElementById("items");
    if(x.style.display === "none")
        x.style.display = "block";
    else
        x.style.display = "none";   
}


/*********** Registration Validation ************/

function validateRegistration(){
	var username = document.reg_form.username.value;
	var name = document.reg_form.name.value;
	var surname = document.reg_form.surname.value;
	var email = document.reg_form.email.value;
	var date = document.reg_form.date.value;  
    var today = new Date();
    var datec = date;
    date = date.split("/");
    var dateRe = /^(((0[1-9]|[12][0-9]|3[01])([-./])(0[13578]|10|12)([-./])(\d{4}))|(([0][1-9]|[12][0-9]|30)([-./])(0[469]|11)([-./])(\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-./])(02)([-./])(\d{4}))|((29)(\.|-|\/)(02)([-./])([02468][048]00))|((29)([-./])(02)([-./])([13579][26]00))|((29)([-./])(02)([-./])([0-9][0-9][0][48]))|((29)([-./])(02)([-./])([0-9][0-9][2468][048]))|((29)([-./])(02)([-./])([0-9][0-9][13579][26])))$/;
    date = new Date(date[2], date[1] - 1, date[0]);
    var tel = document.reg_form.tel.value;
    var phoneRe = /^(\+)?([ 0-9]){10,16}$/;
	var digits = tel.replace(/\D/g, "");
	var address = document.reg_form.address.value;
	var password = document.reg_form.password.value;
	var confirm_password = document.reg_form.confirm_password.value;
	document.getElementById("error").style.color="red";
	if (username==null || username==""){  
		  document.getElementById("error").innerHTML="Lo username non può essere vuoto!";		  
		  return false;  
	}else if (name==null || name==""){  
		  document.getElementById("error").innerHTML="Il nome non può essere vuoto!";		     
		  return false;  
	}else if (surname==null || surname==""){  
		document.getElementById("error").innerHTML="Il Cognome non può essere vuoto!";  
		  return false;  
	}else if(date=="" || date==null || !dateRe.test(datec) || (today-date) < 0){
		document.getElementById("error").innerHTML="Inserire una data valida!";
    	return false;
    }else if (email==null || email==""){  
    	document.getElementById("error").innerHTML="L'Email non può essere vuoto!"; 
		  return false;  
	}else if (!phoneRe.test(digits)) {
		document.getElementById("error").innerHTML="Il numero di telefono deve essere del formato(+393123456789)!";
    	return false;
	}else if (address==null || address==""){  
		document.getElementById("error").innerHTML="L'Indirizzo non può essere vuoto!";  
		  return false;  
	}else if (password==null || password==""){  
		document.getElementById("error").innerHTML="La Password non può essere vuoto!";  
		  return false;  
	}else if (confirm_password!=password){  
		document.getElementById("error").innerHTML="Le Password devono coincidere!";  
		  return false;  
	}else 
		return (true);
}