function check_match(){
    let p = document.getElementById("pwmatch");
    let submit = document.getElementById("submit");

    if(document.getElementById("password_nueva").value == document.getElementById("password_confirm").value && document.getElementById("password_nueva").value!=""){
        p.textContent = "Correcto";
        p.setAttribute("style", "color:green;");
        submit.disabled = false;
    }else{
        p.textContent = "Incorrecto";
        p.setAttribute("style", "color:red;")
        submit.disabled = true;
    }
}