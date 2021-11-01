const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');

loginButton.addEventListener("click", login);

async function loginToApp(){
    let user = {
      username:document.getElementById("username").value,
      password:document.getElementById("password").value
    }
    let response = await fetch(URL+"login", {
        method:"POST",
        body:JSON.stringify(user),
        credentials:"include" 
      });

      if(response.status===200){
        document.getElementsByClassName("formClass")[0].innerHTML = '';

      }
      else{
        let para = document.createElement("p");
        para.setAttribute("style", "color:red")
        para.innerText = "LOGIN FAILED"
        document.getElementsByClassName("formClass")[0].appendChild(para);
      }
    }