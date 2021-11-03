const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let buttonRow = document.getElementById("buttonRow");
let registerButton = document.getElementById("registerButton");
let requestReimbButton = document.getElementById('requestReimbButton');

loginButton.onclick = login; 
registerButton.onclick = addUser;
requestReimbButton.onclick = addReimb;





async function login(){
    let user = {
      username:document.getElementById("username").value,
      password:document.getElementById("password").value
    }
    let response = await fetch(URL+"Login", {
        method:"POST",
        body:JSON.stringify(user),
        credentials:"include" 
      });

      if(response.status===200){
        document.getElementsByClassName("formClass")[0].innerHTML = '';
        buttonRow.appendChild(userButton);
        buttonRow.appendChild(reimbButton);

      }
      else{
        let para = document.createElement("p");
        para.setAttribute("style", "color:red")
        para.innerText = "LOGIN FAILED"
        document.getElementsByClassName("formClass")[0].appendChild(para);
      }
    }

    
    
    function getNewReimb(){
      let newReimbAmount = document.getElementById("reimbAmount").value;
      let newReimbSubmitted = Date.now();
      let newReimbResolved = Date.now();
      let newReimbDescription = document.getElementById("reimDescription").value;
      let newReimbAuthor = document.getElementById("reimbAuthor").value;
      let newReimbResolver = document.getElementById("reimbResolver").value;
      let newReimbStatus = document.getElementById("reimbStatus").value;
      let newReimbType = document.getElementById("reimbType").value;
      
    
      let reimb =  {
        reimbAmount:newReimbAmount,
        reimbSubmitted:newReimbSubmitted,
        reimbResolved:newReimbResolved,
        reimbDescription:newReimbDescription,
        reimbAuthor:newReimbAuthor,
        reimbResolver:newReimbResolver,
        reimbStatusID:newReimbStatus,
        reimbTypeID:newReimbType
    
      }
    
      return reimb;
    }
    
    async function addReimb(){
      let reimb = getNewReimb();
    
      let response = await fetch(URL+"ErsReimbursement", {
        method:'POST',
        body:JSON.stringify(reimb),
        credentials:"include"
      });
    
      if(response.status===201){
        console.log("Reimb created successfully.");
      }else{
        console.log("Something went wrong creating your Reimb.")
      }
    
    }