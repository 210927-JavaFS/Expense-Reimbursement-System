const URL = "http://localhost:8081/";

let loginButton = document.getElementById('loginButton');
let buttonRow = document.getElementById("buttonRow");
let registerButton = document.getElementById("registerButton");
let requestReimbButton = document.getElementById('requestReimbButton');
let showReimsButton = document.createElement("button");
let getUserButton = document.createElement("button");

loginButton.onclick = login; 
registerButton.onclick = addUser;
requestReimbButton.onclick = addReimb;
showReimsButton.onclick = showReimbs;
getUserButton.onclick = getUser;

showReimsButton.innerText = "View All Reimbursements";
showReimsButton.className ="btn btn-primary";

getUserButton.innerText = "Get User";

buttonRow.appendChild(showReimsButton);
buttonRow.appendChild(getUserButton);
document.getElementById("buttonRow").style.display = 'block';




async function getUser(username){
  let response = await fetch(URL+"ErsUser/"+username, {credentials:"include"});
  if(response.status === 200){
    let data = await response.json();
    return data;
  }
  else{
    console.log("Failed to get user.")
  }
}


async function login(){
  sessionStorage.clear();
    let user = {
      username:document.getElementById("username").value,
      password:document.getElementById("password").value
    }
    console.log(user);
    let response = await fetch(URL+"Login", {
        method:"POST",
        body:JSON.stringify(user),
        credentials:"include" 
      });
      console.log(response.status);
      if(response.status===200){
        let data = await getUser(user.username);
        sessionStorage.setItem("login",JSON.stringify(login));
        document.getElementsByClassName("login").innerHTML = '';

      }
      else{
        let para = document.createElement("p");
        para.setAttribute("style", "color:red")
        para.innerText = "LOGIN FAILED"
        document.getElementById("login").appendChild(para);
      }
    }

    async function showReimbs(){
      let response = await fetch(URL+"ErsReimbursement", {credentials:"include"});
      if(response.status===200){
        let data = await response.json();
        populateReimbTable(data);
        document.getElementById("reimbTable").style.display = 'block';
      }else{
        console.log("Reimbs not available.");
      }
    }



    function populateReimbTable(data){

      let tbody = document.getElementById("reimbBody");

      while(tbody.rows.length > 0) {
        tbody.deleteRow(0);
      }

      for(let reimb of data){
        let row = document.createElement("tr");
        for(let cell in reimb){
          let td = document.createElement("td");
          if (cell!="reimbAuthor" && cell!="reimbResolver" && cell!="reimbStatus" && cell!="reimbType") {
            td.innerText = reimb[cell];
          }
          if (cell=="reimbAuthor") td.innerText = `${reimb[cell].ersUsername}`;
          if (cell == "reimbResolver" && reimb[cell]!=null) td.innerText = `${reimb[cell].ersUsername}`;
          if (cell=="reimbStatusId") td.innerText = `${reimb[cell].status}`;
          if (cell=="reimbTypeId") td.innerText = `${reimb[cell].type}`;
          if(cell == "submittedDate") td.innerText = `${new Date(reimb[cell])}`;
          if(cell == "resolvedDate")
            if (reimb[cell]!=null) td.innerText = `${new Date(reimb[cell])}`;
    
          row.appendChild(td);
        }
        tbody.appendChild(row);
      }
    }
    
    function getNewReimb(){
      let newReimbAmount = document.getElementById("reimbAmount").value;
      let newReimbSubmitted = Date.now();
      let newReimbResolved = Date.now();
      let newReimbDescription = document.getElementById("reimbDescription").value;
      let newReimbAuthor = document.getElementById("reimbAuthor").value;
      let newReimbResolver = document.getElementById("reimbResolver").value;
      let newReimbStatusId = document.getElementById("reimbStatusId").value;
      let newReimbTypeId = document.getElementById("reimbTypeId").value;
      
      newReimbStatusId = {
        reimbStatusId:5,
        status:newReimbStatusId
      }
      newReimbTypeId = {
        reimbTypeId:5,
        type:newReimbTypeId
      }

      let reimb =  {
        reimbAmount:parseFloat(newReimbAmount),
        reimbSubmitted:newReimbSubmitted,
        reimbResolved:newReimbResolved,
        reimbDescription:newReimbDescription,
        reimbAuthor:getUser(newReimbAuthor),
        reimbResolver:getUser(newReimbResolver),
        reimbStatusId:newReimbStatusId,
        reimbTypeId:newReimbTypeId
    
      }
      console.log(reimb);
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