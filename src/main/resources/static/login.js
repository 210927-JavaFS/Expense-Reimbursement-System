const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let buttonRow = document.getElementById("buttonRow");
let registerButton = document.getElementById("registerButton");
let requestReimbButton = document.getElementById('requestReimbButton');

loginButton.onclick = login; 
registerButton.onclick = addUser;
requestReimbButton.onclick = addReimb;




userButton.innerText = "See Users";
reimbButton.innerText = "See Reimbursements";



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

    async function getUsers(){
      let response = await fetch(URL+"ErsUser", {credentials:"include"});
    
      if(response.status === 200){
        let data = await response.json();
        populateUsersTable(data);
      }else{
        console.log("The Users cannot be accessed");
      }
    }
    
    function populateUsersTable(data){
      let tbody = document.getElementById("userBody");
    
      tbody.innerHTML="";
    
      for(let user of data){
        let row = document.createElement("tr");
    
        for(let cell in user){
          let td = document.createElement("td");
          td.innerText=user[cell];
          row.appendChild(td);
        }
        tbody.appendChild(row);
      }
    }
    
    async function getReimbs(){
      let response = await fetch(URL+"ErsReimbursement", {credentials:"include"});
      if(response.status===200){
        let data = await response.json();
        populateReimbTable(data);
      }else{
        console.log("Reimbs not available.");
      }
    }
    
    function populateReimbTable(data){
      let tbody = document.getElementById("reimbBody");
    
      tbody.innerHTML="";
    
      for(let reimb of data){
        let row = document.createElement("tr");
        for(let cell in reimb){
          let td = document.createElement("td");
          td.innerText = reimb[cell];
          row.appendChild(td);
        }
        tbody.appendChild(row);
      }
    }
    
    function getNewReimb(){
      let newReimbAmount = document.getElementById("reimbAmount").value;
      let newReimbSubmitted = document.getElementById("reimbSubmitted").value; 
      let newReimbResolved = Date.now();
      let newReimbDescription = Date.now();
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